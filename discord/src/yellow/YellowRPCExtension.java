package yellow;

import arc.*;
import arc.discord.*;
import arc.util.*;
import mindustry.game.*;
import mindustry.gen.*;
import mindustry.graphics.*;
import yellow.mods.*;

//TODO untested nor finished
public class YellowRPCExtension extends YellowExtension{

    public static DiscordRPC.RichPresence presence = new DiscordRPC.RichPresence();

    public YellowRPCExtension(){
        DiscordRPC.close();
        try{
            DiscordRPC.connect(1204425960037027900L);
            send();
            Events.on(EventType.ClientLoadEvent.class, r -> YellowVars.ui.notifrag.showNotification("Yellow RPC backend initialized."));
        }catch(Exception e){
            Events.on(EventType.ClientLoadEvent.class, r -> YellowVars.ui.notifrag.showPersistentNotification(Icon.warning.tint(Pal.remove), "Yellow RPC backend failed to initialize."));
        }
        Events.on(EventType.ClientLoadEvent.class, r -> {
            Timer.schedule(YellowRPCExtension::send, 30, 30);
        });
    }

    public static void send(){
        try{
            presence.details = "Version " + Yellow.meta().version;
            presence.state = "Something";
            presence.largeImageKey = "yellow";
        }catch(Exception e){
            //mhh
        }
    }
}
