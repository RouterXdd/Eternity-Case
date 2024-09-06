package eternity.content;

import mindustry.type.SectorPreset;

import static eternity.content.EtPlanets.ruinex;

public class EternitySectors {
    public static SectorPreset
            devaste, ruinShore, explosion;
    public static void load(){
        devaste = new SectorPreset("devaste", ruinex, 19){{
            captureWave = 8;
            difficulty = 1;
            alwaysUnlocked = true;
        }};
    }
}
