package yellow.comp;

import arc.math.geom.*;
import yellow.entities.units.*;

public interface Magicc extends Position{

    float mana();

    void mana(float mana);

    void consume(float mana);

    SpellEntry[] spells();

    void spells(SpellEntry[] spells);
}
