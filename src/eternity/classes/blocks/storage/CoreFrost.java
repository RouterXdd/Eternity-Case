package eternity.classes.blocks.storage;

import arc.graphics.g2d.TextureRegion;
import arc.math.Mathf;
import arc.util.io.Reads;
import arc.util.io.Writes;
import mindustry.content.Fx;
import mindustry.game.Team;
import mindustry.graphics.Drawf;
import mindustry.graphics.Pal;
import mindustry.world.blocks.storage.CoreBlock;

import static eternity.content.EtBlocks.*;
import static mindustry.Vars.tilesize;
import static mindustry.content.Blocks.*;

public class CoreFrost extends CoreBlock {
    public float frostRange = 15;
    public float frostTime = 60f * 14f;
    public CoreFrost(String name) {
        super(name);
        squareSprite = false;
    }
    @Override
    public TextureRegion[] icons(){
        return new TextureRegion[]{region,teamRegions[Team.sharded.id]};
    }
    @Override
    public void drawPlace(int x, int y, int rotation, boolean valid){
        super.drawPlace(x, y, rotation, valid);

        Drawf.dashCircle(x * tilesize + offset, y * tilesize + offset, frostRange * tilesize, Pal.accent);
    }
    public class CoreFrostBuild extends CoreBuild {
        public float progress;
        public float smoothEfficiency = 1f;
        public float totalProgress;

        public float frostRadius(){
            return frostRange * progress * smoothEfficiency;
        }
        @Override
        public void drawSelect(){
            super.drawSelect();
            Drawf.dashCircle(x, y, frostRadius() * tilesize, Pal.accent);
        }
        @Override
        public void updateTile(){
            super.updateTile();
            smoothEfficiency = Mathf.lerpDelta(smoothEfficiency, efficiency, 0.05f);

            progress += edelta() / frostTime;
            progress = Mathf.clamp(progress);

            totalProgress += efficiency * edelta();
            tile.circle((int) (frostRadius()), (tile) -> {
                if (tile != null && tile.floor() != null){
                    if (tile.floor() == deepwater){
                      tile.setFloor(altIce.asFloor());
                      Fx.vaporSmall.at(tile);
                    }
                    if (tile.floor() == water){
                        tile.setFloor(altIceCrack.asFloor());
                        Fx.vaporSmall.at(tile);
                    }
                    if (tile.floor() == shallowFrostCoreZone){
                        tile.setFloor(frostCoreZone.asFloor());
                        Fx.vaporSmall.at(tile);
                    }
                    if (tile.floor() == mercuryTile){
                        tile.setFloor(mercuryIce.asFloor());
                        Fx.vaporSmall.at(tile);
                    }
                }
            });
        }
        @Override
        public void afterDestroyed(){
            super.afterDestroyed();
            tile.circle((int) (frostRadius()), (tile) -> {
                if (tile != null && tile.floor() != null){
                    if (tile.floor() == altIce){
                        tile.setFloor(deepwater.asFloor());
                        Fx.vaporSmall.at(tile);
                        if (tile.build != null && tile.build != this) tile.build.kill();
                    }
                    if (tile.floor() == altIceCrack){
                        tile.setFloor(water.asFloor());
                        Fx.vaporSmall.at(tile);
                        if (tile.build != null && tile.build != this) tile.build.kill();
                    }
                    if (tile.floor() == frostCoreZone){
                        tile.setFloor(shallowFrostCoreZone.asFloor());
                        Fx.vaporSmall.at(tile);
                        if (tile.build != null && tile.build != this) tile.build.kill();
                    }
                    if (tile.floor() == mercuryIce){
                        tile.setFloor(mercuryTile.asFloor());
                        Fx.vaporSmall.at(tile);
                        if (tile.build != null && tile.build != this) tile.build.kill();
                    }
                }
            });
        }
        @Override
        public void write(Writes write){
            super.write(write);

            write.f(progress);
        }

        @Override
        public void read(Reads read, byte revision){
            super.read(read, revision);

            progress = read.f();
        }
    }
}
