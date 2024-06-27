package yellow.content;

import mindustry.entities.abilities.*;
import mindustry.type.*;
import yellow.ai.*;
import yellow.entities.abilities.*;
import yellow.entities.units.*;
import yellow.type.weapons.*;

@SuppressWarnings("SpellCheckingInspection")
public class YellowUnitTypes{

    public static UnitType yellow, ghostFlare,

    //WIP
    enverizence, oracle, guardian, attrition, symphony;

    public static void load(){
        yellow = new YellowUnitType(YellowCharacters.nihara, "yellow"){{
            health = 23000;
            mana = 5500;
            manaRecovery = 5;
            lives = 5;
            flying = true;
            hideDetails = false;
            armor = 10f;
            speed = 3f;
            accel = 0.08f;
            drag = 0.03f;
            range = 8*30f;
            maxRange = 8*30f;
            mineSpeed = 500f;
            mineTier = 500;
            itemCapacity = 850;
            buildSpeed = 950f;
            deathExplosionEffect = YellowFx.yellowDeathEffect;
            alwaysShootWhenMoving = true;
            faceTarget = true;
            allowedInPayloads = false;
            createScorch = false;
            createWreck = false;

            abilities.addAll(
                    new TeleportAbility(8 * 25f, 8 * 45f),
                    new RegenAbility(){{
                        amount = 3f;
                    }},
                    new ForceFieldAbility(8*3f, 0.25f, 1150f, 60 * 15f)
            );

            weapons.addAll(
                    YellowWeapons.laserBarrage, Mirrorer.reflect(YellowWeapons.laserBarrage),
                    YellowWeapons.bulletStorm,
                    YellowWeapons.homingFlares,
                    YellowWeapons.antiMothSpray, Mirrorer.reflect(YellowWeapons.antiMothSpray),
                    YellowWeapons.decimation, Mirrorer.reflect(YellowWeapons.decimation),
                    YellowWeapons.disruptor,
                    YellowWeapons.ghostCall, Mirrorer.reflect(YellowWeapons.ghostCall),
                    YellowWeapons.ghostRain, Mirrorer.reflect(YellowWeapons.ghostRain),
                    YellowWeapons.igneous, Mirrorer.reflect(YellowWeapons.igneous),
                    YellowWeapons.traversal,
                    YellowWeapons.octa
            );

            spells.add(YellowSpells.missileInverter);
        }};

        ghostFlare = new GhostUnitType("ghost-flare"){{
            flying = true;
            health = 37.5f;
            armor = 5f;
            speed = 5.5f;
            accel = 0.08f;
            drag = 0.01f;
            lifetime = 960f;

            controller = u -> new OrbiterAI(target -> target.type() == yellow); //target yellow by default
        }};
    }
}
