package eternity.classes.blocks.turrets;

import arc.scene.ui.layout.Table;
import arc.util.Nullable;
import arc.util.io.Reads;
import arc.util.io.Writes;
import eternity.classes.type.EternityStats;
import eternity.content.EternityFx;
import mindustry.Vars;
import mindustry.content.Blocks;
import mindustry.entities.bullet.BulletType;
import mindustry.graphics.Pal;
import mindustry.ui.Bar;
import mindustry.world.Block;
import mindustry.world.Tile;
import mindustry.world.blocks.defense.turrets.Turret;
import mindustry.world.meta.Stat;

import static mindustry.Vars.world;

public class StellarTurret extends Turret {
    @Nullable public Block upgradeBlock;
    public int shootRequire = 200;
    public StellarTurret(String name) {
        super(name);
        upgradeBlock = Blocks.fuse;
        squareSprite = false;
        researchCostMultiplier = 0.5f;
    }
    @Override
    public void setStats() {
        super.setStats();
        if (shootRequire >=1 ) {
            stats.add(EternityStats.shootsReq, shootRequire);
        }
    }
    @Override
    public void setBars(){
        super.setBars();
        if (shootRequire >=1 ) {
            addBar("shoots", (StellarTurretBuild e) -> new Bar("bar.shoots", Pal.techBlue, e::amount));
        }
    }
    public class StellarTurretBuild extends TurretBuild {
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
        protected void shoot(BulletType type){
            super.shoot(type);
            if (shootRequire >= 1) {
                shoots += 1f / shootRequire;
            }
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
