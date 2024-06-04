package yellow.world.meta;

import arc.math.*;
import arc.struct.*;
import arc.util.*;
import mindustry.*;
import mindustry.game.*;
import mindustry.type.*;

import java.util.*;

public class SaveIDAssigner{

    public static int skippedSaves, failedSaves, successfulSaves, overallSaves;
    static String collection = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789_-";
    static Rand r = new Rand();

    public static String generate(){
        StringBuilder c16 = new StringBuilder();
        for(int i = 0; i < 17; i++) c16.append(collection.charAt(r.random(0, collection.length() - 1)));
        return c16.toString();
    }

    /** Iterates over ALL saves and assigns a special ID to them. */
    public static void begin(){
        Vars.control.saves.getSaveSlots().each(s -> {
            overallSaves++;

            if(s.meta.rules.tags.containsKey("yellow-save-id")){
                skippedSaves++;
                return;
            }

            //ids are generated in the following way:
            //planet(nullable)-sectorid(nullable)-<random 16 character sequence, a-z, A-Z, 0-9, underscore, dash>
            try{
                StringBuilder builder = new StringBuilder();
                Sector sector = s.getSector();
                Planet planet = sector != null ? sector.planet : null;

                if(planet != null) builder.append(planet.name).append("-");
                if(sector != null) builder.append(sector.id).append("-");
                builder.append(generate());

                s.load();
                s.meta.rules.tags.put("yellow-save-id", builder.toString());
                Vars.state.rules = s.meta.rules;
                s.save();

                successfulSaves++;
            }catch(Exception e){
                Log.err(e);
                failedSaves++;
            }
        });
    }

    public static void update(){
        if(Vars.state.isPlaying()){
            StringMap map = Vars.state.rules.tags;

            if(!map.containsKey("yellow-save-id")){
                StringBuilder builder = new StringBuilder();
                Sector sector = Vars.state.rules.sector;
                Planet planet = sector != null ? sector.planet : null;

                if(planet != null) builder.append(planet.name).append("-");
                if(sector != null) builder.append(sector.id).append("-");
                builder.append(generate());

                map.put("yellow-save-id", builder.toString());
            }
        }
    }

    public static Saves.SaveSlot getSave(String id){
        return Vars.control.saves.getSaveSlots().find(s -> {
            if(!s.meta.rules.tags.containsKey("yellow-save-id")) return false;
            return Objects.equals(s.meta.rules.tags.get("yellow-save-id"), id);
        });
    }
}
