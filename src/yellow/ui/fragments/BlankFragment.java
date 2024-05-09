package yellow.ui.fragments;

import arc.scene.*;
import arc.scene.event.*;
import arc.scene.ui.layout.*;
import mindustry.ui.*;
import yellow.ui.*;

public class BlankFragment implements CommonFragment{

    public Table table;

    @Override
    public void build(Group parent){
        parent.fill(s -> {
            s.background(Styles.black);
            s.visible = false;
            s.touchable = Touchable.childrenOnly;
            table = s;
        });
    }

    public void show(){
        table.visible = true;
    }

    public void hide(){
        table.visible = false;
    }
}