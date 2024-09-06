package eternity;

import arc.*;
import arc.util.*;
import eternity.content.*;
import mindustry.game.EventType.*;
import mindustry.mod.*;
import mindustry.ui.dialogs.*;

import static mindustry.Vars.mods;

public class EternityMain extends Mod{

    public EternityMain(){

    }

    @Override
    public void loadContent(){
        EternityTechTreeRoots.load();
        EtStatuses.load();
        EtLiquids.load();
        EtItems.load();
        EternityUnits.load();
        EternityBlocks.load();
        EtPlanets.load();
        EternitySectors.load();
        RuinexTechTree.load();
    }

}
