package yellow.entities.units;

import yellow.content.*;
import yellow.entities.units.entity.*;

public class YellowUnitType extends MultiLifeUnitType{

    public YellowUnitType(String name){
        super(name);
        constructor = YellowUnitEntity::new;
    }
}
