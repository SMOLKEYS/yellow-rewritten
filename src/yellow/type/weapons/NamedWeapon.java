package yellow.type.weapons;

import arc.*;
import mindustry.type.*;

/** A weapon with a display name and description. */
public class NamedWeapon extends Weapon{
    public String displayName, description;

    public NamedWeapon(String name){
        super(name);
        displayName = Core.bundle.get("weapon." + name + ".name");
        description = Core.bundle.get("weapon." + name + ".description");
    }
}
