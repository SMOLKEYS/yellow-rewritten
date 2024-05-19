package yellow.world.meta;

import arc.*;
import arc.audio.*;
import mindustry.gen.*;
import mindustry.type.*;

public class GameCharacter{
    /** The name of this character. */
    public String name;
    /** The unit representing this character. Used for camera control in cutscenes. */
    public UnitType unit;
    /** The sound bite (AKA "talking sound") of this character. */
    public Sound soundBite = Sounds.none;

    public GameCharacter(String name){
        this.name = name;
    }

    public GameCharacter(String name, UnitType unit){
        this(name);
        this.unit = unit;
    }

    /** Looks for this character's associated unit in the world.
     * @return A unit associated with this character. Null if none are found. */
    public Unit getUnit(){
        return Groups.unit.find(e -> e.type() == unit);
    }

    /** Returns the proper name of this character IF said character has been revealed. "???" otherwise. */
    public String displayed(){
        return isRevealed() ? Core.bundle.get("character." + name + ".name") : "???";
    }

    /** Sets the "revealed" status of this character. */
    public void setRevealed(boolean rev){
        Core.settings.put("character-" + name + "-revealed", rev);
    }

    /** Returns the "revealed" status of this character. */
    public boolean isRevealed(){
        return Core.settings.getBool("character-" + name + "-revealed", false);
    }
}
