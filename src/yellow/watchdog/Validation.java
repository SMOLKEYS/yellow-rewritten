package yellow.watchdog;

import arc.func.*;
import arc.math.*;
import arc.util.*;
import arc.util.pooling.*;
import mindustry.*;
import mindustry.gen.*;
import yellow.entities.bullet.*;
import yellow.game.*;

import static yellow.entities.bullet.SpinSpearBulletType.*;

/** Checks states of content associated with Yellow. */
public class Validation{

    public static void handleBullet(Class<?> type, Cons<Bullet> found){
        Groups.bullet.each(inst -> inst.type.getClass() == type, found);
    }

    public static void validateSpearBullets(){
        if(Vars.state.isMenu()) return;

        handleBullet(SpinSpearBulletType.class, b -> {
            if(b.lifetime > b.type.lifetime && b.data == null){
                SpearBulletData d = Pools.obtain(SpearBulletData.class, SpearBulletData::new);
                b.lifetime(99999999);
                b.drag(0);
                b.vel().setZero();
                b.rotation(d.startRotation = b.rotation() + Mathf.random(470, 750));
                d.targetRotation = Angles.angle(b.x, b.y, b.aimX, b.aimY);
                b.data(d);
            }
        });
    }

    public static <T> void nullPass(@Nullable T t, Cons<T> ifNotNull){
        if(t != null) ifNotNull.get(t);
    }

}
