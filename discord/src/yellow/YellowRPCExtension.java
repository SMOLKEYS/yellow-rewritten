package yellow;

import arc.*;
import arc.discord.*;
import arc.util.*;
import arc.struct.*;
import mindustry.game.*;
import mindustry.gen.*;
import mindustry.graphics.*;
import yellow.mods.*;
import yellow.ui.fragments.*;

//TODO untested nor finished
public class YellowRPCExtension extends YellowExtension{

    public static DiscordRPC.RichPresence presence = new DiscordRPC.RichPresence();
    public static String[] arr = {
    	"This. Is. Yellow.",
    	"A new look!",
    	"*eating chips*",
    	"Operates in real-time!",
    	"(Hopefully) NOT broken!",
    	"Something (nothing)"
    };

    public YellowRPCExtension(){
    }

    @Override
    public void clientLoad(){
        try{
            DiscordRPC.close();
            Log.info("Vanilla RPC closed.");
            DiscordRPC.connect(1204425960037027900L);
            Log.info("Yellow RPC initialized.");
            send();
            YellowVars.ui.notifrag.showNotification("Yellow RPC backend initialized.");
        }catch(Exception e){
            YellowVars.ui.notifrag.showErrorNotification("Yellow RPC backend failed to initialize.", e);
        }

        Timer.schedule(YellowRPCExtension::send, 15, 15);
    }

    public static void send(){
        try{
            presence.details = "Version " + Yellow.meta().version + " (" + Strings.stripColors(FirstLoadFragment.processMeta(Yellow.meta())) + ")";
            presence.state = Structs.random(arr);
            presence.largeImageKey = "yellow";
            presence.label1 = "Repository (Star!)";
            presence.url1 = "https://github.com/SMOLKEYS/yellow-rewritten";

            DiscordRPC.send(presence);
        }catch(Exception e){
            //YellowVars.ui.notifrag.showErrorNotification("Yellow RPC presence properties failed to set, for some reason.", e);
        }
    }
}
