package yellow.ui.fragments;

import arc.math.*;
import arc.scene.*;
import arc.scene.actions.*;
import arc.scene.style.*;
import arc.scene.ui.layout.*;
import arc.util.*;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.ui.*;
import yellow.*;

public class NotificationPopupFragment{

    Table table;
    Drawable persistent;

    public void build(Group parent){
        parent.fill(s -> {
            table = s;
            s.name = "popup table";
            s.visible(() -> true);
            s.top().right();
            s.y = -100;
        });
        persistent = ((TextureRegionDrawable) Tex.whiteui).tint(Pal.accent.cpy().a(0.5f));
    }

    public void showNotification(String message){
        showNotification(Icon.info, message);
    }

    public void showNotification(String message, Runnable clicked){
        showNotification(Icon.info, message, clicked);
    }

    public void showNotification(Drawable icon, String message){
        showNotification(icon, message, () -> {});
    }

    public void showNotification(Drawable icon, String message, Runnable clicked){
        showNotification(icon, message, 150, 70, clicked);
    }

    public void showNotification(Drawable icon, String message, float minWidth, float minHeight, Runnable clicked){
        Cell<Table> t = table.table(Styles.black5).minHeight(minHeight).minWidth(minWidth);
        Table tr = t.get();
        t.right();
        tr.right();
        tr.image(icon).size(32).scaling(Scaling.fit).pad(15).padLeft(20);
        tr.add(message).grow().pad(10).padRight(8);
        float width = Math.max(tr.getMinWidth(), minWidth);
        tr.clicked(() -> {
            tr.hovered(() -> {});
            tr.exited(() -> {});
            tr.clicked(() -> {});
            tr.actions(Actions.sequence(
                    Actions.translateBy(width, 0, 1, Interp.pow3In),
                    Actions.run(() -> {
                        table.getCells().remove(t);
                        clicked.run();
                    }),
                    Actions.remove()
            ));
        });
        tr.hovered(() -> {
            if(tr.getActions().peek() instanceof SequenceAction s && s.getActions().peek() instanceof DelayAction) tr.getActions().clear();
        });
        tr.exited(() -> {
            if(tr.getActions().isEmpty()) tr.actions(Actions.sequence(
                    Actions.delay(YellowVars.getNotificationTime()),
                    Actions.translateBy(width, 0, 1, Interp.pow3In),
                    Actions.run(() -> table.getCells().remove(t)),
                    Actions.remove()
            ));
        });
        tr.setTranslation(width, 0);
        tr.actions(Actions.sequence(
                Actions.translateBy(-width, 0, 1, Interp.pow3Out),
                Actions.delay(YellowVars.getNotificationTime()),
                Actions.translateBy(width, 0, 1, Interp.pow3In),
                Actions.run(() -> table.getCells().remove(t)),
                Actions.remove()
        ));
        t.row();
    }

    public void showPersistentNotification(String message){
        showPersistentNotification(Icon.info, message);
    }

    public void showPersistentNotification(String message, Runnable clicked){
        showPersistentNotification(Icon.info, message, clicked);
    }

    public void showPersistentNotification(Drawable icon, String message){
        showPersistentNotification(icon, message, () -> {});
    }

    public void showPersistentNotification(Drawable icon, String message, Runnable clicked){
        showPersistentNotification(icon, message, 150, 70, clicked);
    }

    public void showPersistentNotification(Drawable icon, String message, float minWidth, float minHeight, Runnable clicked){
        Cell<Table> t = table.table(persistent).minHeight(minHeight).minWidth(minWidth);
        Table tr = t.get();
        t.right();
        tr.right();
        tr.image(icon).size(32).scaling(Scaling.fit).pad(15).padLeft(20);
        tr.add(message).grow().pad(10).padRight(8);
        float width = Math.max(tr.getMinWidth(), minWidth);
        tr.clicked(() -> {
            tr.hovered(() -> {});
            tr.exited(() -> {});
            tr.clicked(() -> {});
            tr.actions(Actions.sequence(
                    Actions.translateBy(width, 0, 1, Interp.pow3In),
                    Actions.run(() -> {
                        table.getCells().remove(t);
                        clicked.run();
                    }),
                    Actions.remove()
            ));
        });
        tr.setTranslation(width, 0);
        tr.actions(Actions.translateBy(-width, 0, 1, Interp.pow3Out));
        t.row();
    }

}
