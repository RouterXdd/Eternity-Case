package eternity.classes.blocks.storage;

import arc.Core;
import arc.graphics.g2d.Draw;
import arc.graphics.g2d.TextureRegion;
import arc.math.geom.Point2;
import eternity.content.EtPlanets;
import mindustry.Vars;
import mindustry.content.Planets;
import mindustry.game.Team;
import mindustry.world.Tile;
import mindustry.world.blocks.storage.CoreBlock;

public class EternityCore extends CoreBlock {
    public TextureRegion[] baseRegion = new TextureRegion[6];
    public TextureRegion iconRegion;
    public EternityCore(String name) {
        super(name);
    }
    @Override
    public void load(){
        super.load();
        iconRegion = Core.atlas.find(name + "-base-icon");
        for (int i = 0; i < baseRegion.length; i++) {
            baseRegion[i] = Core.atlas.find(name + "-base" + i);
        }
    }
    @Override
    public TextureRegion[] icons(){
        return new TextureRegion[]{region,iconRegion,teamRegions[Team.sharded.id]};
    }
    public class EternityCoreBuild extends CoreBuild {
        @Override
        public void draw() {
            super.draw();
            if (Vars.state.rules.planet == Planets.erekir){
                Draw.rect(baseRegion[1], x, y);
            }
            else if (Vars.state.rules.planet == EtPlanets.abotium || Vars.state.rules.planet == EtPlanets.derin){
                Draw.rect(baseRegion[2], x, y);
            }
            else if (Vars.state.rules.planet == EtPlanets.ruinex || Vars.state.rules.planet == EtPlanets.cyclefite){
                Draw.rect(baseRegion[3], x, y);
            }
            else if (Vars.state.rules.planet == EtPlanets.versat){
                Draw.rect(baseRegion[4], x, y);
            }
            else if (Vars.state.rules.planet == Vars.content.planet("dusted-lands-krakai")){
                Draw.rect(baseRegion[5], x, y);
            }
            else if (Vars.state.rules.planet == Vars.content.planet("icicle-world-rki")){
                Draw.rect(baseRegion[6], x, y);
            }
            else {
                Draw.rect(baseRegion[0], x, y);
            }
            drawTeamTop();
        }
    }
}
