package yellow;

import arc.*;
import arc.scene.style.*;
import arc.util.*;
import java.util.*;
import mindustry.*;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.mod.*;
import yellow.util.*;

//TODO absolutely broken, needs multiple revisions
public class Autoupdater{

    static boolean checked = false;

    public static void checkForUpdates(boolean manual){
        if(!Core.settings.getBool("yellow-check-for-updates", true) && !manual) return;

        Mods.ModMeta meta = Yellow.meta();
        checked = false;

        if(!meta.version.endsWith("S") && !meta.version.endsWith("B")){
            YellowVars.ui.notifrag.showNotification("@yellow.ignorever");
            return;
        }

        YellowNetworking.repoReleases(YellowVars.getUpdateServer(), root -> {
            String[] versions = new String[root.size];
            //tag_name cannot possibly be blank/null, as it is literally not allowed, but sometimes the edge cases matter
            for(int i = 0; i < root.size; i++) versions[i] = root.get(i).getString("tag_name", "0N");

            Structsy.eachIndexed((s, i) -> {
                if(s == null){
                    Log.warn("Autoupdater version collection found a null entry! Correcting...");
                    versions[i] = "0N";
                }
            }, versions);

            String lastEntry = versions[versions.length - 1];
            int distance = Structsy.distance(versions, meta.version, lastEntry);
            int currentVerIndex = Structs.indexOf(versions, meta.version);

            if(!Structs.contains(versions, meta.version)){
                checked = true;
                YellowVars.ui.notifrag.showTintedNotification(((TextureRegionDrawable)Tex.whiteui).tint(Pal.gray.cpy().a(0.5f)), Icon.cancel, "@yellow.unknownver", 70, true, () -> {});
            }else if(!Objects.equals(versions[currentVerIndex], lastEntry)){
                checked = true;
                YellowVars.ui.notifrag.showPersistentNotification(Core.bundle.format("yellow.newver", distance), () -> {
                    String[][] availableVersions = new String[distance][1];
                    for(int i = 0; i < distance; i++){
                        availableVersions[i][0] = versions[currentVerIndex + i];
                    }

                    Vars.ui.showMenu(
                            "@yellow.newver-update",
                            "@yellow.newver-dialog",
                            availableVersions,
                            r -> {
                                if(r == -1) return;

                                String ver = availableVersions[r][0];

                                Http.get(root.get(Structs.indexOf(versions, ver)).get("assets").get(0).getString("browser_download_url"), res -> {
                                    Core.settings.getDataDirectory().child("yellow-" + ver + ".jar").writeBytes(res.getResult());
                                });
                            });
                });
            }
        }, e -> Core.app.post(() -> {
            Log.err(e);
            YellowVars.ui.notifrag.showErrorNotification("@yellow.failver", e);
        }));
    }
}
