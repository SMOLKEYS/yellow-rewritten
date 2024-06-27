package yellow.content;

import arc.*;
import arc.graphics.*;
import mindustry.content.*;
import mindustry.entities.bullet.*;
import mindustry.entities.part.*;
import mindustry.entities.pattern.*;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.type.*;
import yellow.entities.bullet.*;
import yellow.equality.*;
import yellow.type.weapons.*;

public class YellowWeapons{

    public static ToggleWeapon

            laserBarrage, bulletStorm, homingFlares, antiMothSpray, decimation, disruptor, ghostCall, ghostRain, igneous,
            traversal, octa;

    public static void load(){
        laserBarrage = new ToggleWeapon("laser-barrage"){{
            x = 8*14;
            y = 0;
            reload = 60*5f;
            rotate = true;
            inaccuracy = 10f;
            velocityRnd = 0.9f;

            shootSound = Sounds.shootBig;

            shoot.shots = 20;
            shoot.shotDelay = 2f;

            bullet = new ContinuousEqualityLaserBulletType(85){{
                speed = 40f;
                drag = 0.04f;
                length = 200f;
                lifetime = 60*3.5f;
                hitEffect = Fx.hitMeltdown;
                hitColor = Pal.meltdownHit;
                status = StatusEffects.melting;
                drawSize = 420f;
                pierceArmor = true;

                incendChance = 0.4f;
                incendSpread = 5f;
                incendAmount = 1;
            }};
        }};

        bulletStorm = new ToggleWeapon("bulletstorm"){{
            x = y = 0f;
            reload = 60*15;

            shootSound = Sounds.none;

            shoot = new ShootSpread(){{
                shotDelay = 1;
                spread = 25;
                shots = 60*8;
            }};

            bullet = new BasicEqualityBulletType(){{
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
                keepVelocity = false;
            }};
        }};

        homingFlares = new ToggleWeapon("homing-flares"){{
            x = y = 0f;
            reload = 90f;
            rotate = true;

            shootSound = Sounds.missileLaunch;

            shoot = new ShootSpread(15, 10);

            bullet = new BasicEqualityBulletType(){{
                speed = 3f;
                damage = 350f;
                lifetime = 280f;
                width = height = 16;
                shrinkX = shrinkY = 0;

                homingDelay = 60;
                homingRange = 8*20f;
                homingPower = 0.09f;

                trailEffect = Fx.trailFade;
                trailLength = 20;

                splashDamage = 150f;
                splashDamageRadius = 8*3;

                hitSound = Sounds.explosion;
                hitEffect = despawnEffect = Fx.blastExplosion;

                sprite = "yellow-old-flare";
            }};
        }};

        antiMothSpray = new ToggleWeapon("anti-moth-spray"){{
            reload = 2f;
            x = 3f;
            mirror = false;
            shoot.shots = 25;
            inaccuracy = 15f;

            shootSound = Sounds.none;

            bullet = new BasicEqualityBulletType(){{
                damage = 20f;
                lifetime = 60f;
                speed = 4f;
                width = 8f;
                height = 8f;
                knockback = 5f;
            }};
        }};

        decimation = new ToggleWeapon("decimation"){{
            reload = 300f;
            x = 48f;
            shoot.shots = 8;
            inaccuracy = 35f;

            shootSound = Sounds.artillery;

            bullet = new BasicEqualityBulletType(){{
                damage = 1100f;
                splashDamage = 990f;
                splashDamageRadius = 192f;
                lifetime = 420f;
                speed = 3f;
                width = 16f;
                height = 16f;
                hitEffect = YellowFx.yellowExplosionOut;
                despawnEffect = YellowFx.yellowExplosionOut;
                pierceArmor = true;

                parts.add(
                        new ShapePart(){{
                            sides = 6;
                            radius = 10f;
                            layer = Layer.bullet;
                            rotateSpeed = 4f;
                            color = colorTo = Color.yellow;

                        }},
                        new ShapePart(){{
                            sides = 3;
                            radius = 15f;
                            hollow = true;
                            layer = Layer.bullet;
                            rotateSpeed = 3f;
                            color = colorTo = Color.yellow;
                        }}
                );
            }
                @Override
                public void draw(Bullet b){
                    super.draw(b);
                    /*
                    Draw.z(Layer.effect);
                    Draw.color(Color.yellow);
                    Lines.square(b.x, b.y, 15, Time.time * 2);
                    Lines.square(b.x, b.y, 15, -Time.time * 2);
                    Fill.circle(b.x, b.y, Mathf.sin(Time.time * 0.1f) * 1 + 4);
                    */
                }
            };
        }};

        disruptor = new ToggleWeapon("disruptor"){{
            reload = 600f;
            x = 0f;
            y = 0f;
            inaccuracy = 360f;

            shoot.shots = 350;

            bullet = new HarshPierceBulletType(15f, 130f, 10f){{
                lifetime = 600f;
                drag = 0.003f;
                weaveMag = 3f;
                weaveScale = 300f;
                hitSize = 12f;
                trailEffect = Fx.trailFade;
                trailLength = 3;
                pierceArmor = true;

                parts.add(new FlarePart(){{
                    followRotation = true;
                    radius = 20f;
                    sides = 4;
                }});
            }};
        }};

        ghostCall = new ToggleWeapon("ghost-call"){{
            reload = 240f;
            x = 24f;
            y = 0f;

            shoot.shots = 35;
        }};

        ghostRain = new ToggleWeapon("ghost-rain"){{
            reload = 30f;
            x = 80f;
            y = 0f;

            inaccuracy = 360f;

            shoot.shots = 20;
            shoot.shotDelay = 5;

            bullet = new ThrowBulletType(){{
                speed = 10f;
                drag = 0.1f;
                lifetime = 240f;
                damage = 40f;
                width = 12f;
                height = 12f;
                pierceArmor = true;
                hitEffect = YellowFx.ghostDespawnMulti;
                despawnEffect = YellowFx.ghostDespawnMulti;
                backRegion = Core.atlas.find("flare");
                frontRegion = Core.atlas.find("flare");
                sprite = "flare";
                shrinkX = 0f;
                shrinkY = 0f;
            }};
        }};

        igneous = new ToggleWeapon("igneous"){{
            reload = 60f;
            x = 40f;
            y = 0f;

            shootSound = Sounds.bolt;

            shoot = new ShootSpread(){{
                shots = 7;
                spread = 4f;
            }};

            bullet = new BasicEqualityBulletType(){{
                damage = 15f;
                speed = 5f;
                lifetime = 180f;
                status = StatusEffects.burning;
                trailChance = 0.8f;
                trailEffect = Fx.fire;
                pierce = true;
                pierceArmor = true;
                homingRange = 80f;
                homingPower = 0.01f;
                width = height = 16f;
                hitEffect = Fx.fireHit;
            }
                @Override
                public void hitEntity(Bullet b, Hitboxc entity, float health) {
                    super.hitEntity(b, entity, health);
                    if(entity instanceof Statusc un){
                        if(un.hasEffect(StatusEffects.burning)) un.apply(StatusEffects.melting, 30f);
                    }
                }
            };
        }};

        traversal = new ToggleWeapon("traversal"){{
            enabledDefault = false;
            rotate = false;
            baseRotation = 180f;
            shootCone = 360f;
            x = y = 0f;
            alwaysContinuous = true;

            shootSound = Sounds.pulse;

            bullet = new ContinuousEqualityFlameBulletType(){{
                recoil = -0.54f;
                damage = 60f;
                knockback = 50f;

                flareColor = Color.white;
                colors = new Color[]{Color.white, Color.yellow, Color.purple, Color.black};
            }};
        }};

        octa = new ToggleWeapon("octa"){{
            enabledDefault = true;
            reload = 60*8f;
            x = y = 0f;

            shoot = new ShootSpread(8, 22.5f);

            shootSound = Sounds.laserblast;

            bullet = new RotatingAnchoredContinuousLaserBulletType(120f){{
                length = 530f;
                lifetime = 60*5f;
                hitEffect = Fx.hitMeltdown;
                hitColor = Pal.meltdownHit;
                status = StatusEffects.melting;
                drawSize = 420f;
                pierceArmor = true;
                rotateSpeed = 0.4f;

                incendChance = 0.4f;
                incendSpread = 5f;
                incendAmount = 1;
            }};
        }};
    }

    public static void afterLoad(){
        BulletType bul = new BulletType();
        bul.spawnUnit = YellowUnitTypes.ghostFlare;
        bul.load();
        ghostCall.bullet = bul;
        ghostCall.mirrored.bullet = bul;
    }
}
