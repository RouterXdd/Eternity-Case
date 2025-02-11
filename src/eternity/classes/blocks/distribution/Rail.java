package eternity.classes.blocks.distribution;

import arc.Core;
import arc.graphics.g2d.*;
import arc.math.Interp;
import arc.math.Mathf;
import arc.math.geom.Geometry;
import arc.util.Eachable;
import arc.util.Tmp;
import mindustry.entities.units.BuildPlan;
import mindustry.graphics.Layer;
import mindustry.world.Tile;

import static mindustry.Vars.*;
import static mindustry.gen.Iconc.link;

public class Rail extends CapRegionDuct{
    public Rail(String name) {
        super(name);
        armored = true;
        hasShadow = false;
    }
    @Override
    public void drawPlanRegion(BuildPlan plan, Eachable<BuildPlan> list){
        int[] bits = getTiling(plan, list);

        if(bits == null) return;

        Draw.scl(bits[1], bits[2]);
        Draw.rect(botRegions[bits[0]], plan.drawx(), plan.drawy(), plan.rotation * 90);
        Draw.color();
        Draw.rect(topRegions[bits[0]], plan.drawx(), plan.drawy(), plan.rotation * 90);
        Draw.scl();
    }
    public class RailBuild extends CapRegionDuctBuild {
        @Override
        public void draw(){
            float rotation = rotdeg();
            int r = this.rotation;

            //draw extra ducts facing this one for tiling purposes
            for(int i = 0; i < 4; i++){
                if((blending & (1 << i)) != 0){
                    int dir = r - i;
                    float rot = i == 0 ? rotation : (dir)*90;
                    drawAt(x + Geometry.d4x(dir) * tilesize*0.75f, y + Geometry.d4y(dir) * tilesize*0.75f, 0, rot, i != 0 ? SliceMode.bottom : SliceMode.top);
                }
            }

            //draw item
            if(current != null){
                Draw.z(Layer.blockUnder + 0.2f);
                Tmp.v1.set(Geometry.d4x(recDir) * tilesize / 2f, Geometry.d4y(recDir) * tilesize / 2f)
                        .lerp(Geometry.d4x(r) * tilesize / 2f, Geometry.d4y(r) * tilesize / 2f,
                                Mathf.clamp((progress + 1f) / 2f));

                Draw.rect(current.fullIcon, x + Tmp.v1.x, y + Tmp.v1.y, itemSize, itemSize);
            }

            Draw.scl(xscl, yscl);
            NdrawAt(x, y, blendbits, rotation, SliceMode.none);
            Draw.z(Layer.blockUnder);
            if(capped && capRegion.found()) Draw.rect(capRegion, x, y, rotdeg());
            if(backCapped && capRegion.found()) Draw.rect(capRegion, x, y, rotdeg() + 180);
            Draw.reset();
        }
        protected void NdrawAt(float x, float y, int bits, float rotation, SliceMode slice){
            Draw.z(Layer.blockUnder - 0.1f);
            Draw.rect(sliced(botRegions[bits], slice), x, y, rotation);
        }
    }
}
