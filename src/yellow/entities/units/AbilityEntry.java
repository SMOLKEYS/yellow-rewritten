package yellow.entities.units;

import arc.util.io.*;
import yellow.comp.*;
import yellow.entities.abilities.*;

public class AbilityEntry implements Savec{
    public AdvancedAbility ability;

    public AbilityEntry(AdvancedAbility ability){
        this.ability = ability;
    }

    @Override
    public void read(Reads read){

    }

    @Override
    public void write(Writes write){

    }
}
