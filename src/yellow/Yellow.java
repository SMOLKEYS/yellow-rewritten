package yellow;

import arc.*;
import arc.files.*;
import arc.util.*;
import mindustry.*;
import mindustry.game.EventType.*;
import mindustry.mod.*;
import yellow.content.*;

public class Yellow extends Mod{

    static boolean debug = false, crashed = false;

    public Yellow(){
        if(flameSpecial()){
            Log.info("yellow load canceled");
            return;
        }

        if(launchFile().exists() && Core.settings.getBool("yellow-enable-failsafe", true)){
            crashed = true;
            launchFile().delete();
            Events.on(ClientLoadEvent.class, e -> Vars.ui.showInfo("@yellow.initfailed"));
            return;
        }

        launchFile().writeString("go away");
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
    public void loadContent(){
        if(crashed || flameSpecial()) return;
        YellowWeapons.load();
        YellowSpells.load();
        YellowUnitTypes.load();
    }
}
