package yellow.content;

import mindustry.content.*;
import mindustry.entities.bullet.*;
import mindustry.entities.pattern.*;
import mindustry.gen.*;
import mindustry.graphics.*;
import yellow.type.weapons.*;

public class YellowWeapons{

    public static ToggleWeapon
            durability, endurance, fist;

    public static void load(){
        durability = new ToggleWeapon("durability"){{
            x = 8*14;
            y = 0;
            reload = 60*4.5f;
            rotate = true;

            shootSound = Sounds.shootBig;

            shoot = new ShootSpread(10, 5);

            bullet = new ContinuousLaserBulletType(78){{
                length = 200f;
                lifetime = 60*1.8f;
                hitEffect = Fx.hitMeltdown;
                hitColor = Pal.meltdownHit;
                status = StatusEffects.melting;
                drawSize = 420f;

                incendChance = 0.4f;
                incendSpread = 5f;
                incendAmount = 1;
                ammoMultiplier = 1f;
            }};
        }};
        Mirrorer.reflect(durability);

        endurance = new ToggleWeapon("endurance"){{
            x = y = 0f;
            reload = 60*15;

            shootSound = Sounds.none;

            shoot = new ShootSpread(){{
                shotDelay = 1;
                spread = 25;
                shots = 60*8;
            }};

            bullet = new BasicBulletType(){{
                damage = 95;
                width = 12;
                height = 12;
                lifetime = 60*3;
                speed = 6;
                sprite = "yellow-old-flare";
                trailEffect = Fx.trailFade;
                trailLength = 4;
                shrinkX = shrinkY = 0;
                weaveMag = 1.205f;
                pierce = true;
                pierceBuilding = true;
                buildingDamageMultiplier = 2.55f;
                pierceCap = 35;
            }};
        }};

        fist = new ToggleWeapon("fist"){{
            x = y = 0f;
            reload = 90f;
            rotate = true;

            shootSound = Sounds.missileLaunch;

            shoot = new ShootSpread(15, 10);

            bullet = new BasicBulletType(){{
                speed = 3f;
                damage = 500f;
                lifetime = 280f;
                width = height = 16;
                shrinkX = shrinkY = 0;

                homingDelay = 60;
                homingRange = 8*20f;
                homingPower = 0.09f;

                trailEffect = Fx.trailFade;
                trailLength = 20;

                splashDamage = 350f;
                splashDamageRadius = 8*3;

                hitSound = Sounds.explosion;
                hitEffect = despawnEffect = Fx.explosion;

                sprite = "yellow-old-flare";
            }};
        }};
    }
}
