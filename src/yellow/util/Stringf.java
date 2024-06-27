package yellow.util;

import arc.math.*;
import arc.util.*;

public class Stringf{

    /** Accepts a list of strings and returns the first non-null one. Returns null if the entire list is all nulls. */
    public static String alternative(String... inputs){
        for(String s: inputs){
            if(s != null) return s;
        }
        return null;
    }

    /** Returns a random char from the inputted string. */
    public static char random(String input){
        return random(input, Mathf.rand);
    }

    /** Returns a random char from the inputted string using the specified random source. */
    public static char random(String input, Rand rand){
        return input.charAt(rand.random(0, input.length() - 1));
    }

    public static float handleNumber(String input){
        StringBuilder s = new StringBuilder();
        boolean finished = false;

        for(int i = 0; i < input.length(); i++){
            char ind = input.charAt(i);
            if((Character.isDigit(ind) || ind == '.') && !finished){
                s.append(ind);
            }else{
                finished = true;
            }
        }

        return Strings.parseFloat(s.toString());
    }
}
