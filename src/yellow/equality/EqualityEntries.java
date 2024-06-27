package yellow.equality;

import yellow.util.*;

public class EqualityEntries{

    public static String[] ent = {"trueHealth"};

    public static boolean hasEntry(Object obj, String field){
        return SafeReflect.get(obj, field) != null;
    }
}
