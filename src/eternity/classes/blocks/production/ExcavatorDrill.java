package eternity.classes.blocks.production;

import arc.Core;
import arc.graphics.Color;
import arc.graphics.g2d.Draw;
import arc.math.Mathf;
import mindustry.gen.Building;
import mindustry.graphics.Drawf;
import mindustry.type.Item;
import mindustry.world.Tile;
import mindustry.world.blocks.production.Drill;

import static mindustry.Vars.*;

public class ExcavatorDrill extends Drill {
    public int range = 4;
    //Shitty works
    public float solidMultipler = 1;
    public float oreMultipler = 1;
    public ExcavatorDrill(String name) {
        super(name);
        squareSprite = false;
        researchCostMultiplier = 0.5f;
    }
    @Override
    public void drawPlace(int x, int y, int rotation, boolean valid) {

        Tile tile = world.tile(x, y);
        if(tile == null) return;

        countOre(tile);

        if(returnItem != null){
            float width = drawPlaceText(Core.bundle.formatFloat("bar.drillspeed", 60f / getDrillTime(returnItem) * returnCount, 2), x, range / 3 + y, valid);
            float dx = x * tilesize + offset - width/2f - 4f, dy = y * tilesize + offset + size * tilesize / 2f + 5 + range * 2, s = iconSmall / 4f;
            Draw.mixcol(Color.darkGray, 1f);
            Draw.rect(returnItem.fullIcon, dx, dy - 1, s, s);
            Draw.reset();
            Draw.rect(returnItem.fullIcon, dx, dy, s, s);

            if(drawMineItem){
                Draw.color(returnItem.color);
                Draw.rect(itemRegion, tile.worldx() + offset, tile.worldy() + offset);
                Draw.color();
            }
        }else{
            Tile to = tile.getLinkedTilesAs(this, tempTiles).find(t -> t.drop() != null && (t.drop().hardness > tier || t.drop() == blockedItem));
            Item item = to == null ? null : to.drop();
            if(item != null){
                drawPlaceText(Core.bundle.get("bar.drilltierreq"), x, y, valid);
            }
        }
        x *= tilesize;
        y *= tilesize;
        x += offset;
        y += offset;

        Drawf.dashSquare(player.team().color, x, y, range * tilesize);
        super.drawPlace(x, y, rotation, valid);
    }
    public class BrokenDrillBuild extends DrillBuild {
        @Override
        public void drawSelect(){
            super.drawSelect();

            Drawf.dashSquare(team.color, x, y, range * tilesize);
        }
        @Override
        public void updateTile(){
            if(timer(timerDump, dumpTime)){
                dump(dominantItem != null && items.has(dominantItem) ? dominantItem : null);
            }

            if(dominantItem == null){
                return;
            }
            if (canConsume()) {
                checkNearby(range);
            }

            timeDrilled += warmup * delta();

            float delay = getDrillTime(dominantItem);

            if(items.total() < itemCapacity && dominantItems > 0 && efficiency > 0){
                float speed = Mathf.lerp(1f, liquidBoostIntensity, optionalEfficiency) * efficiency;

                lastDrillSpeed = (speed * dominantItems * warmup) / delay;
                warmup = Mathf.approachDelta(warmup, speed, warmupSpeed);
                progress += delta() * dominantItems * speed * warmup;

                if(Mathf.chanceDelta(updateEffectChance * warmup))
                    updateEffect.at(x + Mathf.range(size * 2f), y + Mathf.range(size * 2f));
            }else{
                lastDrillSpeed = 0f;
                warmup = Mathf.approachDelta(warmup, 0f, warmupSpeed);
                return;
            }

            if(dominantItems > 0 && progress >= delay && items.total() < itemCapacity){
                offload(dominantItem);

                progress %= delay;

                if(wasVisible && Mathf.chanceDelta(updateEffectChance * warmup)) drillEffect.at(x + Mathf.range(drillEffectRnd), y + Mathf.range(drillEffectRnd), dominantItem.color);
            }

        }
        public void checkNearby(int range){
            if (size == 2 || size == 4 || size == 6 || size == 8 || size == 10 || size == 12 || size == 14 || size == 16) {
                for (int i = (int) (x / tilesize - range / 2) + 1; i <= x / tilesize + range / 2; i ++) {
                    for (int j = (int) (y / tilesize - range / 2) + 1; j <= y / tilesize + range / 2; j ++) {
                        Tile tile = world.tile(i, j);

                        if (tile == null) continue;

                        Building build = tile.build;
                        if(build != this && tile.floor().itemDrop == dominantItem || tile.overlay().itemDrop == dominantItem){
                            efficiency += 1f / ((range*range - size*size) * oreMultipler);
                        }
                        if(build != this && tile.block().solid){
                            efficiency -= 1f / ((range*range - size*size) * solidMultipler);
                        }

                    }
                }
            } else {
                for (int i = (int) (x / tilesize - range / 2); i <= x / tilesize + range / 2; i ++) {
                    for (int j = (int) (y / tilesize - range / 2); j <= y / tilesize + range / 2; j ++) {
                        Tile tile = world.tile(i, j);

                        if (tile == null) continue;

                        Building build = tile.build;
                        if(build != this && tile.floor().itemDrop == dominantItem || tile.overlay().itemDrop == dominantItem){
                            efficiency += 1f / ((range*range - size*size) * oreMultipler);
                        }
                        if(build != this && tile.block().solid){
                            efficiency -= 1f / ((range*range - size*size) * solidMultipler);
                        }
                    }
                }
            }
        }
    }
}
