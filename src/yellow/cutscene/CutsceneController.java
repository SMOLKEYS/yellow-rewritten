package yellow.cutscene;

import arc.func.*;
import arc.math.*;
import arc.util.pooling.*;

/** The base class of all controllers. */
@SuppressWarnings("unused")
public abstract class CutsceneController<T> implements Pool.Poolable{
    protected Pool<CutsceneController<?>> pool;

    public abstract T self(Cons<T> cons);

    /** The pool this controller will be returned to when no longer in use. Can be null. */
    public Pool<CutsceneController<?>> getPool(){
        return pool;
    }

    /** Sets the target pool this controller will be returned to when no longer in use. */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public void setPool(Pool pool){
        this.pool = pool;
    }

    /** Called before all other methods. Can be used for setting up special properties. */
    public void init(){}

    /** Called if the dialogue this controller is part of gets skipped. */
    public void onSkip(){}

    /** Provides this controller with data from the previous controller.
     * @param provider The provided previous controller. Automatically {@link #reset() resets} once this controller finishes. */
    public void provide(CutsceneController<?> provider){}

    /** Updates this controller. */
    public void update(){}

    /** Called once this controller finishes. Is followed up by {@link #provide(CutsceneController)} and {@link #reset()}. */
    public void onFinish(){}

    /** Returns true if this controller has finished whatever it was designed to do. */
    public boolean isFinished(){
        return false;
    }

    /** Returns specified data from this controller that can be used by followup controllers. */
    public Object data(){
        return null;
    }

    /** Returns either a Linear 0-1 value, or any other value used to determine the progress of this controller.
     * @see #progress(Interp) */
    public float progress(){
        return 0;
    }

    /** Returns a non-Linear, interpolated 0-1 value. Certain types of interpolation may make it return a value above 1. */
    public final float progress(Interp interp){
        return interp.apply(progress());
    }

    public void reset(){
        pool = null;
    }
}
