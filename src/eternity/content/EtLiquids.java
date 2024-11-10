package eternity.content;

import arc.graphics.Color;
import eternity.classes.mod.ClassificationMeta;
import mindustry.content.StatusEffects;
import mindustry.type.Liquid;

import static eternity.classes.mod.Classification.*;

public class EtLiquids {
    public static Liquid corruptedWater, helium;
    public static void load() {

        corruptedWater = new Liquid("corrupted-water", Color.valueOf("2b213d")) {{
            heatCapacity = 0.4f;
            coolant = false;
            effect = StatusEffects.sapped;
            boilPoint = 0.5f;
            gasColor = Color.valueOf("4d3a5b").a(0.8f);
            alwaysUnlocked = true;
            ClassificationMeta.put(this, ruin);
        }};
        helium = new Liquid("helium", Color.valueOf("cb3d48")){{
            gas = true;
            ClassificationMeta.put(this, cycle);
        }};
    }
}
