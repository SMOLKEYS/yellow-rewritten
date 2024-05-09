package yellow.util;

import arc.struct.*;
import mindustry.*;
import mindustry.mod.*;
import rhino.*;

/** Basic utility class that allows easily importing classes/packages from the Mindustry {@link Mods#mainLoader() mod class loader}, which all mods use, into Rhino. */
public class ModPackageBridger{
    static boolean loaded = false;
    static ImporterTopLevel scope = null;
    static Seq<String> importedPackages = new Seq<>();
    static Seq<Class<?>> importedClasses = new Seq<>();

    /** Imports a provided list of packages. Nonexistent packages are ignored.
     * @param packageNames The list of packages to import. */
    public static void importPackage(String... packageNames){
        if(!loaded){
            scope = (ImporterTopLevel) Vars.mods.getScripts().scope;
        }

        for(var s: packageNames){
            if(importedPackages.contains(s)) return;

            NativeJavaPackage pkg = new NativeJavaPackage(s, Vars.mods.mainLoader());
            pkg.setParentScope(scope);
            scope.importPackage(pkg);

            if(!importedPackages.contains(s)) importedPackages.add(s);
        }
    }

    /** Imports a provided list of classes using their fully qualified class path. */
    public static void importClassByName(String... classNames){
        try{
            for(var s: classNames) importClass(Class.forName(s, true, Vars.mods.mainLoader()));
        }catch(Exception ignored){

        }
    }

    /** Imports a provided list of classes. Rhino cannot directly access members of a normal Java class, so what this does is it converts it into a NativeJavaClass, in which Rhino can directly access the members of. */
    public static void importClass(Class<?>... classes){
    	if(!loaded){
    		scope = (ImporterTopLevel) Vars.mods.getScripts().scope;
    	}

    	for(var s: classes){
    		if(importedClasses.contains(s)) return;

    		NativeJavaClass cls = new NativeJavaClass(scope, s);
    		scope.importClass(cls);

    		if(!importedClasses.contains(s)) importedClasses.add(s);
    	}
    }
}
