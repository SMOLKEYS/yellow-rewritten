package yellow;

import arc.*;
import mindustry.mod.*;
import yellow.content.*;
import yellow.game.*;

public class Yellow extends Mod{

    boolean debug = false;

    public Yellow(){

    }

    @Override
    public void loadContent(){
        YellowUnitTypes.load();
    }
}
