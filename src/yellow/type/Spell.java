package yellow.type;

import arc.*;
import arc.func.*;
import arc.struct.*;
import yellow.entities.units.*;

public class Spell{

    public String name, displayName, description;
    /** Required mana to cast this spell. */
    public float manaCost = 1f;
    /** Cooldown time when casting this spell, in ticks. */
    public float cooldown = 60f;
    /** The resulting aftermath from casting this spell. */
    public Seq<Aftermath> aftermath = new Seq<>();
    /** Type of spell entry to be used. */
    public Func<Spell, SpellEntry> spellType = SpellEntry::new;

    public Spell(String name){
        this.name = name;
        this.displayName = Core.bundle.get("spell." + name + ".name");
        this.description = Core.bundle.get("spell." + name + ".description");
    }

}
