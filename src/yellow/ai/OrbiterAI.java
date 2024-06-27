package yellow.ai;

import arc.func.*;
import arc.math.*;
import arc.util.*;
import mindustry.entities.units.*;
import mindustry.gen.*;
import mindustry.world.blocks.storage.*;

/** An AI that orbits a target unit. If no target is found, defaults to the team core. If that is also not found (somehow), does nothing. */
public class OrbiterAI extends AIController{

    protected float dist = 80f, wavedist = 20f;

    /** The target unit type to scan for. */
    public Boolf<Unit> targetUnit = p -> true;

    /** The current unit to orbit. */
    protected Unit follow;
    /** The target core building to orbit. */
    protected CoreBlock.CoreBuild core;

    public OrbiterAI(){
        super();
    }

    public OrbiterAI(Boolf<Unit> targetUnit){
        super();
        this.targetUnit = targetUnit;
    }

    @Override
    public void init(){
        //hurricane
        dist = Mathf.random(80f, 680f);
        wavedist = Mathf.random(20f, 680f);
    }

    @Override
    public void updateMovement(){

        if(follow != null && follow.dead) follow = null;

        if(follow == null){
            follow = Groups.unit.find(targetUnit);
        }

        if(follow != null && follow.team == unit.team){
            circle(follow, dist + Mathf.absin(Time.time * 0.05f, 20f, wavedist));
        }else if(core != null){
            circle(core, dist + Mathf.absin(Time.time * 0.05f, 20f, wavedist));
        }else{
            core = unit.team.data().core();
        }

        faceMovement();
    }
}
