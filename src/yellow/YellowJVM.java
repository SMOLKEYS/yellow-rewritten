package yellow;

import arc.func.*;
import mindustry.*;

@SuppressWarnings("SameParameterValue")
final class YellowJVM{

    private static String source = "sun.java.command";


    static void setSource(String newSource){
        source = newSource;
    }

    static boolean hasParameter(String argument){
        if(Vars.mobile) return false; //ah, mobile
        return System.getProperty(source).contains("--" + argument);
    }

    static boolean hasParameter(String argument, Cons<String> ifDetected){
        boolean s = hasParameter(argument);
        if(s) ifDetected.get(argument);
        return s;
    }
}
