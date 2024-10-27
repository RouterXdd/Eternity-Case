package eternity.classes.blocks.units;

import arc.Core;
import arc.graphics.g2d.Draw;
import arc.graphics.g2d.TextureRegion;
import arc.math.Mathf;
import arc.scene.ui.layout.Table;
import arc.util.io.Reads;
import arc.util.io.Writes;
import mindustry.content.Fx;
import mindustry.entities.Effect;
import mindustry.gen.Building;
import mindustry.gen.Unit;
import mindustry.graphics.Layer;
import mindustry.type.UnitType;
import mindustry.world.Block;

import static mindustry.Vars.net;

public class Rift extends Block {
    public int stages = 3;
    public TextureRegion[] riftRegion = new TextureRegion[stages];
    public TextureRegion[] riftShadowRegion = new TextureRegion[stages];
    public float shadowOffset = -0.5f;
    public float spawnInterval = 60 * 36f;
    public float stageChange = 60 * 275f;
    public float rareUnitChance = 0.1f;
    public UnitType[][] spawnUnits;
    public UnitType[][] rareUnits;
    public Effect updateEffect = Fx.none;
    public Effect spawnEffect = Fx.none;
    public Effect rareSpawnEffect = Fx.none;
    public float effectChance = 0.05f;
    public Rift(String name) {
        super(name);
        health = (int) (armor = Float.MAX_VALUE);
        solid = hasShadow = drawTeamOverlay = false;
        update = configurable = true;
        drawCracks = createRubble = false;
        config(Integer.class,(RiftBuild build, Integer val)->{build.stage = val;});
    }
    @Override
    public void load(){
        super.load();
        for (int i = 0; i < riftRegion.length; i++) {
            riftRegion[i] = Core.atlas.find(name + "-" + i);
        }
        for (int i = 0; i < riftShadowRegion.length; i++) {
            riftShadowRegion[i] = Core.atlas.find(name + "-shadow-" + i);
        }
    }
    @Override
    public TextureRegion[] icons(){
        return new TextureRegion[]{riftRegion[0]};
    }
    public class RiftBuild extends Building {
        public float progress, changeProgress;
        int stage = 1;

        @Override
        public void update(){
            if (net.client()) return;
            progress += delta() * stage;
            changeProgress += delta();
            if (Mathf.chance(effectChance)) {
                updateEffect.at(x, y);
            }
            if (stage > stages){
                stage = stages;
            }
            if (changeProgress >= stageChange && stage < stages){
                stage += 1;
                changeProgress = 0;
            }
            if (progress >= spawnInterval){
                Unit unit;
                if (Mathf.chance(rareUnitChance)) {
                    unit = getEnemy(true).create(team);
                    rareSpawnEffect.at(x, y);
                } else{
                    unit = getEnemy(false).create(team);
                    spawnEffect.at(x, y);
                }
                    unit.set(x, y);
                    unit.rotation = Mathf.random(360);
                    unit.add();
                progress = 0;
            }

        }
        public UnitType getEnemy(boolean rare){
            if (rare){
                return rareUnits[Mathf.random(rareUnits.length - 1)][Mathf.random(rareUnits[0].length - 1)];
            } else {
                return spawnUnits[Mathf.random(spawnUnits.length - 1)][Mathf.random(spawnUnits[0].length - 1)];
            }
        }
        @Override
        public void draw(){
            if (stage < stages) {
                Draw.rect(riftRegion[stage - 1], x, y);
            } else {
                Draw.rect(riftRegion[stages - 1], x, y);
            }
            Draw.alpha(0.8f);
            Draw.z(Layer.blockUnder);
            if (stage < stages) {
                Draw.rect(riftShadowRegion[stage - 1], x + shadowOffset, y + shadowOffset);
            } else {
                Draw.rect(riftShadowRegion[stages - 1], x + shadowOffset, y + shadowOffset);
            }
        }
        @Override
        public void buildConfiguration(Table table) {
            super.buildConfiguration(table);
            table.label(()-> Core.bundle.format("bar.stage",
                    stage));
            table.row();
            table.slider(1, stages, 1f, stage, true, this::configure);
        }
        @Override
        public void write(Writes write){
            super.write(write);
            write.s(stage);
        }

        @Override
        public void read(Reads read, byte revision){
            super.read(read, revision);
            stage = read.s();
        }
    }
}
