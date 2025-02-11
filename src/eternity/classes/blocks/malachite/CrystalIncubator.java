package eternity.classes.blocks.malachite;

import arc.Core;
import arc.graphics.g2d.*;
import eternity.classes.interfaces.DestructBlock;
import eternity.graphic.EternityPal;
import mindustry.content.Fx;
import mindustry.entities.Units;
import mindustry.graphics.Drawf;
import mindustry.logic.Ranged;
import mindustry.world.blocks.storage.CoreBlock;
import mindustry.world.meta.*;

import static mindustry.Vars.*;

public class CrystalIncubator extends CoreBlock {
    public float timeMoment = 260f;
    public float range = 120f;
    public float damage = 200f;
    TextureRegion glowRegion;
    public CrystalIncubator(String name) {
        super(name);
        update = true;
        drawTeamOverlay = false;
    }
    @Override
    public void load(){
        super.load();
        glowRegion = Core.atlas.find(this.name + "-glow");
    }
    @Override
    public void drawPlace(int x, int y, int rotation, boolean valid){
        super.drawPlace(x, y, rotation, valid);

        Drawf.dashCircle(x * tilesize + offset, y * tilesize + offset, range, EternityPal.malachiteColor);
    }
    @Override
    public void setStats() {
        super.setStats();

        stats.add(Stat.range, range / tilesize, StatUnit.blocks);
        stats.add(Stat.damage, damage, StatUnit.none);
    }

    public class CrystalIncubatorBuild extends CoreBuild implements DestructBlock, Ranged {
        float opacity = 0;
        public float timerAttack;
        @Override
        public float range(){
            return range;
        }
        @Override
        public void afterDestroyed(){
            super.afterDestroyed();
            obliterate(tile.x, tile.y);
        }
        @Override
        public void updateTile() {
            if (opacity > 0) {
                opacity -= delta() / 50;
            }
            if (optionalEfficiency > 0 && timerAttack >= timeMoment) {
                Units.nearbyEnemies(team, x, y, range, otherUnit -> {
                    Fx.chainLightning.at(x, y, 0f, EternityPal.malachiteColor, otherUnit);
                    otherUnit.damage(damage);
                    opacity = 1;
                });
                timerAttack = 0;
            } else {
                timerAttack += delta();
            }
        }
        @Override
        public void drawSelect() {
            Drawf.dashCircle(x, y, range, EternityPal.malachiteColor);
        }
        @Override
        public void draw(){
            super.draw();
            Draw.alpha(opacity);
            Draw.rect(glowRegion, x, y);
        }
    }
}
