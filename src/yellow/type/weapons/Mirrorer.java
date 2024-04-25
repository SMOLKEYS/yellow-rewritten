package yellow.type.weapons;

import arc.func.*;
import arc.struct.*;
import mindustry.type.*;

public class Mirrorer{
    private static final ObjectMap<String, ToggleWeapon> mirrors = new ObjectMap<>();

    public static void reflect(ToggleWeapon source, ReflectProperty... properties){
        if(mirrors.containsKey(source.name)) return;
        var t = source.copy();
        t.name = source.name + "-mirror";
        t.displayName = source.displayName + " (Mirror)";
        t.x = -source.x;

        t.original = source;

        for(var s: properties){
            s.get(source, t);
        }

        mirrors.put(source.name, t);
    }

    public static ToggleWeapon get(ToggleWeapon original){
        return get(original.name);
    }

    public static ToggleWeapon get(String original){
        return mirrors.get(original);
    }


    public enum ReflectProperty{
        flipY((s, t) -> {
            t.y = -s.y;
        }),
        doubledReload((s, t) -> {
            t.reload = s.reload * 2;
        });

        private final Cons2<ToggleWeapon, ToggleWeapon> cons;

        ReflectProperty(Cons2<ToggleWeapon, ToggleWeapon> cons){
            this.cons = cons;
        }

        public void get(ToggleWeapon source, ToggleWeapon target){
            cons.get(source, target);
        }
    }
}
