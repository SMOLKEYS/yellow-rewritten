package yellow.game;

import arc.*;
import arc.flabel.*;
import arc.scene.style.*;
import arc.struct.*;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.ui.dialogs.*;
import yellow.*;

@SuppressWarnings("ALL")
public class YellowSpecialNotifications{

    public static Seq<Trio> nots = Seq.with(
            Trio.with(Icon.download.tint(Pal.accent), Core.bundle.get("yellow.specnof-fos-notif"), () -> {
                BaseDialog d = new BaseDialog("@yellow.specnof-fos-title");
                d.addCloseButton();

                d.cont.table(v -> {
                    v.add(new FLabel("@yellow.specnof-fos-1")).row();
                    v.add(new FLabel("@yellow.specnof-fos-2")).row();
                    v.add(new FLabel("@yellow.specnof-fos-3")).row();
                }).growX().pad(40f).row();
                d.cont.add("@yellow.specnof-fos-tryout").row();
                d.cont.button("@yellow.specnof-fos-repo", () -> Core.app.openURI("https://github.com/TeamOct/fictional-octo-system")).wrapLabel(false).row();



                d.show();
            })
    );

    public static void launchNotif(){
        if(!Core.settings.getBool("yellow-enable-special-notifications", true)) return;
        Trio t = nots.random();

        YellowVars.ui.notifrag.showPersistentNotification(t.a, t.b, t.c);
    }


    public static class Trio{
        public Drawable a;
        public String b;
        public Runnable c;

        public Trio(Drawable a, String b, Runnable c){
            this.a = a;
            this.b = b;
            this.c = c;
        }

        public static Trio with(Drawable a, String b, Runnable c){
            return new Trio(a, b, c);
        }
    }
}