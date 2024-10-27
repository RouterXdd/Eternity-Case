package eternity.classes.blocks.defence;

import arc.graphics.Color;
import arc.math.Mathf;
import arc.util.Tmp;
import eternity.classes.blocks.production.FrostyDrill;
import eternity.classes.blocks.units.Rift;
import mindustry.Vars;
import mindustry.content.Fx;
import mindustry.entities.Units;
import mindustry.gen.Building;
import mindustry.graphics.Drawf;
import mindustry.graphics.Pal;
import mindustry.logic.Ranged;
import mindustry.world.Block;
import mindustry.world.meta.BlockGroup;

import static mindustry.Vars.*;
import static mindustry.Vars.tilesize;

public class Nullifier extends Block {
    @Deprecated
    public final int timerUse = timers++;
    public float useTime = 300f;
    public float range = 9 * 8f;
    public float effectChance = 0.2f;
    public Nullifier(String name) {
        super(name);
        solid = true;
        update = true;
        group = BlockGroup.projectors;
        hasPower = true;
        hasItems = true;
        canOverdrive = false;
    }
    @Override
    public void drawPlace(int x, int y, int rotation, boolean valid){
        super.drawPlace(x, y, rotation, valid);

        Drawf.dashCircle(x * tilesize + offset, y * tilesize + offset, range, Color.valueOf("209d2a"));

        indexer.eachBlock(player.team(), x * tilesize + offset, y * tilesize + offset, range, other -> other.block instanceof Rift, other -> Drawf.selected(other, Tmp.c1.set(Color.valueOf("209d2a")).a(Mathf.absin(4f, 1f))));
    }
    public class NullifierBuild extends Building implements Ranged {
        boolean overload = false;
        public float useProgress;
        @Override
        public float range() {
            return range;
        }
        @Override
        public void drawSelect(){
            super.drawSelect();
            Units.nearbyBuildings(x, y, range, other -> {
                if (other instanceof Rift.RiftBuild r) {
                    Drawf.selected(other, Tmp.c1.set(Color.valueOf("209d2a")).a(Mathf.absin(4f, 1f)));
                }
            });

            Drawf.dashCircle(x, y, range, Color.valueOf("209d2a"));
        }
        @Override
        public void updateTile() {
            if (efficiency > 0) {
                useProgress += delta();
            }
            if (canConsume() && efficiency == 1) {
                Units.nearbyBuildings(x, y, range, other -> {
                    if (other instanceof Rift.RiftBuild r) {
                        if (Mathf.chance(effectChance)) Fx.chainLightning.at(x, y, 0, other);
                        r.progress = 0;
                        r.changeProgress = 0;
                    }
                });

                if (useProgress >= useTime) {
                    consume();
                    useProgress %= useTime;
                }
            }
        }
    }
}
