package yellow.game;

import arc.*;
import arc.struct.*;
import arc.util.*;
import mindustry.game.*;
import yellow.*;

public class YellowTips{

    public static Seq<String> tips = new Seq<>();
    private static float timer = 0;

    public static void load(){
        for(int i = 1; i < 6; i++) tips.add(Core.bundle.get("yellow.tip-header") + Core.bundle.get("yellow.tip-" + i));

        Events.run(EventType.Trigger.update, () -> {
            timer += Time.delta;
            if(timer >= YellowVars.getTipTime()){
                YellowVars.ui.notifrag.showNotification(tips.random());
                timer = 0;
            }
        });
    }
}
