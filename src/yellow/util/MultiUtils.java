package yellow.util;

import arc.scene.ui.layout.*;
import arc.util.*;
import mindustry.*;
import mindustry.core.*;
import mindustry.maps.*;
import yellow.*;

public class MultiUtils{

    public static void forceHideLoading(){
        Vars.ui.loadfrag.hide();
        ((Table) Reflect.get(Vars.ui.loadfrag, "table")).visible = false;
    }

    /** Forcibly loads a map. */
    public static void forceLoad(Map map){
        YellowVars.ui.blankfrag.show();
        YellowVars.ui.multiGroup.toFront();
        Time.run(15f, () -> {
            Vars.world.loadMap(map);
            Vars.state.set(GameState.State.playing);
            YellowVars.ui.blankfrag.hide();
        });

    }
}
