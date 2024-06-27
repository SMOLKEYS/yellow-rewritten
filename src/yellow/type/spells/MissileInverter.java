package yellow.type.spells;

import mindustry.ai.types.*;
import mindustry.entities.*;
import mindustry.gen.*;
import yellow.comp.*;
import yellow.entities.units.*;
import yellow.type.*;

public class MissileInverter implements Aftermath{
    public float radius = 8*10f;

    public MissileInverter(){
    }

    public MissileInverter(float radius){
        this.radius = radius;
    }

    @Override
    public void get(Magicc caster, SpellEntry spell){
        if(caster instanceof Unit u){
            Units.nearbyEnemies(u.team(), u.x, u.y, radius, un -> {
                //get all enemy missiles, change team, flip rotation and change owner
                if(un instanceof TimedKillUnit tk){
                    tk.team(u.team());
                    tk.vel().inv();
                    tk.rotation(tk.rotation() + 180);
                    if(tk.controller() instanceof MissileAI ai) ai.shooter = u;
                }
            });
        }
    }
}
