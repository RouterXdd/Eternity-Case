package eternity.content;

import arc.Core;
import arc.graphics.Color;
import arc.graphics.g2d.Draw;
import arc.graphics.g2d.Fill;
import arc.math.Interp;
import arc.math.Mathf;
import arc.math.geom.Rect;
import arc.struct.ObjectSet;
import arc.struct.Seq;
import arc.util.Time;
import eternity.classes.entities.abilities.*;
import eternity.classes.entities.bullets.AdvBasicBulletType;
import eternity.classes.entities.draw.BladeDraw;
import eternity.classes.entities.weapons.AdvWeapon;
import eternity.classes.mod.ClassificationMeta;
import eternity.classes.type.*;
import eternity.graphic.EternityPal;
import mindustry.ai.UnitCommand;
import mindustry.ai.types.*;
import mindustry.content.*;
import mindustry.entities.abilities.EnergyFieldAbility;
import mindustry.entities.abilities.MoveEffectAbility;
import mindustry.entities.bullet.*;
import mindustry.entities.effect.*;
import mindustry.entities.part.*;
import mindustry.entities.pattern.*;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.type.*;
import mindustry.type.ammo.*;
import mindustry.type.unit.*;
import mindustry.type.weapons.RepairBeamWeapon;
import mindustry.world.meta.Env;

import static eternity.classes.mod.Classification.*;
import static eternity.content.EtStatuses.*;
import static mindustry.Vars.*;
import static mindustry.type.ItemStack.*;
import static mindustry.content.Items.*;
import static mindustry.content.StatusEffects.*;
import static eternity.content.EtItems.*;

