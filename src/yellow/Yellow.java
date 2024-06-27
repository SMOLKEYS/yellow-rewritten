package yellow;

import arc.*;
import arc.files.*;
import arc.struct.*;
import arc.util.*;
import mindustry.*;
import mindustry.game.EventType.*;
import mindustry.mod.*;
import yellow.content.*;
import yellow.mods.*;

import static yellow.game.YellowEventType.*;
import static yellow.mods.ExtensionCore.*;

public class Yellow extends Mod{

    static boolean debug = false, crashed = false;

    static int foundExtensions, loadedExtensions, erroredExtensions;
    static Seq<ErroneousExtension> erroredExtensionList = new Seq<>();

    public Yellow(){
        Events.fire(new YellowPreInitializationEvent());

        if(launchFile().exists() && Core.settings.getBool("yellow-enable-failsafe", true)){
            crashed = true;
            launchFile().delete();
            Events.on(ClientLoadEvent.class, e -> Vars.ui.showInfo("@yellow.initfailed"));
            return;
        }

        if(!Vars.clientLoaded) launchFile().writeString("go away");

        YellowVars.extensionDir.mkdirs();
        YellowVars.extensionDir.walk(f -> {
            foundExtensions++;

            try{
                ExtensionCore.load(f);
                loadedExtensions++;
            }catch(Exception e){
                Log.err(e);
                erroredExtensionList.add(new ErroneousExtension(f, e));
                erroredExtensions++;
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

    @Override
    public void init(){
        extensions.each(s -> {
            if(s.meta.enabled()) s.main.init();
        });
    }

    @Override
    public void loadContent(){
        if(crashed) return;
        YellowWeapons.load();
        YellowSpells.load();
        YellowCharacters.load();
        YellowUnitTypes.load();
        YellowWeapons.afterLoad();
        extensions.each(s -> {
            try{
                if(s.meta.enabled()) s.main.loadContent();
            }catch(Exception e){
                throw new RuntimeException("Extension " + s.name + " contains content that failed to load.", e);
            }
        });
        Events.fire(new YellowContentInitEvent());
    }
}
