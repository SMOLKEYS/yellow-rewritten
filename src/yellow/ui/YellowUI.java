package yellow.ui;

import arc.*;
import arc.scene.*;
import arc.scene.event.*;
import arc.scene.ui.layout.*;
import yellow.ui.fragments.*;

import static mindustry.Vars.*;

public class YellowUI{

    public WidgetGroup multiGroup;

    public NotificationPopupFragment notifrag;
    public FirstLoadFragment firstfrag;

    public void init(){
        notifrag = new NotificationPopupFragment();
        firstfrag = new FirstLoadFragment();

        multiGroup = new WidgetGroup();

        multiGroup.setFillParent(true);
        multiGroup.touchable = Touchable.childrenOnly;
        multiGroup.visible(() -> true);

        Core.scene.add(multiGroup);

        notifrag.build(multiGroup);
        firstfrag.build(ui.menuGroup);
    }
}
