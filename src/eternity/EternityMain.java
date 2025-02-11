package eternity;

import arc.Events;
import arc.Settings;
import eternity.classes.mod.Classification;
import eternity.classes.mod.ClassificationMeta;
import eternity.content.*;
import eternity.graphic.EtEmojis;
import mindustry.game.EventType;
import mindustry.mod.*;

import static arc.Core.settings;

public class EternityMain extends Mod{

    public EternityMain(){
        //Huh uh, no linear filtering for you
        Events.on(EventType.ClientLoadEvent.class, e -> {
            settings.put("linear", false);
        });
    }
    @Override
    public void init() {
        EtEmojis.load();
    }

    @Override
    public void loadContent(){
        Classification.init();
        EtSounds.load();
        EternityTeams.load();
        EtAttributes.load();
        EtStatuses.load();
        EtLiquids.load();
        EtItems.load();
        EtUnitTypes.load();
        EtBlocks.load();
        EtSchematics.load();
        EtPlanets.load();
        EtSectors.load();
        RuinexTechTree.load();
        CyclefiteTechTree.load();
        ClassificationMeta.init();
    }

}
