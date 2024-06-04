package yellow;

import arc.*;
import arc.files.*;
import arc.struct.*;
import arc.util.*;
import mindustry.*;
import mindustry.game.EventType.*;
import mindustry.mod.*;
import yellow.content.*;
import yellow.game.*;
import yellow.mods.*;

import static yellow.game.YellowEventType.*;
import static yellow.mods.ExtensionCore.*;

public class Yellow extends Mod{

    static boolean debug = false, crashed = false;

    static int foundExtensions, loadedExtensions, erroredExtensions;
    static Seq<ErroneousExtension> erroredExtensionList = new Seq<>();

    public Yellow(){
        if(flameSpecial()){
            Log.info("yellow load canceled");
            return;
        }
        Events.fire(new YellowPreInitializationEvent());

        if(launchFile().exists() && Core.settings.getBool("yellow-enable-failsafe", true)){
            crashed = true;
            launchFile().delete();
            Events.on(ClientLoadEvent.class, e -> Vars.ui.showInfo("@yellow.initfailed"));
            return;
        }

        launchFile().writeString("go away");

        Fi extdir = configDir().child("extensions");
        extdir.mkdirs();

        extdir.walk(f -> {
            foundExtensions++;
            try{
                ExtensionCore.load(f);
                loadedExtensions++;
            }catch(Exception e){
                Log.err(e);
                erroredExtensionList.add(new ErroneousExtension(e, f));
                erroredExtensions++;
            }
        });

        extensions.each(ext -> {
            if(ext.meta.enabled()){
                try{
                    ext.main = (YellowExtension) ext.loader.loadClass(ext.meta.main).getConstructor().newInstance();
                }catch(Exception e){
                    Log.err(e);
                }
            }
        });

        YellowVars.init();
    }

    public static Mods.ModMeta meta(){
        return Vars.mods.getMod("yellow").meta;
    }

    public static Fi launchFile(){
        return configDir().child("yellow_launchid.dat");
    }

    public static Fi configDir(){
        Fi f = Core.settings.getDataDirectory().child("smol_common").child("yellow");
        f.mkdirs();
        return f;
    }

    public static boolean flameSpecial(){
        int f = Core.settings.getInt("flame-special", 0);

        return !(f <= 0 || f >= 6) && Core.settings.getBool("mod-flameout-enabled");
    }

    @Override
    public void init(){
        extensions.each(s -> {
            if(s.meta.enabled()) s.main.init();
        });
    }

    @Override
    public void loadContent(){
        if(crashed || flameSpecial()) return;
        YellowWeapons.load();
        YellowSpells.load();
        YellowUnitTypes.load();
        extensions.each(s -> {
            if(s.meta.enabled()) s.main.loadContent();
        });
        Events.fire(new YellowContentInitEvent());
    }
}
