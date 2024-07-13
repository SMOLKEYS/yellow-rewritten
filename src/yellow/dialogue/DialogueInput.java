package yellow.dialogue;

import arc.struct.*;
import yellow.cutscene.*;

//TODO
/** Manages all major interaction components, like dialogue and cutscenes. */
@SuppressWarnings("unused")
public class DialogueInput{
    public Queue<Dialogue<?>> dialogueQueue;
    public Queue<CutsceneController<?>> controllerQueue;
    public int progress;
}
