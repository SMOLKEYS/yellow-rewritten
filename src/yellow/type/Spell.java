package yellow.type;

import arc.*;
import arc.func.*;
import arc.scene.ui.layout.*;
import arc.struct.*;
import mindustry.content.*;
import mindustry.entities.*;
import mindustry.graphics.*;
import mindustry.world.meta.*;
import yellow.comp.*;
import yellow.entities.units.*;
import yellow.world.meta.*;

public class Spell{

    public String name, displayName, description;
    /** Required mana to cast this spell. */
    public float manaCost = 1f;
    /** Cooldown time when casting this spell, in ticks. */
    public float cooldown = 60f;
    /** Type of spell entry to be used. */
    public Func<Spell, SpellEntry> spellType = SpellEntry::new;
    /** Effect spawned upon casting this spell. */
    public Effect castEffect = Fx.none;
    /** Various other stats this spell has. */
    public Stats stats = new Stats();
    /** Persistent keybind associated with this spell. */
    protected KeyBinds.KeyBind keyBind;

    public Spell(String name){
        this.name = name;
        this.displayName = Core.bundle.get("spell." + name + ".name");
        this.description = Core.bundle.get("spell." + name + ".description");
    }

    public void addStats(){
        stats.add(YellowStats.name, displayName);
        stats.add(YellowStats.cooldown, cooldown/60, StatUnit.seconds);
        stats.add(YellowStats.manaCost, manaCost, YellowStatUnits.manaPoints);
    }

    public void handleStats(Table t){
        addStats();


        //a minute amount of code grabby
        //ContentInfoDialog line 66-89
        for(StatCat cat : stats.toMap().keys()){
            OrderedMap<Stat, Seq<StatValue>> map = stats.toMap().get(cat);

            if(map.size == 0) continue;

            if(stats.useCategories){
                t.add("@category." + cat.name).color(Pal.accent).fillX();
                t.row();
            }

            for(Stat stat : map.keys()){
                t.table(inset -> {
                    inset.left();
                    inset.add("[lightgray]" + stat.localized() + ":[] ").left().top();
                    Seq<StatValue> arr = map.get(stat);
                    for(StatValue value : arr){
                        value.display(inset);
                        inset.add().size(10f);
                    }

                }).fillX().padLeft(10);
                t.row();
            }
        }
    }

    public void use(Magicc user, SpellEntry spell){

    }
}
