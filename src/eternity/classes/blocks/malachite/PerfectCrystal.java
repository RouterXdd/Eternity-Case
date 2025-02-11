package eternity.classes.blocks.malachite;

import arc.math.geom.Point2;
import arc.util.Time;
import eternity.classes.interfaces.DestructBlock;
import mindustry.Vars;
import mindustry.gen.Building;
import mindustry.gen.Bullet;
import mindustry.world.Block;
import mindustry.world.Tile;

public class PerfectCrystal extends Block {
    public float delay = 0.2f * 60;
    public PerfectCrystal(String name) {
        super(name);
        update = true;
        drawTeamOverlay = rebuildable = false;
    }
    public class PerfectCrystalBuild extends Building implements DestructBlock {
        public float hit;
        @Override
        public void afterDestroyed(){
            super.afterDestroyed();
            Time.run(delay, () -> {
                obliterate(tile.x, tile.y);
            });
        }

        @Override
        public boolean collision(Bullet bullet){
            super.collision(bullet);

            hit = 1f;

                if(bullet.vel.len() <= 0.1f || !bullet.type.reflectable) return true;

                bullet.trns(-bullet.vel.x, -bullet.vel.y);

                float penX = Math.abs(x - bullet.x), penY = Math.abs(y - bullet.y);

                if(penX > penY){
                    bullet.vel.x *= -1;
                }else{
                    bullet.vel.y *= -1;
                }

                bullet.owner = this;
                bullet.team = team;
                bullet.time += 1f;

                //disable bullet collision by returning false
                return false;
        }
    }
}
