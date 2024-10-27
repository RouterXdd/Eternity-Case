package eternity;

import eternity.content.*;
import mindustry.mod.*;

public class EternityMain extends Mod{

    public EternityMain(){

    }

    @Override
    public void loadContent(){
        EtTechTreeRoots.load();
        EtAttributes.load();
        EtStatuses.load();
        EtLiquids.load();
        EtItems.load();
        EtUnits.load();
        EtBlocks.load();
        EtSchematics.load();
        EtPlanets.load();
        EtSectors.load();
        RuinexTechTree.load();
    }

}
