package yellow.entities.bullet;

import arc.math.*;
import arc.util.pooling.*;
import mindustry.gen.*;
import yellow.comp.*;
import yellow.equality.*;

public class SpinSpearBulletType extends BasicEqualityBulletType{

    public float launchTime;
    public Interp spinInterp = Interp.linear;

    public SpinSpearBulletType(float speed, float damage, String bulletSprite){
        super(speed, damage, bulletSprite);
    }

    public SpinSpearBulletType(float speed, float damage){
        super(speed, damage);
    }

    public SpinSpearBulletType(){
        super();
    }


    @Override
    public void init(Bullet b){
        super.init(b);
        SpearBulletData d = Pools.obtain(SpearBulletData.class, SpearBulletData::new);
        b.lifetime(99999999);
        b.drag(0);
        b.vel().setZero();
        b.rotation(d.startRotation = b.rotation() + Mathf.random(470, 750));
        d.targetRotation = Angles.angle(b.x, b.y, b.aimX, b.aimY);
        b.data(d);
    }

    @Override
    public void update(Bullet b){
        super.update(b);

        if(b.data() instanceof SpearBulletData s){
            float p = Mathf.clamp(b.time / launchTime);
            b.rotation(Mathf.lerp(s.startRotation, s.targetRotation, spinInterp.apply(p)));

            if(p >= 1f){
                b.time(0f);
                b.vel().trns(s.targetRotation, speed);
                b.lifetime(lifetime);
                b.drag(drag);
                s.scrap(b);
            }
        }
    }



    //THIS FUCKING WORKED FIRST TRY HOLY SHSHSHSHSHHIIIIIIIITTTTT
    public static class SpearBulletData implements Pool.Poolable, Scrapped<Bullet>{
        public float targetRotation, startRotation;

        public SpearBulletData(){

        }

        @Override
        public void reset(){
            targetRotation = startRotation = 0f;
        }

        @Override
        public void scrap(Bullet bullet){
            bullet.data = null;
            this.reset();
        }
    }
}
