package yellow.entities.units;

import mindustry.game.*;
import mindustry.gen.*;
import yellow.entities.units.entity.*;
import yellow.world.meta.*;

public class YellowUnitType extends MagicSpecialistUnitType{

    public YellowUnitType(GameCharacter character, String name){
        super(character, name);
        constructor = YellowUnitEntity::new;
    }
}
