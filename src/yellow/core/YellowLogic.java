package yellow.core;

import arc.*;
import arc.input.*;
import arc.math.geom.*;
import mindustry.*;
import mindustry.entities.*;
import mindustry.gen.*;
import yellow.*;
import yellow.comp.*;
import yellow.entities.units.entity.*;
import yellow.graphics.*;
import yellow.input.*;
import yellow.util.*;
import yellow.watchdog.*;
import yellow.world.meta.*;

import static mindustry.game.EventType.*;

public class YellowLogic{

    public static void load(){
        Events.on(UnitDestroyEvent.class, s -> {
            if(s.unit instanceof Soulc ss) ss.onDeath();
        });

        Core.input.addProcessor(new GestureDetector(new UnabstractedGestureListener(){

            @Override
            public boolean tap(float x, float y, int count, KeyCode button){
                if(Vars.mobile) return false; //do not register for mobile

                Vec2 c = Core.input.mouseWorld(x, y);
                if(count == 2){
                    Units.nearby(Vars.player.team(), c.x, c.y, 8 * 2, u -> {
                        if(u instanceof WeaponSpecialistEntity e && Vars.player.unit() != e) YellowVars.ui.weaponManager.show(e.mounts);
                    });
                }

                return false;
            }

            @Override
            public boolean longPress(float x, float y){
                Vec2 c = Core.input.mouseWorld(x, y);

                Units.nearby(Vars.player.team(), c.x, c.y, 8 * 2, u -> {
                    if(u instanceof WeaponSpecialistEntity e && Vars.player.unit() != e) YellowVars.ui.weaponManager.show(e.mounts);
                });

                return false;
            }

        }));
    }

    public static void clientPost(){
        Events.run(Trigger.update, SaveIDAssigner::update);
        Events.run(Trigger.draw, () -> {
            UserFx.FxPair<Unit> pair = UserFx.getEntry(null);
            pair.global.run();
            if(Vars.player.unit() != null) pair.target.get(Vars.player.unit());
        });

        //check for potentially broken spear bullets every 10 seconds and fix them if possible
        Timey.runLoop(Validation::validateSpearBullets, 10f);
    }
}
