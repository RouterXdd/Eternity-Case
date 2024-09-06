package eternity.content;

import arc.struct.Seq;
import mindustry.content.Planets;
import mindustry.game.Objectives.*;

import static eternity.content.EtItems.*;
import static eternity.content.EternityBlocks.*;
import static eternity.content.EternitySectors.*;
import static eternity.content.EternityTechTreeRoots.*;
import static eternity.content.EternityUnits.*;
import static mindustry.content.TechTree.*;
import static mindustry.content.UnitTypes.*;

public class RuinexTechTree{
        public static void load(){
    EtPlanets.ruinex.techTree = nodeRoot("stellar", stellarRoot, () -> {
        node(stellarDuct, () ->{
            node(stellarRouter, () ->{

            });
        });
        node(commandBase, () ->{
            node(coreDrone, () ->{

            });
            node(viraDrone, () ->{

            });
            node(commandCenter, () ->{
                node(coreDrone, () ->{

                });
                node(viraDrone, () ->{

                });
                node(quasar, () ->{

                });

            });
        });
        node(brokenDrill, () ->{
            node(cosmicExcavator, () ->{

            });
        });
        node(steelFoundry, () ->{
            node(filter, () ->{
                node(glassFabricator, () ->{
                    node(electronicWeaver, () ->{
                        node(plateForge, () ->{

                        });

                    });
                });
            });
        });
        node(lightningNode, () ->{
            node(stellarRTG, () ->{
                node(thermalReactor, () ->{

                });
            });
        });
        node(fragilePump, () ->{

        });
        node(novaTurret, () ->{
            node(aegisBarrier, Seq.with(new SectorComplete(devaste)), () ->{
                node(infiniteFortress, () ->{

                });
                node(titanBarrier, () ->{

                });
            });

            node(coreBlaster, () ->{
                node(viraHealer, () ->{
                    node(sanctumBeacon, () ->{

                    });
                });
                node(nexxonPhasor, () ->{
                    node(coldPhasor, () ->{

                    });
                    node(zyconStorm, () ->{
                        node(poseidon, () ->{

                        });
                        node(grandBlaster, () ->{
                            node(gloriousBlaster, () ->{

                            });
                            node(despondence, () ->{

                            });

                        });

                    });
                });

            });
        });
        node(advancedRadar, () ->{

        });
        node(devaste, () ->{

        });
        nodeProduce(strontium, () ->{
            nodeProduce(gold, () ->{
                nodeProduce(steel, () ->{
                    nodeProduce(electronicPart, () ->{
                        nodeProduce(repairPart, () ->{

                        });
                        nodeProduce(platePart, () ->{

                        });
                        nodeProduce(laserPart, () ->{

                        });
                        nodeProduce(launchPart, () ->{

                        });
                        nodeProduce(darknessPart, () ->{

                        });
                    });
                    nodeProduce(cosmicDust, () ->{
                        nodeProduce(heatproofGlass, () ->{

                        });
                        nodeProduce(oxidePalladium, () ->{
                            nodeProduce(palladium, () ->{

                            });
                        });
                    });
                    nodeProduce(ruinSandItem, () ->{

                    });
                });
            });
        });
    });
}}
