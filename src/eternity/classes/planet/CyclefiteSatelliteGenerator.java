package eternity.classes.planet;

import arc.graphics.Color;
import arc.math.Mathf;
import arc.math.geom.Vec3;
import arc.util.Tmp;
import arc.util.noise.Simplex;
import eternity.content.EtSchematics;
import mindustry.maps.generators.PlanetGenerator;
import mindustry.world.Block;

import static eternity.content.EtBlocks.*;

public class CyclefiteSatelliteGenerator extends PlanetGenerator {
    public float heightScl = 0.9f, octaves = 6, persistence = 0.7f, heightPow = 3f, heightMult = 1.5f;
    {
        defaultLoadout = EtSchematics.commBase;
    }

    @Override
    public float getHeight(Vec3 position){
        return Mathf.pow(rawHeight(position), heightPow) * heightMult;
    }

    float rawHeight(Vec3 position){
        return Simplex.noise3d(seed, octaves, persistence, 1f/heightScl, 10f + position.x, 10f + position.y, 10f + position.z);
    }

    @Override
    public Color getColor(Vec3 position){
        Block block = rawHeight(position) < 0.6 ? altIce : Mathf.chance(0.42f) ? blueSnow : mercuryFloor;
        return Tmp.c1.set(block.mapColor).a(1f - block.albedo);
    }
}
