package yellow.comp;

import arc.func.*:
import mindustry.gen.*;

public interface ToggleWeaponsc extends Weaponsc{
    
    default void eachToggleMount(Cons<ToggleWeaponMount> cons){
        for(WeaponMount w : mounts()){
            if(w instanceof ToggleWeaponMount t) cons.get(t);
        }
    }
}