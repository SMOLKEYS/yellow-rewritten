package yellow.game;

import arc.*;
import arc.struct.*;
import arc.util.*;
import mindustry.game.*;
import yellow.*;

import java.util.*;

public class YellowTips{

    public static Seq<String> tips = new Seq<>();
    private static String recent = "???";
    private static float timer = 0;

    public static void load(){
        for(int i = 1; i < 6; i++) tips.add(Core.bundle.get("yellow.tip-header") + Core.bundle.get("yellow.tip-" + i));

        Events.run(EventType.Trigger.update, () -> {
            timer += Time.delta;
            if(timer >= YellowVars.getTipTime()){
                recent = tips.select(a -> !Objects.equals(recent, a)).random();
                YellowVars.ui.notifrag.showNotification(recent);
                timer = 0;
            }
        });
    }
}
