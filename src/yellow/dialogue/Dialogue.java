package yellow.dialogue;

import arc.util.*;
import arc.util.pooling.*;
import yellow.world.meta.*;

//TODO
/** The root class of all dialogue types. Supports standard operations like changing text, changing active characters
 * (3 max), changing character display status (changing position, showing and hiding), and showing options. */
public class Dialogue<T> implements Pool.Poolable{
    protected Pool<Dialogue<?>> pool;

    /** The text to be typed out. If null, previous text will not be removed. */
    public @Nullable String text;
    /** The currently talking character. If null, the previously talking character will be reused. */
    public @Nullable GameCharacter character = GameCharacter.empty;

    public Pool<Dialogue<?>> getPool(){
        return pool;
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    public void setPool(Pool pool){
        this.pool = pool;
    }

    @Override
    public void reset(){
        pool = null;
    }
}
