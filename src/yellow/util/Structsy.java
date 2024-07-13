package yellow.util;

import arc.func.*;
import arc.util.*;

public class Structsy{

    @SafeVarargs
    public static <T> void eachIndexed(Cons2<T, Integer> cons, T... arr){
        for(int i = 0; i < arr.length; i++) cons.get(arr[i], i);
    }

    public static <T> int distance(T[] arr, T item1, T item2){
        int i1 = Structs.indexOf(arr, item1);
        int i2 = Structs.indexOf(arr, item2);

        if(i1 == -1 || i2 == -1) return -1;

        return Math.abs(i1 - i2);
    }
}
