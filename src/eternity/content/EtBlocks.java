package eternity.content;


import arc.graphics.Color;
import arc.graphics.g2d.Draw;
import arc.graphics.g2d.Fill;
import arc.math.Interp.*;
import arc.struct.Seq;
import eternity.classes.blocks.defence.AdvRadar;
import eternity.classes.blocks.defence.Nullifier;
import eternity.classes.blocks.distribution.CapRegionDuct;
import eternity.classes.blocks.defence.StellarWall;
import eternity.classes.blocks.environment.*;
import eternity.classes.blocks.storage.CoreFrost;
import eternity.classes.blocks.units.Rift;
import eternity.classes.blocks.power.OverheatGenerator;
import eternity.classes.blocks.production.*;
import eternity.classes.blocks.storage.CommandCore;
import eternity.classes.blocks.turrets.*;
import eternity.classes.blocks.units.PlaceUnitBlock;
import eternity.classes.mod.ClassificationMeta;
import eternity.graphic.EternityPal;
import mindustry.content.*;
import mindustry.entities.Effect;
import mindustry.entities.bullet.*;
import mindustry.entities.effect.*;
import mindustry.entities.pattern.*;
import mindustry.gen.Bullet;
import mindustry.gen.Sounds;
import mindustry.graphics.CacheLayer;
import mindustry.graphics.Pal;
import mindustry.type.*;
import mindustry.type.unit.*;
import mindustry.world.Block;
import mindustry.world.blocks.defense.turrets.*;
import mindustry.world.blocks.distribution.Duct;
import mindustry.world.blocks.distribution.DuctBridge;
import mindustry.world.blocks.distribution.DuctRouter;
import mindustry.world.blocks.environment.*;
import mindustry.world.blocks.payloads.*;
import mindustry.world.blocks.power.BeamNode;
import mindustry.world.blocks.power.ConsumeGenerator;
import mindustry.world.blocks.production.AttributeCrafter;
import mindustry.world.blocks.production.GenericCrafter;
import mindustry.world.blocks.production.Pump;
import mindustry.world.consumers.ConsumeItemRadioactive;
import mindustry.world.draw.*;
import mindustry.world.meta.*;

import static arc.graphics.g2d.Draw.*;
import static arc.math.Angles.randLenVectors;
import static eternity.classes.mod.Classification.*;
import static eternity.content.EtAttributes.*;
import static eternity.content.EtLiquids.*;
import static eternity.content.EtUnits.*;
import static mindustry.Vars.*;
import static mindustry.content.StatusEffects.*;
import static mindustry.content.UnitTypes.*;
import static mindustry.content.Items.*;
import static eternity.content.EtItems.*;
import static mindustry.content.Liquids.*;
import static mindustry.type.ItemStack.*;

