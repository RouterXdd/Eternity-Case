package eternity.content;

import arc.graphics.Color;
import mindustry.content.StatusEffects;
import mindustry.type.Liquid;

public class EtLiquids {
    public static Liquid corruptedWater;
    public static void load() {

        corruptedWater = new Liquid("corrupted-water", Color.valueOf("2b213d")) {{
            heatCapacity = 0.4f;
            coolant = false;
            effect = StatusEffects.sapped;
            boilPoint = 0.5f;
            gasColor = Color.valueOf("4d3a5b").a(0.8f);
            alwaysUnlocked = true;
        }};
    }
}
