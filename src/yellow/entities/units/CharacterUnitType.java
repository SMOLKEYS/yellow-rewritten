package yellow.entities.units;

import java.util.*;
import mindustry.*;
import mindustry.game.*;
import mindustry.gen.*;
import mindustry.type.*;
import yellow.entities.units.entity.*;
import yellow.world.meta.*;

/** A special type of unit with an associated {@link GameCharacter}. Only one for each team may exist in an entire game save. */
public class CharacterUnitType extends UnitType{

    public GameCharacter character;

    public CharacterUnitType(GameCharacter character, String name){
        super(name);
        this.character = character;
        constructor = CharacterEntity::new;
    }

    @Override
    public Unit spawn(Team team, float x, float y){
        if(!locationMatch(team) || exists(team)){
            if(!character.locationLock(team) && !exists(team)){
                character.setLocation(team, Vars.state.rules.tags.get("yellow-save-id", "<none>"));
                return super.spawn(team, x, y);
            }
            return Nulls.unit;
        }
        return super.spawn(team, x, y);
    }

    @Override
    public Unit create(Team team){
        if(!locationMatch(team) || exists(team)){
            if(!character.locationLock(team) && !exists(team)){
                character.setLocation(team, Vars.state.rules.tags.get("yellow-save-id", "<none>"));
                return super.create(team);
            }
            return Nulls.unit;
        }
        return super.create(team);
    }

    public boolean locationMatch(Team team){
        if(!Vars.state.isPlaying()) return false;
        return Objects.equals(character.location(team), Vars.state.rules.tags.get("yellow-save-id")) && !Objects.equals(character.location(team), "<none>");
    }

    public boolean exists(Team team){
        return Groups.unit.find(u -> u.type() == this && u.team == team) != null;
    }
}
