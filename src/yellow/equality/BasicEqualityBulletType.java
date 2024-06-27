package yellow.equality;

import arc.util.*;
import mindustry.entities.*;
import mindustry.entities.bullet.*;
import mindustry.gen.*;
import yellow.util.*;

public class BasicEqualityBulletType extends BasicBulletType{

    public BasicEqualityBulletType(float speed, float damage, String bulletSprite){
        super(speed, damage, bulletSprite);
    }

    public BasicEqualityBulletType(float speed, float damage){
        super(speed, damage);
    }

    public BasicEqualityBulletType(){
        super();
    }

    @Override
    public void hitEntity(Bullet b, Hitboxc entity, float health){
        super.hitEntity(b, entity, health);

        if(entity instanceof Healthc h && SafeSettings.getBool("yellow-equal-treatment", false, false)){
            Structs.each(v -> {
                if(EqualityEntries.hasEntry(h, v)) SafeReflect.set(h, v, h.health() - b.damage);
            }, EqualityEntries.ent);
        }
    }

    @Override
    public void createSplashDamage(Bullet b, float x, float y){
        super.createSplashDamage(b, x, y);

        if(splashDamageRadius > 0 && !b.absorbed){
            Units.nearbyEnemies(b.team, x, y, splashDamageRadius, en -> {
                Structs.each(v -> {
                    if(EqualityEntries.hasEntry(en, v)) SafeReflect.set(en, v, en.health() - b.damage);
                }, EqualityEntries.ent);
            });
        }
    }
}
