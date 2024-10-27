package eternity.classes.blocks.distribution;

import arc.Core;
import arc.graphics.g2d.*;
import mindustry.gen.Building;
import mindustry.world.blocks.distribution.Duct;

public class CapRegionDuct extends Duct {
    public TextureRegion capRegion;
    public CapRegionDuct(String name) {
        super(name);
    }
    @Override
    public void load() {
        super.load();
        capRegion = Core.atlas.find(this.name + "-cap");
    }
    @Override
    public TextureRegion[] icons(){
        return new TextureRegion[]{botRegions[0], topRegions[0]};
    }
    public class CapRegionDuctBuild extends DuctBuild {
        public boolean capped, backCapped = false;
        @Override
        public void draw(){
            super.draw();
            if(capped && capRegion.found()) Draw.rect(capRegion, x, y, rotdeg());
            if(backCapped && capRegion.found()) Draw.rect(capRegion, x, y, rotdeg() + 180);
        }
        @Override
        public void onProximityUpdate(){
            super.onProximityUpdate();
            Building prev = back();
            capped = next == null || next.team != team || !next.block.hasItems;
            backCapped = blendbits == 0 && (prev == null || prev.team != team || !prev.block.hasItems);
        }
    }
}
