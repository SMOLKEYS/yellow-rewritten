package yellow.entities.abilities;

import arc.math.*;
import arc.math.geom.*;
import arc.scene.ui.layout.*;
import arc.util.*;
import mindustry.*;
import mindustry.content.*;
import mindustry.entities.*;
import mindustry.gen.*;
import yellow.world.meta.*;

public class TeleportAbility extends DeathStopAbility{

    /** Minimum/maximum teleport distance away from the death coordinates. */
    public float minTeleportDistance = 8*5, maxTeleportDistance = 8*10;
    /** Visual effect when teleporting. The effect is provided {@link Position} data and the rotation of the unit. */
    public Effect effect = Fx.none;

    public TeleportAbility(float teleportDistance, Effect effect){
        this(teleportDistance, teleportDistance, effect);
    }

    public TeleportAbility(float minTeleportDistance, float maxTeleportDistance, Effect effect){
        super();
        this.minTeleportDistance = minTeleportDistance;
        this.maxTeleportDistance = maxTeleportDistance;
        this.effect = effect;
    }

    public TeleportAbility(float teleportDistance){
        this(teleportDistance, teleportDistance);
    }

    public TeleportAbility(float minTeleportDistance, float maxTeleportDistance){
        super();
        this.minTeleportDistance = minTeleportDistance;
        this.maxTeleportDistance = maxTeleportDistance;
    }

    @Override
    public void addStats(Table t){
        t.add("[lightgray]" + YellowStats.minDst.localized() + ": [white]" + Strings.fixed(minTeleportDistance/8, 1) + " blocks");
        t.row();
        t.add("[lightgray]" + YellowStats.maxDst.localized() + ": [white]" + Strings.fixed(maxTeleportDistance/8, 1) + " blocks");
        t.row();
    }

    @Override
    public void onDeath(Unit unit){
        float fx = Mathf.range(minTeleportDistance, maxTeleportDistance), fy = Mathf.range(minTeleportDistance, maxTeleportDistance);;

        if(unit.x + fx > Vars.world.width() * 8f || unit.x + fx < 0f || unit.y + fy > Vars.world.height() * 8f || unit.y + fy < 0f){
            unit.x = Mathf.random(Vars.world.width() * 8);
            unit.y = Mathf.random(Vars.world.height() * 8);
        }else{
            unit.x += fx;
            unit.y += fy;
        }

        effect.at(unit.x, unit.y, unit.rotation, unit);
    }
}
