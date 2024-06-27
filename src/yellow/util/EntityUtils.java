package yellow.util;

import arc.util.*;
import mindustry.entities.units.*;
import mindustry.gen.*;
import yellow.entities.units.*;

public class EntityUtils{

    static boolean t = false;

    public static void unexist(Unit unit){
        unit.damage(Float.MAX_VALUE);
        unit.health = unit.armor = unit.shield = unit.maxHealth = 0;
        unit.dead(true);
        unit.kill();
        unit.destroy();
        unit.remove();

        SafeReflect.set(unit,  "trueHealth", 0f);
        SafeReflect.set(unit,  "trueMaxHealth", 0f);
    }
}
