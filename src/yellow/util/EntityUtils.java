package yellow.util;

import arc.util.*;
import mindustry.entities.units.*;
import mindustry.gen.*;
import yellow.entities.units.*;

public class EntityUtils{

    static boolean t = false;

    public static void unexist(Unit unit){
        unit.damage(Float.MAX_VALUE);
        unit.health = unit.armor = unit.shield = 0;
        unit.dead(true);
        unit.kill();
        unit.destroy();
        unit.remove();

        deviousReflect(unit,  "trueHealth", 0f);
        deviousReflect(unit,  "trueMaxHealth", 0f);
    }

    public static boolean containsToggleMount(WeaponMount[] mounts){
        for(var w: mounts){
            if(w instanceof ToggleWeaponMount) return true;
        }
        return false;
    }

    @SuppressWarnings("SameParameterValue")
    private static void deviousReflect(Object thing, String field, Object val){
        try{
            Reflect.set(thing, field, val);
        }catch(Exception ignored){

        }
    }
}
