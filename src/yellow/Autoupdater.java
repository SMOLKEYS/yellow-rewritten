package yellow;

import arc.*;
import arc.util.*;
import arc.util.serialization.*;
import mindustry.*;
import mindustry.mod.*;
import yellow.util.*;

import java.util.*;

public class Autoupdater{

    public static void load(){
        if(!Core.settings.getBool("yellow-check-for-updates", true)) return;

        Mods.ModMeta meta = Yellow.meta();
        ReleaseType type = meta.version.endsWith("S") ? ReleaseType.stable : ReleaseType.beta;

        YellowNetworking.repoReleases(YellowVars.getUpdateServer(), root -> {
            String[] versions = new String[root.size];
            for(int i = 0; i < root.size; i++) versions[i] = root.get(i).getString("name", "0S").split(" ")[0];

            String stable = null, beta = null;

            for(var s: versions){
                if(s.endsWith("S") && stable == null){
                    stable = s;
                }else if(s.endsWith("B") && beta == null){
                    beta = s;
                }
            }

            if(type == ReleaseType.stable && stable != null){
                if(!Objects.equals(meta.version, stable)){
                    YellowVars.ui.notifrag.showNotification(Core.bundle.format("yellow.newver", meta.version, stable));
                }
            }else if(beta != null){
                if(!Objects.equals(meta.version, beta)){
                    YellowVars.ui.notifrag.showNotification(Core.bundle.format("yellow.newver", meta.version, beta));
                }
            }
        });
    }

    enum ReleaseType{
        stable, beta
    }
}
