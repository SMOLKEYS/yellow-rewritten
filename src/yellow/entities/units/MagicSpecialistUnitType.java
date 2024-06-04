package yellow.entities.units;

import arc.struct.*;
import mindustry.game.*;
import mindustry.gen.*;
import yellow.entities.units.entity.*;
import yellow.type.*;
import yellow.world.meta.*;

public class MagicSpecialistUnitType extends MultiLifeUnitType{

    public Seq<Spell> spells = new Seq<>();

    public MagicSpecialistUnitType(GameCharacter character, String name){
        super(character, name);
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

    @Override
    public void setStats(){
        super.setStats();
        if(spells.any()){
            stats.add(YellowStats.spells, YellowStatValues.spells(this, spells));
        }
    }
}
