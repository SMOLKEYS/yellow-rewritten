package yellow;

import arc.*;
import arc.struct.*;
import arc.util.*;
import mindustry.mod.*;
import yellow.util.*;

public class Autoupdater{

    static Seq<StableBundle> bundles = new Seq<>();
    static boolean hasStable = false;

    public static void checkForUpdates(boolean manual){
        if(!Core.settings.getBool("yellow-check-for-updates", true) && !manual) return;

        Mods.ModMeta meta = Yellow.meta();

        if(!meta.version.endsWith("S") && !meta.version.endsWith("B")){
            YellowVars.ui.notifrag.showNotification("@yellow.ignorever");
            return;
        }

        YellowNetworking.repoReleases(YellowVars.getUpdateServer(), root -> {
            if(root == null){
            	Log.warn("Autoupdater errored out during checking; cancelling.");
            	return;
            }
            String[] versions = new String[root.size];
            //insert version strings from right to left, with the oldest release as the first entry and the newest as the last
            for(int i = 0; i < root.size; i++) versions[root.size - 1 - i] = root.get(i).getString("name", "0S").split(" ")[0];

            StableBundle sb = new StableBundle();

            for(int i = 0; i < versions.length; i++){
                String cv = versions[i];

                if(cv.endsWith("S") && sb.st == null){
                    sb.st = cv;
                }else if(sb.st != null && cv.endsWith("S")){
                    sb.from = Stringf.handleNumber(versions[Structs.indexOf(versions, sb.st) + 1]);
                    sb.until = Stringf.handleNumber(versions[i - 1]);
                    bundles.add(sb);
                    sb = new StableBundle();
                    sb.st = cv;
                    if(Stringf.handleNumber(sb.st) == 1){
                        sb.from = Stringf.handleNumber(versions[Structs.indexOf(versions, sb.st) + 1]);
                        sb.until = Stringf.handleNumber(versions[versions.length - 1]);
                        bundles.add(sb);
                    }
                }
            }

            process(meta, versions);
        }, e -> Core.app.post(() -> {
            Log.err(e);
            YellowVars.ui.notifrag.showErrorNotification("@yellow.failver", e);
        }));
    }

    private static void process(Mods.ModMeta meta, String[] input){
        String curVer = meta.version;

        if(!curVer.endsWith("S") && !curVer.endsWith("B")){
            YellowVars.ui.notifrag.showPersistentNotification("@yellow.ignorever");
            return;
        }

        String[] stables = Structs.filter(String.class, input, s -> s.endsWith("S"));
        String[] betas = Structs.filter(String.class, input, s -> s.endsWith("B"));

        bundles.each(e -> {
            String ss = null;
            if(!hasStable){

                try{
                    ss = betas[Structs.indexOf(betas, curVer) + 1];
                    Log.info("cur @ new @", curVer, ss);
                }catch(Exception ex){
                    //ignore
                }

            }else{

                try{
                    ss = stables[Structs.indexOf(stables, curVer) + 1];
                    Log.info("stable @ new @", curVer, ss);
                }catch(Exception ex){
                    //ignore, again
                }

            }

            if(ss != null) YellowVars.ui.notifrag.showPersistentNotification(Core.bundle.format("yellow.newver", curVer, ss));
        });
    }

    static class StableBundle{
        public String st;
        public float from, until;

        public StableBundle(String st, float from, float until){
            this.st = st;
            this.from = from;
            this.until = until;
        }

        public StableBundle(){

        }

        public boolean inRange(float input){
            return input <= from && input >= until;
        }
    }
}
