package yellow;

import arc.*;
import arc.math.*;
import mindustry.*;
import mindustry.game.EventType.*;
import yellow.comp.*;
import yellow.core.*;
import yellow.game.*;
import yellow.ui.*;
import yellow.ui.fragments.*;
import yellow.util.*;

public class YellowVars{

    public static YellowUI ui;

    public static void init(){
        ui = new YellowUI();

        Events.on(ClientLoadEvent.class, a -> {
            ui.init();

            ModPackageBridger.importPackage("yellow", "yellow.util", "yellow.ui");
            Autoupdater.load();
            YellowTips.load();
            YellowSettings.load();
            YellowSpecialNotifications.launchNotif();
            YellowLogic.clientPost();
            
            if(Yellow.launchFile().exists()) Yellow.launchFile().delete();
        });

        YellowLogic.load();

        if(!Core.settings.has("yellow-event-number")){
            Core.settings.put("yellow-event-number", Mathf.random(1, 100));
        }
    }

    public static String getUpdateServer(){
        return Core.settings.getString("yellow-update-server", "SMOLKEYS/yellow-rewritten");
    }

    public static void setUpdateServer(String server){
        Core.settings.put("yellow-update-server", server);
    }

    public static float getNotificationTime(){
        return (float)Core.settings.getInt("yellow-notification-time", 5);
    }

    public static void setNotificationTime(int time){
        Core.settings.put("yellow-notification-time", time);
    }

    public static float getTipTime(){
        return (float)Core.settings.getInt("yellow-tip-time", 60*60*5);
    }

    public static void setTipTime(int time){
        Core.settings.put("yellow-tip-time", time);
    }
}
