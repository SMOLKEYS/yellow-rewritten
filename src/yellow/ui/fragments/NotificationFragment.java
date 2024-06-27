package yellow.ui.fragments;

import arc.*;
import arc.func.*;
import arc.graphics.*;
import arc.math.*;
import arc.scene.*;
import arc.scene.actions.*;
import arc.scene.style.*;
import arc.scene.ui.*;
import arc.scene.ui.layout.*;
import arc.util.*;
import mindustry.*;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.ui.*;
import yellow.*;
import yellow.ui.*;

public class NotificationFragment implements CommonFragment{

    private Table table;
    private Drawable persistent, error;
    private Label.LabelStyle lstyle;
    private float notifWidth = Core.graphics.getWidth() / 3f;

    @Override
    public void build(Group parent){
        parent.fill(s -> {
            table = s;
            s.name = "notifications";
            s.visible(() -> true);
            s.top().right();
            s.y = -100;
        });
        persistent = ((TextureRegionDrawable) Tex.whiteui).tint(Pal.accent.cpy().a(0.5f));
        error = ((TextureRegionDrawable) Tex.whiteui).tint(Pal.remove.cpy().a(0.5f));
        lstyle = Styles.outlineLabel;
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
        showNotification(icon, message, 70, clicked);
    }

    public void showNotification(Drawable icon, String message, float minHeight, Runnable clicked){
        Cell<Table> t = table.table(Styles.black5).minHeight(minHeight).width(notifWidth);
        Table tr = t.get();
        t.right();
        tr.right();
        tr.image(icon).size(32).scaling(Scaling.fit).pad(15).padLeft(20);
        tr.labelWrap(message).style(lstyle).grow().pad(10).padRight(8);
        float width = Math.max(tr.getMinWidth(), notifWidth);
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
        showPersistentNotification(icon, message, 70, clicked);
    }

    public void showPersistentNotification(Drawable icon, String message, float minHeight, Runnable clicked){
        Cell<Table> t = table.table(persistent).minHeight(minHeight).width(notifWidth);
        Table tr = t.get();
        t.right();
        tr.right();
        tr.image(icon).size(32).scaling(Scaling.fit).pad(15).padLeft(20);
        tr.labelWrap(message).style(lstyle).grow().pad(10).padRight(8);
        float width = Math.max(tr.getMinWidth(), notifWidth);
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

    public void showCustomNotification(Cons<Table> cons){
        Cell<Table> t = table.table(Styles.black5).minHeight(70).minWidth(notifWidth);
        Table tr = t.get();
        t.right();
        try{
            cons.get(tr);
        }catch(Exception e){
            //man
        }
        float width = Math.max(tr.getMinWidth(), notifWidth);
        tr.clicked(() -> {
            tr.hovered(() -> {});
            tr.exited(() -> {});
            tr.clicked(() -> {});
            tr.actions(Actions.sequence(
                    Actions.translateBy(width, 0, 1, Interp.pow3In),
                    Actions.run(() -> {
                        table.getCells().remove(t);
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

    public void showCustomPersistentNotification(Cons<Table> cons){
        Cell<Table> t = table.table(persistent).minHeight(70).width(Core.graphics.getWidth() / 4f);
        Table tr = t.get();
        t.right();
        try{
            cons.get(tr);
        }catch(Exception e){
            //man...
        }
        if(!tr.hasChildren()) tr.image(Icon.trash).size(32).scaling(Scaling.fit);
        float width = Math.max(tr.getMinWidth(), 150);
        tr.clicked(() -> {
            tr.hovered(() -> {
            });
            tr.exited(() -> {
            });
            tr.clicked(() -> {
            });
            tr.actions(Actions.sequence(
                    Actions.translateBy(width, 0, 1, Interp.pow3In),
                    Actions.run(() -> {
                        table.getCells().remove(t);
                    }),
                    Actions.remove()
            ));
        });
        tr.setTranslation(width, 0);
        tr.actions(Actions.translateBy(-width, 0, 1, Interp.pow3Out));
        t.row();
    }

    // --- NEAR-EXACT COPY OF ABOVE METHODS ---

    public void showErrorNotification(String message){
        showErrorNotification(Icon.warning, message);
    }

    public void showErrorNotification(String message, Throwable throwable){
        showErrorNotification(Icon.warning, throwable, message);
    }

    public void showErrorNotification(String message, Runnable clicked){
        showErrorNotification(Icon.warning, message, clicked);
    }

    public void showErrorNotification(String message, Throwable throwable, Runnable clicked){
        showErrorNotification(Icon.warning, message, () -> {
            Vars.ui.showException(message, throwable);
            clicked.run();
        });
    }

    public void showErrorNotification(Drawable icon, String message){
        showErrorNotification(icon, message, () -> {});
    }

    public void showErrorNotification(Drawable icon, Throwable throwable, String message){
        showErrorNotification(icon, message, () -> Vars.ui.showException(message, throwable));
    }

    public void showErrorNotification(Drawable icon, String message, Runnable clicked){
        showErrorNotification(icon, message, 70, clicked);
    }

    public void showErrorNotification(Drawable icon, Throwable throwable, String message, Runnable clicked){
        showErrorNotification(icon, message, 70, () -> {
            Vars.ui.showException(message, throwable);
            clicked.run();
        });
    }

    public void showErrorNotification(Drawable icon, String message, float minHeight, Runnable clicked){
        Cell<Table> t = table.table(error).minHeight(minHeight).width(notifWidth);
        Table tr = t.get();
        t.right();
        tr.right();
        tr.image(icon).size(32).scaling(Scaling.fit).pad(15).padLeft(20);
        tr.labelWrap(message).style(lstyle).grow().pad(10).padRight(8);
        float width = Math.max(tr.getMinWidth(), notifWidth);
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
