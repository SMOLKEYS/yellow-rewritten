package yellow;

import arc.*;
import arc.util.*;
import mindustry.mod.*;
import yellow.util.*;

import java.util.*;

public class Autoupdater{

    public static void checkForUpdates(){
        if(!Core.settings.getBool("yellow-check-for-updates", true)) return;

        Mods.ModMeta meta = Yellow.meta();

        YellowNetworking.repoReleases(YellowVars.getUpdateServer(), root -> {
            String[] versions = new String[root.size];
            //insert version strings from right to left, with the oldest release as the first entry and the newest as the last
            for(int i = 0; i < root.size; i++) versions[root.size - 1 - i] = root.get(i).getString("name", "0S").split(" ")[0];

            String stable = null, beta = null, releaseCandidate = null;

            for(var s: versions){
                int d = 0;

                for(int i = 0; i < s.length(); i++){
                    char ch = s.charAt(i);
                    //support floating point
                    if(Character.isDigit(i) || ch == '.') d++;
                }

                switch(s.substring(d)){
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
            if(inp != null){
                int d = 0;
                int d2 = 0;

                for(int i = 0; i < inp.length(); i++){
                    char ch = inp.charAt(i);
                    //support floating point
                    if(Character.isDigit(i) || ch == '.') d++;
                }

                String metav = meta.version;

                for(int i = 0; i < metav.length(); i++){
                    char ch = metav.charAt(i);
                    //support floating point
                    if(Character.isDigit(i) || ch == '.') d2++;
                }


                if(Strings.parseFloat(metav.substring(d2, d2 + 1)) < Strings.parseFloat(inp.substring(d, d + 1))) YellowVars.ui.notifrag.showPersistentNotification(Core.bundle.format("yellow.newver", meta.version, inp));
            }
        }
    }
}
