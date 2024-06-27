package yellow.util;

import arc.util.*;

public class SafeReflect{

    @SuppressWarnings("SameParameterValue")
    public static void set(Object thing, String field, Object val){
        try{
            Reflect.set(thing, field, val);
        }catch(Exception ignored){

        }
    }

    public static <T> T get(Object thing, String field){
        try{
            return Reflect.get(thing, field);
        }catch(Exception ignored){
            return null;
        }
    }
}
