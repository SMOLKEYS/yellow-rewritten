package yellow.debug;

import arc.*;
import arc.struct.*;
import mindustry.*;
import mindustry.game.*;
import yellow.cutscene.*;

public class UpdateTester{

    public static Queue<CutsceneController<?>> queue = new Queue<>();
    public static CutsceneController<?> active;
    public static boolean lock = false;

    public static void update(){
        lock = active != null || !queue.isEmpty();

        if(active != null){
            active.update();
            if(active.isFinished()){
                active.onFinish();
                if(active.getPool() != null) active.getPool().free(active);
                active = null;
            }
        }else if(!queue.isEmpty()){
            active = queue.removeFirst();
            active.init();
        }
    }

    public static void load(){
        Events.run(EventType.Trigger.update, UpdateTester::update);
        Vars.control.input.addLock(() -> lock);
    }

    public static void addController(CutsceneController<?>... controllers){
        for(CutsceneController<?> controller: controllers) queue.add(controller);
    }
}
