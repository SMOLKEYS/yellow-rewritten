package yellow.util;

import arc.util.*;
import arc.util.io.*;
import mindustry.gen.*;
import yellow.entities.units.*;

public class EntityUtils{

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

    @SuppressWarnings("SameParameterValue")
    private static void deviousReflect(Object thing, String field, Object val){
        try{
            Reflect.set(thing, field, val);
        }catch(Exception ignored){

        }
    }
}