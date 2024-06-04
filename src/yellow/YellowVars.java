package yellow;

import arc.*;
import arc.math.*;
import mindustry.*;
import mindustry.game.EventType.*;
import yellow.core.*;
import yellow.debug.*;
import yellow.game.*;
import yellow.mods.*;
import yellow.ui.*;
import yellow.util.*;
import yellow.world.meta.*;

import java.util.concurrent.atomic.*;

import static yellow.game.YellowEventType.*;

public class YellowVars{

    public static YellowUI ui;

    public static void init(){
        Events.fire(new YellowFirstStageInitializationEvent());
        ui = new YellowUI();

        Events.run(ClientLoadEvent.class, () -> {
            ui.init();

            ModPackageBridger.importPackage("yellow", "yellow.util", "yellow.ui", "yellow.math", "yellow.content", "yellow.cutscene", "yellow.dialogue", "yellow.debug");
            Autoupdater.checkForUpdates();
            YellowTips.load();
            YellowSettings.load();
            YellowSpecialNotifications.launchNotif();

            if(Core.settings.getBool("yellow-autoassign-save-ids", true) && !Vars.control.saves.getSaveSlots().isEmpty()){
                SaveIDAssigner.begin();
                ui.notifrag.showPersistentNotification(Core.bundle.format("yellow.save-id-results", SaveIDAssigner.overallSaves, SaveIDAssigner.successfulSaves, SaveIDAssigner.skippedSaves, SaveIDAssigner.failedSaves));
            }

            if(!Yellow.erroredExtensionList.isEmpty()){
                StringBuilder b = new StringBuilder();
                Yellow.erroredExtensionList.each(e -> {
                    b.append(e.file.name()).append(": ").append(e.exception.getClass().getSimpleName()).append("\n");
                });
                ui.notifrag.showErrorNotification(Core.bundle.format("yellow.extensions-failed", b.toString()));
            }

            if(ExtensionCore.extensions.find(e -> e.meta.enabled()) != null) ui.notifrag.showPersistentNotification(Core.bundle.format("yellow.extensions-loaded", ExtensionCore.extensions.select(p -> p.meta.enabled()).size));

            YellowLogic.clientPost();
            if(Yellow.debug) CutsceneTester.load();

            ExtensionCore.extensions.each(s -> {
                if(s.meta.enabled()) s.main.clientLoad();
            });

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
        return Core.settings.getString("yellow-update-server", "SMOLKEYS/yellow-rewritten");
    }

    public static void setUpdateServer(String server){
        Core.settings.put("yellow-update-server", server);
    }

    public static float getNotificationTime(){
        try{
            return (float) Core.settings.getInt("yellow-notification-time", 5);
        }catch(Exception e){
            return 5;
        }
    }

    public static void setNotificationTime(int time){
        Core.settings.put("yellow-notification-time", time);
    }

    public static float getTipTime(){
        try{
            return (float) Core.settings.getInt("yellow-tip-time", 60 * 60 * 5);
        }catch(Exception e){
            return 60*60*5;
        }
    }

    public static void setTipTime(int time){
        Core.settings.put("yellow-tip-time", time);
    }
}
