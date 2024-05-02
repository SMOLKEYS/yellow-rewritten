package yellow.entities.units;

import arc.func.*;
import arc.struct.*;
import mindustry.content.*;
import mindustry.entities.*;
import mindustry.game.*;
import mindustry.gen.*;
import mindustry.type.*;
import yellow.entities.units.entity.*;
import yellow.type.*;
import yellow.world.meta.*;

public class MultiLifeUnitType extends UnitType{
    /** How many lives this unit has. */
    public int lives = 1;
    /** Invincibility frames this unit gets when losing a life. */
    public float invFrames = 1f;
    /** Multiplier that makes inv-frames last longer the more lives are lost. */
    public float invDeathMultiplier = 1f;
    /** Individual pieces of code ran when the unit's life count hits a specific number. */
    public ObjectMap<Integer, Cons<MultiLifeUnitEntity>> perDeath = new ObjectMap<>();
    /** Visual effect used when a life is consumed. */
    public Effect deathStopEffect = Fx.none;

    public MultiLifeUnitType(String name){
        super(name);
        constructor = MultiLifeUnitEntity::new;
    }

    @Override
    public Unit create(Team team){
        MultiLifeUnitEntity unit = (MultiLifeUnitEntity) super.create(team);
        unit.lives(lives);
        return unit;
    }

    @Override
    public void setStats(){
        super.setStats();

        stats.add(YellowStats.lives, lives);
    }
}
