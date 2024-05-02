package yellow.io;

import arc.util.io.*;
import mindustry.entities.units.*;
import mindustry.io.*;
import yellow.entities.units.*;
import yellow.type.*;

public class YellowTypeIO{

    /** Grabs all instances of {@link ToggleWeaponMount} in the specified {@link WeaponMount} array
     * and writes them to the target {@link Writes} instance.
     * @apiNote Writes one short before doing anything else. */
    public static void writeToggleWeapons(WeaponMount[] mounts, Writes write){
        short ss = 0;
        for(var w: mounts) if(w instanceof ToggleWeaponMount) ss++;
        write.s(ss);

        for(var w: mounts) if(w instanceof ToggleWeaponMount s) s.write(write);
    }

    /** Grabs all instances of {@link ToggleWeaponMount} in the specified {@link WeaponMount} array
     * and reads data from the target {@link Reads} instance to them.
     * @param dumpIfNotEqual Whether to dump the old bytes if the old mount length is not equal to
     *                       the current one. Otherwise, read the bytes to the first instances and
     *                       dump any excess bytes.
     * @apiNote Reads one short before doing anything else.
     */
    public static void readToggleWeapons(WeaponMount[] mounts, Reads read, boolean dumpIfNotEqual){
        short oldMountLength = read.s(); //old amount of mounts
        short currentMountLength = 0; //current amount of mounts
        for(var w: mounts) if(w instanceof ToggleWeaponMount) currentMountLength++;

        if(oldMountLength == currentMountLength){
            for(var w: mounts) if(w instanceof ToggleWeaponMount s) s.read(read);
        }else{
            if(dumpIfNotEqual){
                for(int i = 0; i < oldMountLength; i++){
                    read.bool();
                }
                return;
            }
            ToggleWeaponMount[] saveMounts = new ToggleWeaponMount[currentMountLength];
            int strayBytes = 0;

            //list down all current mounts
            for(var w: mounts){
                if(w instanceof ToggleWeaponMount s){
                    saveMounts[strayBytes] = s;
                    strayBytes++;
                }
            }

            for(var w: saveMounts){
                w.read(read);
            }

            //dump any unused bytes
            for(int i = 0; i < currentMountLength - strayBytes; i++){
                read.bool();
            }
        }
    }

    public static void writeSpells(SpellEntry[] spells, Writes write){
        short ss = 0;
        for(var s: spells) ss++;
        write.s(ss);

        for(var s: spells){
            s.write(write);
        }
    }

    public static void readSpells(SpellEntry[] spells, Reads read, boolean dumpIfNotEqual){
        short oldSpellLength = read.s();
        short currentSpellLength = (short) spells.length;

        if(oldSpellLength == currentSpellLength){
            for(var s: spells) s.read(read);
        }else{
            if(dumpIfNotEqual){
                for(int i = 0; i < oldSpellLength; i++){
                    read.f();
                }
                return;
            }
            int strayBytes = 0;

            for(var s: spells){
                s.read(read);
                strayBytes++;
            }

            //dump any unused bytes, again
            for(int i = 0; i < currentSpellLength - strayBytes; i++){
                read.f();
            }
        }
    }
}
