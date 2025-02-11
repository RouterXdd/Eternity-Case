package eternity.classes.interfaces;

import arc.math.geom.Point2;
import mindustry.Vars;
import mindustry.gen.Buildingc;
import mindustry.world.Block;
import mindustry.world.Tile;

public interface DestructBlock extends Buildingc {
    default void obliterate(int x, int y){
        Point2[] edges = block().getEdges();
        for (Point2 edge : edges) {
            Tile other = Vars.world.tile(x + edge.x, y + edge.y);
            if (other != null && other.build != null) other.build.kill();
        }
    }
}
