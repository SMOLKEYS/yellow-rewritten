package yellow;

import arc.*;
import arc.files.*;
import arc.scene.style.*;
import arc.util.*;
import arc.util.serialization.*;
import java.util.*;
import mindustry.*;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.mod.*;
import yellow.util.*;

public class Autoupdater{

    public static void checkForUpdates(boolean manual){
        if(!Core.settings.getBool("yellow-check-for-updates", true) && !manual) return;

        Mods.ModMeta meta = Yellow.meta();

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

            String lastEntry = versions[0];
            int distance = Structsy.distance(versions, meta.version, lastEntry);
            int currentVerIndex = Structs.indexOf(versions, meta.version);

            if(!Structs.contains(versions, meta.version)){
                YellowVars.ui.notifrag.showTintedNotification(((TextureRegionDrawable)Tex.whiteui).tint(Pal.gray.cpy().a(0.5f)), Icon.cancel, "@yellow.unknownver", 70, true, () -> {});
            }else if(!Objects.equals(meta.version, lastEntry)){
                YellowVars.ui.notifrag.showPersistentNotification(Core.bundle.format("yellow.newver", distance), () -> {
                    String[][] availableVersions = new String[distance][1];
                    for(int i = 0; i < distance; i++){
                        availableVersions[i][0] = versions[i];
                    }

                    Vars.ui.showMenu(
                            "@yellow.newver-update",
                            "@yellow.newver-dialog",
                            availableVersions,
                            r -> {
                                if(r == -1) return;

                                String ver = availableVersions[r][0];

                                JsonValue mainAsset = root.get(Structs.indexOf(versions, ver)).get("assets");
                                String[][] assets = new String[mainAsset.size][1];

                                for(int i = 0; i < mainAsset.size; i++) assets[i][0] = mainAsset.get(i).getString("name", "bruh???").replace("yellow-rewritten.jar", "[accent]yellow-rewritten.jar[]");

                                Vars.ui.showMenu(
                                        "@yellow.newver-update",
                                        Core.bundle.format("yellow.newver-select", ver),
                                        assets,
                                        r2 -> {
                                            if(r2 == -1) return;

                                            Vars.ui.loadfrag.show("[accent]Downloading...[]");

                                            JsonValue mval = mainAsset.get(Structsy.indexOf(assets, Strings.stripColors(assets[r2][0])));

                                            Http.get(mval.getString("browser_download_url"), res -> {
                                                byte[] out = res.getResult();
                                                Vars.ui.loadfrag.setProgress(out.length / mval.getFloat("size"));

                                                Fi tg = Yellow.configDir().child("jars").child(Strings.stripColors(assets[r2][0]).replace(".jar", "") + ver + ".jar");
                                                tg.writeBytes(out);

                                                Vars.ui.loadfrag.hide();
                                            }, e -> Core.app.post(() -> {
                                                Log.err(e);
                                                Vars.ui.showException("Could not download update " + ver + " jar", e);
                                                Vars.ui.loadfrag.hide();
                                            }));
                                        }
                                );
                            });
                });
            }
        }, e -> Core.app.post(() -> {
            Log.err(e);
            YellowVars.ui.notifrag.showErrorNotification("@yellow.failver", e);
        }));
    }
}
