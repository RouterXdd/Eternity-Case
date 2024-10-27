package eternity.content;

import arc.graphics.Color;
import arc.struct.Seq;
import eternity.graphic.EternityPal;
import mindustry.type.Item;

import static mindustry.content.Items.blastCompound;
import static mindustry.content.Items.plastanium;

public class EtItems {
    public static Item
    //stellar
    ruinSandItem, gold, strontium, steel, heatproofGlass, cosmicDust, oxidePalladium, palladium, electronicPart, repairPart, laserPart, platePart, launchPart, darknessPart,
    //cult
    expPlast, wardCapsule, voidAlloy,
    //misc
    nissin,
            //Encycle
    cobalt, selenium, anthracite;
    public static final Seq<Item> ruinexItems = new Seq<>(), abotiumItems = new Seq<>(), encycleItems = new Seq<>();
    public static void load() {
        //misc
        nissin = new Item("nissin", Color.valueOf("82863b")){{
            hardness = 1;
            cost = 1.2f;
        }};
        //stellar
        ruinSandItem = new Item("corrupted-sand", Color.valueOf("4e4c47")){{
            lowPriority = true;
            buildable = false;
        }};
        gold = new Item("gold", Color.valueOf("dcd59c")){{
            hardness = 2;
            cost = 1.6f;
            healthScaling = 0.8f;
            charge = 0.25f;
        }};
        strontium = new Item("strontium", Color.valueOf("ca95ba")){{
            hardness = 2;
            cost = 1.75f;
            radioactivity = 1f;
        }};
        steel = new Item("steel", Color.valueOf("9781ab")){{
            hardness = 2;
            cost = 2.1f;
            healthScaling = 1.5f;
        }};
        heatproofGlass = new Item("heatproof-glass", Color.valueOf("4b5f6d"));
        cosmicDust = new Item("cosmic-dust", Color.valueOf("57b18b")){{
            hardness = 1;
            cost = 0.9f;
            radioactivity = 0.2f;
        }};
        oxidePalladium = new Item("oxide-palladium", Color.valueOf("3d2e2a")){{
            hardness = 4;
            buildable = false;
        }};
        palladium = new Item("palladium", Color.valueOf("cb4427")){{
            flammability = 0.5f;
            charge = 0.5f;
        }};
        electronicPart = new Item("electronic-part", Color.valueOf("d8c441"));
        repairPart = new Item("repair-part", EternityPal.viraColor);
        platePart = new Item("plate-part", Color.valueOf("cb6e21"));
        laserPart = new Item("laser-part", Color.valueOf("5684a0"));
        launchPart = new Item("launch-part", Color.valueOf("cb4a45"));
        darknessPart = new Item("darkness-part", EternityPal.darknessColor);
        //cult
        expPlast = new Item("explosive-plastanium", Color.valueOf("ca8962")){{
            hardness = 2;
            cost = 1.75f;
            explosiveness = 1f;
            flammability = 0.65f;
        }};
        wardCapsule = new Item("ward-capsule", Color.valueOf("47b199")){{
            hardness = 1;
            cost = 1.8f;
            charge = 2f;
            frames = 2;
            transitionFrames = 10;
        }};
        voidAlloy = new Item("void-alloy", Color.valueOf("1a1a1a")){{
            hardness = 1;
            cost = 2.5f;
        }};
        //Misc: Encycle
        cobalt = new Item("cobalt", Color.valueOf("75b1cb")){{
            hardness = 1;
            cost = 1.5f;
            healthScaling = 0.8f;
        }};
        selenium = new Item("selenium", Color.valueOf("81be72")){{
            hardness = 2;
            cost = 1.1f;
            explosiveness = 0.2f;
        }};
        anthracite = new Item("anthracite", Color.valueOf("4e4b4b")){{
            hardness = 2;
            cost = 1.5f;
            flammability = 1.1f;
        }};
        ruinexItems.addAll(
                strontium, gold, ruinSandItem, steel, heatproofGlass, cosmicDust, oxidePalladium, palladium,
                electronicPart, repairPart, laserPart, platePart, launchPart, darknessPart

        );
        abotiumItems.addAll(
                plastanium, blastCompound, expPlast, voidAlloy
        );
        encycleItems.addAll(
                cobalt, selenium, anthracite
        );
    }
}
