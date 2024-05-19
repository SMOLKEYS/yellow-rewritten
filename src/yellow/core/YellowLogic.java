package yellow.core;

import arc.*;
import yellow.comp.*;

import static mindustry.game.EventType.*;

public class YellowLogic{

    public static void load(){
        Events.on(UnitDestroyEvent.class, s -> {
            if(s.unit instanceof Soulc ss) ss.onDeath();
        });

        /*
        Events.on(TapEvent.class, s -> {
            Units.nearby(s.player.team(), s.tile.x*8, s.tile.y*8, 8*2, cl -> {
                if(cl instanceof WeaponSpecialistEntity we && Mathf.dst(s.player.x, s.player.y, cl.x, cl.y) < 8*6 && s.player.unit() != cl && !Core.input.keyDown(KeyCode.controlLeft)){
                    we.lookAt(s.player);
                    YellowVars.ui.weaponManager.show(we);
                }
            });
        });
         */
    }

    public static void clientPost(){
        /*
        Events.run(Trigger.update, () -> {
            if(Core.input.keyTap(KeyCode.comma) && Vars.player.unit() instanceof WeaponSpecialistEntity w && !YellowVars.ui.weaponManager.isShown()){
                YellowVars.ui.weaponManager.show(w);
            }
        });
         */
    }
}
