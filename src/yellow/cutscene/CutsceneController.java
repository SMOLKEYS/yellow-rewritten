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
    public abstract void init();

    /** Called if the dialogue this controller is part of gets skipped. */
    public abstract void onSkip();

    /** Updates this controller. */
    public abstract void update();

    /** Called once this controller finishes. */
    public abstract void onFinish();

    /** Returns true if this controller has finished whatever it was designed to do. */
    public abstract boolean isFinished();

    /** Returns either a Linear 0-1 value, or any other value used to determine the progress of this controller.
     * @see #progress(Interp) */
    public abstract float progress();

    /** Returns a non-Linear, interpolated 0-1 value. Certain types of interpolation may make it return a value above 1. */
    public final float progress(Interp interp){
        return interp.apply(progress());
    }

    public void reset(){
        pool = null;
    }
}
