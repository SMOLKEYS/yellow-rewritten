package yellow.world.meta;

import arc.*;
import arc.audio.*;
import mindustry.gen.*;

/** The base class for characters. Comes with basic values, like location and location locking. */
public class GameCharacter{
    /** The internal name of this character. */
    public String name;
    /** The sound bite (AKA "talking sound") of this character. */
    public Sound soundBite = Sounds.none;

    public GameCharacter(String name){
        this.name = name;
    }

    /** Returns the special ID of the save this character was last found in. */
    public String location(){
        return Core.settings.getString("character-" + name + "-location", "<none>");
    }

    public void setLocation(String loc){
        Core.settings.put("character-" + name + "-location", loc);
    }

    /** Returns true if the location lock for this character is enabled.
     * If true, this character's location cannot be changed. */
    public boolean locationLock(){
        return Core.settings.getBool("character-" + name + "-location-lock", false);
    }

    public void setLocationLock(boolean lock){
        Core.settings.put("character-" + name + "-location-lock", lock);
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
