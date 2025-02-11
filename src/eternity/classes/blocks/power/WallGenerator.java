package eternity.classes.blocks.power;

import arc.Core;
import arc.graphics.Color;
import arc.graphics.g2d.*;
import arc.math.Mathf;
import arc.math.geom.Point2;
import arc.util.Time;
import eternity.content.EtAttributes;
import mindustry.Vars;
import mindustry.content.Fx;
import mindustry.entities.Effect;
import mindustry.game.Team;
import mindustry.graphics.*;
import mindustry.world.Tile;
import mindustry.world.blocks.power.PowerGenerator;
import mindustry.world.meta.Attribute;

public class WallGenerator extends PowerGenerator {
    public Attribute attribute = EtAttributes.skinted;
    public TextureRegion glowRegion;
    public Color glowColor = Color.valueOf("ffefcf");
    public float sinMag = 0.6f, sinScl = 8f;
    float minEfficiency = 0;
    public WallGenerator(String name) {
        super(name);
    }
    @Override
    public void drawPlace(int x, int y, int rotation, boolean valid) {
        super.drawPlace(x, y, rotation, valid);

        float a = getEfficiency(x, y);

        drawPlaceText(Core.bundle.formatFloat("bar.efficiency", Mathf.round((a) * 100), 0), x, y, valid);
    }
    @Override
    public boolean canPlaceOn(Tile tile, Team team, int rotation){
        return getEfficiency(tile.x, tile.y) > minEfficiency;
    }
    float getEfficiency(int x, int y){
        float a = 0;

        Point2[] edges = getEdges();
        for (Point2 edge : edges) {
            Tile other = Vars.world.tile(x + edge.x, y + edge.y);
            if (other != null && other.solid()) {
                float at = other.block().attributes.get(attribute);
                a += at;
            }
        }
        return a;
    }
    @Override
    public void load(){
        super.load();

        glowRegion = Core.atlas.find(name + "-glow");
    }
    public class WallGeneratorBuild extends GeneratorBuild {
        @Override
        public void update(){
            super.update();
            productionEfficiency = getEfficiency(tile.x, tile.y);
        }
        @Override
        public void draw(){
            super.draw();
            Draw.color(glowColor);
            Draw.alpha(warmup() * (1f - sinMag) + Mathf.absin(Time.time, sinScl, sinMag) * warmup());
            Draw.rect(glowRegion, x, y);
            Draw.reset();
        }
    }
}
