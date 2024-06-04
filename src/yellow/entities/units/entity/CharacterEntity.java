package yellow.entities.units.entity;

import mindustry.gen.*;
import mindustry.type.*;
import yellow.entities.units.*;

public class CharacterEntity extends UnitEntity{

    private static final int mappingId = EntityMapping.register("character-entity", CharacterEntity::new);

    public CharacterEntity(){
        super();
    }

    @Override
    public CharacterUnitType type(){
        return (CharacterUnitType) super.type();
    }

    @Override
    public int classId(){
        return mappingId;
    }

    @Override
    public void update(){
        super.update();

        if(!type().locationMatch()) super.remove();
    }
}
