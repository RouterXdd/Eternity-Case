package eternity.classes.blocks.production;

import arc.Core;
import arc.graphics.g2d.Draw;
import arc.graphics.g2d.TextureRegion;
import arc.util.io.Reads;
import arc.util.io.Writes;
import mindustry.graphics.Pal;
import mindustry.ui.Bar;
import mindustry.world.blocks.production.Drill;

public class FrostyDrill extends Drill {
    public TextureRegion iceRegion;
    public float frostSpeed = 60 * 70;
    public FrostyDrill(String name) {
        super(name);
        buildType = FrostyDrillBuild::new;
        squareSprite = false;
    }
    @Override
    public void load() {
        super.load();
        iceRegion = Core.atlas.find(this.name + "-ice");
    }
    @Override
    public void setBars(){
        super.setBars();
        addBar("frost", (FrostyDrillBuild entity) -> new Bar("bar.frost", Pal.techBlue, () -> entity.frost));
    }
    public class FrostyDrillBuild extends DrillBuild {
        public float frost;
        @Override
        public void updateTile(){
            if (frost <= 1){
                frost += delta() / frostSpeed;
            }
            efficiency = 1 - frost;
            super.updateTile();
        }
        @Override
        public void draw() {
            super.draw();
            Draw.alpha(frost);
            Draw.rect(iceRegion, x, y);
        }
        @Override
        public void write(Writes write){
            super.write(write);
            write.f(frost);
        }

        @Override
        public void read(Reads read, byte revision){
            super.read(read, revision);
            frost = read.f();
        }


    }
}
