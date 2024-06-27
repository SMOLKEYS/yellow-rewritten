package yellow.entities.units;

import mindustry.*;
import mindustry.game.*;
import mindustry.gen.*;
import mindustry.type.*;
import yellow.entities.units.entity.*;
import yellow.world.meta.*;

import java.util.*;

/** A special type of unit with an associated {@link GameCharacter}. Only one may exist in an entire game save. */
public class CharacterUnitType extends UnitType{

    public GameCharacter character;

    public CharacterUnitType(GameCharacter character, String name){
        super(name);
        this.character = character;
        constructor = CharacterEntity::new;
    }

    @Override
    public Unit spawn(Team team, float x, float y){
        if(!locationMatch() || exists()){
            if(!character.locationLock() && !exists()){
                character.setLocation(Vars.state.rules.tags.get("yellow-save-id", "<none>"));
                return super.spawn(team, x, y);
            }
            return Nulls.unit;
        }
        return super.spawn(team, x, y);
    }

    @Override
    public Unit create(Team team){
        if(!locationMatch() || exists()){
            if(!character.locationLock() && !exists()){
                character.setLocation(Vars.state.rules.tags.get("yellow-save-id", "<none>"));
                return super.create(team);
            }
            return Nulls.unit;
        }
        return super.create(team);
    }

    public boolean locationMatch(){
        if(!Vars.state.isPlaying()) return false;
        return Objects.equals(character.location(), Vars.state.rules.tags.get("yellow-save-id")) && !Objects.equals(character.location(), "<none>");
    }

    public boolean exists(){
        return Groups.unit.find(u -> u.type() == this) != null;
    }
}
