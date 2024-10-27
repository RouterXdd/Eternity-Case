package eternity.classes.blocks.production;

import arc.graphics.g2d.TextureRegion;
import arc.math.Mathf;
import arc.util.Tmp;
import mindustry.content.Fx;
import mindustry.entities.Effect;
import mindustry.gen.Building;
import mindustry.graphics.*;
import mindustry.logic.Ranged;
import mindustry.world.Block;
import mindustry.world.draw.*;
import mindustry.world.meta.BlockGroup;

import static mindustry.Vars.*;

public class GroundHeater extends Block {
    @Deprecated
    public final int timerUse = timers++;
    public float frostDecrease = 60 * 280;
    public float useTime = 300f;
    public float range = 8 * 2;

    public DrawBlock drawer = new DrawDefault();
    public float effectChance = 0.01f;
    public Effect effect = Fx.hitFlameBeam;
    public GroundHeater(String name) {
        super(name);
        solid = true;
        update = true;
        group = BlockGroup.projectors;
        hasPower = true;
        hasItems = true;
        canOverdrive = false;
        emitLight = true;
        lightRadius = 50f;
    }
    @Override
    public void load() {
        super.load();
        drawer.load(this);
    }
    @Override
    protected TextureRegion[] icons() {
        return drawer.icons(this);
    }
    @Override
    public void drawPlace(int x, int y, int rotation, boolean valid){
        super.drawPlace(x, y, rotation, valid);

        Drawf.dashCircle(x * tilesize + offset, y * tilesize + offset, range, Pal.slagOrange);

        indexer.eachBlock(player.team(), x * tilesize + offset, y * tilesize + offset, range, other -> other.block instanceof FrostyDrill, other -> Drawf.selected(other, Tmp.c1.set(Pal.slagOrange).a(Mathf.absin(4f, 1f))));
    }
    public class GroundHeaterBuild extends Building implements Ranged {
        public float smoothEfficiency, useProgress;

        @Override
        public float range() {
            return range;
        }
        @Override
        public void drawLight(){
            Drawf.light(x, y, lightRadius * smoothEfficiency, lightColor, 0.7f * smoothEfficiency);
        }
        @Override
        public void drawSelect(){
            super.drawSelect();
            indexer.eachBlock(player.team(), x, y, range, other -> other.block instanceof FrostyDrill, other -> Drawf.selected(other, Tmp.c1.set(Pal.slagOrange).a(Mathf.absin(4f, 1f))));

            Drawf.dashCircle(x, y, range, Pal.slagOrange);
        }
        @Override
        public void updateTile() {
            smoothEfficiency = Mathf.lerpDelta(smoothEfficiency, efficiency, 0.08f);
            if (efficiency > 0) {
                useProgress += delta();
            }
            if (canConsume()) {
                if (Mathf.chance(effectChance)) effect.at(x, y);
                indexer.eachBlock(this, range, other -> other.block instanceof FrostyDrill, other -> {
                        if (other instanceof FrostyDrill.FrostyDrillBuild f) {
                            if (f.frost >= 0) f.frost -= delta() / frostDecrease;

                        };
                });

                if (useProgress >= useTime) {
                    consume();
                    useProgress %= useTime;
                }
            }
        }
        @Override
        public void draw() {
            super.draw();
            drawer.draw(this);
        }
    }
}
