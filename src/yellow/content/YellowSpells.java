package yellow.content;

import yellow.type.*;
import yellow.type.spells.*;

public class YellowSpells{

    public static Spell missileInverter;

    public static void load(){
        missileInverter = new Spell("missile-inverter"){{
            cooldown = 60*3.5f;
            manaCost = 550;

            aftermath.add(new MissileInverter());
        }};
    }
}
