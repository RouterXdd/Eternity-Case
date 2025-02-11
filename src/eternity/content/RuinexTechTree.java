package eternity.content;

import arc.struct.Seq;
import mindustry.game.Objectives.*;

import static eternity.content.EtItems.*;
import static eternity.content.EtBlocks.*;
import static eternity.content.EtSectors.*;
import static eternity.content.EtUnitTypes.*;
import static mindustry.content.TechTree.*;
import static mindustry.content.UnitTypes.*;

public class RuinexTechTree{
        public static void load(){
    EtPlanets.ruinex.techTree = nodeRoot("stellar", commandBase, () -> {
        node(stellarDuct, () ->{
            node(stellarRouter, () ->{
                node(stellarBridge, () ->{

                });
            });
        });
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
        node(brokenDrill, () ->{
            node(cosmicExcavator, () ->{

            });
        });
        node(steelFoundry, Seq.with(new OnSector(ruinShore)), () ->{
            node(filter, () ->{
                node(glassFabricator, () ->{

                });
                node(electronicWeaver, () ->{
                    node(plateForge, () ->{

                    });
                });
            });
        });
        node(lightningNode, Seq.with(new OnSector(ruinShore)), () ->{
            node(stellarRTG, () ->{
                node(thermalReactor, () ->{

                });
            });
        });
        node(fragilePump, Seq.with(new SectorComplete(ruinShore)), () ->{

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
            node(ruinShore, Seq.with(new SectorComplete(devaste)), () ->{

            });
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
