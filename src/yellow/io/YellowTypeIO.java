package yellow.io;

import arc.util.io.*;
import mindustry.entities.units.*;
import yellow.entities.units.*;

public class YellowTypeIO{

    public static void writeToggleWeapons(WeaponMount[] mounts, Writes write){
        if(mounts.length == 0){
            write.s(0);
            return;
        }

        short ss = 0;
        for(var w: mounts) if(w instanceof ToggleWeaponMount) ss++;
        write.s(ss);

        for(var w: mounts) if(w instanceof ToggleWeaponMount s) s.write(write);
    }

    public static void readToggleWeapons(WeaponMount[] mounts, Reads read, boolean dumpIfNotEqual){
        if(mounts.length == 0){
            for(int i = 0; i < read.s(); i++){
                read.bool();
            }
            return;
        }

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
        if(spells.length == 0){
            write.s(0);
            return;
        }

        short ss = 0;
        for(var s: spells) ss++;
        write.s(ss);

        for(var s: spells){
            s.write(write);
        }
    }

    public static void readSpells(SpellEntry[] spells, Reads read, boolean dumpIfNotEqual){
        if(spells.length == 0){
            for(int i = 0; i < read.s(); i++){
                read.f();
            }
            return;
        }

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
