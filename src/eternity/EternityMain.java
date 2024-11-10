package eternity;

import eternity.classes.mod.Classification;
import eternity.classes.mod.ClassificationMeta;
import eternity.content.*;
import mindustry.mod.*;

public class EternityMain extends Mod{

    public EternityMain(){

    }

    @Override
    public void loadContent(){
        Classification.init();
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
        ClassificationMeta.init();
    }

}
