package eternity.content;

import mindustry.type.SectorPreset;

import static eternity.content.EtPlanets.cyclefite;
import static eternity.content.EtPlanets.ruinex;

public class EtSectors {
    public static SectorPreset
            devaste, ruinShore, explosion,
            frostborn, glacier, isolatedStation, atomize, permanent,
            arrival;
    public static void load(){
        devaste = new SectorPreset("devaste", ruinex, 19){{
            captureWave = 8;
            difficulty = 1;
            alwaysUnlocked = true;
        }};
        ruinShore = new SectorPreset("ruin-shore", ruinex, 24){{
            captureWave = 25;
            difficulty = 4;
        }};
        frostborn = new SectorPreset("frostborn", cyclefite, 3){{
            captureWave = 10;
            difficulty = 3;
            alwaysUnlocked = true;
        }};
    }
}
