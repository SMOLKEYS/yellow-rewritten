package yellow.ui;

import arc.*;
import arc.scene.event.*;
import arc.scene.ui.layout.*;
import yellow.ui.dialogs.*;
import yellow.ui.fragments.*;

import static mindustry.Vars.*;

public class YellowUI{

    public WeaponManagerDialog weaponManager;
    public SpellManagerDialog spellManager;
    public ExtensionsDialog extensions;

    public WidgetGroup multiGroup;

    public NotificationFragment notifrag;
    public FirstLoadFragment firstfrag;
    public BlankFragment blankfrag;
    public ManagerFragment managefrag;

    //interactions
    public BackgroundFragment backfrag;
    public CharacterDisplayFragment chdispfrag;
    public CutsceneCurtainFragment curtfrag;
    public DialogueBoxFragment boxfrag;
    public InteractionBoxFragment iboxfrag;

    public void init(){
        weaponManager = new WeaponManagerDialog();
        spellManager = new SpellManagerDialog();
        extensions = new ExtensionsDialog();

        notifrag = new NotificationFragment();
        firstfrag = new FirstLoadFragment();
        blankfrag = new BlankFragment();
        managefrag = new ManagerFragment();

        backfrag = new BackgroundFragment();
        chdispfrag = new CharacterDisplayFragment();
        curtfrag = new CutsceneCurtainFragment();
        boxfrag = new DialogueBoxFragment();
        iboxfrag = new InteractionBoxFragment();

        multiGroup = new WidgetGroup();

        multiGroup.setFillParent(true);
        multiGroup.touchable = Touchable.childrenOnly;
        multiGroup.visible(() -> true);

        Core.scene.add(multiGroup);

        notifrag.build(multiGroup);
        firstfrag.build(ui.menuGroup);
        blankfrag.build(multiGroup);
        managefrag.build(ui.hudGroup);
    }
}
