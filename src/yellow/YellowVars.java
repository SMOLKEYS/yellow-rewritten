package yellow;

import arc.*;
import arc.files.*;
import arc.math.*;
import arc.struct.*;
import mindustry.*;
import mindustry.game.EventType.*;
import mindustry.game.*;
import yellow.core.*;
import yellow.debug.*;
import yellow.game.*;
import yellow.mods.*;
import yellow.ui.*;
import yellow.util.*;
import yellow.world.meta.*;

import static yellow.game.YellowEventType.*;

public class YellowVars{

    static Seq<Saves.SaveSlot> foundSlots = new Seq<>(), brokenSlots = new Seq<>();

    public static YellowUI ui;
    public static Fi extensionDir = Yellow.configDir().child("extensions");

    public static void init(){
        Events.fire(new YellowFirstStageInitializationEvent());
        ui = new YellowUI();

        Events.run(ClientLoadEvent.class, () -> {
            ui.init();

            ModPackageBridger.importPackage("yellow", "yellow.util", "yellow.ui", "yellow.math", "yellow.content", "yellow.cutscene", "yellow.dialogue", "yellow.debug");
            Autoupdater.checkForUpdates(false);
            YellowTips.load();
            YellowSettings.load();
            YellowSpecialNotifications.launchNotif();

            if(Core.settings.getBool("yellow-check-unassigned-save-ids", true) && !Vars.control.saves.getSaveSlots().isEmpty()){
                Vars.control.saves.getSaveSlots().each(s -> {
                    if(!s.meta.rules.tags.containsKey("yellow-save-id")) foundSlots.add(s);
                });

                if(foundSlots.size != 0) ui.notifrag.showPersistentNotification(Core.bundle.format("yellow.found-saves", foundSlots.size), () -> {
                    Vars.ui.showConfirm(Core.bundle.format("yellow.found-saves.prompt", foundSlots.size), () -> {
                        foundSlots.each(s -> {
                            if(!SaveIDAssigner.assignId(s)) brokenSlots.add(s);
                        });
                        if(brokenSlots.size != 0){
                            ui.notifrag.showPersistentNotification(Core.bundle.format("yellow.found-saves.results", foundSlots.size - brokenSlots.size, brokenSlots.size));
                        }else{
                            ui.notifrag.showPersistentNotification(Core.bundle.format("yellow.found-saves.noerr-results", foundSlots.size));
                        }
                    });
                });
            }

            if(!Yellow.erroredExtensionList.isEmpty()){
                StringBuilder b = new StringBuilder();
                Yellow.erroredExtensionList.each(e -> {
                    b.append(e.file.name()).append(": ").append(e.exception.getClass().getSimpleName()).append("\n");
                });
                ui.notifrag.showErrorNotification(Core.bundle.format("yellow.extensions-failed", b.toString()));
            }

            ExtensionCore.extensions.each(s -> {
                if(s.meta.enabled()) s.main.clientLoad();
            });

            if(ExtensionCore.extensions.find(e -> e.meta.enabled()) != null) ui.notifrag.showPersistentNotification(Core.bundle.format("yellow.extensions-loaded", ExtensionCore.extensions.select(p -> p.meta.enabled()).size));

            YellowLogic.clientPost();
            if(Yellow.debug) CutsceneTester.load();

            Events.fire(new YellowFinalStageInitializationEvent());
            if(Yellow.launchFile().exists()) Yellow.launchFile().delete();
        });

        YellowLogic.load();

        if(!Core.settings.has("yellow-event-number")){
            Core.settings.put("yellow-event-number", Mathf.random(1, 100));
        }

        Events.fire(new YellowSecondStageInitializationEvent());
    }

    public static String getUpdateServer(){
        return SafeSettings.getString("yellow-update-server", "SMOLKEYS/yellow-rewritten", "SMOLKEYS/yellow-rewritten");
    }

    public static void setUpdateServer(String server){
        Core.settings.put("yellow-update-server", server);
    }

    public static float getNotificationTime(){
        return SafeSettings.getFloat("yellow-notification-time", 5,  5);
    }

    public static void setNotificationTime(float time){
        Core.settings.put("yellow-notification-time", time);
    }

    public static float getTipTime(){
        return SafeSettings.getFloat("yellow-tip-time", 60*60*5, 60*60*5);
    }

    public static void setTipTime(float time){
        Core.settings.put("yellow-tip-time", time);
    }
}
