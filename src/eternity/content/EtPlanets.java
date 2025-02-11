package eternity.content;

import arc.graphics.Color;
import eternity.classes.planet.*;
import eternity.graphic.EternityPal;
import mindustry.game.Team;
import mindustry.graphics.g3d.*;
import mindustry.maps.planet.TantrosPlanetGenerator;
import mindustry.type.Planet;

import static eternity.content.EtItems.*;
import static eternity.content.EtBlocks.*;

public class EtPlanets {
    public static Planet
            novaStar, ruinex, abotium, cyclefite, derin, versat;
    public static void load(){
        novaStar = new Planet("nova-star", null, 5.5f){{
            bloom = true;
            accessible = false;

            meshLoader = () -> new SunMesh(
                    this, 4,
                    6, 0.3, 1.5, 1.3, 1,
                    1.25f,
                    Color.valueOf("36587eff"),
                    Color.valueOf("436a91ff"),
                    Color.valueOf("558db0ff"),
                    Color.valueOf("68b2c9ff"),
                    Color.valueOf("80e2e5ff")
            );
        }};
        abotium = new Planet("abotium", novaStar, 1f, 3){{
            generator = new TantrosPlanetGenerator();
            meshLoader = () -> new HexMesh(this, 6);
            accessible = false;
            visible = false;
            atmosphereColor = EternityPal.darkCultColor;
            iconColor = EternityPal.cultColor;
            startSector = 10;
            atmosphereRadIn = -0.01f;
            atmosphereRadOut = 0.3f;
            ruleSetter = r -> {
                r.waveTeam = EternityTeams.cultist;
                r.placeRangeCheck = false;
                r.showSpawns = true;
            };
            itemWhitelist.addAll(abotiumItems);
        }};
        ruinex = new Planet("ruinex", novaStar, 1f, 3){{
            generator = new RuinexPlanetGenerator();
            meshLoader = () -> new HexMesh(this, 6);
            cloudMeshLoader = () -> new MultiMesh(
                    new HexSkyMesh(this, 11, 0.15f, 0.13f, 5, new Color().set(EternityPal.viraColor).mul(0.9f).a(0.75f), 2, 0.45f, 0.9f, 0.38f),
                    new HexSkyMesh(this, 1, 0.6f, 0.16f, 5, Color.white.cpy().lerp(EternityPal.darkViraColor, 0.7f).a(0.75f), 2, 0.45f, 1f, 0.41f)
            );
            drawOrbit = true;
            allowLaunchToNumbered = false;
            sectorSeed = 2;
            allowWaves = true;
            clearSectorOnLose = true;
            defaultCore = commandBase;
            ruleSetter = r -> {
                r.waveTeam = EternityTeams.risen;
                r.placeRangeCheck = false;
                r.showSpawns = true;
            };
            iconColor = EternityPal.zyconColor;
            atmosphereColor = EternityPal.darkViraColor;
            atmosphereRadIn = 0.02f;
            atmosphereRadOut = 0.3f;
            startSector = 19;
            alwaysUnlocked = true;
            itemWhitelist.addAll(ruinexItems);
        }};

        cyclefite = new Planet("cyclefite", ruinex, 1f, 2){{
            generator = new CyclefiteSatelliteGenerator();
            meshLoader = () -> new HexMesh(this, 6);
            cloudMeshLoader = () -> new MultiMesh(
                    new HexSkyMesh(this, 11, 0.15f, 0.13f, 5, new Color().set(EternityPal.zyconColor).mul(0.9f).a(0.75f), 2, 0.45f, 0.9f, 0.38f),
                    new HexSkyMesh(this, 1, 0.6f, 0.16f, 5, Color.white.cpy().lerp(EternityPal.zyconColor, 0.7f).a(0.75f), 2, 0.45f, 1f, 0.41f)
            );
            orbitRadius = 3.7f;
            minZoom = 1.8f;
            radius = 0.45f;
            drawOrbit = true;
            allowLaunchToNumbered = false;
            sectorSeed = 3;
            allowWaves = true;
            clearSectorOnLose = true;
            defaultCore = coreFrost;
            ruleSetter = r -> {
                r.waveTeam = EternityTeams.risen;
                r.placeRangeCheck = false;
                r.showSpawns = true;
            };
            iconColor = EternityPal.cosmicColor;
            atmosphereColor = EternityPal.zyconColor;
            atmosphereRadIn = 0.02f;
            atmosphereRadOut = 0.3f;
            startSector = 3;
            alwaysUnlocked = true;
            itemWhitelist.addAll(cyclefiteItems);
        }};
        derin = new Planet("derin", abotium, 1f, 2){{
            generator = new TantrosPlanetGenerator();
            meshLoader = () -> new HexMesh(this, 6);
            accessible = false;
            visible = false;
            atmosphereColor = EternityPal.darkCultColor;
            iconColor = EternityPal.cultColor;
            startSector = 10;
            atmosphereRadIn = -0.01f;
            atmosphereRadOut = 0.3f;
            orbitRadius = 4f;
            minZoom = 1.8f;
            radius = 0.4f;
            ruleSetter = r -> {
                r.waveTeam = EternityTeams.cultist;
                r.placeRangeCheck = false;
                r.showSpawns = true;
            };
            itemWhitelist.addAll(abotiumItems);
        }};
        versat = new Planet("versat", novaStar, 1f, 2){{
            generator = new TantrosPlanetGenerator();
            meshLoader = () -> new HexMesh(this, 6);
            accessible = false;
            visible = false;
            atmosphereColor = EternityPal.darkMalachiteColor;
            iconColor = EternityPal.malachiteColor;
            startSector = 10;
            atmosphereRadIn = -0.01f;
            atmosphereRadOut = 0.3f;
            ruleSetter = r -> {
                r.waveTeam = EternityTeams.malachite;
                r.placeRangeCheck = false;
                r.showSpawns = true;
            };
            itemWhitelist.addAll(abotiumItems);
        }};
    }
}
