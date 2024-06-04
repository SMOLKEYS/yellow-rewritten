package yellow.world.meta;

import arc.*;
import arc.audio.*;
import mindustry.core.*;
import mindustry.game.*;
import mindustry.gen.*;
import mindustry.io.*;
import mindustry.maps.*;
import mindustry.type.*;

public class GameCharacter{
    /** The name of this character. */
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

    /** Returns true if the location lock for this character is enabled. */
    public boolean locationLock(){
        return Core.settings.getBool("character-" + name + "-location-lock", false);
    }

    public void location(String loc){
        Core.settings.put("character-" + name + "-location", loc);
    }

    public void location(Sector sector){
        if(sector.save != null && sector.save.meta.rules.tags.containsKey("yellow-save-id")) location(sector.save.meta.rules.tags.get("yellow-save-id"));
    }

    public void location(Saves.SaveSlot slot){
        if(slot.meta.rules.tags.containsKey("yellow-save-id")) location(slot.meta.rules.tags.get("yellow-save-id"));
    }

    public void locationLock(boolean lock){
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
