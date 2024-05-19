package yellow.dialogue;

import arc.flabel.*;
import arc.func.*;
import arc.struct.*;
import arc.util.pooling.*;
import yellow.cutscene.*;
import yellow.world.meta.*;

public class Dialogue implements Pool.Poolable{
    private static final Pool<Dialogue> pool = Pools.get(Dialogue.class, Dialogue::new);

    /** The character talking for this dialogue. If null, the dialogue will instead refer to the last character that was talking. */
    public GameCharacter character;
    /** The text for this dialogue. */
    public String text;
    /** If true, this dialogue is automatically skipped once it finishes. Can be used for "interrupted" dialogue. */
    public boolean autoskip;
    /** Delay before automatically skipping this dialogue. */
    public float autoskipDelay;
    public Runnable begin, end;
    public Queue<CutsceneController<?>> controls = new Queue<>();
    private CutsceneController<?> activeControl;

    protected Dialogue(){

    }

    public static Dialogue obtain(){
        return pool.obtain();
    }

    public static Dialogue obtain(Cons<Dialogue> builder){
        Dialogue instance = obtain();
        return instance.self(builder);
    }

    public Dialogue self(Cons<Dialogue> builder){
        builder.get(this);
        return this;
    }

    public void update(){
        if(activeControl != null){
            activeControl.update();
            if(activeControl.isFinished()){
                activeControl.onFinish();
                activeControl.reset();
                activeControl = null;
            }
        }else if(!controls.isEmpty()){
            activeControl = controls.removeFirst();
            activeControl.init();
        }
    }

    public boolean isFinished(FLabel backer){
        return backer.hasEnded() && controls.isEmpty();
    }

    @Override
    public void reset(){
        character = null;
        text = null;
        autoskip = false;
        autoskipDelay = 0f;
        begin = end = null;
        controls.each(Pool.Poolable::reset);
        controls.clear();
        controls.shrink();
        if(activeControl != null){
            activeControl.reset();
            activeControl = null;
        }
    }
}
