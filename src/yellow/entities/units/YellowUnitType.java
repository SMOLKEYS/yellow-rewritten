package yellow.entities.units;

import arc.math.geom.*;
import mindustry.game.*;
import mindustry.gen.*;
import yellow.content.*;
import yellow.entities.units.entity.*;

public class YellowUnitType extends MagicSpecialistUnitType{

    /** Whether only one of this unit can exist in a map. */
    public boolean limited = true;

    public YellowUnitType(String name){
        super(name);
        constructor = YellowUnitEntity::new;
    }

    @Override
    public Unit spawn(Team team, float x, float y){
        if(YellowUnitEntity.occupied(this, team) && limited) return Nulls.unit;
        return super.spawn(team, x, y);
    }

    @Override
    public Unit create(Team team){
        if(YellowUnitEntity.occupied(this, team) && limited) return Nulls.unit;
        YellowUnitEntity unit = (YellowUnitEntity) super.create(team);
        return unit;
    }
}
