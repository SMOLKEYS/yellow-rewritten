package yellow.entities.units;

import arc.util.io.*;
import yellow.entities.abilities.*;

public class OverheatingAbilityEntry extends AbilityEntry{
    public float heat;
    public float cooldown;

    public OverheatingAbilityEntry(AdvancedAbility ability){
        super(ability);
    }

    @Override
    public void read(Reads read){
        heat = read.f();
        cooldown = read.f();
    }

    @Override
    public void write(Writes write){
        write.f(heat);
        write.f(cooldown);
    }
}
