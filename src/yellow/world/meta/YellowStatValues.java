package yellow.world.meta;

import arc.struct.*;
import mindustry.type.*;
import mindustry.ui.*;
import mindustry.world.meta.*;
import yellow.type.*;

public class YellowStatValues{

    public static StatValue spells(UnitType unit, Seq<Spell> spells){
        return table -> {
            table.row();
            for(int i = 0; i < spells.size; i++){
                Spell spell = spells.get(i);

                table.table(Styles.grayPanel, s -> {
                    s.left().top().defaults().padRight(3).left();
                    s.row();

                    spell.addStats(unit, s);
                }).growX().pad(5).margin(10);
                table.row();
            }
        };
    }
}
