package yellow.entities.abilities;

import arc.func.*;
import mindustry.entities.abilities.*;
import mindustry.gen.*;
import yellow.entities.units.*;

public abstract class AdvancedAbility extends Ability{

    public Func<AdvancedAbility, AbilityEntry> abilityType = AbilityEntry::new;

    public void update(Unit unit, AbilityEntry entry){}
    public void draw(Unit unit, AbilityEntry entry){}
}
