package yellow.util;

import arc.struct.*;
import mindustry.*;
import rhino.*;

/** Basic utility class that allows easily importing a package from a mod into Rhino. */
public class ModPackageBridger{
    static boolean loaded = false;
    static ImporterTopLevel scope = null;
    static Seq<String> loadedPackages = new Seq<>();


    /** Imports a provided list of packages. Nonexistent packages are ignored. Packages that were already imported will be re-imported.*/
    public static void importPackage(String... packageNames){
        importPackage(false, packageNames);
    }

    /** Imports a provided list of packages. Nonexistent packages are ignored.
     * @param ignoreLoadedPackages Whether to ignore packages that were already imported.
     * @param packageNames The list of packages to import. */
    public static void importPackage(boolean ignoreLoadedPackages, String... packageNames){
        if(!loaded){
            scope = (ImporterTopLevel) Vars.mods.getScripts().scope;
        }

        for(var s: packageNames){
            if(loadedPackages.contains(s) && ignoreLoadedPackages) return;

            NativeJavaPackage pkg = new NativeJavaPackage(s, Vars.mods.mainLoader());
            pkg.setParentScope(scope);
            scope.importPackage(pkg);

            if(!loadedPackages.contains(s)) loadedPackages.add(s);
        }
    }
}
