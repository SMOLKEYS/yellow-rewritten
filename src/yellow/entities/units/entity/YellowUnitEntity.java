package yellow.entities.units.entity;

import arc.util.io.*;
import mindustry.entities.units.*;
import yellow.comp.*;
import yellow.entities.units.*;

public class YellowUnitEntity extends MultiLifeUnitEntity{

    public YellowUnitEntity(){
        super();
    }

    @Override
    protected void init(){
        super.init();
    }

    @Override
    public void removeLife(){
        super.removeLife();
    }

    @Override
    public YellowUnitType type(){
        return (YellowUnitType) super.type();
    }

    @Override
    public void kill(){
        super.destroy();
    }

    @Override
    public void read(Reads read){
        super.read(read);

        for(WeaponMount w: mounts){
            if(w instanceof Savec s) s.read(read);
        }
    }

    @Override
    public void write(Writes write){
        super.write(write);

        for(WeaponMount w: mounts){
            if(w instanceof Savec s) s.write(write);
        }
    }
}
