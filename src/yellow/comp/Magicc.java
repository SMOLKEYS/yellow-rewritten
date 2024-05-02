package yellow.comp;

import yellow.entities.units.*;

public interface Magicc{

    float mana();

    void mana(float mana);

    void consume(float mana);

    SpellEntry[] spells();

    void spells(SpellEntry[] spells);
}
