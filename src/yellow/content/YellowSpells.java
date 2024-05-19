package yellow.content;

import yellow.type.*;
import yellow.type.spells.*;

public class YellowSpells{

    public static Spell missileInverter;

    public static void load(){
        missileInverter = new Spell("missile-inverter"){{
            cooldown = 60*10;
            manaCost = 250;

            aftermath.add(new MissileInverter());
        }};
    }
}
