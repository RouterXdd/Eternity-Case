package eternity.classes.blocks.defence;

import arc.util.Nullable;
import arc.util.io.Reads;
import arc.util.io.Writes;
import eternity.classes.blocks.turrets.StellarTurret;
import eternity.classes.type.EternityStats;
import eternity.content.EternityFx;
import mindustry.Vars;
import mindustry.content.Blocks;
import mindustry.gen.Bullet;
import mindustry.graphics.Pal;
import mindustry.ui.Bar;
import mindustry.world.Block;
import mindustry.world.Tile;
import mindustry.world.blocks.defense.Wall;
import mindustry.world.blocks.defense.turrets.Turret;
import mindustry.world.meta.Stat;

import static mindustry.Vars.world;

public class StellarWall extends Wall {
    @Nullable
    public Block upgradeBlock;
    public int hitsRequire = 200;
    public StellarWall(String name) {
        super(name);
        update = true;
        upgradeBlock = Blocks.surgeWallLarge;
    }
    @Override
    public void setStats() {
        super.setStats();
        stats.add(EternityStats.hitsReq, hitsRequire);
    }
    @Override
    public void setBars(){
        super.setBars();
        if (hitsRequire >= 1) {
            addBar("shoots", (StellarWallBuild e) -> new Bar("bar.shoots", Pal.techBlue, e::amount));
        }
    }

    public class StellarWallBuild extends WallBuild {
        float shoots;
        public float amount(){
            return shoots;
        }
        @Override
        public void updateTile(){
            super.updateTile();
            if (Vars.state.isCampaign() && team == Vars.state.rules.defaultTeam) {
                if (shoots >= 1 && upgradeBlock != null && upgradeBlock.unlocked()) {
                    Tile tile;
                    if (size == 2 || size == 4 || size == 6 || size == 8 || size == 10 || size == 12 || size == 14 || size == 16) {
                        tile = world.tileWorld(x - 1, y - 1);
                    } else {
                        tile = world.tileWorld(x, y);
                    }
                    tile.setBlock(upgradeBlock, this.team);
                    EternityFx.upgradeBlock.at(tile.drawx(), tile.drawy(), upgradeBlock.size);
                }

            } else if (shoots >= 1 && upgradeBlock != null) {
                Tile tile;
                if (size == 2 || size == 4 || size == 6) {
                    tile = world.tileWorld(x - 1, y - 1);
                } else {
                    tile = world.tileWorld(x, y);
                }
                tile.setBlock(upgradeBlock, this.team);
                EternityFx.upgradeBlock.at(tile.drawx(), tile.drawy(), upgradeBlock.size);

            }

        }
        @Override
        public boolean collision(Bullet bullet) {
            super.collision(bullet);
            if (hitsRequire >= 1) {
                shoots += 1f / hitsRequire;
            }
            return true;
        }
        @Override
        public void write(Writes write) {
            super.write(write);

            write.f(shoots);
        }

        @Override
        public void read(Reads read, byte revision) {
            super.read(read, revision);

            shoots = read.f();
        }
    }
}
