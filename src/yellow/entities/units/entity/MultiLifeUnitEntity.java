package yellow.entities.units.entity;

import arc.*;
import arc.func.*;
import arc.util.io.*;
import mindustry.gen.*;
import yellow.entities.units.*;
import yellow.game.YellowEventType.*;

/** A generic implementation of a unit with multiple lives. */
public class MultiLifeUnitEntity extends UnitEntity{

    protected boolean inited = false;
    public int lives = 0;

    public MultiLifeUnitEntity(){
        super();
    }

    protected void init(){
        if(inited) return;
        lives = type().lives;
    }

    public int lives(){
        return lives;
    }

    public void lives(int lives){
        this.lives = lives;
    }

    public float livesf(){
        return (float) lives / type().lives;
    }

    public void removeLife(){
        health = type().health;
        lives--;

        if(type().deathStopEffect != null) type().deathStopEffect.at(this);

        Cons<MultiLifeUnitEntity> m = type().perDeath.get(lives);
        if(m != null) m.get(this);

        type().deathStopAbilities.each(e -> e.onDeath(this));

        Events.fire(new DeathStopEvent(this));
    }

    @Override
    public MultiLifeUnitType type(){
        return (MultiLifeUnitType) super.type();
    }

    @Override
    public void read(Reads read){
        super.read(read);
        init();

        inited = read.bool();
        lives = read.i();
    }

    @Override
    public void write(Writes write){
        super.write(write);

        write.bool(inited);
        write.i(lives);
    }

    @Override
    public void destroy(){
        if(lives > 0){
            removeLife();
            return;
        }

        super.destroy();
    }

    @Override
    public void kill(){
        if(lives > 0){
            removeLife();
            return;
        }

        super.kill();
    }
}
