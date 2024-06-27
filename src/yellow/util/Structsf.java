package yellow.util;

import arc.func.*;

public class Structsf{

    @SafeVarargs
    public static <T> void eachIndexed(Cons2<T, Integer> cons, T... arr){
        for(int i = 0; i < arr.length; i++) cons.get(arr[i], i);
    }
}
