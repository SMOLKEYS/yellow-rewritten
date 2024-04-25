package yellow.ui.fragments;

import arc.*;
import arc.flabel.*;
import arc.scene.*;
import arc.scene.event.*;
import arc.scene.actions.*;
import arc.scene.ui.*;
import arc.graphics.*;
import arc.scene.ui.layout.*;
import mindustry.*;
import mindustry.graphics.*;
import mindustry.mod.*;
import mindustry.ui.*;
import yellow.*;

public class FirstLoadFragment{

    boolean cur = false;

    public void build(Group parent){
        cur = false;
        boolean b = Core.settings.getBoolOnce("yellow-first-load");
        if(b) return;

        parent.fill(s -> {
            s.name = "first load";
            s.visible(() -> true);
            s.touchable = Touchable.childrenOnly;
            s.background(Styles.black);
            s.center();

            Mods.ModMeta meta = Yellow.meta();

            s.table(v -> {
                v.touchable = Touchable.childrenOnly;

                Cell<FLabel> fcell = v.add(new FLabel(Vars.tree.get("texts/notes.txt").readString())).grow().left().pad(70);
                FLabel f = fcell.get();

                f.setWrap(true);

                v.row();
                v.add("Next").self(l -> {
                    Label ll = l.get();

                    ll.clicked(() -> {
                        if(f.hasEnded()){
                            if(cur){
                                s.actions(Actions.sequence(
                                        Actions.run(() -> {
                                            s.clearChildren();
                                            s.add(new FLabel("Good luck out there!"));
                                        }),
                                        Actions.delay(1),
                                        Actions.fadeOut(3),
                                        Actions.remove()
                                ));
                            }else{
                                cur = true;
                                ll.setText("Proceed");
                                f.restart(Vars.tree.get("texts/notes2.txt").readString()
                                        .replace("-ver-", meta.version)
                                        .replace(
                                                "-type-",
                                                (meta.version.endsWith("S") ? "[accent]Stable[]" : meta.version.endsWith("B") ? "[cyan]Beta[]" : "[gray]Dev[]")
                                        )
                                );
                            }
                        }
                    });
                    ll.update(() -> ll.setColor(f.hasEnded() ? Pal.accent : Color.white));
                });
            }).growX();
        });
    }
}
