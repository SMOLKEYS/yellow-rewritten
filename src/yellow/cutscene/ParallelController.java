package yellow.cutscene;

import arc.func.*;
import arc.struct.*;

/** Allows execution of multiple controllers at once.
 * May yield unruly results if two controllers of the same type are used. */
public class ParallelController extends CutsceneController<ParallelController>{

    /** The controllers to be executed. Controllers that have finished are automatically reset and removed. */
    public Seq<CutsceneController<?>> controllers = new Seq<>(8);


    public void addController(CutsceneController<?> controller){
        controllers.add(controller);
    }

    @Override
    public ParallelController self(Cons<ParallelController> cons){
        cons.get(this);
        return this;
    }

    @Override
    public void init(){
        for(int i = 0; i < controllers.size; i++) controllers.get(i).init();
    }

    @Override
    public void onSkip(){
        for(int i = 0; i < controllers.size; i++) controllers.get(i).onSkip();
    }

    @Override
    public void update(){
        for(int i = 0; i < controllers.size; i++) controllers.get(i).update();
        CutsceneController<?> p = controllers.find(CutsceneController::isFinished);
        if(p != null){
            controllers.remove(p);
            p.onFinish();
            p.reset();
        }
    }

    @Override
    public void onFinish(){

    }

    @Override
    public boolean isFinished(){
        return controllers.isEmpty();
    }

    @Override
    public float progress(){
        return 0;
    }

    @Override
    public void reset(){
        super.reset();
        for(int i = 0; i < controllers.size; i++) controllers.get(i).reset();
        controllers.clear();
    }
}
