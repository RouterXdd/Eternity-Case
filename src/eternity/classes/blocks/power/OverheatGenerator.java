package eternity.classes.blocks.power;

import arc.Core;
import arc.Events;
import arc.audio.Sound;
import arc.graphics.Color;
import arc.graphics.g2d.*;
import arc.math.Mathf;
import arc.util.Time;
import arc.util.io.*;
import eternity.content.EtLiquids;
import mindustry.content.*;
import mindustry.entities.*;
import mindustry.game.EventType;
import mindustry.gen.Sounds;
import mindustry.graphics.Pal;
import mindustry.logic.LAccess;
import mindustry.type.Liquid;
import mindustry.ui.Bar;
import mindustry.world.blocks.power.ThermalGenerator;
import mindustry.world.meta.Env;

import static mindustry.Vars.tilesize;
import static mindustry.Vars.world;

public class OverheatGenerator extends ThermalGenerator {

    public Color coolColor = new Color(1, 1, 1, 0f);
    public Color hotColor = Color.valueOf("ff9575a3");
    public float heating = 0.01f;
    public float smokeThreshold = 0.3f;
    public float flashThreshold = 0.46f;
    public Liquid coolandLiquid = EtLiquids.corruptedWater;
    public TextureRegion lightsRegion, silhouetteRegion;
    public Effect explodeEffect;
    public Sound explodeSound;
    public float explosionRadius;
    public float explosionDamage;
    public float explosionShake;
    public float explosionShakeDuration;
    public Liquid depositLiquid;
    public float puddleSize = 70f;

    public OverheatGenerator(String name) {
        super(name);
        depositLiquid = Liquids.slag;
        liquidCapacity = 30;
        hasLiquids = true;
        rebuildable = false;
        schematicPriority = -5;
        envEnabled = Env.any;

        explosionShake = 6f;
        explosionShakeDuration = 16f;

        explosionRadius = 19;
        explosionDamage = 20 * 8;

        explodeEffect = Fx.reactorExplosion;
        explodeSound = Sounds.explosionbig;
    }
    @Override
    public void load(){
        super.load();
        lightsRegion = Core.atlas.find(this.name + "-lights");
        silhouetteRegion = Core.atlas.find(this.name + "-silhouette");
    }
    @Override
    public void setBars(){
        super.setBars();
        addBar("heat", (OverheatGeneratorBuild entity) -> new Bar("bar.heat", Pal.lightOrange, () -> entity.heated));
    }
    public class OverheatGeneratorBuild extends ThermalGeneratorBuild {
        public float heated;
        public float flash;
        @Override
        public void updateTile(){

            if(productionEfficiency > 0.1f && enabled){
                heated += productionEfficiency * heating * Math.min(delta(), 4f);
            }else{
                efficiency = 0f;
            }

            if(heated > 0){
                float maxUsed = Math.min(liquids.get(coolandLiquid), heated / coolandLiquid.heatCapacity);
                heated -= maxUsed * coolandLiquid.heatCapacity;
            }

            if(heated > smokeThreshold){
                float smoke = 1.0f + (heated - smokeThreshold) / (1f - smokeThreshold); //ranges from 1.0 to 2.0
                if(Mathf.chance(smoke / 20.0 * delta())){
                    Fx.reactorsmoke.at(x + Mathf.range(size * tilesize / 2f),
                            y + Mathf.range(size * tilesize / 2f));
                }
            }

            heated = Mathf.clamp(heated);

            if(heated >= 0.999f){
                Effect.shake(explosionShake, explosionShakeDuration, this);
                Events.fire(EventType.Trigger.thoriumReactorOverheat);
                Damage.damage(x, y, explosionRadius * tilesize, explosionDamage);
                explodeEffect.at(this);
                explodeSound.at(this);
                Puddles.deposit(world.tileWorld(this.x,this.y), depositLiquid, puddleSize);
                kill();
            }
            super.updateTile();
        }

        @Override
        public double sense(LAccess sensor){
            if(sensor == LAccess.heat) return heated;
            return super.sense(sensor);
        }

        @Override
        public void draw(){
            super.draw();

            Draw.color(coolColor, hotColor, heated);
            Draw.rect(silhouetteRegion,x, y);

            if(heated > flashThreshold){
                flash += (1f + ((heated - flashThreshold) / (1f - flashThreshold)) * 5.4f) * Time.delta;
                Draw.color(Color.red, Color.yellow, Mathf.absin(flash, 9f, 1f));
                Draw.alpha(0.3f);
                Draw.rect(lightsRegion, x, y);
            }

            Draw.reset();
        }

        @Override
        public void write(Writes write){
            super.write(write);
            write.f(heated);
        }

        @Override
        public void read(Reads read, byte revision){
            super.read(read, revision);
            heated = read.f();
        }
    }
}
