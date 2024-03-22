package yellow.game;

import yellow.entities.units.entity.*;

@SuppressWarnings("ClassCanBeRecord")
public class YellowEventType{

    public static class DeathStopEvent{
        public final MultiLifeUnitEntity unit;

        public DeathStopEvent(MultiLifeUnitEntity unit){
            this.unit = unit;
        }
    }
}
