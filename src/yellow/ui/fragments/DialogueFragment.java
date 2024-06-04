package yellow.ui.fragments;

import arc.*;
import arc.flabel.*;
import arc.graphics.*;
import arc.math.*;
import arc.scene.*;
import arc.scene.actions.*;
import arc.scene.ui.*;
import arc.scene.ui.layout.*;
import arc.struct.*;
import arc.util.*;
import mindustry.*;
import mindustry.game.*;
import mindustry.ui.*;
import yellow.ui.*;
import yellow.world.meta.*;

public class DialogueFragment implements CommonFragment{


    private GameCharacter currentCharacter;
    private Label nameLabel;
    private FLabel textLabel;
    private float curHeight;
    private boolean cutsceneLock = false, curshow = false, curqueue = false;

    private Table curtop, curbottom, curfull, chardisp, dbox, choices, textinp;

    @Override
    public void build(Group parent){
        curHeight = Scl.scl(Vars.mobile ? Core.graphics.isPortrait() ? Core.graphics.getWidth() : Core.graphics.getHeight() : Core.graphics.getHeight());

        Vars.control.input.addLock(() -> cutsceneLock);

        //cutscene curtains (top and bottom)
        parent.fill(s -> {
            curtop = s;
            s.name = "upper curtain";
            s.x = Core.graphics.getWidth();
            s.top();
            s.image().color(Color.black).height(curHeight / 4.3f).growX();
        });

        parent.fill(s -> {
            curbottom = s;
            s.name = "lower curtain";
            s.x = -Core.graphics.getWidth();
            s.bottom();
            s.image().color(Color.black).height(curHeight / 4.3f).growX();
        });

        //cutscene curtain (whole screen)
        parent.fill(s -> {
            curfull = s;
            s.name = "full curtain";
            s.actions(Actions.fadeOut(0), Actions.visible(false));
            s.image().color(Color.black).grow();
        });

        //character scene
        parent.fill(s -> {
            chardisp = s;
            s.name = "character display";
            s.actions(Actions.fadeOut(0), Actions.visible(false));
        });

        //dialogue box root
        parent.fill(s -> {
            dbox = s;
            s.name = "dialogue box";
            s.bottom();
            s.actions(Actions.fadeOut(0), Actions.visible(false));

            s.table(Styles.black5, box -> {
                box.top();

                //upper table (name display)
                box.table(upper -> {
                    upper.marginLeft(40).left();
                    nameLabel = upper.add("???").get();
                }).growX().height(20f).row();
                //lower table (dialogue)
                box.table(lower -> {
                    lower.marginLeft(40).top().left();
                    textLabel = lower.add(new FLabel("you should NOT be seeing this")).get();
                }).growX().padTop(20).row();
            }).height(curHeight / 3f).growX().row();
        });

        //choices table (resizeable)
        parent.fill(s -> {
            choices = s;
            s.visible = false;
        });

        //text input
        parent.fill(s -> {
            textinp = s;
            s.visible = false;
        });

        Events.run(EventType.ResizeEvent.class, () -> {
            curtop.x = curshow ? 0f : Core.graphics.getWidth();
            curbottom.x = curshow ? 0f : -Core.graphics.getWidth();
        });
    }

    private void forceLandscape(boolean set){
        if(set){
            Vars.platform.beginForceLandscape();
        }else{
            Vars.platform.endForceLandscape();
        }
    }

    /** Toggles the cutscene curtains.
     * @return {@code true} if the operation succeeded (curtains aren't currently entering/exiting the scene). {@code false} otherwise. */
    public boolean toggleCurtains(){
        if(curtop.hasActions() || curbottom.hasActions() || curqueue) return false;

        curshow = !curshow;
        curqueue = true;

        if(Core.app.isAndroid()) forceLandscape(curshow);

        //delay because i know how landscape be sometimes
        Time.run(Core.app.isAndroid() ? 30 : 10, () -> Core.app.post(() -> {
            curqueue = false;

            if(curshow){
                curtop.actions(Actions.translateBy(-Core.graphics.getWidth(), 0, 1.2f, Interp.pow3Out));
                curbottom.actions(Actions.translateBy(Core.graphics.getWidth(), 0, 1.2f, Interp.pow3Out));
            }else{
                curtop.actions(Actions.translateBy(Core.graphics.getWidth(), 0, 1.2f, Interp.pow3Out));
                curbottom.actions(Actions.translateBy(-Core.graphics.getWidth(), 0, 1.2f, Interp.pow3Out));
            }
        }));

        return true;
    }
}
