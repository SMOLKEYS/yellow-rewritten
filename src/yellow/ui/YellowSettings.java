package yellow.ui;

import arc.*;
import arc.func.*;
import arc.graphics.*;
import arc.scene.style.*;
import arc.scene.ui.*;
import arc.util.*;
import mindustry.*;
import mindustry.gen.*;
import mindustry.ui.dialogs.*;
import yellow.*;

public class YellowSettings{

    public static void load(){
        Vars.ui.settings.addCategory("Yellow", Tex.alphaaaa, t -> {

            seperatorPref(t, "yellow-general-section");

            t.sliderPref("yellow-notification-time", 5, 3, 60, 1, s -> Core.bundle.format("setting.yellow-notification-time.text", s));

            t.sliderPref("yellow-tip-time", 60*60*5, 60*60, 60*60*30, 60*60, s -> Core.bundle.format("setting.yellow-tip-time.text", s/60/60, s == 60*60 ? "minute" : "minutes"));

            t.checkPref("yellow-enable-failsafe", true);

            t.checkPref("yellow-enable-special-notifications", true);

            seperatorPref(t, "yellow-typeio-section", Icon.download, Icon.upload);

            t.checkPref("yellow-toggle-read-method", true);

            t.checkPref("yellow-spell-read-method", true);

            seperatorPref(t, "yellow-startup-section", Icon.wrench, Icon.save);

            t.checkPref("yellow-autoassign-save-ids", true);

            seperatorPref(t, "yellow-updating-section");

            t.checkPref("yellow-check-for-updates", true);

            labelPref(t, "yellow-update-server", s -> Formatter.format(s.name, YellowVars.getUpdateServer()));

            buttonPref(t, "yellow-change-update-server", () -> Vars.ui.showTextInput("@setting.yellow-change-update-server.name", "@setting.yellow-change-update-server.text", 256, YellowVars.getUpdateServer(), YellowVars::setUpdateServer));
        });
    }

    public static void buttonPref(SettingsMenuDialog.SettingsTable target, String name, Runnable clicked){
        target.pref(new ButtonSetting(name, clicked));
    }

    public static void labelPref(SettingsMenuDialog.SettingsTable target, String name, Func<SettingsMenuDialog.SettingsTable.Setting, CharSequence> supplier){
        target.pref(new LabelSetting(name, supplier));
    }

    public static void labelPref(SettingsMenuDialog.SettingsTable target, String name, boolean wrap, Func<SettingsMenuDialog.SettingsTable.Setting, CharSequence> supplier){
        target.pref(new LabelSetting(name, wrap, supplier));
    }

    public static void seperatorPref(SettingsMenuDialog.SettingsTable target, String name){
        target.pref(new NamedSeperatorSetting(name));
    }

    public static void seperatorPref(SettingsMenuDialog.SettingsTable target, String name, TextureRegionDrawable leftIcon, TextureRegionDrawable rightIcon){
        target.pref(new NamedSeperatorSetting(name, leftIcon, rightIcon));
    }

    public static class ButtonSetting extends SettingsMenuDialog.SettingsTable.Setting{
        public Runnable clicked;

        public ButtonSetting(String name){
            super(name);
        }

        public ButtonSetting(String name, Runnable clicked){
            super(name);
            this.clicked = clicked;
        }

        @Override
        public void add(SettingsMenuDialog.SettingsTable table){
            TextButton b = new TextButton(title);
            b.clicked(clicked);
            table.add(b).minHeight(30).growX().row();
        }
    }

    public static class LabelSetting extends SettingsMenuDialog.SettingsTable.Setting{
        public Func<SettingsMenuDialog.SettingsTable.Setting, CharSequence> supplier;
        public boolean wrap = true;


        private LabelSetting(String name){
            super(name);
        }

        public LabelSetting(String name, Func<SettingsMenuDialog.SettingsTable.Setting, CharSequence> supplier){
            super(name);
            this.supplier = supplier;
        }

        public LabelSetting(String name, boolean wrap, Func<SettingsMenuDialog.SettingsTable.Setting, CharSequence> supplier){
            super(name);
            this.supplier = supplier;
            this.wrap = wrap;
        }

        @Override
        public void add(SettingsMenuDialog.SettingsTable table){
            Label l = new Label("");
            l.setWrap(wrap);
            l.update(() -> {
                l.setText(supplier.get(this));
            });
            table.add(l).growX().row();
        }
    }


    public static class NamedSeperatorSetting extends SettingsMenuDialog.SettingsTable.Setting{
        public TextureRegionDrawable leftIcon, rightIcon;

        public NamedSeperatorSetting(String name){
            super(name);
        }

        public NamedSeperatorSetting(String name, TextureRegionDrawable leftIcon, TextureRegionDrawable rightIcon){
            super(name);
            this.leftIcon = leftIcon;
            this.rightIcon = rightIcon;
        }

        @Override
        public void add(SettingsMenuDialog.SettingsTable table){
            table.table(t -> {
                t.center();
                if(leftIcon != null) t.image(() -> leftIcon.getRegion()).size(32f).scaling(Scaling.fit);
                t.add(title).growX().labelAlign(Align.center);
                if(rightIcon != null){
                    t.image(() -> rightIcon.getRegion()).size(32f).scaling(Scaling.fit);
                }else{
                    t.row();
                }
            }).growX().padTop(10f).row();
            table.image().color(Color.gray).height(5f).padTop(5f).padBottom(8f).growX().row();
        }
    }

    private static class Formatter{

        public static String format(String name, Object... objs){
            return Core.bundle.format("setting." + name + ".text", objs);
        }
    }
}
