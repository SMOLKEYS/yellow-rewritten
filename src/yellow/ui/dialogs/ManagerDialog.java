package yellow.ui.dialogs;

import arc.*;
import arc.flabel.*;
import arc.scene.ui.*;
import arc.util.*;
import mindustry.entities.units.*;
import mindustry.gen.*;
import mindustry.ui.*;
import mindustry.ui.dialogs.*;
import yellow.entities.units.*;
import yellow.entities.units.entity.*;
import yellow.type.weapons.*;

public class ManagerDialog extends BaseDialog{

    private static Unit cache;
    private static int attempts = 0;
    private static float time = 0f;

    public ManagerDialog(){
        super("@yellow.manager");
        addCloseButton();
    }

    public Dialog show(Unit entity){

        if(!(entity instanceof WeaponSpecialistEntity w)){
            cont.clear();

            attempts++;
            FLabel f = cont.add(new FLabel("@yellow.manager-wtf-" + attempts)).wrap().growX().get();

            f.update(() -> {
                time += Time.delta;
                if(f.hasEnded() && time > 60f){
                    hide();
                    time = 0;
                    f.update(() -> {});
                }
                if(!isShown()) f.update(() -> {});
            });

            if(attempts > 10){
                String s = Core.bundle.get("yellow.manager-wtf-11");
                Threads.throwAppException(new IllegalArgumentException(s));
            }

            return super.show();
        }else{
            if(entity == cache) return super.show();

            cache = entity;
            cont.clear();
            cont.pane(weapons -> {
                weapons.top().left().background(Styles.grayPanel).margin(15);
                for(WeaponMount m: w.mounts){
                    if(m instanceof ToggleWeaponMount wm) weapons.check(((NamedWeapon)wm.weapon).displayName, wm.enabled, res -> {
                        wm.enabled = res;
                    }).growX().left().row();
                }
            }).grow().uniform();

            if(Core.graphics.isPortrait()) cont.row();

            if(w instanceof MagicSpecialistEntity m) cont.pane(spells -> {
                spells.top().background(Styles.grayPanel).margin(15);
                for(SpellEntry s: m.spells){
                    spells.button(s.spell.displayName, () -> s.get(m)).wrapLabel(false).growX().row();
                }
            }).grow().uniform();
        }

        return super.show();
    }

}
