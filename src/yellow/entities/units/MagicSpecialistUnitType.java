package yellow.entities.units;

import arc.struct.*;
import mindustry.game.*;
import mindustry.gen.*;
import mindustry.type.*;
import yellow.entities.units.entity.*;
import yellow.type.*;

public class MagicSpecialistUnitType extends MultiLifeUnitType{

    public Seq<Spell> spells = new Seq<>();

    public MagicSpecialistUnitType(String name){
        super(name);
        constructor = MagicSpecialistEntity::new;
    }

    @Override
    public Unit create(Team team){
        MagicSpecialistEntity unit = (MagicSpecialistEntity) super.create(team);
        SpellEntry[] entries = new SpellEntry[spells.size];
        for(int i = 0; i < spells.size; i++){
            entries[i] = spells.get(i).spellType.get(spells.get(i));
        }
        unit.spells = entries;
        return unit;
    }
}
