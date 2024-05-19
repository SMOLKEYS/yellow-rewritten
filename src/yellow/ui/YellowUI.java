package yellow.ui;

import arc.*;
import arc.scene.event.*;
import arc.scene.ui.layout.*;
import yellow.ui.dialogs.*;
import yellow.ui.fragments.*;

import static mindustry.Vars.*;

public class YellowUI{

    public ManagerDialog manager;

    public WidgetGroup multiGroup;

    public NotificationFragment notifrag;
    public FirstLoadFragment firstfrag;
    public BlankFragment blankfrag;
    public DialogueFragment dialfrag;

    public void init(){
        manager = new ManagerDialog();

        notifrag = new NotificationFragment();
        firstfrag = new FirstLoadFragment();
        blankfrag = new BlankFragment();
        dialfrag = new DialogueFragment();

        multiGroup = new WidgetGroup();

        multiGroup.setFillParent(true);
        multiGroup.touchable = Touchable.childrenOnly;
        multiGroup.visible(() -> true);

        Core.scene.add(multiGroup);

        notifrag.build(multiGroup);
        firstfrag.build(ui.menuGroup);
        blankfrag.build(multiGroup);
        dialfrag.build(multiGroup);
    }
}
