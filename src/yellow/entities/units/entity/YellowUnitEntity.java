package yellow.entities.units.entity;

import mindustry.game.*;
import mindustry.gen.*;
import mindustry.type.*;
import yellow.comp.*;
import yellow.entities.units.*;

public class YellowUnitEntity extends MagicSpecialistEntity implements Soulc{

    private static final int mappingId = EntityMapping.register("yellow-unit", YellowUnitEntity::new);

    public YellowUnitEntity(){
        super();
    }

    public static boolean occupied(UnitType type, Team team){
        return Groups.unit.find(e -> e.type() == type && e.team == team) != null;
    }

    @Override
    public int classId(){
        return mappingId;
    }

    @Override
    public boolean spawnedByCore(){
        spawnedByCore = false;
        return false;
    }

    @Override
    public void spawnedByCore(boolean spawnedByCore){
        super.spawnedByCore(false); //no
    }

    @Override
    public int cap(){
        return 1;
    }

    @Override
    public void removeLife(){
        super.removeLife();
    }

    @Override
    public YellowUnitType type(){
        return (YellowUnitType) super.type();
    }

    @Override
    public void kill(){
        super.destroy();
    }
}
