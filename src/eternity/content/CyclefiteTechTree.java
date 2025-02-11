package eternity.content;

import static eternity.content.EtItems.*;
import static eternity.content.EtBlocks.*;
import static eternity.content.EtLiquids.*;
import static eternity.content.EtSectors.*;
import static eternity.content.EtUnitTypes.*;
import static mindustry.content.TechTree.*;

public class CyclefiteTechTree {
    public static void load(){
        EtPlanets.cyclefite.techTree = nodeRoot("stellar-cycle", coreFrost, () -> {
            node(cobaltDuct, () ->{
                node(cobaltRouter, () ->{
                    node(cobaltBridge, () ->{

                    });
                });
            });
            node(frostDrone, () ->{

            });
            node(frostyDrill, () ->{
                node(radiator, () ->{

                });
            });
            node(atmosphericSeparator, () ->{
                node(paralineKiln, () ->{
                    node(mercuryExtractor, () ->{

                    });
                    node(lunarConstructor, () ->{

                    });
                });
            });
            node(gale, () ->{

                node(cobaltWall, () ->{
                    node(cobaltWallLarge, () ->{

                    });
                });
                node(whirl, () ->{
                    node(ignite, () ->{

                    });
                });
            });
            node(freezeReactor, () ->{

            });
            node(frostDrone, () ->{

            });
            node(cobaltPort, () ->{
                node(mistake, () ->{
                    node(bug, () ->{

                    });
                });
            });
            node(frostborn, () ->{

            });
            nodeProduce(cobalt, () ->{
                nodeProduce(selenium, () ->{
                    nodeProduce(paraline, () ->{

                    });
                    nodeProduce(anthracite, () ->{
                        nodeProduce(redcury, () ->{

                        });
                    });
                });
                nodeProduce(helium, () ->{
                    nodeProduce(mercury, () ->{

                    });
                });
            });
        });
        }
}
