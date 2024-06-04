package yellow.content;

import mindustry.type.*;
import yellow.entities.units.*;
import yellow.type.abilities.*;
import yellow.type.weapons.*;
import yellow.world.meta.*;

public class YellowUnitTypes{

    public static UnitType yellow;

    public static void load(){
        yellow = new YellowUnitType(new GameCharacter("nihara"), "yellow"){{
            health = 23000;
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
            alwaysShootWhenMoving = true;
            faceTarget = true;
            allowedInPayloads = false;
            createScorch = false;
            createWreck = false;

            abilities.addAll(new TeleportAbility(8*25, 8*45));

            weapons.add(
                    YellowWeapons.durability, Mirrorer.reflect(YellowWeapons.durability),
                    YellowWeapons.endurance,
                    YellowWeapons.fist
            );

            spells.add(YellowSpells.missileInverter);
        }};
    }
}
