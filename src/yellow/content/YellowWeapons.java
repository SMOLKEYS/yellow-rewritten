package yellow.content;

import arc.*;
import arc.graphics.*;
import arc.math.*;
import mindustry.content.*;
import mindustry.entities.bullet.*;
import mindustry.entities.part.*;
import mindustry.entities.pattern.*;
import mindustry.gen.*;
import mindustry.graphics.*;
import yellow.entities.bullet.*;
import yellow.equality.*;
import yellow.type.weapons.*;

public class YellowWeapons{

    public static ToggleWeapon

            laserBarrage, bulletStorm, homingFlares, antiMothSpray, decimation, disruptor, ghostCall, ghostRain, igneous,
            traversal, octa, energySpheres, spearCall;

    public static void load(){
        laserBarrage = new ToggleWeapon("laser-barrage"){{
            x = 8*14;
            y = 0;
            reload = 60*5f;
            rotate = true;
            willMirror = true;
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
            predictTarget = false;
            ignoreRotation = true;

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
            predictTarget = false;
            ignoreRotation = true;

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
            willMirror = true;
            shoot.shots = 25;
            inaccuracy = 15f;

            shootSound = Sounds.none;

            bullet = new BasicEqualityBulletType(){{
                damage = 20f;
                lifetime = 60f;
                velocityRnd = 0.6f;
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
            willMirror = true;

            shootSound = Sounds.artillery;

            bullet = new BasicEqualityBulletType(){{
                damage = 2400f;
                splashDamage = 1540f;
                splashDamageRadius = 192f;
                lifetime = 420f;
                velocityRnd = 0.5f;
                speed = 3f;
                width = 16f;
                height = 16f;
                hitEffect = YellowFx.decimatorPortalExplosion;
                despawnEffect = YellowFx.decimatorPortalExplosion;
                pierceArmor = true;

                trailEffect = Fx.coreBurn;
                trailChance = 0.5f;

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
            }};
        }};

        disruptor = new ToggleWeapon("disruptor"){{
            reload = 600f;
            x = 0f;
            y = 0f;
            inaccuracy = 360f;
            predictTarget = false;
            ignoreRotation = true;

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
            willMirror = true;
            predictTarget = false;
            ignoreRotation = true;

            shoot.shots = 35;
        }};

        ghostRain = new ToggleWeapon("ghost-rain"){{
            reload = 30f;
            x = 80f;
            y = 0f;
            willMirror = true;
            ignoreRotation = true;

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
            willMirror = true;

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
            ignoreRotation = true;
            predictTarget = false;

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

        energySpheres = new ToggleWeapon("energy-spheres"){{
            reload = 60*2f;
            x = 8*20f;
            y = 0f;
            willMirror = true;

            shoot = new ShootSpread(5, 5f);

            shootSound = Sounds.bolt;

            bullet = new EqualityBulletType(5f, 240f){{
                lifetime = 60*5f;
                drag = -0.007f;
                hitSize = 12f;
                hitEffect = YellowFx.energySphereExplosion;
                status = StatusEffects.shocked;
                pierceArmor = true;

                lightning = 5;
                lightningLength = 5;
                lightningDamage = 120f;
                lightningColor = Pal.lancerLaser;
                lightningType = new LightningBulletType();

                trailEffect = Fx.trailFade;
                trailColor = Pal.lancerLaser;
                trailLength = 20;
                trailWidth = 5f;
                lightOpacity = 1f;
                lightColor = Pal.lancerLaser;
                homingRange = 8*50f;
                homingPower = 0.01f;

                splashDamage = 200f;
                splashDamageRadius = 8*8f;

                parts.add(new ShapePart(){{
                    circle = true;
                    radius = 15f;
                    color = Pal.lancerLaser;
                }});
            }};
        }};

        spearCall = new ToggleWeapon("spear-call"){{
            reload = 3.5f;
            x = 0f;
            y = 8*10f;
            xRand = 8*120f;
            willMirror = true;
            ignoreRotation = true;
            predictTarget = false;

            properties = new Mirror.ReflectProperty[]{
                    Mirror.ReflectProperty.flipY
            };

            shootSound = Sounds.pulseBlast;

            bullet = new SpinSpearBulletType(){{
                damage = 80f;
                speed = 0.5f;
                drag = -0.03f;
                lifetime = 60*4f;
                launchTime = 60f;
                spinInterp = Interp.pow3Out;
                height = 75f;
                pierce = true;
                pierceCap = 3;
                pierceArmor = true;
                keepVelocity = false;

                sprite = "flare";
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
