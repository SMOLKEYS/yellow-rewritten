package yellow.type.abilities;

import arc.math.*;
import arc.math.geom.*;
import mindustry.content.*;
import mindustry.entities.*;
import mindustry.gen.*;
import yellow.type.*;

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
    public void onDeath(Unit unit){
        float x = unit.x, y = unit.y;

        unit.x = Mathf.random(minTeleportDistance, maxTeleportDistance);
        unit.y = Mathf.random(minTeleportDistance, maxTeleportDistance);

        effect.at(x, y, unit.rotation, unit);
    }
}