public class EtBlocks {
    public static Block
            //environment
                //Ruinex
            doronStone, roughDoronStone, doronStoneWall, doronBoulder,
            ruinSand, ruinSandWall, ruinSandBoulder, corruptWater, sandCorruptWater,
            burnedGrass, burnedShrub, burnedClunch, burnedTree,
            overheatStone, overheatStoneMagma, overheatStoneWall, overheatStoneBoulder,
            nukeBasalt, nukeBasaltPollute, nukeBasaltWall, nukeBasaltBoulder,
            riftedStone, riftedStoneWall, riftedBoulder,
                //Abotium
            sharpslate, wardSharpslate, sharpslateWall, sharpslateBoulder,
            blueSand, blueSandWall, blueSandBoulder,
                //Stellar
            stellarMetal, stellarMetal1, stellarMetalLamp,
                //Cult
                //Misc
            digFloor1, digFloor2, digFloor3, digFloor4, digFloor5,
            blueSnow, blueSnowWall, blueSnowBoulder,
            altIceCrack, altIce, altIceWall, frostCoreZone, shallowFrostCoreZone, iceShatters,
            smallRift, mediumRift, largeRift, malachiteCrystal,
            oreGold, oreStrontium, oreCosmicDust, orePalladium,
            oreCobalt, oreSelenium, oreAnthracite,
            //crafting
            nissinForge, terraSmelter, steelFoundry, filter, glassFabricator, electronicWeaver, healingPress, plateForge,
            //power
            lightningNode, lightningTower, powerHub, stellarRTG, thermalReactor,
            //production
            brokenDrill, cosmicExcavator, frostyDrill, radiator, oilCracker, monolite,
            //defence
            aegisBarrier, titanBarrier, infiniteFortress, techCenter, advancedRadar, nullifier,
            //storage
            commandBase, commandCenter, commandStation, coreFrost,
            //distribution
            stellarDuct, stellarRouter, stellarBridge,
            cobaltDuct,
            //liquid
            fragilePump,
            //payload
            plastMassConveyor, plastMassRouter, surgeMassConveyor, surgeMassRouter, smallMassDriver,
            //turrets
            recall, glare,
            galaxyTurret, novaTurret, substanceBlaster, coreBlaster, coldPhasor, nexxonPhasor, poseidon, zyconStorm, grandBlaster, gloriousBlaster, despondence, viraHealer, sanctumBeacon,
            gale,
            hope, lie, resonance, well,
            mionDroneBlock;
    public static void load(){
        doronStone = new Floor("doron-stone");
        roughDoronStone = new Floor("rough-doron-stone");
        doronStoneWall = new StaticWall("doron-stone-wall"){{
            variants = 3;
            roughDoronStone.asFloor().wall = this;
        }};
        doronBoulder = new Prop("doron-boulder"){{
            variants = 2;
            doronStone.asFloor().decoration = this;
            roughDoronStone.asFloor().decoration = this;
        }};
        ruinSand = new Floor("ruin-sand");
        ruinSandWall = new StaticWall("ruin-sand-wall");
        ruinSandBoulder = new Prop("ruin-sand-boulder"){{
            variants = 2;
            ruinSand.asFloor().decoration = this;
        }};
        corruptWater = new Floor("corrupt-water"){{
            speedMultiplier = 0.5f;
            variants = 0;
            status = StatusEffects.sapped;
            statusDuration = 90f;
            liquidDrop = corruptedWater;
            isLiquid = true;
            cacheLayer = CacheLayer.water;
            albedo = 0.9f;
            supportsOverlay = true;
        }};
        sandCorruptWater = new ShallowLiquid("sand-corrupt-water"){{
            speedMultiplier = 0.8f;
            variants = 3;
            status = StatusEffects.sapped;
            statusDuration = 50f;
            liquidDrop = corruptedWater;
            liquidMultiplier = 0.6f;
            isLiquid = true;
            shallow = true;
            cacheLayer = CacheLayer.water;
            mapColor = Color.valueOf("383141");
        }};
        burnedGrass = new Floor("burned-grass", 4);
        burnedShrub = new StaticWall("burned-shrub"){{
            burnedGrass.asFloor().wall = this;
        }};
        burnedClunch = new Prop("burned-clunch"){{
            variants = 2;
            burnedGrass.asFloor().decoration = this;
        }};
        burnedTree = new TreeBlock("burned-tree"){{
            variants = 2;
        }};
        overheatStone = new Floor("overheat-stone");
        overheatStoneMagma = new Floor("overheat-stone-magma"){{
            attributes.set(Attribute.heat, 0.5f);
            attributes.set(Attribute.water, -0.5f);
            mapColor = Color.valueOf("c54a07");
            blendGroup = overheatStone;

            emitLight = true;
            lightRadius = 35f;
            lightColor = Color.orange.cpy().a(0.3f);
        }};
        overheatStoneWall = new StaticWall("overheat-stone-wall"){{
            variants = 3;
        }};
        overheatStoneBoulder = new Prop("overheat-boulder"){{
            variants = 2;
            overheatStone.asFloor().decoration = this;
        }};
        nukeBasalt = new Floor("nuke-basalt", 4);
        nukeBasaltPollute = new Floor("nuke-basalt-pollute", 4){{
            mapColor = Color.valueOf("146c24");
            blendGroup = nukeBasalt;

            emitLight = true;
            lightRadius = 40f;
            lightColor = Color.valueOf("146c24").cpy().a(0.3f);
        }};
        nukeBasaltWall = new StaticWall("nuke-basalt-wall"){{
            variants = 3;
        }};
        nukeBasaltBoulder = new Prop("nuke-basalt-boulder"){{
            variants = 2;
            nukeBasalt.asFloor().decoration = this;
        }};
        riftedStone = new Floor("rifted-stone"){{
            variants = 4;
        }};
        riftedStoneWall = new StaticWall("rifted-stone-wall"){{
            variants = 4;
        }};
        riftedBoulder = new Prop("rifted-boulder"){{
            variants = 2;
            riftedStone.asFloor().decoration = this;
        }};
        sharpslate = new Floor("sharpslate"){{
            variants = 4;
        }};
        wardSharpslate = new Floor("ward-sharpslate"){{
            variants = 4;
            blendGroup = sharpslate;
            attributes.set(ward, 0.25f);
        }};
        sharpslateWall = new StaticWall("sharpslate-wall"){{
            variants = 4;
            wardSharpslate.asFloor().wall = this;
            sharpslate.asFloor().wall = this;
        }};
        sharpslateBoulder = new Prop("sharpslate-boulder"){{
            variants = 2;
            sharpslate.asFloor().decoration = this;
        }};
        blueSand = new Floor("blue-sand"){{
            variants = 4;
            attributes.set(oily, 0.25f);
        }};
        blueSandWall = new StaticWall("blue-sand-wall"){{
            variants = 4;
            blueSand.asFloor().wall = this;
        }};
        blueSandBoulder = new Prop("blue-sand-boulder"){{
            variants = 2;
            blueSand.asFloor().decoration = this;
        }};
        stellarMetal = new Floor("stellar-metal", 0){{
            mapColor = Color.valueOf("2e2f34");
        }};
        stellarMetal1 = new AdvFloor("stellar-metal1", 0, stellarMetal);
        stellarMetalLamp = new AdvFloor("stellar-metal-lamp", 3, stellarMetal){{
            mapColor = Color.valueOf("27282b");
        }};
        digFloor1 = new Floor("dig-floor1", 0);
        digFloor2 = new Floor("dig-floor2", 0);
        digFloor3 = new Floor("dig-floor3", 0){{
            placeableOn = false;
        }};
        digFloor4 = new Floor("dig-floor4", 0){{
            placeableOn = false;
        }};
        digFloor5 = new Floor("dig-floor5", 0){{
            placeableOn = false;
            solid = true;
            variants = 0;
            canShadow = false;
        }};
        blueSnow = new Floor("blue-snow"){{
            variants = 4;
        }};
        blueSnowWall = new StaticWall("blue-snow-wall"){{
            variants = 4;
        }};
        blueSnowBoulder = new Prop("blue-snow-boulder"){{
            variants = 2;
            ruinSand.asFloor().decoration = this;
        }};
        altIceCrack = new Floor("alt-ice-crack"){{
            dragMultiplier = 0.4f;
            speedMultiplier = 0.9f;
            attributes.set(Attribute.water, 0.4f);
        }};
        altIce = new Floor("alt-ice"){{
            variants = 4;
            dragMultiplier = 0.4f;
            speedMultiplier = 0.9f;
            attributes.set(Attribute.water, 0.4f);
            altIceCrack.asFloor().blendGroup = this;
        }};
        altIceWall = new StaticWall("alt-ice-wall"){{
            variants = 4;
        }};
        frostCoreZone = new Floor("frost-core-zone", 0){{
            allowCorePlacement = true;
        }};
        shallowFrostCoreZone = new Floor("shallow-frost-core-zone", 0){{
            allowCorePlacement = true;
            speedMultiplier = 0.8f;
            status = StatusEffects.wet;
            statusDuration = 50f;
            liquidDrop = water;
            liquidMultiplier = 0.6f;
            isLiquid = true;
            shallow = true;
            cacheLayer = CacheLayer.water;
            mapColor = Color.valueOf("688ec2");
        }};
        iceShatters = new OverlayFloor("ice-shatters");
        smallRift = new Rift("small-rift"){{
            requirements(Category.effect,BuildVisibility.sandboxOnly, with());
            size = 3;
            rareUnitChance = 0.25f;
            updateEffect = new ParticleEffect(){{
                line = true;
                colorFrom = colorTo = EternityPal.malachiteColor;
                lenFrom = 5f;
                lenTo = 0;
                particles = 2;
                cone = 360;
                lifetime = 65f;
                length = 28f;
            }};
            spawnUnits = new UnitType[][]{
                    //TODO make nuclear units
                    {electron},
                    {nova},
                    {crawler}
            };
            rareUnits = new UnitType[][]{
                    {pain},
                    {infectedGerb},
                    {infectedMionDrone}
            };
            ClassificationMeta.put(this, malachite);
        }};
        malachiteCrystal = new ExplosiveCrystal("malachite-crystal"){{
                requirements(Category.effect, BuildVisibility.sandboxOnly, with());
                health = 460;
            explodeEffect = new MultiEffect(new ParticleEffect(){{
                line = true;
                colorFrom = colorTo = EternityPal.malachiteColor;
                lenFrom = 7f;
                lenTo = 0;
                particles = 8;
                cone = 360;
                lifetime = 45f;
                length = explosionRadius * tilesize;
            }}, new WaveEffect(){{
                colorFrom = EternityPal.malachiteColor;
                colorTo = EternityPal.darkMalachiteColor;
                sizeTo = explosionRadius * tilesize;
                lifetime = 45f;
            }}
            );
            ClassificationMeta.put(this, malachite);
        }};
        oreGold = new OreBlock(gold);
        oreStrontium = new OreBlock(strontium);
        oreCosmicDust = new OreBlock(cosmicDust);
        orePalladium = new OreBlock(oxidePalladium);
        oreCobalt = new OreBlock(cobalt);
        oreSelenium = new OreBlock(selenium);
        oreAnthracite = new OreBlock(anthracite);
        nissinForge = new GenericCrafter("nissin-forge"){{
            requirements(Category.crafting, with(copper, 90, silicon, 65, plastanium, 20));
            outputItems = with(nissin, 2);
            craftTime = 165f;
            size = 2;
            hasPower = true;
            itemCapacity = 10;
            drawer = new DrawMulti(new DrawDefault(), new DrawFlame());

            consumePower(4.5f);
            consumeItems(with(copper, 2, titanium, 3));
            researchCostMultiplier = 0.5f;
            ClassificationMeta.put(this, misc);
        }};
        terraSmelter = new NuclearCrafter("terra-smelter"){{
            requirements(Category.crafting, with(silicon, 145, thorium, 90, titanium, 75, plastanium, 60, nissin, 110));
            craftEffect = EtFx.terraCraft;
            outputItems = with(surgeAlloy, 3, titanium, 2, lead, 2);
            craftTime = 110f;
            size = 3;
            hasPower = true;
            itemCapacity = 20;
            drawer = new DrawMulti(new DrawDefault(), new DrawFlame());

            consumePower(6f);
            consumeLiquids(LiquidStack.with(slag, 20f/60, cryofluid, 8f/60));
            consumeItems(with(silicon, 3, copper, 2));
            researchCostMultiplier = 0.5f;
            ClassificationMeta.put(this, misc);
        }};
        steelFoundry = new AttributeCrafter("steel-foundry"){{
            requirements(Category.crafting, with(strontium, 110));
            outputItems = with(steel, 1);
            craftTime = 115f;
            boostScale = 0.15f;
            size = 2;
            hasPower = true;
            itemCapacity = 10;
            drawer = new DrawMulti(new DrawDefault(), new DrawCrucibleFlame(){{
                particleInterp = new Pow(0f);
                particleSize = 4f;
                particles = 16;
                rotateScl = 0f;
                flameColor = Color.valueOf("9781ab");
                midColor = Color.valueOf("c7add2");
            }});
            researchCost = with(strontium, 650);
            squareSprite = false;
            consumePower(3f);
            consumeItems(with(strontium, 1, gold, 1));
            researchCostMultiplier = 0.5f;
            ClassificationMeta.put(this, ruin);
        }};
        filter = new GenericCrafter("filter"){{
            requirements(Category.crafting, with(strontium, 95, steel, 60));
            hasPower = true;
            craftTime = 65f;
            size = 2;
            squareSprite = false;

            consumePower(1.5f);
            consumeLiquid(corruptedWater, 16f / 60f);
            outputLiquid = new LiquidStack(water, 8 / 60f);
            outputItems = with(ruinSandItem, 1);

            drawer = new DrawMulti(new DrawRegion("-bottom"), new DrawLiquidTile(corruptedWater, 1), new DrawRegion("-rotator", 3, true), new DrawDefault());
            ClassificationMeta.put(this, ruin);
        }};
        glassFabricator = new GenericCrafter("glass-fabricator"){{
            requirements(Category.crafting, with(strontium, 140, steel, 85, cosmicDust, 60));
            hasPower = true;
            craftTime = 100f;
            size = 3;
            squareSprite = false;
            consumeItems(with(ruinSandItem, 3, cosmicDust, 1));

            consumePower(4.5f);
            outputItems = with(heatproofGlass, 1);

            drawer = new DrawMulti(new DrawDefault(), new DrawFlame(){{
                flameColor = Color.valueOf("57b18b");
            }}, new DrawPistons(){{
                angleOffset = 45f;
                sides = 4;
                lenOffset = 6.5f;
                sinScl = 12f;
                sinMag = 3;
            }});
            ClassificationMeta.put(this, ruin);
        }};
        electronicWeaver = new GenericCrafter("electronic-weaver"){{
            requirements(Category.crafting, with(strontium, 120, gold, 90, steel, 110, cosmicDust, 85));
            squareSprite = false;
            hasItems = true;
            craftTime = 80f;
            outputItem = new ItemStack(electronicPart, 1);
            size = 2;
            health = 320;
            hasPower = true;
            craftEffect = new MultiEffect(new WaveEffect(){{
                sizeTo = 8;
                strokeFrom = 2f;
                sides = 4;
                lifetime = 25;
                colorFrom = Color.valueOf("d8c441");
            }});
            updateEffect = Fx.none;
            drawer = new DrawMulti(new DrawDefault(), new DrawFade());
            consumePower(7.5f);
            consumeItems(with(steel, 1, gold, 3));
            ClassificationMeta.put(this, ruin);
        }};
        plateForge = new GenericCrafter("plate-forge"){{
            requirements(Category.crafting, with(strontium, 210, gold, 145, steel, 120, electronicPart, 90));
            squareSprite = false;
            hasItems = true;
            craftTime = 235f;
            outputItem = new ItemStack(platePart, 1);
            size = 3;
            health = 460;
            hasPower = true;
            craftEffect = new MultiEffect(
            new ParticleEffect(){{
                region = "eternity-case-plate-forge-light";
                sizeFrom = sizeTo = 12;
                particles = 1;
                randLength = false;
                length = cone = 0;
                colorFrom = Color.valueOf("e78242");
                colorTo = Color.clear;
                lifetime = 90;
            }});
            updateEffect = Fx.none;
            drawer = new DrawMulti(new DrawDefault());
            consumePower(9f);
            consumeItems(with(steel, 3));
            ClassificationMeta.put(this, ruin);
        }};
        lightningNode = new BeamNode("lightning-node"){{
            requirements(Category.power, with(gold, 10));
            consumesPower = outputsPower = true;
            laserColor1 = Color.valueOf("f2f1c2");
            laserColor2 = Color.valueOf("cfbd7b");
            laserWidth = 0.65f;
            health = 90;
            range = 7;
            fogRadius = 1;
            researchCost = with(gold, 25);
            consumePower(0.1f);
            ClassificationMeta.put(this, ruin);
        }};
        stellarRTG = new ConsumeGenerator("stellar-rtg"){{
            requirements(Category.power, with(strontium, 60, gold, 35));
            size = 2;
            powerProduction = 1.5f;
            itemDuration = 60 * 10f;
            envEnabled = Env.any;
            generateEffect = Fx.generatespark;

            drawer = new DrawMulti(new DrawDefault(), new DrawWarmupRegion());
            consume(new ConsumeItemRadioactive());
            squareSprite = false;
            ClassificationMeta.put(this, ruin);
        }};
        thermalReactor = new OverheatGenerator("thermal-reactor"){{
            requirements(Category.power, with(strontium, 120, gold, 90, electronicPart, 65, steel, 80));
            powerProduction = 1.5f;
            generateEffect = Fx.redgeneratespark;
            explodeEffect = new ExplosionEffect(){{
                lifetime = 60f;
                waveStroke = 6f;
                waveColor = sparkColor = Color.valueOf("feb380");
                waveRad = 20f;
                smokeSize = 10f;
                smokes = 14;
                smokeSizeBase = 0f;
                smokeColor = Color.valueOf("feb380");
                sparks = 14;
                sparkRad = 40f;
                sparkLen = 6f;
                sparkStroke = 5f;
            }};
            effectChance = 0.011f;
            size = 3;
            floating = true;
            ambientSound = Sounds.hum;
            ambientSoundVolume = 0.06f;
            squareSprite = false;
            consumeLiquid(corruptedWater, 8f / 60f).optional(true, false);
            ClassificationMeta.put(this, ruin);
        }};
        brokenDrill = new ExcavatorDrill("broken-drill"){{
            requirements(Category.production, with(strontium, 25));
            tier = 2;
            drillTime = 600;
            size = 2;
            researchCost = with(strontium, 25);
            oreMultipler = 1.85f;
            solidMultipler = 0.5f;

            consumeLiquid(water, 0.1f).boost();
            ClassificationMeta.put(this, ruin);
        }};
        cosmicExcavator = new ExcavatorDrill("cosmic-excavator"){{
            requirements(Category.production, with(strontium, 80, electronicPart, 20, cosmicDust, 45));
            tier = 2;
            drillTime = 400;
            range = 9;
            size = 3;
            oreMultipler = 0.75f;
            solidMultipler = 0.7f;
            liquidBoostIntensity = 1.8f;

            consumeLiquid(water, 0.15f).boost();
            consumePower(4f);
            ClassificationMeta.put(this, ruin);
        }};
        frostyDrill = new FrostyDrill("frosty-drill"){{
            requirements(Category.production, with(cobalt, 10, selenium, 5));
            tier = 2;
            drillTime = 450;
            size = 2;
            researchCost = with(cobalt, 40, selenium, 20);
            liquidBoostIntensity = 1;
            ClassificationMeta.put(this, cycle);
        }};
        radiator = new GroundHeater("radiator"){{
            requirements(Category.production, with(cobalt, 45, anthracite, 30));
            ClassificationMeta.put(this, cycle);
        }};
        oilCracker = new AttributeCrafter("oil-cracker"){{
            requirements(Category.production, with(monoShards, 80));
            craftTime = 100;
            size = 2;
            hasLiquids = true;
            hasPower = true;
            hasItems = true;

            craftEffect = Fx.none;
            attribute = oily;
            squareSprite = false;

            legacyReadWarmup = true;
            drawer = new DrawMulti(
                    new DrawLiquidTile(oil, 1.5f),
                    new DrawDefault()
            );
            minEfficiency = 0.01f;
            baseEfficiency = 0;

            consumePower(75f / 60f);
            outputLiquid = new LiquidStack(oil, 5f / 60f);
            ClassificationMeta.put(this, cult);
        }};
        titanBarrier = new StellarWall("titan-barrier"){{
            requirements(Category.defense, BuildVisibility.sandboxOnly, with(strontium, 80));
            health = 3550;
            size = 2;
            armor = 3;
            hitsRequire = 0;
            researchCostMultiplier = 0.55f;
            ClassificationMeta.put(this, ruin);
        }};
        aegisBarrier = new StellarWall("aegis-barrier"){{
            requirements(Category.defense, with(strontium, 80));
            health = 1100;
            size = 2;
            hitsRequire = 750;
            upgradeBlock = titanBarrier;
            buildCostMultiplier = 0.5f;
            researchCostMultiplier = 0.3f;
            ClassificationMeta.put(this, ruin);
        }};
        infiniteFortress = new StellarWall("infinite-fortress"){{
            requirements(Category.defense, with(steel, 130, platePart, 55));
            health = 2650;
            size = 3;
            armor = 5;
            hitsRequire = 0;
            buildCostMultiplier = 0.5f;
            researchCostMultiplier = 0.5f;
            ClassificationMeta.put(this, ruin);
        }};
        advancedRadar = new AdvRadar("advanced-radar"){{
            requirements(Category.effect, with(strontium, 120, steel, 75, electronicPart, 35));
            outlineColor = Color.valueOf("2b2b4e");
            discoveryTime = 60 * 6f;
            fogRadius = 25;
            size = 2;

            consumePower(1.5f);
            ClassificationMeta.put(this, ruin);
        }};
        nullifier = new Nullifier("nullifier"){{
            requirements(Category.effect, with(strontium, 280, gold, 210, steel, 180, electronicPart, 90));
            fogRadius = (int) range;
            size = 3;

            consumePower(9f);
            ClassificationMeta.put(this, ruin);
        }};

        commandBase = new CommandCore("command-base"){{
            requirements(Category.effect,  with(strontium, 1500, gold, 800));
            alwaysUnlocked = true;

            isFirstTier = true;
            unitType = coreDrone;
            health = 1100;
            itemCapacity = 2000;
            size = 3;

            unitCapModifier = 10;
            plans = Seq.with(
                    new CommandCoreUnit(coreDrone, 1),
                    new CommandCoreUnit(viraDrone, 2)
            );
            ClassificationMeta.put(this, ruin);
        }};
        commandCenter = new CommandCore("command-center"){{
            //TODO requirements
            requirements(Category.effect,  with(strontium, 1900, gold, 1100, electronicPart, 750, cosmicDust, 1450));

            unitType = coreDrone;
            health = 6700;
            itemCapacity = 8000;
            size = 4;

            unitCapModifier = 20;
            plans = Seq.with(
                    new CommandCoreUnit(coreDrone, 1),
                    new CommandCoreUnit(viraDrone, 2),
                    new CommandCoreUnit(quasar, 3)
            );
            ClassificationMeta.put(this, ruin);
        }};
        coreFrost = new CoreFrost("core-frost"){{
            requirements(Category.effect,  with(cobalt, 900, selenium, 300));
            alwaysUnlocked = true;

            isFirstTier = true;
            unitType = frostDrone;
            health = 1100;
            itemCapacity = 1500;
            size = 3;

            unitCapModifier = 6;
            ClassificationMeta.put(this, cycle);
        }};
        stellarDuct = new CapRegionDuct("stellar-duct"){{
            requirements(Category.distribution, with(strontium, 1));
            health = 140;
            speed = 4.5f;
            researchCost = with(strontium, 10);
            ClassificationMeta.put(this, ruin);
        }};
        stellarRouter = new DuctRouter("stellar-router"){{
            requirements(Category.distribution, with(strontium, 8));
            squareSprite = false;
            health = 130;
            speed = 4.5f;
            ClassificationMeta.put(this, ruin);
        }};
        stellarBridge = new DuctBridge("stellar-bridge"){{
            requirements(Category.distribution, with(strontium, 10, gold, 4));
            health = 100;
            speed = 2.25f;
            buildCostMultiplier = 2f;
            researchCostMultiplier = 0.35f;
            ClassificationMeta.put(this, ruin);
        }};
        cobaltDuct = new CapRegionDuct("cobalt-duct"){{
            requirements(Category.distribution, with(cobalt, 1));
            health = 140;
            speed = 5.5f;
            researchCost = with(cobalt, 30);
            ClassificationMeta.put(this, cycle);
        }};
        ((Duct) stellarDuct).bridgeReplacement = stellarBridge;
        ((Duct) cobaltDuct).bridgeReplacement = stellarBridge;
        fragilePump = new Pump("fragile-pump"){{
            requirements(Category.liquid, with(strontium, 10, steel, 8));
            pumpAmount = 8f / 60f;
            ClassificationMeta.put(this, ruin);
        }};
        plastMassConveyor = new PayloadConveyor("plast-mass-conveyor"){{
            requirements(Category.units, with(graphite, 20, plastanium, 14));
            health = 100;
            canOverdrive = false;
            moveTime = 30;
            ClassificationMeta.put(this, misc);
        }};
        plastMassRouter = new PayloadRouter("plast-mass-router"){{
            requirements(Category.units, with(graphite, 25, plastanium, 18));
            health = 100;
            canOverdrive = false;
            moveTime = 30;
            ClassificationMeta.put(this, misc);
        }};
        surgeMassConveyor = new PayloadConveyor("surge-mass-conveyor"){{
            requirements(Category.units, with(graphite, 25, surgeAlloy, 15, phaseFabric, 8));
            health = 180;
            canOverdrive = false;
            moveTime = 20;
            moveForce = 260;
        }};
        surgeMassRouter = new PayloadRouter("surge-mass-router"){{
            requirements(Category.units, with(graphite, 40, surgeAlloy, 25, phaseFabric, 15));
            health = 180;
            canOverdrive = false;
            moveTime = 20;
            moveForce = 26;
            ClassificationMeta.put(this, misc);
        }};
        smallMassDriver = new PayloadMassDriver("small-mass-driver"){{
            requirements(Category.units, with(titanium, 170, silicon, 120, graphite, 140, plastanium, 100));
            size = 3;
            reload = 140f;
            chargeTime = 110f;
            range = 500f;
            maxPayloadSize = 2.5f;
            fogRadius = 5;
            consumePower(2f);
            ClassificationMeta.put(this, misc);
        }};
        recall = new ItemTurret("recall"){{
            requirements(Category.turret, with(copper, 110, lead, 70, silicon, 65, titanium, 40));
            ammo(
                    metaglass, new BasicBulletType(4f, 10, "shell"){{
                        lifetime = 10f;
                        scaleLife = true;
                        ammoMultiplier = 5f;
                        shootEffect = Fx.shootSmall;
                        width = 10f;
                        height = 10f;
                        hitEffect = Fx.flakExplosion;
                        splashDamage = 25f;
                        splashDamageRadius = 30f;
                        backColor = Pal.gray;
                        frontColor = Color.white;
                        fragBullets = 3;
                        fragRandomSpread = 0;
                        fragSpread = 15;
                        fragVelocityMin = 1;
                        fragAngle = 0;
                        fragBullet = new BasicBulletType(-1f, 5){{
                            width = 6f;
                            height = 13f;
                            collides = false;
                            shrinkY = shrinkX = 0f;
                            drag = 0.05f;
                            lifetime = 40f;
                            backColor = Pal.gray;
                            frontColor = Color.white;
                            despawnEffect = hitEffect = Fx.none;
                            fragBullets = 1;
                            fragRandomSpread = 0;
                            fragSpread = 0;
                            fragVelocityMin = 1;
                            fragAngle = 180;
                            fragBullet = new BasicBulletType(6f, 15){{
                                width = 6f;
                                height = 13f;
                                lifetime = 25f;
                                backColor = Pal.gray;
                                frontColor = Color.white;
                            }};
                        }};
                    }}
            );

            reload = 50f;
            range = 160f;
            size = 2;

            recoil = 1f;
            rotateSpeed = 13f;
            shootCone = 35f;

            scaledHealth = 200;
            shootSound = Sounds.shootSnap;
            coolant = consumeCoolant(0.2f);
            researchCostMultiplier = 0.3f;

            limitRange(2);
            ClassificationMeta.put(this, misc);
        }};
        glare = new PowerTurret("glare"){{
            requirements(Category.turret,BuildVisibility.sandboxOnly, with());
            shootType = new BasicBulletType(8f, 60){{
                        width = 9f;
                        height = 16f;
                        ammoMultiplier = 7;
                        lifetime = 30f;
                        frontColor = EternityPal.glareColor;
                        backColor = EternityPal.darkGlareColor;
            }};

            recoil = 1f;
            size = 3;
            shootY = -0.5f;
            reload = 55f;
            range = 240;
            shoot.shots = 4;
            shoot.shotDelay = 7;
            shootCone = 35f;
            ammoUseEffect = Fx.casing1;
            health = 5890;
            inaccuracy = 6f;
            rotateSpeed = 11f;
            coolant = consumeCoolant(0.12f);
            drawer = new DrawTurret("ast-");
            outlineColor = Color.valueOf("22262b");
            connectedPower = false;
            ClassificationMeta.put(this, misc);
        }};
        novaTurret = new StellarPowerTurret("nova-turret"){{
            requirements(Category.turret, with(strontium, 45, gold, 40));
            shootType = new BasicBulletType(8f, 12f){{
                width = 6f;
                height = 16f;
                lifetime = 12.5f;
                sprite = "eternity-case-laser-blast";
                backColor = Pal.techBlue;
                shootEffect = smokeEffect = Fx.none;
            }};
            connectedPower = false;
            squareSprite = false;
            drawer = new DrawTurret("stellar-");
            reload = 25f;
            shootCone = 30f;
            rotateSpeed = 9f;
            range = 100f;
            recoil = 1f;
            size = 2;
            health = 360;
            shootRequire = 0;
            shootSound = Sounds.blaster;
            coolant = consumeCoolant(0.1f);
            shoot = new ShootAlternate(2.5f);
            researchCostMultiplier = 0.05f;
            ClassificationMeta.put(this, ruin);
        }};
        coreBlaster = new StellarPowerTurret("core-blaster"){{
            requirements(Category.turret, with(strontium, 35, gold, 50));
            shootType = new BasicBulletType(8f, 28){{
                width = 8f;
                height = 8f;
                splashDamage = 15;
                splashDamageRadius = 12;
                lifetime = 8.125f;
                sprite = "eternity-case-sphere";
                backColor = Pal.redderDust;
                shootEffect = smokeEffect = Fx.none;
            }};
            connectedPower = false;
            squareSprite = false;
            drawer = new DrawTurret("stellar-");
            reload = 65f;
            shootCone = 30f;
            rotateSpeed = 9f;
            range = 65f;
            recoil = 2f;
            size = 2;
            health = 430;
            shootRequire = 0;
            shootSound = Sounds.blaster;
            coolant = consumeCoolant(0.1f);
            researchCostMultiplier = 0.25f;
            ClassificationMeta.put(this, ruin);
        }};
        coldPhasor = new StellarPowerTurret("cold-phasor"){{
            requirements(Category.turret, BuildVisibility.sandboxOnly, with(strontium, 65, gold, 40, steel, 25));
            shootType = new PointBulletType(){{
                damage = 24;
                status = freezing;
                statusDuration = 5 * 60;
                lifetime = 15f;
                trailInterval = 100f;
                trailEffect = new MultiEffect(
                        new ParticleEffect(){{
                            randLength = false;
                            line = true;
                            colorFrom = colorTo = Color.valueOf("87ceeb");
                            lenFrom = lenTo = 14;
                            strokeFrom = 3;
                            particles = 1;
                            cone = 0;
                            lifetime = 82f;
                            length = 0.1f;
                        }},
                        new ParticleEffect(){{
                            startDelay = 2;
                            randLength = false;
                            line = true;
                            colorFrom = colorTo = Color.white;
                            lenFrom = lenTo = 14;
                            strokeFrom = 1.5f;
                            particles = 1;
                            cone = 0;
                            lifetime = 80f;
                            length = 0.1f;
                        }}
                );
                shootEffect = smokeEffect = despawnEffect = Fx.none;
            }};
            predictTarget = false;
            inaccuracy = 3.5f;
            shoot.shots = 3;
            shoot.shotDelay = 12;
            connectedPower = false;
            squareSprite = false;
            drawer = new DrawTurret("stellar-");
            reload = 100f;
            shootCone = 10f;
            rotateSpeed = 7.5f;
            range = 125f;
            recoil = 1f;
            size = 2;
            health = 1120;
            shootRequire = 0;
            shootSound = Sounds.lasershoot;
            coolant = consumeCoolant(0.1f);
            ClassificationMeta.put(this, ruin);
        }};
        nexxonPhasor = new StellarPowerTurret("nexxon-phasor"){{
            requirements(Category.turret, with(strontium, 65, gold, 40, steel, 25));
            shootType = new PointBulletType(){{
                damage = 15;
                lifetime = 15f;
                trailInterval = 100f;
                trailEffect = new MultiEffect(
                        new ParticleEffect(){{
                            randLength = false;
                            line = true;
                            colorFrom = colorTo = Color.valueOf("664488");
                            lenFrom = lenTo = 14;
                            strokeFrom = 3;
                            particles = 1;
                            cone = 0;
                            lifetime = 82f;
                            length = 0.1f;
                        }},
                        new ParticleEffect(){{
                            startDelay = 2;
                            randLength = false;
                            line = true;
                            colorFrom = colorTo = Color.white;
                            lenFrom = lenTo = 14;
                            strokeFrom = 1.5f;
                            particles = 1;
                            cone = 0;
                            lifetime = 80f;
                            length = 0.1f;
                        }}
                );
                shootEffect = smokeEffect = despawnEffect = Fx.none;
            }};
            predictTarget = false;
            inaccuracy = 3.5f;
            shoot.shots = 3;
            shoot.shotDelay = 12;
            connectedPower = false;
            squareSprite = false;
            drawer = new DrawTurret("stellar-");
            reload = 110f;
            shootCone = 10f;
            rotateSpeed = 7.5f;
            range = 125f;
            recoil = 1f;
            size = 2;
            health = 740;
            shootRequire = 480;
            upgradeBlock = coldPhasor;
            shootSound = Sounds.lasershoot;
            coolant = consumeCoolant(0.1f);
            ClassificationMeta.put(this, ruin);
        }};
        poseidon = new PowerTurret("poseidon"){{
            requirements(Category.turret, BuildVisibility.sandboxOnly, with(silicon, 195, graphite, 140, steel, 85, thorium, 100));
            reload = 90f;
            recoilTime = reload * 1.5f;
            coolantMultiplier = 0.5f;
            ammoUseEffect = Fx.none;
            range = 260f;
            inaccuracy = 3f;
            recoil = 3f;
            shootY = 8.5f;
            shoot = new ShootAlternate(12f);
            shoot.shots = 4;
            shoot.shotDelay = 6;
            shake = 2f;
            size = 3;
            shootCone = 24f;
            shootSound = Sounds.shockBlast;
            drawer = new DrawTurret("stellar-");

            scaledHealth = 160;
            coolant = consumeCoolant(0.45f);
            consumePower(10f);
            shootType = new BasicBulletType(0f, 0){{
                shootEffect = EtFx.zyconMissileShoot;
                smokeEffect = Fx.none;
                shake = 1f;
                speed = 0f;
                keepVelocity = false;

                spawnUnit = new MissileUnitType("poseidon-missile"){{
                    speed = 3.5f;
                    maxRange = 8f;
                    lifetime = 75;
                    missileAccelTime = 1.5f;
                    homingDelay = 10f;
                    engineSize = 0;
                    targetable = hittable = drawCell = outlines = false;
                    health = Float.MAX_VALUE;
                    loopSoundVolume = 0.1f;

                    weapons.add(new Weapon(){{
                        shootCone = 360f;
                        x = y = 0;
                        mirror = false;
                        reload = 1f;
                        shootOnDeath = true;
                        deathExplosionEffect = Fx.none;
                        bullet = new ExplosionBulletType(60f, 25f){{
                            shootEffect = new MultiEffect(new WaveEffect(){{
                                sizeTo = 20;
                                strokeFrom = 3.5f;
                                sides = 4;
                                lifetime = 40;
                                colorFrom = EternityPal.zyconColor;
                            }});
                        }};
                    }});
                }};
            }};
            researchCostMultiplier = 0.3f;

            limitRange(5);
            ClassificationMeta.put(this, ruin);
        }};
        zyconStorm = new StellarPowerTurret("zycon-storm"){{
            requirements(Category.turret, with(silicon, 195, graphite, 140, steel, 85, thorium, 100));
            reload = 90f;
            recoilTime = reload * 1.5f;
            coolantMultiplier = 0.5f;
            ammoUseEffect = Fx.none;
            range = 200f;
            inaccuracy = 3f;
            recoil = 3f;
            shootY = 8.5f;
            shoot = new ShootAlternate(12f);
            shoot.shots = 2;
            shake = 2f;
            size = 3;
            shootCone = 24f;
            shootSound = Sounds.shockBlast;
            shootRequire = 275;
            upgradeBlock = poseidon;
            drawer = new DrawTurret("stellar-");

            scaledHealth = 160;
            coolant = consumeCoolant(0.45f);
            consumePower(10f);
            shootType = new BasicBulletType(0f, 0){{
                shootEffect = EtFx.zyconMissileShoot;
                smokeEffect = Fx.none;
                shake = 1f;
                speed = 0f;
                keepVelocity = false;

                spawnUnit = new MissileUnitType("zycon-missile"){{
                    speed = 3.5f;
                    maxRange = 8f;
                    lifetime = 65;
                    missileAccelTime = 1.5f;
                    homingDelay = 10f;
                    engineSize = 0;
                    targetable = hittable = drawCell = outlines = false;
                    health = Float.MAX_VALUE;
                    loopSoundVolume = 0.1f;

                    weapons.add(new Weapon(){{
                        shootCone = 360f;
                        x = y = 0;
                        mirror = false;
                        reload = 1f;
                        shootOnDeath = true;
                        deathExplosionEffect = Fx.none;
                        bullet = new ExplosionBulletType(45f, 25f){{
                            shootEffect = new MultiEffect(new WaveEffect(){{
                                sizeTo = 20;
                                strokeFrom = 3.5f;
                                sides = 4;
                                lifetime = 40;
                                colorFrom = EternityPal.zyconColor;
                            }});
                        }};
                    }});
                }};
            }};
            researchCostMultiplier = 0.3f;

            limitRange(5);
            ClassificationMeta.put(this, ruin);
        }};
        gloriousBlaster = new StellarPowerTurret("glorious-blaster"){{
            requirements(Category.turret, BuildVisibility.sandboxOnly, with(lead, 115, silicon, 145, graphite, 120, steel, 90));
            range = 210f;

            recoil = 2.5f;
            reload = 48f;
            shake = 2f;
            shootEffect = EtFx.grandLaserShoot;
            smokeEffect = Fx.none;
            size = 3;
            shootRequire = 0;

            scaledHealth = 275;
            shootSound = Sounds.blaster;
            coolant = consumeCoolant(0.3f);
            drawer = new DrawTurret("stellar-");

            consumePower(7f);

            shootType = new BasicBulletType(10f, 70){{
                width = 12f;
                height = 26f;
                lifetime = 21f;
                sprite = "eternity-case-laser-blast";
                backColor = EternityPal.grandColor;
                shootEffect = smokeEffect = Fx.none;
            }};
            ClassificationMeta.put(this, ruin);
        }};
        grandBlaster = new StellarPowerTurret("grand-blaster"){{
            requirements(Category.turret, with(lead, 115, silicon, 145, graphite, 120, steel, 90));
            range = 180f;

            recoil = 2f;
            reload = 65f;
            shake = 2f;
            shootEffect = EtFx.grandLaserShoot;
            smokeEffect = Fx.none;
            size = 3;
            upgradeBlock = gloriousBlaster;
            scaledHealth = 230;
            shootSound = Sounds.blaster;
            shootRequire = 210;
            coolant = consumeCoolant(0.3f);
            drawer = new DrawTurret("stellar-");

            consumePower(5f);

            shootType = new BasicBulletType(10f, 50){{
                width = 10f;
                height = 24f;
                lifetime = 18f;
                sprite = "eternity-case-laser-blast";
                backColor = EternityPal.grandColor;
                shootEffect = smokeEffect = Fx.none;
            }};
            researchCostMultiplier = 0.3f;
            ClassificationMeta.put(this, ruin);
        }};
        despondence = new StellarPowerTurret("descondence"){{
            requirements(Category.turret, with(cosmicDust, 170, steel, 110, electronicPart, 45, laserPart, 20));
            shootType = new BasicBulletType(5.5f, 90){{
                pierce = pierceBuilding = true;
                ammoMultiplier = 1f;
                lifetime = 32f;
                shrinkX = shrinkY = 0;
                height = width = 18;
                trailWidth = 2.6f;
                trailLength = 18;
                sprite = "eternity-case-sphere";
                hitEffect = despawnEffect = new MultiEffect(new WaveEffect(){{
                    sizeTo = 30;
                    strokeFrom = 3f;
                    lifetime = 20;
                    colorFrom = EternityPal.cosmicColor;
                }},
                new WaveEffect(){{
                    sizeTo = 45;
                    sides = 8;
                    strokeFrom = 3f;
                    lifetime = 35;
                    colorFrom = EternityPal.darkCosmicColor;
                }});
                frontColor = Color.white;
                backColor = hitColor = healColor = lightColor = trailColor = EternityPal.cosmicColor;
                intervalBullet = new BasicBulletType(3f, 10){{
                    width = 8f;
                    hitSize = 5f;
                    height = 13f;
                    pierce = true;
                    lifetime = 35f;
                    pierceBuilding = true;
                    hitColor = backColor = trailColor = EternityPal.cosmicColor;
                    frontColor = Color.white;
                    trailWidth = 2f;
                    trailLength = 4;
                    hitEffect = despawnEffect = new WaveEffect(){{
                        colorFrom = colorTo = EternityPal.cosmicColor;
                        sizeTo = 4f;
                        strokeFrom = 4f;
                        lifetime = 10f;
                    }};
                    buildingDamageMultiplier = 0.5f;
                }};

                bulletInterval = 3.5f;
                intervalRandomSpread = 40f;
                intervalBullets = 3;
                intervalAngle = 0f;
                intervalSpread = 35f;
            }};
            shootRequire = 0;
            squareSprite = false;
            drawer = new DrawTurret("stellar-");
            shoot = new ShootHelix(){{
                scl = 2.2F;
                mag = 4.5F;
                offset = 2F;
            }};
            reload = 170f;
            shootCone = 25f;
            rotateSpeed = 9.5f;
            range = 175f;
            recoil = 3f;
            size = 3;
            scaledHealth = 240;
            shootSound = Sounds.blaster;
            consumePower(6f);
            coolant = consumeCoolant(0.4f);
            ClassificationMeta.put(this, ruin);
        }};
        viraHealer = new StellarPowerTurret("vira-healer"){{
            requirements(Category.effect, with(strontium, 70, gold, 55, steel, 20));
            shootType = new BasicBulletType(){{
                collidesAir = false;
                collidesGround = true;
                collidesTeam = true;
                pierce = pierceBuilding = true;

                ammoMultiplier = 1f;
                speed = 6;
                lifetime = 15f;
                damage = 0;
                shrinkX = shrinkY = 0;
                height = width = 10;
                healPercent = 2.5f;
                sprite = "eternity-case-sphere";
                hitEffect = despawnEffect = new MultiEffect(new WaveEffect(){{
                    sizeTo = 15;
                    strokeFrom = 2f;
                    lifetime = 20;
                    colorFrom = Pal.heal;
                }});
                healEffect = new MultiEffect(Fx.healBlockFull, new WaveEffect(){{
                    sizeTo = 20;
                    strokeFrom = 3f;
                    lifetime = 20;
                    colorFrom = Pal.heal;
                }});
                backColor = frontColor = hitColor = healColor = lightColor = Pal.heal;
            }};
            shootRequire = 0;
            squareSprite = false;
            drawer = new DrawTurret("stellar-");
            reload = 75f;
            shootCone = 40f;
            rotateSpeed = 8f;
            targetAir = false;
            targetGround = false;
            targetHealing = true;
            range = 90f;
            recoil = 1f;
            size = 2;
            health = 470;
            shootSound = Sounds.blaster;
            consumePower(1.5f);
            coolant = consumeCoolant(0.1f);
            ClassificationMeta.put(this, ruin);
        }};
        sanctumBeacon = new StellarPowerTurret("sanctum-beacon"){{
            requirements(Category.effect, with(strontium, 95, steel, 70, repairPart, 25));
            shootType = new BasicBulletType(){{
                collidesAir = false;
                collidesGround = true;
                collidesTeam = true;
                pierce = pierceBuilding = true;
                shootRequire = 0;
                ammoMultiplier = 1f;
                speed = 6;
                lifetime = 70 / 6f;
                damage = 0;
                shrinkX = shrinkY = 0;
                height = width = 10;
                healPercent = 3f;
                sprite = "eternity-case-sphere";
                hitEffect = despawnEffect = new MultiEffect(new WaveEffect(){{
                    sizeTo = 15;
                    strokeFrom = 2f;
                    lifetime = 20;
                    colorFrom = Pal.heal;
                }});
                healEffect = new MultiEffect(Fx.healBlockFull, new WaveEffect(){{
                    sizeTo = 20;
                    strokeFrom = 3f;
                    lifetime = 20;
                    colorFrom = Pal.heal;
                }});
                backColor = frontColor = hitColor = healColor = lightColor = Pal.heal;
            }};
            shootEffect = smokeEffect = Fx.none;
            shootY = 0;
            heatColor = Pal.heal;
            squareSprite = false;
            drawer = new DrawTurret("empty-");
            reload = 6f;
            shootCone = inaccuracy = 360f;
            rotateSpeed = 0f;
            targetAir = false;
            targetGround = false;
            targetHealing = true;
            range = 70f;
            recoil = 0f;
            size = 2;
            health = 600;
            shootSound = Sounds.blaster;
            consumePower(2.6f);
            ClassificationMeta.put(this, ruin);
        }};
        gale = new LiquidTurret("gale"){{
            requirements(Category.turret, with(cobalt, 80, selenium, 35));
            ammo(
                    helium,new BasicBulletType(5, 6){{
                        knockback = 2.5f;
                        drag = 0.002f;
                        lifetime = 32;
                        trailEffect = despawnEffect = hitEffect = new Effect(50f, e -> {
                            color(helium.color);
                            alpha(e.fout());

                            randLenVectors(e.id, 4, 2f + e.finpow() * 5f, (x, y) -> {
                                Fill.circle(e.x + x, e.y + y, 1f + e.fin() * 4f);
                            });
                        });
                        trailInterval = 2;
                        ammoMultiplier = 1;
                    }
                        @Override
                        public void draw(Bullet b){
                            Draw.color(helium.color, Color.white, b.fout() / 100f);
                            Fill.circle(b.x, b.y, 3);
                            Draw.reset();
                        }
                    }
            );
            drawer = new DrawTurret("stellar-");
            shoot.shots = 4;
            velocityRnd = 0.5f;
            extinguish = false;
            size = 2;
            recoil = 2f;
            reload = 30f;
            inaccuracy = 10f;
            shootCone = 35f;
            liquidCapacity = 5f;
            shootEffect = Fx.none;
            range = 160f;
            scaledHealth = 120;
            ClassificationMeta.put(this, cycle);
        }};
        hope = new ItemTurret("hope"){{
            requirements(Category.turret, with(monoShards, 70));
            ammo(
                    monoShards, new BasicBulletType(5f, 8){{
                        width = 7f;
                        height = 11f;
                        homingPower = 0.05f;
                        ammoMultiplier = 7;
                        lifetime = 23f;
                        frontColor = EternityPal.cultColor;
                        backColor = EternityPal.darkCultColor;
                    }}
            );

            recoil = 1f;
            shootY = 3f;
            reload = 8f;
            range = 135;
            shootCone = 20f;
            ammoUseEffect = Fx.casing1;
            health = 340;
            inaccuracy = 5f;
            rotateSpeed = 8f;
            coolant = consumeCoolant(0.12f);
            researchCostMultiplier = 0.1f;
            drawer = new DrawTurret("cult-");
            outlineColor = Color.valueOf("0e0e0e");

            limitRange();
            ClassificationMeta.put(this, cult);
        }};
        lie = new ItemReplaceTurret("lie"){{
            requirements(Category.turret, with(titanium, 95, silicon, 70, lead, 120));
            ammo(
                    titanium, new PointBulletType(){{
                        damage = 30;
                        ammoMultiplier = 2;
                        lifetime = 20f;
                        trailEffect = EtFx.cultTrail;
                        trailInterval = 4;
                        pierceArmor = true;
                        trailSpacing = 5;
                        hitEffect = despawnEffect = new MultiEffect(new WaveEffect(){{
                            sizeTo = 35;
                            strokeFrom = 4f;
                            sides = 4;
                            lifetime = 45;
                            colorFrom = EternityPal.cultColor;
                        }},
                        new WaveEffect(){{
                            sizeTo = 20;
                            strokeFrom = 4f;
                            sides = 4;
                            lifetime = 80;
                            colorFrom = EternityPal.darkCultColor;
                        }});
                    }},
                    nissin, new PointBulletType(){{
                        damage = 55;
                        splashDamage = 10;
                        splashDamageRadius = 12;
                        ammoMultiplier = 1.5f;
                        lifetime = 25f;
                        trailEffect = EtFx.cultTrail;
                        trailInterval = 4;
                        pierceArmor = true;
                        trailSpacing = 5;
                        hitEffect = despawnEffect = new MultiEffect(new WaveEffect(){{
                            sizeTo = 35;
                            strokeFrom = 4f;
                            sides = 4;
                            lifetime = 45;
                            colorFrom = EternityPal.cultColor;
                        }},
                                new WaveEffect(){{
                                    sizeTo = 20;
                                    strokeFrom = 4f;
                                    sides = 4;
                                    lifetime = 80;
                                    colorFrom = EternityPal.darkCultColor;
                                }},
                                new WaveEffect(){{
                                    sizeTo = 30 ;
                                    strokeFrom = 4f;
                                    sides = 8;
                                    lifetime = 60;
                                    colorFrom = EternityPal.nissinColor;
                                }});
                    }}
            );
            predictTarget = false;
            size = 2;
            replaceBlock = hope;
            recoil = 2f;
            shootY = -2f;
            reload = 65f;
            range = 190;
            shootCone = 20f;
            ammoUseEffect = Fx.casing2;
            ammoPerShot = 4;
            scaledHealth = 340;
            rotateSpeed = 5.5f;
            coolant = consumeCoolant(0.2f);
            researchCostMultiplier = 0.2f;
            drawer = new DrawTurret("cult-");
            outlineColor = Color.valueOf("0e0e0e");

            limitRange();
            ClassificationMeta.put(this, cult);
        }};
        resonance = new PowerTurret("resonance"){{
            requirements(Category.turret, with(monoShards, 220, plastanium, 80, wardCapsule, 20));
            shootType = new BasicBulletType(10f, 180f){{
                width = 10f;
                height = 10f;
                lifetime = 17f;
                backColor = EternityPal.cultColor;
                shootEffect = smokeEffect = Fx.none;
            }};
            shootY = 4;
            connectedPower = false;
            squareSprite = false;
            outlineColor = Color.valueOf("0e0e0e");
            drawer = new DrawTurret("cult-");
            reload = 75f;
            shootCone = 15f;
            rotateSpeed = 11f;
            range = 170f;
            recoil = 1f;
            size = 3;
            health = 1370;
            shootSound = Sounds.pulseBlast;
            researchCostMultiplier = 0.05f;
            consumeLiquid(oil, 20f / 60f);
            ClassificationMeta.put(this, cult);
        }};
        well = new ItemTurret("well"){{
            requirements(Category.turret, with(copper, 155, plastanium, 170, titanium, 210, silicon, 190, graphite, 240));
            ammo(
                    plastanium, new BasicBulletType(5f, 40){{
                        width = 12f;
                        height = 17f;
                        pierce = true;
                        lifetime = 34f;
                        ammoMultiplier = 2;
                        frontColor = EternityPal.cultColor;
                        backColor = EternityPal.darkCultColor;
                        intervalBullet = new BasicBulletType(2.5f, 15, "bullet"){{
                            width = 12f;
                            height = 12f;
                            shrinkY = 1f;
                            lifetime = 15f;
                            frontColor = EternityPal.cultColor;
                            backColor = EternityPal.darkCultColor;
                            despawnEffect = Fx.none;
                        }};
                        intervalBullets = 6;
                        intervalDelay = 10;
                        trailEffect = new ParticleEffect(){{
                            sizeFrom = 7;
                            region = "bullet";
                            baseRotation = 90;
                            colorFrom = EternityPal.darkCultColor;
                            lifetime = 15;
                            length = 0;
                            cone = 0;
                        }};
                        trailRotation = true;
                        trailInterval = 1;
                    }}
            );
            shoot.shots = 3;
            shoot.shotDelay = 6;

            recoil = 0.5f;
            shootY = 3f;
            reload = 45f;
            range = 170;
            shootCone = 15f;
            size = 4;
            ammoUseEffect = Fx.casing1;
            scaledHealth = 250;
            inaccuracy = 2f;
            rotateSpeed = 8.5f;
            coolant = consumeCoolant(0.4f);
            researchCostMultiplier = 0.3f;
            drawer = new DrawTurret("cult-");
            outlineColor = Color.valueOf("0e0e0e");

            limitRange();
            ClassificationMeta.put(this, cult);
        }};
        mionDroneBlock = new PlaceUnitBlock("mion-drone-block"){{
            requirements(Category.units, with(titanium, 110, metaglass, 60, silicon, 75, steel, 40));
            unitType = mionDrone;
            size = 2;
            hasShadow = false;
            ClassificationMeta.put(this, ruin);
        }};
    }
}
