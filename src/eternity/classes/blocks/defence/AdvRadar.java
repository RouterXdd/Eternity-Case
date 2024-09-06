package eternity.classes.blocks.defence;

import arc.math.Mathf;
import eternity.content.EtStatuses;
import mindustry.Vars;
import mindustry.entities.Units;
import mindustry.gen.Building;
import mindustry.world.blocks.defense.Radar;

public class AdvRadar extends Radar {
    public AdvRadar(String name) {
        super(name);
    }
    public class AdvRadarBuild extends RadarBuild {
        @Override
        public void updateTile(){
            super.updateTile();
            Units.nearbyEnemies(this.team, x,y,fogRadius() * 8f, u -> {
                u.apply(EtStatuses.detected, 5);
            });
        }
    }
}
