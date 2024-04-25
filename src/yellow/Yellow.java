package yellow;

import arc.*;
import arc.math.*;
import arc.files.*;
import arc.util.*;
import mindustry.*;
import mindustry.mod.*;
import mindustry.game.EventType.*;
import yellow.content.*;
import yellow.game.*;
import yellow.util.*;

public class Yellow extends Mod{

    boolean debug = false, crashed = false;

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

        return !(f <= 0 || f >= 6);
    }

    @Override
    public void loadContent(){
        if(crashed || flameSpecial()) return;
        YellowWeapons.load();
        YellowUnitTypes.load();
    }
}
