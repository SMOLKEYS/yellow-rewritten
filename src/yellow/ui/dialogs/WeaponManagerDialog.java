package yellow.ui.dialogs;

import arc.scene.ui.*;
import arc.scene.ui.layout.*;
import arc.struct.*;
import mindustry.entities.units.*;
import mindustry.ui.dialogs.*;
import yellow.entities.units.*;
import yellow.entities.units.entity.*;
import yellow.type.weapons.*;

public class WeaponManagerDialog extends BaseDialog{

    private static WeaponSpecialistEntity cache;

    public WeaponManagerDialog(){
        super("@yellow.weapon-manager");
        addCloseButton();
    }

    public Dialog show(WeaponSpecialistEntity entity){

        if(entity == cache) return super.show();

        cache = entity;
        cont.clear();
        cont.pane(tb -> {
            tb.top().left();
            for(var w: entity.mounts){
                if(w instanceof ToggleWeaponMount m && m.weapon instanceof ToggleWeapon tw){
                    tb.check(tw.displayName, s -> m.enabled = s).growX().left().get().getLabel().setWrap(false);
                    tb.row();
                }
            }
        }).minWidth(360).minHeight(500).row();
        if(entity instanceof MagicSpecialistEntity e){
            cont.pane(tb -> {
                tb.top().left();
                for(var sp: e.spells){
                    tb.button(sp.spell.displayName, sp::get);
                }
            }).minWidth(360).minHeight(500);
        }


        return super.show();
    }
}
