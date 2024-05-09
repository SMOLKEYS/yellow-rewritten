package yellow.ui;

import arc.*;
import arc.scene.event.*;
import arc.scene.ui.layout.*;
import yellow.ui.dialogs.*;
import yellow.ui.fragments.*;

import static mindustry.Vars.*;

public class YellowUI{

    public WeaponManagerDialog weaponManager;

    public WidgetGroup multiGroup;

    public NotificationFragment notifrag;
    public FirstLoadFragment firstfrag;
    public BlankFragment blankfrag;

    public void init(){
        weaponManager = new WeaponManagerDialog();

        notifrag = new NotificationFragment();
        firstfrag = new FirstLoadFragment();
        blankfrag = new BlankFragment();

        multiGroup = new WidgetGroup();

        multiGroup.setFillParent(true);
        multiGroup.touchable = Touchable.childrenOnly;
        multiGroup.visible(() -> true);

        Core.scene.add(multiGroup);

        notifrag.build(multiGroup);
        firstfrag.build(ui.menuGroup);
        blankfrag.build(multiGroup);
    }
}
