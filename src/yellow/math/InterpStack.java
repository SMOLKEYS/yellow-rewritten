package yellow.math;

import arc.math.*;

public class InterpStack implements Interp{
    public Interp[] interps;

    public InterpStack(Interp... interps){
        this.interps = interps;
    }

    @Override
    public float apply(float a){
        for(Interp i: interps) a = i.apply(a);

        return a;
    }
}