public class EtUnitTypes {
    public static UnitType
            //stellar units
            coreDrone, viraDrone, frostDrone,
            star, constellation, accumulation, starfall, space,
            commander, overseer, overlord, emperor, kingdom,
            barricade, shield, barrier, border, dome,
            discuss, skirmish, fight, conflict, war,
            mionDrone, viraSavior, oblivion,
            //Malachite units
            infectedMionDrone, infectedMionDroneL, infectedMionDroneXL, electron, proton, neutron, atom, infectedGerb,
            twist, force,
            //Stellar encycle units
            mistake, bug, exploit,
            mineral, geode, gem,
            //cult units
            graviton, fault,
            //random units
            clear, tyranny, sandStorm, aquarion, bloodwave, liquidShock, aphelops, cultura, abyss, radiance, tasik, jewel, depth, resurrection, skintphase,
            //defiance ported units
            pain,
            spinor;
    public static BulletType oblivionBullet, ancientBomb;
    public static void load(){
        oblivionBullet = new BasicBulletType(6.5f, 80){{
            width = 16f;
            height = 30f;
            lifetime = 26f;
            sprite = "eternity-case-laser-blast";
            backColor = EternityPal.oblivionColor;
            shootEffect = smokeEffect = Fx.none;
        }};
        ancientBomb = new BasicBulletType(7f, 300){{
            width = 12f;
            height = 12f;
            hitSize = 18;
            splashDamage = 250;
            splashDamageRadius = 8 * 10;
            lifetime = 80f;
            sprite = "eternity-case-sphere";
            backColor = Color.valueOf("f2e878");
            shootEffect = smokeEffect = Fx.none;
            despawnHit = true;
            hitEffect = new MultiEffect(
                    EtFx.plasmaExplosion
            );
        }};
        coreDrone = new StellarUnit("core-drone"){{
            constructor = UnitEntity::create;
            aiController = BuilderAI::new;
            isEnemy = false;

            lowAltitude = true;
            flying = true;
            mineSpeed = 2.5f;
            mineTier = 2;
            buildSpeed = 0.5f;
            drag = 0.05f;
            speed = 3.5f;
            rotateSpeed = 12f;
            accel = 0.1f;
            fogRadius = 0f;
            itemCapacity = 20;
            health = 340f;
            engineSize = 0f;
            hitSize = 9f;
            alwaysUnlocked = true;
            ClassificationMeta.put(this, ruin);

            weapons.add(new Weapon("none-weapon"){{
                reload = 16.5f;
                x = 4.75f;
                y = 4f;
                top = false;
                ejectEffect = Fx.none;

                bullet = new BasicBulletType(4f, 10){{
                    width = 6f;
                    height = 14f;
                    lifetime = 25f;
                    sprite = "eternity-case-laser-blast";
                    backColor = Pal.redderDust;
                    shootEffect = smokeEffect = Fx.none;
                    buildingDamageMultiplier = 0.01f;
                    shootSound = Sounds.blaster;
                }};
            }});
            parts.add(new RegionPart("-engine"){{
                          y = 0;
                          layerOffset = -0.001F;
                          under = true;
                          mirror = outline = false;
                          color = Color.valueOf("cb4848ff");
                          colorTo = Pal.redderDust;
                          progress = p -> Mathf.sinDeg(Time.time * 7f);
                      }},
                    new RegionPart("-engine2"){{
                        y = 0;
                        layerOffset = -0.001F;
                        under = true;
                        mirror = outline = false;
                        color = Color.valueOf("ffffffe6");
                        colorTo = Color.valueOf("ffffff");
                        progress = p -> Mathf.sinDeg(Time.time * 7f);
                    }});
        }};
        viraDrone = new StellarUnit("vira-drone"){{
            constructor = UnitEntity::create;
            aiController = BuilderAI::new;
            isEnemy = false;

            lowAltitude = true;
            flying = true;
            mineSpeed = 2f;
            mineTier = 2;
            buildSpeed = 0.85f;
            drag = 0.05f;
            speed = 3.5f;
            rotateSpeed = 12f;
            accel = 0.1f;
            fogRadius = 0f;
            itemCapacity = 20;
            health = 210f;
            engineSize = 0f;
            hitSize = 9f;
            alwaysUnlocked = true;
            ClassificationMeta.put(this, ruin);
            parts.add(new RegionPart("-engine"){{
                          y = 0;
                          layerOffset = -0.001F;
                          under = true;
                          mirror = outline = false;
                          color = Color.valueOf("98ffa98c");
                          colorTo = Pal.heal;
                          progress = p -> Mathf.sinDeg(Time.time * 7f);
                      }},
                    new RegionPart("-engine2"){{
                        y = 0;
                        layerOffset = -0.001F;
                        under = true;
                        mirror = outline = false;
                        color = Color.valueOf("ffffffe6");
                        colorTo = Color.valueOf("ffffff");
                        progress = p -> Mathf.sinDeg(Time.time * 7f);
            }});


            weapons.add(new RepairBeamWeapon(){{
                widthSinMag = 0.10f;
                reload = 20f;
                x = 0f;
                y = 3f;
                rotate = false;
                shootY = 0f;
                beamWidth = 0.5f;
                repairSpeed = 2.25f;
                fractionRepairSpeed = 0.1f;
                aimDst = 0f;
                shootCone = 15f;
                mirror = false;

                targetUnits = false;
                targetBuildings = true;
                autoTarget = false;
                controllable = true;
                laserColor = Pal.heal;
                healColor = Pal.heal;

                bullet = new BulletType(){{
                    maxRange = 45f;
                }};
            }});
        }};
        frostDrone = new StellarUnit("frost-drone"){{
            constructor = UnitEntity::create;
            aiController = BuilderAI::new;
            isEnemy = false;

            lowAltitude = true;
            flying = true;
            mineSpeed = 5.5f;
            mineTier = 2;
            buildSpeed = 0.6f;
            drag = 0.06f;
            speed = 2.2f;
            rotateSpeed = 10f;
            accel = 0.1f;
            fogRadius = 0f;
            itemCapacity = 20;
            health = 300f;
            engineSize = 0f;
            hitSize = 9f;
            alwaysUnlocked = true;
            immunities.add(freezing);
            ClassificationMeta.put(this, cycle);

            weapons.add(new Weapon("none-weapon"){{
                reload = 35f;
                x = 0f;
                y = 2.75f;
                top = false;
                ejectEffect = Fx.none;

                bullet = new BasicBulletType(5f, 20){{
                    width = 8f;
                    height = 8f;
                    splashDamage = 5;
                    splashDamageRadius = 8 * 5;
                    status = freezing;
                    statusDuration = 60 * 3.5f;
                    lifetime = 20f;
                    sprite = "eternity-case-sphere";
                    backColor = Color.valueOf("75b1cb");
                    shootEffect = smokeEffect = Fx.none;
                    buildingDamageMultiplier = 0.01f;
                    shootSound = Sounds.blaster;
                }};
            }});
            parts.add(new RegionPart("-engine"){{
                          y = 0;
                          layerOffset = -0.001F;
                          under = true;
                          mirror = outline = false;
                          color = Color.valueOf("54769c");
                          colorTo = Color.valueOf("75b1cb");
                          progress = p -> Mathf.sinDeg(Time.time * 7f);
                      }},
                    new RegionPart("-engine2"){{
                        y = 0;
                        layerOffset = -0.001F;
                        under = true;
                        mirror = outline = false;
                        color = Color.valueOf("ffffffe6");
                        colorTo = Color.valueOf("ffffff");
                        progress = p -> Mathf.sinDeg(Time.time * 7f);
                    }});
        }};

        star = new StellarTankUnit("star"){{
            constructor = TankUnit::create;
            hitSize = 11f;
            treadPullOffset = 3;
            speed = 0.95f;
            rotateSpeed = 2.85f;
            health = 245;
            armor = 5f;
            itemCapacity = 0;
            treadRects = new Rect[]{new Rect(25 - 32f, 7 - 32f, 14, 14), new Rect(25 - 32f, 43 - 32f, 14, 18)};
            ClassificationMeta.put(this, ruin);
            parts.add(new FlarePart(){{
                progress = p -> Mathf.cosDeg(Time.time * 4.5f);
                color1 = Color.valueOf("dcda5b");
                layer = Layer.groundUnit + 1;
                radius = 5f;
                radiusTo = 20f;
                stroke = 3f;
                rotation = 0f;
                followRotation = true;
            }});

            weapons.add(new Weapon("star-weapon"){{
                layerOffset = 0.0001f;
                reload = 65f;
                shootY = 0f;
                recoil = 0f;
                rotate = true;
                rotateSpeed = 100f;
                mirror = false;
                x = 0f;
                y = 0f;
                heatColor = Color.valueOf("f9350f");
                cooldownTime = 35f;

                bullet = new BasicBulletType(3f, 20){{
                    sprite = "eternity-case-star-b";
                    splashDamage = 15;
                    splashDamageRadius = 3 * 8f;
                    scaledSplashDamage = false;
                    smokeEffect = shootEffect = Fx.none;
                    shrinkX = shrinkY = 0;
                    width = 10f;
                    height = 10f;
                    lifetime = 30f;
                    hitSize = 5f;
                    hitColor = backColor = trailColor = Color.valueOf("dcda5b");
                    frontColor = Color.white;
                    trailWidth = 1.5f;
                    trailLength = 7;
                    shootSound = Sounds.shockBlast;
                    despawnEffect = hitEffect = Fx.hitBulletColor;
                }};
            }});
        }};
        //TODO
        constellation = new StellarTankUnit("constellation"){{
            constructor = TankUnit::create;
            hitSize = 15.5f;
            treadPullOffset = 0;
            speed = 0.8f;
            rotateSpeed = 2.7f;
            health = 655;
            armor = 8f;
            itemCapacity = 0;
            treadRects = new Rect[]{new Rect(29 - 39f, 6 - 39f, 20, 65)};
            ClassificationMeta.put(this, ruin);
            weapons.add(new Weapon("eternity-case-constellation-weapon"){{
                layerOffset = 0.0001f;
                reload = 190f;
                shootY = 5f;
                recoil = 1f;
                rotate = true;
                rotateSpeed = 3f;
                mirror = false;
                x = 0f;
                y = 0f;
                heatColor = Color.valueOf("f9350f");
                cooldownTime = 190f;
                continuous = alwaysContinuous = true;
                shootSound = Sounds.beam;
                bullet = new ContinuousLaserBulletType(){{
                    damage = 8.5f;
                    length = 70f;
                    width = 3;
                    hitEffect = Fx.hitMeltdown;
                    drawSize = 50f;
                    lifetime = 160f;
                    shake = 0.2f;
                    despawnEffect = Fx.smokeCloud;
                    smokeEffect = Fx.none;
                    pierceCap = 2;
                    incendChance = 0f;

                    colors = new Color[]{Color.valueOf("dcda5b").cpy().a(.2f), Color.valueOf("dcda5b").cpy().a(.5f), Color.valueOf("dcda5b").cpy().mul(1.2f), Color.white};
                }};
            }});
        }};
        //TODO
        accumulation = new StellarTankUnit("accumulation"){{
            constructor = TankUnit::create;
            hitSize = 19f;
            treadPullOffset = 0;
            speed = 0.67f;
            rotateSpeed = 2.485f;
            health = 1460;
            armor = 11f;
            itemCapacity = 0;
            treadRects = new Rect[]{new Rect(28 - 54f, 9 - 54f, 14, 15), new Rect(43 - 54f, 88 - 54f, 11, 12)};
            ClassificationMeta.put(this, ruin);
        }};
        //TODO
        starfall = new StellarTankUnit("starfall"){{
            constructor = TankUnit::create;
            hitSize = 23.5f;
            treadPullOffset = 0;
            speed = 0.55f;
            rotateSpeed = 2.35f;
            health = 4790;
            armor = 15f;
            itemCapacity = 0;
            treadRects = new Rect[]{new Rect(32 - 71f, 19 - 71f, 21, 24), new Rect(49 - 71f, 84 - 71f, 13, 48) };
            ClassificationMeta.put(this, ruin);
        }};
        space = new StellarTankUnit("space"){{
            constructor = TankUnit::create;
            hitSize = 42f;
            treadPullOffset = 0;
            speed = 0.38f;
            rotateSpeed = 2.1f;
            health = 18760;
            armor = 20f;
            itemCapacity = 0;
            targetAir = false;
            treadRects = new Rect[]{new Rect(30 - 113f, 38 - 113f, 28, 35), new Rect(92 - 113f, 24 - 113f, 21, 42), new Rect(75 - 113f, 161 - 113f, 38, 41)};
            ClassificationMeta.put(this, ruin);
            weapons.add(new Weapon("eternity-case-space-weapon"){{
                    shootSound = Sounds.missileLarge;
                    x = 0f / 4f;
                    y = -40f / 4f;
                    mirror = false;
                    rotate = false;
                    rotateSpeed = 0f;
                    reload = 200f;
                    layerOffset = 1f;
                    recoil = 0f;
                    shootY = 40f / 4f;
                    shootCone = 360f;
                    shoot.shots = 3;
                    shoot.shotDelay = 20f;
                    shoot.firstShotDelay = 100f;
                                bullet = new BasicBulletType(8f, 200f){{
                                    sprite = "eternity-case-under-missile";
                                    backSprite = "eternity-case-none";
                                    layer = Layer.floor + 0.5f;
                                    smokeEffect = shootEffect = Fx.none;
                                    shrinkX = shrinkY = 0;
                                    splashDamage = 350;
                                    splashDamageRadius = 65;
                                    homingPower = 1;
                                    homingRange = 280;
                                    width = height = 18f;
                                    lifetime = 35f;
                                    frontColor = backColor = Color.valueOf("06060926");
                                    hitColor = Color.valueOf("dcda5b");
                                    hitEffect = despawnEffect = new MultiEffect(Fx.massiveExplosion, Fx.scatheExplosion, Fx.scatheLight, new WaveEffect(){{
                                        lifetime = 10f;
                                        strokeFrom = 4f;
                                        sizeTo = 130f;
                                    }});
                                    trailEffect = Fx.mine;
                                    trailChance = 0.3f;
                                    absorbable = reflectable = hittable = false;

                                    collidesAir = false;
                                    buildingDamageMultiplier = 0.8f;

                                    ammoMultiplier = 1f;
                                    fragLifeMin = 0.1f;
                                    fragBullets = 7;
                                    fragBullet = new ArtilleryBulletType(3.4f, 32){{
                                        buildingDamageMultiplier = 0.8f;
                                        drag = 0.02f;
                                        hitEffect = Fx.massiveExplosion;
                                        despawnEffect = Fx.scatheSlash;
                                        knockback = 0.8f;
                                        lifetime = 23f;
                                        width = height = 18f;
                                        collidesTiles = false;
                                        splashDamageRadius = 40f;
                                        splashDamage = 160f;
                                        trailColor = hitColor = Color.valueOf("dcda5b");
                                        frontColor = backColor = Color.valueOf("413030a6");
                                        smokeEffect = Fx.shootBigSmoke2;
                                        despawnShake = 7f;

                                        lightRadius = 30f;
                                        lightColor = Color.valueOf("dcda5b");
                                        lightOpacity = 0.5f;

                                        trailLength = 20;
                                        trailWidth = 3.5f;
                                        trailEffect = Fx.none;
                                    }};
                                }};
                    }},
                    new Weapon("eternity-case-space-weapon-fx"){{
                            shootSound = Sounds.none;
                            x = 0f / 4f;
                            y = -40f / 4f;
                            mirror = false;
                            rotate = false;
                            rotateSpeed = 0f;
                            reload = 200f;
                            layerOffset = 1f;
                            recoil = 0f;
                            shootY = 40f / 4f;
                            shootCone = 360f;
                            shootStatus = unmoving;
                            shootStatusDuration = 260;
                            bullet = new BulletType(){{
                                shootEffect = new MultiEffect(
                                        new ParticleEffect(){{
                                            region = "eternity-case-floor-crack";
                                            sizeFrom = sizeTo = 32;
                                            particles = 1;
                                            randLength = false;
                                            length = cone = 0;
                                            colorFrom = Color.valueOf("303441f2");
                                            colorTo = Color.clear;
                                            lifetime = 290;
                                            layer = Layer.floor + 1f;
                                        }},
                                        new ParticleEffect(){{
                                            region = "eternity-case-missile-fall";
                                            sizeFrom = 8;
                                            sizeTo = 0;
                                            particles = 1;
                                            randLength = false;
                                            length = 16;
                                            offsetX = -10;
                                            cone = 0;
                                            colorTo = Color.clear;
                                            lifetime = 25;
                                            startDelay = 20;
                                            interp = Interp.sine;
                                            layer = Layer.floor + 2f;
                                        }},
                                        new ParticleEffect(){{
                                            region = "eternity-case-missile-fall";
                                            sizeFrom = 8;
                                            sizeTo = 0;
                                            particles = 1;
                                            randLength = false;
                                            length = 17;
                                            offsetX = -10;
                                            cone = 0;
                                            colorTo = Color.clear;
                                            lifetime = 25;
                                            startDelay = 45;
                                            interp = Interp.sine;
                                            layer = Layer.floor + 2f;
                                        }},
                                        new ParticleEffect(){{
                                            region = "eternity-case-missile-fall";
                                            sizeFrom = 8;
                                            sizeTo = 0;
                                            particles = 1;
                                            randLength = false;
                                            length = 18;
                                            offsetX = -10;
                                            cone = 0;
                                            colorTo = Color.clear;
                                            lifetime = 25;
                                            startDelay = 70;
                                            interp = Interp.sine;
                                            layer = Layer.floor + 2f;
                                        }}
                                );
                                collidesAir = false;
                                absorbable = reflectable = hittable = false;
                                smokeEffect = hitEffect = despawnEffect = Fx.none;
                                damage = 0;
                                homingPower = 1;
                                homingRange = Float.MAX_VALUE;
                                speed = 8;
                                lifetime = 35f;
                            }};
                    }}
            );
        }};
        commander = new SpyUnitType("commander"){{
            constructor = TankUnit::create;
            squareShape = true;
            omniMovement = false;
            rotateMoveFirst = true;
            envDisabled = Env.none;
            hitSize = 11f;
            treadPullOffset = 3;
            speed = 0.8f;
            rotateSpeed = 3.15f;
            health = 190;
            armor = 2f;
            itemCapacity = 0;
            treadRects = new Rect[]{new Rect(9 - 32f, 37 - 32f, 11, 9), new Rect(24 - 32f, 52 - 32f, 8, 8)};
            ClassificationMeta.put(this, ruin);
            weapons.add(new AdvWeapon("eternity-case-commander-weapon"){{
                shootSound = Sounds.pew;
                y = -2.5f;
                x = 0;
                top = rotate = true;
                mirror = false;
                reload = 8f;
                shootCone = 35f;
                inaccuracy = 25;
                bullet = new BasicBulletType(){{
                    speed = 3f;
                    damage = 5;
                    width = 6f;
                    height = 10f;
                    lifetime = 15f;
                    shootEffect = Fx.sparkShoot;
                    smokeEffect = Fx.shootBigSmoke;
                    hitColor = backColor = trailColor = EternityPal.droneColor;
                    sprite = "eternity-case-laser-blast";
                    frontColor = Color.white;
                    hitEffect = despawnEffect = Fx.hitBulletColor;
                }};
            }});
        }};
        barricade = new StellarUnit("barricade"){{
            hovering = true;
            shadowElevation = 0.1f;

            drag = 0.07f;
            speed = 1.55f;
            rotateSpeed = 4.8f;
            buildSpeed = 0.05f;
            buildBeamOffset = 6.5f;

            accel = 0.09f;
            health = 340f;
            armor = 2f;
            hitSize = 11f;
            engineOffset = 7f;
            engineSize = 0f;
            itemCapacity = 0;
            useEngineElevation = false;
            constructor = ElevationMoveUnit::create;
            ClassificationMeta.put(this, ruin);

            abilities.add(new MoveEffectAbility(0f, -2f, EternityPal.viraColor, Fx.missileTrailShort, 3f));

                parts.add(new HoverPart(){{
                    x = 0f;
                    y = -2.5f;
                    mirror = false;
                    radius = 8.5f;
                    phase = 70f;
                    stroke = 2.5f;
                    layerOffset = -0.001f;
                    color = EternityPal.viraColor;
                }});

            weapons.add(new AdvWeapon("eternity-case-barricade-weapon"){{
                shootSound = Sounds.pew;
                y = -1.25f;
                x = 3.75f;
                top = true;
                mirror = rotate = true;
                reload = 45f;
                shootCone = 70f;

                bullet = new AdvBasicBulletType(){{
                    speed = 5.5f;
                    damage = 20;
                    leechAmount = 5;
                    homingPower = 0.3f;
                    homingDelay = 9f;
                    width = 7f;
                    height = 12f;
                    lifetime = 22.5f;
                    shootEffect = Fx.sparkShoot;
                    smokeEffect = Fx.shootBigSmoke;
                    hitColor = backColor = trailColor = EternityPal.viraColor;
                    frontColor = Color.white;
                    trailWidth = 1.5f;
                    trailLength = 5;
                    hitEffect = despawnEffect = Fx.hitBulletColor;
                }};
            }});
        }};
        //TODO
        shield = new StellarUnit("shield"){{
            hovering = true;
            shadowElevation = 0.12f;

            drag = 0.09f;
            speed = 1.12f;
            rotateSpeed = 4.2f;
            buildSpeed = 0.1f;
            buildBeamOffset = 8f;

            accel = 0.09f;
            health = 870f;
            armor = 3f;
            hitSize = 17f;
            engineOffset = 7f;
            engineSize = 0f;
            itemCapacity = 0;
            useEngineElevation = false;
            constructor = ElevationMoveUnit::create;
            ClassificationMeta.put(this, ruin);

            abilities.add(new MoveEffectAbility(4.25f, 0f, EternityPal.viraColor, Fx.missileTrailShort, 4f), new MoveEffectAbility(-4.25f, 0f, EternityPal.viraColor, Fx.missileTrailShort, 3f));

            parts.add(new HoverPart(){{
                x = 4.25f;
                y = 0f;
                mirror = true;
                radius = 12f;
                phase = 80f;
                stroke = 3.6f;
                layerOffset = -0.001f;
                color = EternityPal.viraColor;
            }});
        }};
        //TODO
        barrier = new StellarUnit("barrier"){{
            hovering = true;
            shadowElevation = 0.2f;

            drag = 0.11f;
            speed = 0.9f;
            rotateSpeed = 3.7f;
            buildSpeed = 0.2f;
            buildBeamOffset = 11f;

            accel = 0.09f;
            health = 1730f;
            armor = 4f;
            hitSize = 23f;
            engineOffset = 7f;
            engineSize = 0f;
            itemCapacity = 0;
            useEngineElevation = false;
            constructor = ElevationMoveUnit::create;
            ClassificationMeta.put(this, ruin);

            abilities.add(new MoveEffectAbility(8f, -5.5f, EternityPal.viraColor, Fx.missileTrailShort, 3f), new MoveEffectAbility(-8f, -5.5f, EternityPal.viraColor, Fx.missileTrailShort, 3f), new MoveEffectAbility(0f, -7f, EternityPal.viraColor, Fx.missileTrailShort, 3f));

            parts.add(new HoverPart(){{
                x = 8f;
                y = -5.5f;
                mirror = true;
                radius = 17.5f;
                phase = 95f;
                stroke = 4f;
                layerOffset = -0.001f;
                color = EternityPal.viraColor;
            }},
                new HoverPart(){{
                    x = 0f;
                    y = -7f;
                    mirror = false;
                    radius = 17.5f;
                    phase = 95f;
                    stroke = 4f;
                    layerOffset = -0.001f;
                    color = EternityPal.viraColor;
            }});
        }};
        border = new StellarUnit("border"){{
            hovering = true;
            shadowElevation = 0.25f;

            drag = 0.14f;
            speed = 0.68f;
            rotateSpeed = 3.2f;
            buildSpeed = 0.35f;
            buildBeamOffset = 16f;

            accel = 0.09f;
            health = 5430f;
            armor = 5f;
            hitSize = 29f;
            engineOffset = 7f;
            engineSize = 0f;
            itemCapacity = 0;
            useEngineElevation = false;
            constructor = ElevationMoveUnit::create;
            ClassificationMeta.put(this, ruin);

            abilities.add(new MoveEffectAbility(8f, -9f, EternityPal.viraColor, Fx.missileTrailShort, 3f), new MoveEffectAbility(-8f, -9f, EternityPal.viraColor, Fx.missileTrailShort, 3f), new MoveEffectAbility(9f, 4f, EternityPal.viraColor, Fx.missileTrailShort, 3f), new MoveEffectAbility(-9f, 4f, EternityPal.viraColor, Fx.missileTrailShort, 3f));

            parts.add(new HoverPart(){{
                          x = 8f;
                          y = -9f;
                          mirror = true;
                          radius = 18f;
                          phase = 110f;
                          stroke = 5f;
                          layerOffset = -0.001f;
                          color = EternityPal.viraColor;
                      }},
                    new HoverPart(){{
                        x = 9f;
                        y = 4f;
                        mirror = true;
                        radius = 18f;
                        phase = 110f;
                        stroke = 5f;
                        layerOffset = -0.001f;
                        color = EternityPal.viraColor;
                    }});
        }};
        dome = new StellarUnit("dome"){{
            hovering = true;
            shadowElevation = 0.25f;

            drag = 0.14f;
            speed = 0.68f;
            rotateSpeed = 3.2f;
            buildSpeed = 0.5f;
            buildBeamOffset = 16f;

            accel = 0.09f;
            health = 21200f;
            armor = 8f;
            hitSize = 35f;
            engineOffset = 7f;
            engineSize = 0f;
            itemCapacity = 0;
            useEngineElevation = false;
            constructor = ElevationMoveUnit::create;
            ClassificationMeta.put(this, ruin);

            abilities.add(new MoveEffectAbility(6.5f, -12.5f, EternityPal.viraColor, Fx.missileTrailShort, 3f), new MoveEffectAbility(-6.5f, -12.5f, EternityPal.viraColor, Fx.missileTrailShort, 3f), new MoveEffectAbility(13f, -4.75f, EternityPal.viraColor, Fx.missileTrailShort, 3f), new MoveEffectAbility(-13f, -4.75f, EternityPal.viraColor, Fx.missileTrailShort, 3f));
            abilities.add(new MoveEffectAbility(12.75f, 5.5f, EternityPal.viraColor, Fx.missileTrailShort, 3f), new MoveEffectAbility(-12.75f, 5.5f, EternityPal.viraColor, Fx.missileTrailShort, 3f));

            parts.add(
                    new HoverPart(){{
                        x = 6.5f;
                        y = -12.5f;
                        mirror = true;
                        radius = 20f;
                        phase = 110f;
                        stroke = 5f;
                        layerOffset = -0.001f;
                        color = EternityPal.viraColor;
                    }},
                    new HoverPart(){{
                        x = 13f;
                        y = -4.75f;
                        mirror = true;
                        radius = 20f;
                        phase = 110f;
                        stroke = 5f;
                        layerOffset = -0.001f;
                        color = EternityPal.viraColor;
                    }},
                    new HoverPart(){{
                        x = 12.75f;
                        y = 5.5f;
                        mirror = true;
                        radius = 20f;
                        phase = 110f;
                        stroke = 5f;
                        layerOffset = -0.001f;
                        color = EternityPal.viraColor;
                    }});
        }};
        mionDrone = new StellarUnit("mion-drone"){{
            constructor = UnitEntity::create;
            controller = u -> new MinerAI(){{
                mineItems = Seq.with(strontium, gold);
                targetItem = strontium;
            }};

            defaultCommand = UnitCommand.mineCommand;

            flying = true;
            drag = 0.06f;
            accel = 0.12f;
            speed = 1.7f;
            health = 230;
            engineSize = 2.2f;
            engineOffset = 6f;
            range = 40f;
            isEnemy = false;

            ammoType = new PowerAmmoType(500);
            ClassificationMeta.put(this, ruin);

            mineTier = 2;
            mineSpeed = 3f;
            abilities.add(new ItemProductionAbility(){{
                produceItems = with(gold, 2);
            }});
        }};
        oblivion = new StellarUnit("oblivion"){{
            speed = 0.55f;
            accel = 0.12f;
            drag = 0.12f;
            flying = true;
            health = 18500;
            armor = 6;
            engineSize = 0f;
            hitSize = 26;
            itemCapacity = 100;
            constructor = UnitEntity::create;
            ammoType = new PowerAmmoType(3600);
            abilities.add(new EnrageAbility());
            ClassificationMeta.put(this, ruin);
            parts.add(new RegionPart("-engine"){{
                y = 0;
                layerOffset = -0.001F;
                under = true;
                mirror = outline = false;
                color = Color.valueOf("f2a1468c");
                colorTo = EternityPal.oblivionColor;
                progress = p -> Mathf.sinDeg(Time.time * 7f);
            }},
                    new RegionPart("-engine2"){{
                        y = 0;
                        layerOffset = -0.001F;
                        under = true;
                        mirror = outline = false;
                        color = Color.valueOf("ffffffe6");
                        colorTo = Color.valueOf("ffffff");
                        progress = p -> Mathf.sinDeg(Time.time * 7f);
                    }});

            weapons.add(new Weapon("eternity-case-oblivion-weapon"){{
                y = 2.25f;
                x = 17.25f;
                shootY = 2.5f;
                rotate = true;
                reload = 6.5f;
                ejectEffect = Fx.none;
                heatColor = EternityPal.oblivionColor;
                layerOffset = -0.001F;
                bullet = oblivionBullet;
                shootSound = Sounds.blaster;
            }},
                    new Weapon("eternity-case-oblivion-weapon"){{
                        y = 6.75f;
                        x = 10.5f;
                        shootY = 2.5f;
                        rotate = true;
                        reload = 6.5f;
                        ejectEffect = Fx.none;
                        heatColor = EternityPal.oblivionColor;
                        layerOffset = -0.002F;
                        bullet = oblivionBullet;
                        shootSound = Sounds.blaster;
                    }}
            );
        }};
        infectedMionDrone = new StellarUnit("infected-mion-drone"){{
            constructor = UnitEntity::create;
            flying = true;
            drag = 0.06f;
            accel = 0.12f;
            speed = 1.7f;
            health = 230;
            engineSize = 2.2f;
            engineOffset = 6f;
            range = 35f;
            ClassificationMeta.put(this, malachite);

            weapons.add(new Weapon(){{
                shootOnDeath = true;
                reload = 60f;
                shootCone = 90f;
                ejectEffect = Fx.none;
                shootSound = Sounds.explosion;
                x = shootY = 0f;
                mirror = false;
                bullet = new BulletType(){{
                    collidesTiles = false;
                    collides = false;
                    hitSound = Sounds.explosion;
                    shootEffect = Fx.none;
                    status = EtStatuses.infected;
                    statusDuration = 140;
                    rangeOverride = 30f;
                    hitEffect = Fx.pulverize;
                    speed = 0f;
                    splashDamageRadius = 30f;
                    instantDisappear = true;
                    splashDamage = 20f;
                    killShooter = true;
                    hittable = false;
                    collidesAir = true;
                }};
            }});
            abilities.add(new CrystalLandAbility());
        }};
        electron = new StellarUnit("electron"){{
            speed = 1.32f;
            drag = 0.4f;
            hitSize = 12f;
            rotateSpeed = 3.4f;
            health = 310;

            legCount = 4;
            legLength = 7.5f;
            legForwardScl = 0.8f;
            legMoveSpace = 1.25f;
            hovering = true;
            armor = 2f;
            constructor = LegsUnit::create;

            shadowElevation = 0.2f;
            groundLayer = Layer.legUnit - 1f;
            ClassificationMeta.put(this, malachite);

            weapons.add(new Weapon("electron-weapon"){{
                top = mirror = false;
                shootY = 0f;
                reload = 30f;
                ejectEffect = Fx.none;
                recoil = 0f;
                x = 0f;
                shootSound = Sounds.spark;

                bullet = new LightningBulletType(){{
                    lightningColor = hitColor = EternityPal.malachiteColor;
                    damage = 14f;
                    lightningLength = 10;
                    lightningLengthRand = 5;
                    shootEffect = Fx.shootHeal;

                    lightningType = new BulletType(0.0001f, 0f) {{
                        lifetime = Fx.lightning.lifetime;
                        hitEffect = Fx.hitLancer;
                        despawnEffect = Fx.none;
                        status = StatusEffects.shocked;
                        statusDuration = 10f;
                        hittable = false;
                    }};
                }};
            }},
                    new AdvWeapon("electron-death-weapon"){{
                        top = mirror = display = false;
                        shootY = 0f;
                        alwaysShooting = true;
                        healthPercent = 35;
                        ejectEffect = Fx.none;
                        recoil = 0f;
                        x = 0f;
                        shootSound = Sounds.spark;
                        inaccuracy = 360;
                        shoot.shots = 8;
                        shoot.firstShotDelay = 0.85f * 60;
                        shootStatus = unmoving;
                        shootStatusDuration = shoot.firstShotDelay;
                        reload = shoot.firstShotDelay;

                        bullet = new LightningBulletType(){{
                            lightningColor = hitColor = EternityPal.malachiteColor;
                            damage = 45f;
                            lightningLength = 10;
                            lightningLengthRand = 8;
                            shootEffect = Fx.shootHeal;
                            chargeEffect = new WaveEffect(){{
                                colorFrom = colorTo = EternityPal.malachiteColor;
                                sizeFrom = 4 * 8;
                                sizeTo = 0;
                                lifetime = shoot.firstShotDelay;
                            }};

                            lightningType = new BulletType(0.0001f, 0f) {{
                                lifetime = Fx.lightning.lifetime;
                                hitEffect = Fx.hitLancer;
                                despawnEffect = Fx.none;
                                status = StatusEffects.shocked;
                                statusDuration = 20f;
                                hittable = false;
                            }};
                        }};
                    }});
        }};
        infectedGerb = new StellarUnit("infected-gerb"){{
            health = 280;
            outlineColor = Color.valueOf("032310");

            legMinLength = 0.9f;
            legMaxLength = 1.1f;
            hitSize = 16;
            legMoveSpace = 1.2f;
            legLength = 9;
            legPairOffset = 1;
            legExtension = 0.5f;
            rotateSpeed = 6;
            legBaseOffset = 4;
            faceTarget = true;
            allowLegStep = false;
            drownTimeMultiplier = 9000;
            mechStepParticles = true;
            hovering = true;
            armor = 5f;
            constructor = LegsUnit::create;
            ClassificationMeta.put(this, malachite);

            immunities.add(infected);
            envEnabled|= Env.terrestrial | Env.underwater;
            envDisabled = Env.none;
            legStraightness = 0.3f;
            baseLegStraightness = 0.5f;
            lockLegBase = true;
            speed = 0.42f;
            constructor = LegsUnit::create;
            parts.add(new RegionPart("-outline"){{
                          under = true;
                          mirror = outline = false;
                      }});

            weapons.add(new Weapon("infection-launcher"){{
                            top = mirror = false;
                            shootY = 0f;
                            reload = 45f;
                            ejectEffect = Fx.none;
                            recoil = 0f;
                            x = 0f;
                            y = 15f;
                            shootSound = Sounds.shootAlt;
                            shoot = new ShootSpread(){{
                                shots = 3;
                                spread = 6;
                            }};

                            bullet = new BasicBulletType(6f, 25){{
                                smokeEffect = Fx.none;
                                shootEffect = Fx.none;
                                width = 10f;
                                height = 15f;
                                lifetime = 25f;
                                hitSize = 6f;
                                hitColor = backColor = trailColor = EternityPal.darkMalachiteColor;
                                frontColor = EternityPal.malachiteColor;
                                trailWidth = 2f;
                                trailLength = 7;
                                status = infected;
                                statusDuration = 8 * 60;
                                despawnEffect = hitEffect = Fx.hitBulletColor;
                            }};
                        }});
        }};
        twist = new StellarUnit("twist"){{
            useUnitCap = false;
            controller = u -> new HugAI();
            health = 75;
            outlineColor = Color.valueOf("032310");
            crushDamage = 0.1f;

            baseRegion = legRegion = Core.atlas.white();

            constructor = CrawlUnit::create;
            ClassificationMeta.put(this, malachite);
        }};
        force = new StellarUnit("force"){{
            outlineColor = Color.valueOf("030304");
            speed = 0.95f;
            drag = 0.4f;
            hitSize = 20f;
            rotateSpeed = 4.5f;
            health = 440;
            outlineRadius = 3;

            legCount = 4;
            legLength = 10f;
            legForwardScl = 0.8f;
            legMoveSpace = 1.8f;
            hovering = true;
            armor = 2f;
            constructor = LegsUnit::create;

            shadowElevation = 0.2f;
            groundLayer = Layer.legUnit - 1f;
            ClassificationMeta.put(this, malachite);
            parts.add(new RegionPart("-outline"){{
                under = true;
                mirror = outline = false;
            }});

            weapons.add(new Weapon("force-weapon"){{
                            top = mirror = false;
                            shootY = 7f;
                            reload = 85f;
                            ejectEffect = Fx.none;
                            recoil = 0f;
                            x = 0f;
                            shootSound = Sounds.sap;

                            bullet = new SapBulletType(){{
                                sapStrength = 0f;
                                length = 70f;
                                damage = 80;
                                shootEffect = Fx.shootSmall;
                                hitColor = color = EternityPal.malachiteColor;
                                despawnEffect = Fx.none;
                                width = 0.6f;
                                lifetime = 20f;
                                knockback = 4f;
                            }};
                        }});
        }};
        mistake = new StellarUnit("mistake"){{
            speed = 0.6f;
            drag = 0.07f;
            hitSize = 11f;
            health = 380;
            accel = 0.3f;
            rotateSpeed = 2.5f;
            faceTarget = false;
            constructor = UnitWaterMove::create;
            canBoost = true;
            boostMultiplier = 0.75f;
            engineOffset = 6f;
            engineSize = 2.5f;
            ClassificationMeta.put(this, cycle);

            armor = 2f;
            weapons.add(new Weapon("eternity-case-mistake-weapon"){{
                reload = 40f;
                x = 0;
                top = mirror = false;
                rotate = true;
                shootCone = 50;
                ejectEffect = Fx.none;
                bullet = new MissileBulletType(3.3f, 10){{
                    homingPower = 0.1f;
                    weaveMag = 3;
                    weaveScale = 3;
                    lifetime = 40f;
                    shootEffect = Fx.none;
                    smokeEffect = Fx.none;
                    splashDamage = 5f;
                    splashDamageRadius = 20f;
                    frontColor = Color.white;
                    hitSound = Sounds.none;
                    width = height = 6f;

                    lightColor = trailColor = backColor = Pal.techBlue;
                    lightRadius = 20f;
                    lightOpacity = 0.5f;

                    trailWidth = 2.2f;
                    trailLength = 12;
                    trailChance = -1f;
                    despawnSound = Sounds.dullExplosion;

                    despawnEffect = Fx.none;
                    hitEffect = new ExplosionEffect(){{
                        lifetime = 20f;
                        waveStroke = 2f;
                        waveColor = sparkColor = trailColor;
                        waveRad = 8f;
                        smokeSize = 0f;
                        smokeSizeBase = 0f;
                        sparks = 6;
                        sparkRad = 20f;
                        sparkLen = 3f;
                        sparkStroke = 1.5f;
                    }};
                }};
            }});
        }};
        bug = new StellarUnit("bug"){{
            speed = 0.48f;
            drag = 0.09f;
            hitSize = 17f;
            health = 1080;
            accel = 0.3f;
            rotateSpeed = 2.1f;
            faceTarget = false;
            constructor = UnitWaterMove::create;
            canBoost = true;
            boostMultiplier = 0.8f;
            engineOffset = 9.5f;
            engineSize = 3.6f;
            ClassificationMeta.put(this, cycle);

            armor = 4f;
            weapons.add(new Weapon("eternity-case-bug-weapon"){{
                reload = 65f;
                y = -1;
                x = 0;
                mirror = false;
                top = rotate = true;
                rotateSpeed = 2.6f;
                shootCone = 50;
                ejectEffect = Fx.none;
                bullet = new MissileBulletType(3.3f, 30){{
                    homingPower = 0.1f;
                    weaveMag = 5;
                    weaveScale = 5;
                    lifetime = 50f;
                    shootEffect = Fx.none;
                    smokeEffect = Fx.none;
                    splashDamage = 10f;
                    splashDamageRadius = 20f;
                    frontColor = Color.white;
                    hitSound = Sounds.none;
                    width = height = 8f;
                    collidesGround = collides = false;

                    lightColor = trailColor = backColor = Pal.techBlue;
                    lightRadius = 20f;
                    lightOpacity = 0.5f;

                    trailWidth = 2.5f;
                    trailLength = 12;
                    trailChance = -1f;
                    despawnSound = Sounds.dullExplosion;

                    despawnEffect = Fx.none;
                    hitEffect = new ExplosionEffect(){{
                        lifetime = 25f;
                        waveStroke = 2f;
                        waveColor = sparkColor = trailColor;
                        waveRad = 12f;
                        smokeSize = 0f;
                        smokeSizeBase = 0f;
                        sparks = 8;
                        sparkRad = 20f;
                        sparkLen = 3f;
                        sparkStroke = 1.5f;
                    }};
                    intervalBullet = new BasicBulletType(3f, 20){{
                        width = 3f;
                        hitSize = 5f;
                        height = 7f;
                        lifetime = 20f;
                        hitColor = backColor = trailColor = Pal.techBlue;
                        frontColor = Color.white;
                        trailWidth = 1.5f;
                        trailLength = 5;
                        hitEffect = despawnEffect = new WaveEffect(){{
                            colorFrom = colorTo = Pal.techBlue;
                            sizeTo = 6f;
                            strokeFrom = 4f;
                            lifetime = 18f;
                        }};
                        homingPower = 0.05f;
                    }};

                    bulletInterval = 7f;
                    intervalRandomSpread = 270f;
                    intervalBullets = 2;
                }};
            }});
        }};
        mineral = new StellarUnit("mineral") {
            {
                constructor = MechUnit::create;
                speed = 0.75f;
                hitSize = 7f;
                rotateSpeed = 3f;
                health = 260f;
                armor = 2.5f;
                singleTarget = true;
                immunities.addAll(wet);
                ClassificationMeta.put(this, cycle);
                weapons.add(new Weapon(name + "-weapon") {{
                    x = 5f;
                    y = -0.5f;
                    shootY = 2;
                    top = true;
                    layerOffset = -0.001f;
                    alternate = true;
                    rotate = true;
                    recoil = 1.2f;
                    rotationLimit = 70;
                    rotateSpeed = 2f;
                    reload = 30f;
                    shootCone = 60f;
                    bullet = new BasicBulletType(6f, 10f) {{
                        lifetime = 10f;
                        hitEffect = despawnEffect = Fx.flakExplosion;
                        shootEffect = Fx.shootSmall;
                        trailWidth = 2f;
                        trailLength = 8;
                        impact = true;
                        width = 8f;
                        height = 14f;
                        shrinkY = 0f;
                        shrinkX = 0f;
                        pierceArmor = true;
                        pierceBuilding = false;
                        pierceCap = 1;
                    }};
                }});
            }};
        fault = new UnitType("fault"){{
            speed = 1.15f;
            drag = 0.4f;
            hitSize = 11f;
            rotateSpeed = 3f;
            targetAir = false;
            health = 220;
            immunities = ObjectSet.with(StatusEffects.electrified);

            legCount = 4;
            legLength = 11f;
            legForwardScl = 0.7f;
            legMoveSpace = 1.2f;
            hovering = true;
            armor = 2f;
            constructor = LegsUnit::create;
            ammoType = new ItemAmmoType(Items.graphite);
            outlineColor = Color.valueOf("0e0e0e");

            shadowElevation = 0.2f;
            groundLayer = Layer.legUnit - 1f;
            ClassificationMeta.put(this, cult);

            weapons.add(new Weapon("fault-weapon"){{
                top = false;
                shootY = 2.75f;
                reload = 10f;
                ejectEffect = Fx.none;
                recoil = 1.5f;
                x = 2.25f;
                shootSound = Sounds.pew;

                bullet = new BasicBulletType(4f, 15){{
                    width = 6f;
                    height = 6f;
                    frontColor = EternityPal.cultColor;
                    backColor = EternityPal.darkCultColor;
                    lifetime = 25f;
                }};
            }});
        }};
        //TODO
        clear = new ErekirUnitType("clear"){{
                coreUnitDock = true;
                controller = u -> new BuilderAI(true, 650);
                isEnemy = false;
                envDisabled = 0;

                range = 60f;
                faceTarget = true;
                targetPriority = -2;
                lowAltitude = false;
                mineWalls = true;
                mineFloor = false;
                mineHardnessScaling = false;
                flying = true;
                mineSpeed = 10f;
                mineTier = 5;
                buildSpeed = 2f;
                drag = 0.08f;
                speed = 5.6f;
                rotateSpeed = 7f;
                accel = 0.09f;
                itemCapacity = 60;
                health = 1100f;
                armor = 2f;
                hitSize = 17f;
                engineSize = 0;
                payloadCapacity = 3f * 3f * tilesize * tilesize;
                vulnerableWithPayloads = true;
                constructor = UnitEntity::create;

                fogRadius = 0f;
                targetable = false;
                hittable = false;
                ClassificationMeta.put(this, misc);
            }};
        tyranny = new ErekirUnitType("tyranny"){{
                drag = 0.1f;
                speed = 0.48f;
                hitSize = 31f;
                health = 12600;
                armor = 5f;

                lockLegBase = true;
                legContinuousMove = true;
                legGroupSize = 3;
                legStraightness = 0.4f;
                baseLegStraightness = 0.5f;
                legMaxLength = 1.3f;
                researchCostMultiplier = 0f;

                rotateSpeed = 2.1f;

                legCount = 6;
                legLength = 18f;
                legForwardScl = 0.4f;
                legMoveSpace = 1.4f;
                rippleScale = 2f;
                stepShake = 0.5f;
                legExtension = -8f;
                legBaseOffset = 8f;

                ammoType = new PowerAmmoType(2000);

                legSplashDamage = 32;
                legSplashRange = 30;
                drownTimeMultiplier = 2f;
                constructor = LegsUnit::create;

                hovering = true;
                shadowElevation = 0.4f;
                groundLayer = Layer.legUnit;
                ClassificationMeta.put(this, misc);
                weapons.add(new Weapon("tyranny-blast-weapon"){{
                    top = false;
                    mirror = false;
                    shootY = 0f;
                    reload = 180f;
                    ejectEffect = Fx.none;
                    recoil = 0f;
                    x = 0;
                    y = -1f;
                    shootSound = Sounds.corexplode;

                    bullet = new BasicBulletType(4f, 200, "eternity-case-rib"){{
                        width = 16f;
                        height = 16f;
                        frontColor = EternityPal.cellOrangeFrontColor;
                        backColor = EternityPal.cellOrangeBackColor;
                        pierce = pierceBuilding = true;
                        lifetime = 80f;
                        drag = 0.01f;
                        trailLength = 9;
                        trailColor = EternityPal.cellOrangeFrontColor;
                        trailWidth = 2.5f;
                        fragRandomSpread = 0;
                        fragVelocityMin = 1;
                        fragOnHit = false;
                        fragBullets = 1;
                        fragBullet = shrapnel(shrapnel(shrapnel(shrapnel(shrapnel(shrapnel(shrapnel(shrapnel(shrapnel(shrapnel(shrapnel(shrapnel(shrapnel(shrapnel(null))))))))))))));
                        shootEffect = new MultiEffect(
                                new WaveEffect(){{
                                    colorFrom = EternityPal.cellOrangeFrontColor;
                                    sizeTo = 14;
                                    sides = 12;
                                    strokeFrom = 3.5f;
                                    lifetime = 75;
                                }}
                        );
                        smokeEffect = Fx.none;
                    }};
                }});
            }
            BulletType shrapnel(BulletType frag) {
                return new ShrapnelBulletType(){{
                        toColor = EternityPal.cellOrangeFrontColor;
                        damage = 100f;
                        length = 230;
                        lifetime = 2;
                        fragRandomSpread = 0;
                        fragVelocityMin = fragVelocityMax = 0;
                        fragOnHit = false;
                        fragBullets = 1;
                        fragAngle = 30;
                        fragBullet = frag;
                    }};
            }
        };
        sandStorm = new ErekirUnitType("sand-storm"){{
            hovering = true;
            shadowElevation = 0.15f;
            constructor = ElevationMoveUnit::create;

            drag = 0.07f;
            speed = 2.1f;
            rotateSpeed = 4f;
            omniMovement = false;
            outlineColor = Color.valueOf("3c3835");

            accel = 0.09f;
            health = 7400f;
            armor = 20f;
            hitSize = 17f;
            engineOffset = 6.5f;
            engineSize = 4f;
            itemCapacity = 0;
            useEngineElevation = false;
            researchCostMultiplier = 0f;
            ClassificationMeta.put(this, misc);

            abilities.add(new SawbladeAbility());
            parts.add(new BladeDraw(8));
            weapons.add(new Weapon("none"){{
                top = false;
                shootSound = Sounds.none;
                display = false;
                ejectEffect = Fx.none;
                bullet = new BulletType(0f, 0f){{
                    lifetime = 0f;
                    shootEffect = Fx.none;
                    hitEffect = Fx.none;
                    despawnEffect = Fx.none;
                }};
            }});
        }};
        aquarion = new UnitType("aquarion"){{
            speed = 0.85f;
            drag = 0.13f;
            hitSize = 22f;
            health = 3180;
            accel = 0.4f;
            rotateSpeed = 2.9f;
            faceTarget = false;
            constructor = UnitWaterMove::create;
            outlineColor = Color.valueOf("080811");
            canBoost = true;
            engineOffset = 15.5f;
            engineSize = 4.5f;
            ClassificationMeta.put(this, misc);
            hideDetails = false;

            armor = 5f;
            abilities.add(new AutoCannon(){{
                bullet = new BasicBulletType(8f, 30){{
                    width = 6.5f;
                    height = 15f;
                    lifetime = 25f;
                    shootOnBoost = true;
                }};
            }});
            weapons.add(new Weapon("eternity-case-aquarion-weapon"){{
                reload = 10f;
                x = 9.5f;
                shootY = 3f;
                top = false;
                rotate = false;
                shootCone = 50;
                ejectEffect = Fx.casing1;
                bullet = new BasicBulletType(6f, 45){{
                    width = 9f;
                    height = 14f;
                    lifetime = 45f;
                }};
            }});
        }};
        bloodwave = new UnitType("bloodwave"){{
            constructor = UnitEntity::create;

            lowAltitude = true;
            flying = true;
            drag = 0.06f;
            speed = 1.75f;
            rotateSpeed = 9f;
            accel = 0.025f;
            itemCapacity = 20;
            health = 3480f;
            engineOffset = 13.5f;
            engineSize = 7f;
            hitSize = 23f;
            ClassificationMeta.put(this, misc);

            weapons.add(
                    new Weapon("bloodwave-meat-launcher"){{
                        x = 0;
                        y = -7;
                        mirror = rotate = false;
                        top = false;
                        reload = 190f;
                        shootSound = Sounds.plantBreak;
                        bullet = new BulletType(){{
                            shootEffect = new MultiEffect(Fx.shootBig, EtFx.bloodMissileShoot);
                            smokeEffect = Fx.shootBigSmoke2;
                            shake = 1f;
                            speed = 0f;
                            keepVelocity = false;
                            collidesAir = false;

                            spawnUnit = new MissileUnitType("meat-missile"){{
                                drawCell = false;
                                speed = 2f;
                                maxRange = 6f;
                                rotateSpeed = 2.75f;
                                lifetime = 60f * 3.5f;
                                outlineColor = Color.valueOf("7c1b1b");
                                engineColor = trailColor = EternityPal.bloodRedLight;
                                engineLayer = Layer.effect;
                                health = 300;
                                loopSoundVolume = 0.1f;

                                weapons.add(new Weapon(){{
                                    shootCone = 360f;
                                    mirror = false;
                                    reload = 1f;
                                    shootOnDeath = true;
                                    bullet = new ExplosionBulletType(160f, 25f){{
                                        shootEffect = new MultiEffect(
                                                new WaveEffect(){{
                                                    sizeTo = 40;
                                                    lifetime = 65;
                                                    colorFrom = EternityPal.bloodRed;
                                                }},
                                                new ParticleEffect() {{
                                                    colorFrom = EternityPal.bloodRedLight;
                                                    colorTo = EternityPal.bloodRed;
                                                    particles = 7;
                                                    sizeFrom = 4.5f;
                                                    sizeTo = 0;
                                                }}
                                        );
                                        fragBullets = 9;
                                        fragBullet  = new BasicBulletType(2.5f, 40){
                                            {
                                                width = 5;
                                                height = 5;
                                                lifetime = 40f;
                                                shrinkX = shrinkY = 0;
                                                homingDelay = 4;
                                                homingPower = 0.35f;
                                                homingRange = 35;
                                                backColor = EternityPal.bloodRedLight;
                                                frontColor = EternityPal.bloodRedLight;
                                                sprite = "large-orb";

                                                shootEffect = hitEffect = despawnEffect = new ParticleEffect() {{
                                                    colorFrom = EternityPal.bloodRedLight;
                                                    colorTo = EternityPal.bloodRed;
                                                    particles = 2;
                                                    sizeFrom = 3;
                                                    sizeTo = 0;
                                                }};
                                            }};
                                    }};
                                }});
                            }};
                        }};
                    }}
            );

            outlineColor = Color.valueOf("2b2626");
        }};
        liquidShock = new UnitType("liquid-shock"){{
            speed = 0.4f;
            hitSize = 22f;
            rotateSpeed = 2.5f;
            health = 18500;
            armor = 12f;
            mechFrontSway = 1f;
            ammoType = new ItemAmmoType(surgeAlloy);
            constructor = MechUnit::create;
            mechLegColor = outlineColor = Color.valueOf("0c0b0d");

            mechStepParticles = true;
            stepShake = 0.15f;
            singleTarget = true;
            drownTimeMultiplier = 2.5f;
            ClassificationMeta.put(this, misc);

            weapons.add(
                    new Weapon("eternity-case-liquid-shock-slag-weapon"){{
                        top = mirror = false;
                        y = 0f;
                        x = -17.5f;
                        reload = 120f;
                        recoil = 5f;
                        shake = 2f;
                        ejectEffect = Fx.none;
                        shootSound = Sounds.flame2;
                        inaccuracy = 3f;

                        shoot = new ShootSpread(3, 12);

                        bullet = new FlakBulletType(2.5f, 45){{
                            sprite = "missile-large";
                            collidesGround = collidesAir = true;
                            explodeRange = 20f;
                            width = height = 8f;
                            shrinkY = 0f;
                            homingPower = 0.1f;
                            homingRange = 60f;
                            homingDelay = 20;
                            keepVelocity = false;
                            lightRadius = 60f;
                            lightOpacity = 0.7f;
                            lightColor = Pal.slagOrange;
                            shootEffect = Fx.hitFlamePlasma;

                            splashDamageRadius = 35f;
                            splashDamage = 30f;
                            status = melting;
                            statusDuration = 150;
                            trailColor = Pal.slagOrange;
                            trailWidth = 2f;
                            trailLength = 25;

                            lifetime = 70f;
                            backColor = Pal.slagOrange;
                            frontColor = Color.white;

                            hitEffect = new ExplosionEffect(){{
                                lifetime = 28f;
                                waveStroke = 6f;
                                waveLife = 10f;
                                waveRadBase = 7f;
                                waveColor = Pal.slagOrange;
                                waveRad = 30f;
                                smokes = 6;
                                smokeColor = Color.white;
                                sparkColor = Pal.slagOrange;
                                sparks = 6;
                                sparkRad = 35f;
                                sparkStroke = 1.5f;
                                sparkLen = 5f;
                            }};
                        }};
                    }},
                    new Weapon("eternity-case-liquid-shock-cryo-weapon"){{
                        top = mirror = false;
                        y = 0f;
                        x = 17.5f;
                        reload = 120f;
                        recoil = 5f;
                        shake = 2f;
                        ejectEffect = Fx.none;
                        shootSound = Sounds.shootSmite;
                        inaccuracy = 3f;

                        shoot.shots = 3;
                        shoot.shotDelay = 6;
                        shoot.firstShotDelay = 60;

                        bullet = new BasicBulletType(5f, 90){{
                            width = 12f;
                            height = 26f;
                            lifetime = 35f;
                            sprite = "eternity-case-laser-blast";
                            backColor = Pal.lancerLaser;
                            shootEffect = smokeEffect = Fx.none;
                            status = freezing;
                            statusDuration = 180;
                            pierceBuilding = true;
                            pierceCap = 3;
                        }};
                    }}
            );
        }};
        aphelops = new TankUnitType("aphelops"){{
            constructor = TankUnit::create;
            hitSize = 30f;
            treadPullOffset = 4;
            speed = 0.76f;
            health = 12600;
            armor = 12f;
            rotateSpeed = 3;
            itemCapacity = 0;
            crushDamage = 4.5f;
            treadRects = new Rect[]{new Rect(29 - 154f/2f, 13 - 154f/2f, 24, 126)};
            outlineColor = Color.valueOf("44413c");
            ClassificationMeta.put(this, misc);

            weapons.add(new Weapon("eternity-case-aphelops-weapon"){{
                shootSound = Sounds.mediumCannon;
                layerOffset = 0.0001f;
                reload = 70f;
                shootY = 7.25f;
                shake = 4f;
                recoil = 4f;
                rotate = true;
                rotateSpeed = 1f;
                mirror = false;
                x = 0f;
                y = 0;
                shadow = 28f;
                heatColor = Color.valueOf("f9350f");
                cooldownTime = 80f;

                bullet = new BasicBulletType(8f, 100){{
                    sprite = "missile-large";
                    width = 9.5f;
                    height = 13f;
                    lifetime = 19f;
                    hitSize = 6f;
                    shootEffect = Fx.shootTitan;
                    smokeEffect = Fx.shootSmokeTitan;
                    hitColor = backColor = trailColor = Color.valueOf("feb380");
                    frontColor = Color.white;
                    trailWidth = 3.1f;
                    trailLength = 8;
                    hitEffect = despawnEffect = Fx.blastExplosion;
                    splashDamageRadius = 20f;
                    splashDamage = 200f;

                    fragRandomSpread = 0f;
                    fragSpread = 45f;
                    fragBullets = 8;
                    fragVelocityMin = 1f;
                    despawnSound = Sounds.dullExplosion;

                    fragBullet = new BasicBulletType(8f, 40){{
                        sprite = "missile-large";
                        width = 8f;
                        height = 12f;
                        lifetime = 6f;
                        hitSize = 4f;
                        hitColor = backColor = trailColor = Color.valueOf("feb380");
                        frontColor = Color.white;
                        pierceCap = 2;
                        pierce = pierceBuilding = true;
                        trailWidth = 2.8f;
                        trailLength = 6;
                        hitEffect = despawnEffect = Fx.blastExplosion;
                        splashDamageRadius = 10f;
                        splashDamage = 20f;
                    }};
                }};
            }});
                weapons.add(new Weapon("eternity-case-aphelops-blade-launcher"){{
                    reload = 65;
                    x = 10.25f;
                    y = 10.25f;
                    recoil = 2f;
                    rotate = true;
                    rotateSpeed = 3f;
                    shootSound = Sounds.shockBlast;

                    bullet = new BulletType(){{
                        shootEffect = Fx.shootBig;
                        smokeEffect = Fx.shootBigSmoke2;
                        shake = 1f;
                        speed = 0f;
                        keepVelocity = false;

                        spawnUnit = new MissileUnitType("aphelops-blade"){{
                            speed = 2.5f;
                            maxRange = 6f;
                            lifetime = 60f * 2f;
                            drawCell = false;
                            hoverable = true;
                            parts.add(new BladeDraw(6){{
                                blade = "aphelops-blade-rot";
                                layer = Layer.flyingUnitLow;
                            }});
                            abilities.add(new SawbladeAbility(){{
                                damage = 3;
                                range = 20;
                            }});
                            outlineColor = Color.valueOf("44413c");
                            engineSize = 0;
                            engineLayer = Layer.effect;
                            health = 170;
                            loopSoundVolume = 0.1f;
                            weapons.add(new Weapon(){{
                                shootCone = 360f;
                                mirror = false;
                                reload = 1f;
                                shootOnDeath = true;
                                bullet = new ExplosionBulletType(60f, 25f);
                            }});
                        }};
                    }};
                }});
        }};
        cultura = new ErekirUnitType("cultura"){{
            hovering = true;
            constructor = ElevationMoveUnit::create;
            drawCell = outlines = false;

            drag = 0.1f;
            speed = 4.5f;
            rotateSpeed = 6f;
            outlineColor = Color.valueOf("3c3835");

            accel = 0.09f;
            health = 10000f;
            armor = 50f;
            hitSize = 20f;
            engineOffset = 6.5f;
            engineSize = 0f;
            itemCapacity = 0;
            researchCostMultiplier = 0f;
            ClassificationMeta.put(this, misc);

            abilities.add(new SawbladeAbility(){{
                damage = 25;
                range = 30;
            }});
            weapons.add(new Weapon("none"){{
                top = false;
                shootSound = Sounds.none;
                display = false;
                ejectEffect = Fx.none;
                bullet = new BulletType(0f, 0f){{
                    lifetime = 0f;
                    shootEffect = Fx.none;
                    hitEffect = Fx.none;
                    despawnEffect = Fx.none;
                }};
            }});
        }};
        tasik = new UnitType("tasik"){{
            flying = true;
            speed = 0.6f;
            hitSize = 22f;
            health = 200f;
            armor = 200f;
            ammoType = new PowerAmmoType(100);
            engineOffset = 10;
            engineSize = 4.5f;
            constructor = UnitEntity::create;
            ClassificationMeta.put(this, misc);

            weapons.add(new Weapon(""){{
                top = false;
                shootY = 2f;
                reload = 10f;
                x = 0f;
                alternate = mirror = false;
                ejectEffect = Fx.none;
                recoil = 2f;
                shootSound = Sounds.lasershoot;

                bullet = new LaserBoltBulletType(5f, 20){{
                    lifetime = 30f;
                    healPercent = -2f;
                    healAmount = 5;
                    collidesTeam = true;
                    backColor = Pal.heal;
                    frontColor = Color.white;
                }};
            }});
        }};
        jewel = new UnitType("jewel"){{
            speed = 1.1f;
            drag = 0.55f;
            hitSize = 14f;
            rotateSpeed = 3.6f;
            targetAir = drawCell = false;
            health = 2400;
            immunities = ObjectSet.with(infected, ancientEMP);

            legCount = 2;
            legLength = 21f;
            legMinLength = 0.7f;
            legMaxLength = 1.6f;
            legForwardScl = 0.6f;
            legExtension = -3f;
            legBaseOffset = 5f;
            legMoveSpace = 1.1f;
            hovering = true;
            armor = 5f;

            shadowElevation = 0.2f;
            groundLayer = Layer.legUnit - 1f;
            outlines = false;
            constructor = LegsUnit::create;
            ClassificationMeta.put(this, misc);
        }};

        abyss = new UnitType("abyss"){{
            constructor = UnitEntity::create;

            lowAltitude = true;
            flying = true;
            drag = 0.09f;
            speed = 1.15f;
            rotateSpeed = 3.85f;
            itemCapacity = 0;
            health = 16300f;
            engineOffset = 16.5f;
            engineSize = 6.5f;
            hitSize = 38f;
            ClassificationMeta.put(this, i);

            weapons.add(new AdvWeapon("eternity-case-abyss-main-weapon"){{
                reload = 75f;
                shootY = 0f;
                recoil = 1f;
                rotate = false;
                rotateSpeed = 3f;
                mirror = false;
                x = 0f;
                y = 2f;
                heatColor = Color.valueOf("f9350f");

                bullet = new AdvBasicBulletType(){{
                    smokeEffect = Fx.shootSmallSmoke;
                    shootEffect = Fx.shootSmallColor;
                    damage = 80;
                    speed = 10;
                    width = 9f;
                    height = 17f;
                    lifetime = 20f;
                    ghosts = true;
                    hitColor = backColor = trailColor = Color.valueOf("783dae");
                    frontColor = Color.valueOf("b25bdb");
                    trailWidth = 2.5f;
                    trailLength = 10;
                    despawnEffect = hitEffect = Fx.hitBulletColor;
                }};
            }},
            new AdvWeapon("eternity-case-abyss-side-weapon"){{
                reload = 26f;
                shootY = 0f;
                recoil = 1f;
                rotate = true;
                rotateSpeed = 2.4f;
                rotationLimit = 45;
                shootCone = 10;
                mirror = true;
                inaccuracy = 15;
                shoot.shots = 6;
                velocityRnd = 0.2f;
                x = 15.5f;
                y = 4.5f;

                bullet = new BasicBulletType(){{
                    smokeEffect = Fx.shootSmallSmoke;
                    shootEffect = Fx.shootSmallColor;

                    damage = 20;
                    speed = 6;
                    width = 6f;
                    height = 11f;
                    lifetime = 25f;
                    despawnEffect = hitEffect = Fx.hitBulletColor;
                }};
            }});

            outlineColor = Color.valueOf("000000");
        }};
        radiance = new ErekirUnitType("radiance"){{
            constructor = UnitEntity::create;

            lowAltitude = true;
            flying = true;
            drag = 0.2f;
            speed = 0.3f;
            rotateSpeed = 2f;
            itemCapacity = 1000;
            health = 4500000f;
            armor = 140;
            engineOffset = 46.5f;
            engineSize = 25f;
            engineColor = EternityPal.ancient;
            hitSize = 110f;
            drawCell = false;
            outlineRadius = 5;
            outlineColor = Color.valueOf("1b1b1e");
            immunities.addAll(ancientEMP, freezing, melting, blasted, shocked, sapped, electrified, wet, infected, burning);
            ClassificationMeta.put(this, i);

            weapons.add(new Weapon("eternity-case-radiance-small-weapon"){{
                top = rotate = true;
                shootY = 4f;
                reload = 70f;
                ejectEffect = Fx.none;
                recoil = 2f;
                rotateSpeed = 1.25f;
                y = 34;
                x = 55.25f;
                shootSound = Sounds.pulseBlast;
                bullet = ancientBomb;
            }},
            new Weapon("eternity-case-radiance-small-weapon"){{
                top = rotate = true;
                shootY = 4f;
                reload = 70f;
                ejectEffect = Fx.none;
                recoil = 2f;
                rotateSpeed = 1.25f;
                y = 44.75f;
                x = -83.5f;
                shootSound = Sounds.pulseBlast;
                bullet = ancientBomb;
            }},
            new Weapon("eternity-case-radiance-weapon"){{
                top = rotate = true;
                shootY = 9.25f;
                reload = 25f;
                ejectEffect = Fx.none;
                recoil = 2f;
                rotateSpeed = 1.8f;
                y = -33.5f;
                x = 63.5f;
                shoot = new ShootAlternate(){{
                    shots = 8;
                    shotDelay = 6;
                    spread = 6;
                }};
                bullet = new BasicBulletType(10f, 200){{
                    width = 12f;
                    height = 20f;
                    lifetime = 60f;
                    pierceArmor = pierceBuilding = true;
                    pierceCap = 2;
                    backColor = frontColor = trailColor = EternityPal.ancient;
                    shootEffect = smokeEffect = Fx.none;
                    shootSound = Sounds.shockBlast;
                    trailWidth = 3;
                    trailLength = 22;
                }};
            }},
            new AdvWeapon("eternity-case-radiance-main-weapon"){{
                top = rotate = false;
                mirror = false;
                anywaysShoot = true;
                shootY = 0f;
                reload = 780;
                ejectEffect = Fx.none;
                shootCone = 360;
                recoil = 0;
                y = 47.5f;
                x = 0;
                bullet = new BasicBulletType(0, 0.00000001f){{
                    width = 0;
                    height = 0f;
                    lifetime = 1f;
                    pierceArmor = pierceBuilding = pierce = true;
                    splashDamageRadius = 20 * 60;
                    status = ancientEMP;
                    statusDuration = 320;
                    shootEffect = new MultiEffect(
                            new WaveEffect(){{
                                sizeFrom = 0;
                                sizeTo = splashDamageRadius;
                                lifetime = 30;
                                colorFrom = colorTo = EternityPal.ancient;
                                strokeFrom = 6f;
                                strokeTo = 0;
                            }}
                    );
                }};
                parts.add(
                new HaloPart(){{
                    progress = PartProgress.reload;
                    color = Pal.accent;
                    colorTo = Color.valueOf("00000000");
                    layer = Layer.effect;

                    rotateSpeed = 4f;
                    shapes = 2;
                    stroke = 1.5f;
                    strokeTo = 0;
                    triLength = 20f;
                    triLengthTo = 0;
                    haloRadius = radiusTo = 0f;
                    tri = true;
                    radius = 20f;
                }},
                new HaloPart(){{
                    progress = PartProgress.reload;
                    color = Pal.accent;
                    colorTo = Color.valueOf("00000000");
                    layer = Layer.effect;

                    rotateSpeed = -4f;
                    shapes = 2;
                    stroke = 1.5f;
                    strokeTo = 0;
                    triLength = 20f;
                    triLengthTo = 0;
                    haloRadius = radiusTo = 0f;
                    tri = true;
                    radius = 20f;
                }}, new ShapePart(){{
                    sides = 360;
                    radius = stroke = 15f;
                    radiusTo = strokeTo = 0;
                    layer = Layer.effect;
                    color = EternityPal.ancient;
                    colorTo = Color.valueOf("00000000");
                    progress = PartProgress.reload;
                }});
            }});
        }};
        depth = new UnitType("depth"){{
            flying = true;
            hideDetails = false;
            speed = 3.8f;
            hitSize = 20f;
            health = 2200f;
            armor = 8f;
            ammoType = new PowerAmmoType(200);
            buildSpeed = 1.45f;
            engineOffset = 10;
            engineSize = 0f;
            outlineColor = Color.valueOf("0c0b0d");
            constructor = UnitEntity::create;
            ClassificationMeta.put(this, i);
            abilities.add(new EnergyFieldAbility(30f, 40f, 80f){{
                color = Color.valueOf("dc3232");
                status = none;
                healEffect = Fx.none;
                effectRadius = 3.5f;
                sectors = 3;
                maxTargets = 15;
                healPercent = 3f;
                sameTypeHealMult = 1f;
                y = -3;
            }});
        }};
        //TODO
        resurrection = new UnitType("resurrection"){{
            speed = 0.25f;
            hideDetails = false;
            hitSize = 52f;
            rotateSpeed = 1f;
            health = 1260000;
            armor = 60f;
            mechFrontSway = 1.1f;
            ammoType = new ItemAmmoType(voidAlloy);
            constructor = MechUnit::create;
            mechLegColor = outlineColor = Color.valueOf("121116");

            mechStepParticles = true;
            stepShake = 1f;
            singleTarget = true;
            targetPriority = 1;
            drownTimeMultiplier = 5f;
            outlineRadius = 4;
            ClassificationMeta.put(this, i);
            drawCell = false;

            weapons.add(
                    new Weapon("eternity-case-resurrection-r-weapon"){{
                        top = mirror = false;
                        rotate = true;
                        shootCone = 30;
                        rotationLimit = 35;
                        rotateSpeed = 1.5f;
                        y = 0f;
                        x = 36.25f;
                        reload = 120f;
                        recoil = 5f;
                        shake = 2f;
                        ejectEffect = Fx.none;
                        shootSound = Sounds.flame2;

                        shoot = new ShootBarrel(){{
                            barrels = new float[]{
                                0, 17.5f, 0,
                                2.25f, 16.25f, -10,
                                -2.25f, 16.25f, 10,
                            };
                        }};
                        shoot.shots = 3;

                        bullet = new FlakBulletType(2.5f, 45){{
                            collidesGround = collidesAir = true;
                            explodeRange = 20f;
                            width = height = 8f;
                            shrinkY = 0f;
                            homingPower = 0.1f;
                            homingRange = 60f;
                            homingDelay = 20;
                            keepVelocity = false;
                            lightRadius = 60f;
                            lightOpacity = 0.7f;
                            lightColor = Pal.slagOrange;
                            shootEffect = Fx.hitFlamePlasma;

                            splashDamageRadius = 35f;
                            splashDamage = 30f;
                            status = melting;
                            statusDuration = 150;
                            trailColor = Pal.slagOrange;
                            trailWidth = 2f;
                            trailLength = 25;

                            lifetime = 70f;
                            backColor = Color.valueOf("cfd6de");
                            frontColor = Color.white;

                            hitEffect = new ExplosionEffect(){{
                                lifetime = 28f;
                                waveStroke = 6f;
                                waveLife = 10f;
                                waveRadBase = 7f;
                                waveColor = Pal.slagOrange;
                                waveRad = 30f;
                                smokes = 6;
                                smokeColor = Color.white;
                                sparkColor = Pal.slagOrange;
                                sparks = 6;
                                sparkRad = 35f;
                                sparkStroke = 1.5f;
                                sparkLen = 5f;
                            }};
                        }
                            @Override
                            public void draw(Bullet b){
                            super.draw(b);
                                Draw.color(backColor);
                                Fill.poly(b.x, b.y, 360, 6);
                                Draw.color(Color.valueOf("000000FF"));
                                Fill.poly(b.x, b.y, 360, 5);
                            }
                        };
                    }},
                    new Weapon("eternity-case-resurrection-l-weapon"){{
                        top = mirror = false;
                        y = -0.5f;
                        x = -36.5f;
                        rotate = true;
                        shootCone = 30;
                        rotationLimit = 35;
                        rotateSpeed = 0.9f;
                        shootY = 41.5f;
                        reload = 120f;
                        recoil = 5f;
                        shake = 2f;
                        heatColor = Color.white;
                        ejectEffect = Fx.none;
                        shootSound = Sounds.shootSmite;
                        inaccuracy = 3f;

                        shoot.shots = 3;
                        shoot.shotDelay = 6;

                        bullet = new BasicBulletType(5f, 90){{
                            width = 12f;
                            height = 26f;
                            lifetime = 35f;
                            sprite = "eternity-case-laser-blast";
                            backColor = Pal.lancerLaser;
                            shootEffect = smokeEffect = Fx.none;
                            status = freezing;
                            statusDuration = 180;
                            pierceBuilding = true;
                            pierceCap = 3;
                        }};
                    }}
            );
        }};
        pain = new TankUnitType("pain"){{
            constructor = TankUnit::create;
            hitSize = 13f;
            treadPullOffset = 3;
            speed = 1.2f;
            rotateSpeed = 3.5f;
            health = 470;
            armor = 2f;
            itemCapacity = 0;
            treadRects = new Rect[]{new Rect(10 - 32f, 22 - 32f, 10, 20), new Rect(16 - 32f, 42 - 32f, 10, 15)};
            researchCostMultiplier = 0f;
            outlineColor = Color.valueOf("101113");
            ClassificationMeta.put(this, i);

            weapons.add(new Weapon("eternity-case-pain-weapon"){{
                layerOffset = 0.0001f;
                reload = 13f;
                shootY = 4.25f;
                recoil = 1f;
                rotate = true;
                rotateSpeed = 3f;
                mirror = false;
                x = 0f;
                y = -2f;
                heatColor = Color.valueOf("f9350f");
                cooldownTime = 25f;

                bullet = new BasicBulletType(5f, 12){{
                    sprite = "missile-large";
                    smokeEffect = Fx.shootSmallSmoke;
                    shootEffect = Fx.shootSmallColor;
                    width = 3f;
                    height = 5f;
                    lifetime = 25f;
                    hitSize = 4f;
                    hitColor = backColor = trailColor = Color.valueOf("ff5a5a");
                    frontColor = Color.white;
                    trailWidth = 1f;
                    trailLength = 6;
                    despawnEffect = hitEffect = Fx.hitBulletColor;
                }};
            }});
        }};
        spinor = new UnitType("spinor"){{
            outlineColor = Color.valueOf("101113");
            isEnemy = false;
            constructor = PayloadUnit::create;

            range = 60f;
            faceTarget = true;
            targetPriority = -1;
            lowAltitude = false;
            flying = true;
            buildSpeed = 0.2f;
            drag = 0.08f;
            speed = 2.1f;
            rotateSpeed = 8f;
            accel = 0.09f;
            itemCapacity = 10;
            health = 450f;
            armor = 1f;
            hitSize = 11f;
            engineSize = 0;
            payloadCapacity = 2f * 2f * tilesize * tilesize;
            pickupUnits = false;

            fogRadius = 0f;
            ClassificationMeta.put(this, i);

            setEnginesMirror(
                    new UnitEngine(-18 / 4f, -20 / 4f, 2.5f, -135f)
            );
        }};
    }
}
