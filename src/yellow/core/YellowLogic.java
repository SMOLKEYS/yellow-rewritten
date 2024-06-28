package yellow.core;

import arc.*;
import arc.input.*;
import arc.math.geom.*;
import mindustry.*;
import mindustry.entities.*;
import yellow.*;
import yellow.comp.*;
import yellow.entities.units.entity.*;
import yellow.world.meta.*;

import static mindustry.game.EventType.*;

public class YellowLogic{

    public static void load(){
        Events.on(UnitDestroyEvent.class, s -> {
            if(s.unit instanceof Soulc ss) ss.onDeath();
        });

        Core.input.addProcessor(new GestureDetector(new GestureDetector.GestureListener(){

            @Override
            public boolean touchDown(float x, float y, int pointer, KeyCode button){
                return false; //why
            }

            @Override
            public boolean fling(float velocityX, float velocityY, KeyCode button){
                return false; //whY
            }

            @Override
            public boolean pan(float x, float y, float deltaX, float deltaY){
                return false; //wHY
            }

            @Override
            public boolean panStop(float x, float y, int pointer, KeyCode button){
                return false; //WHY
            }

            @Override
            public boolean pinch(Vec2 initialPointer1, Vec2 initialPointer2, Vec2 pointer1, Vec2 pointer2){
            	return false; //WHY IS THIS NEEDED
            }

            @Override
            public void pinchStop(){
            	//ARE YOU SERIOUS
            }

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
    }
}
