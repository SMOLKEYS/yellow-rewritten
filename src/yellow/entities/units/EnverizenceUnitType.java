package yellow.entities.units;

import yellow.entities.units.entity.*;
import yellow.world.meta.*;

public class EnverizenceUnitType extends CharacterUnitType{


    public EnverizenceUnitType(GameCharacter character, String name){
        super(character, name);
        constructor = EnverizenceUnitEntity::new;
    }
}
