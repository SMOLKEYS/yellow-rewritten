package yellow.entities.units;

import arc.struct.*;
import mindustry.entities.abilities.*;
import mindustry.world.meta.*;
import yellow.entities.abilities.*;
import yellow.entities.units.entity.*;
import yellow.type.weapons.*;
import yellow.world.meta.*;

public class YellowUnitType extends MagicSpecialistUnitType{


    public YellowUnitType(GameCharacter character, String name){
        super(character, name);
        constructor = YellowUnitEntity::new;
    }

    @Override
    public void setStats(){
        super.setStats();

        if(weapons.any()){
            stats.remove(Stat.weapons);
            stats.add(Stat.weapons, StatValues.weapons(this, weapons.select(s -> (s instanceof ToggleWeapon t) && t.original == null)));
        }
    }
}
