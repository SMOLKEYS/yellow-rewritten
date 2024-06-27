package yellow.content;

import yellow.world.meta.*;
import yellow.world.meta.character.*;

@SuppressWarnings("SpellCheckingInspection")
public class YellowCharacters{

    //WIP

    public static GameCharacter
            //mc
            zen,
            //fmc
            nihara,
            //major side character (mc)
            enzie,
            //younger sister (fmc)
            ophelia,
            //personal maid + childhood friend (fmc)
            mykka,
            //parents (fmc)
            orson, rose;

    public static void load(){
        zen = new GameCharacter("zen");

        nihara = new AffectionCharacter("nihara");

        enzie = new AffectionCharacter("enzie");

        ophelia = new GameCharacter("ophelia");

        orson = new GameCharacter("orson");

        rose = new GameCharacter("rose");
    }
}
