package yellow;

import arc.*;
import arc.util.*;
import mindustry.mod.*;
import yellow.util.*;

public class Autoupdater{

    public static void checkForUpdates(){
        if(!Core.settings.getBool("yellow-check-for-updates", true)) return;

        Mods.ModMeta meta = Yellow.meta();

        YellowNetworking.repoReleases(YellowVars.getUpdateServer(), root -> {
            String[] versions = new String[root.size];
            for(int i = 0; i < root.size; i++) versions[i] = root.get(i).getString("name", "0S").split(" ")[0];

            String stable = null, beta = null, releaseCandidate = null;

            for(var s: versions){
                switch(s.substring(1)){
                    case "S" -> {
                        stable = s;
                    }
                    case "B" -> {
                        beta = s;
                    }
                    case "RC" -> {
                        releaseCandidate = s;
                    }
                }
            }

            process(meta, new String[]{stable, beta, releaseCandidate});
        });
    }

    private static void process(Mods.ModMeta meta, String[] input){
        for(String inp : input){
            if(inp != null && Strings.parseInt(meta.version.substring(0, 1)) < Strings.parseInt(inp.substring(0, 1))){
                YellowVars.ui.notifrag.showNotification(Core.bundle.format("yellow.newver", meta.version, inp));
            }
        }
    }
}
