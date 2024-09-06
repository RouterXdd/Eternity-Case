package eternity.classes.blocks.production;

import arc.*;
import arc.audio.Sound;
import arc.graphics.*;
import arc.graphics.g2d.*;
import arc.math.*;
import arc.util.*;
import arc.util.io.*;
import mindustry.content.*;
import mindustry.entities.Damage;
import mindustry.entities.Effect;
import mindustry.game.EventType.*;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.logic.*;
import mindustry.type.*;
import mindustry.ui.*;
import mindustry.world.blocks.production.GenericCrafter;
import mindustry.world.meta.*;

import static mindustry.Vars.*;

public class NuclearCrafter extends GenericCrafter {
    public final int timerFuel = timers++;

    public Color lightColor = Color.valueOf("7f19ea");
    public Color coolColor = new Color(1, 1, 1, 0f);
    public Color hotColor = Color.valueOf("ff9575a3");
    public float itemDuration = 120;
    public float heating = 0.01f;
    public float smokeThreshold = 0.3f;
    public float flashThreshold = 0.46f;

    public Item fuelItem = Items.silicon;
    public Liquid coolandLiquid = Liquids.cryofluid;
    public TextureRegion lightsRegion;
    public Effect explodeEffect;
    public Sound explodeSound;
    public float explosionRadius;
    public float explosionDamage;
    public float explosionShake;
    public float explosionShakeDuration;
    public NuclearCrafter(String name){
        super(name);
        itemCapacity = 30;
        liquidCapacity = 30;
        hasItems = true;
        hasLiquids = true;
        rebuildable = false;
        schematicPriority = -5;
        envEnabled = Env.any;

        explosionShake = 6f;
        explosionShakeDuration = 16f;

        explosionRadius = 19;
        explosionDamage = 1250 * 4;

        explodeEffect = Fx.reactorExplosion;
        explodeSound = Sounds.explosionbig;
    }
    @Override
    public void load(){
        super.load();
        lightsRegion = Core.atlas.find(this.name + "-lights");
    }

    @Override
    public void setStats(){
        super.setStats();

        if(hasItems){
            stats.add(Stat.productionTime, itemDuration / 60f, StatUnit.seconds);
        }
    }

    @Override
    public void setBars(){
        super.setBars();
        addBar("heat", (NuclearCrafterBuild entity) -> new Bar("bar.heat", Pal.lightOrange, () -> entity.heat));
    }

    public class NuclearCrafterBuild extends GenericCrafterBuild{
        public float heat;
        public float flash;
        public float smoothLight;

        @Override
        public void updateTile(){
            int fuel = items.get(fuelItem);
            float fullness = (float)fuel / itemCapacity;

            if(fuel > 0 && enabled){
                heat += fullness * heating * Math.min(delta(), 4f);
            }else{
                efficiency = 0f;
            }

            if(heat > 0){
                float maxUsed = Math.min(liquids.get(coolandLiquid), heat / coolandLiquid.heatCapacity);
                heat -= maxUsed * coolandLiquid.heatCapacity;
            }

            if(heat > smokeThreshold){
                float smoke = 1.0f + (heat - smokeThreshold) / (1f - smokeThreshold); //ranges from 1.0 to 2.0
                if(Mathf.chance(smoke / 20.0 * delta())){
                    Fx.reactorsmoke.at(x + Mathf.range(size * tilesize / 2f),
                            y + Mathf.range(size * tilesize / 2f));
                }
            }

            heat = Mathf.clamp(heat);

            if(heat >= 0.999f){
                Effect.shake(explosionShake, explosionShakeDuration, this);
                Events.fire(Trigger.thoriumReactorOverheat);
                Damage.damage(x, y, explosionRadius * tilesize, explosionDamage);
                explodeEffect.at(this);
                explodeSound.at(this);
                kill();
            }
            super.updateTile();
        }

        @Override
        public double sense(LAccess sensor){
            if(sensor == LAccess.heat) return heat;
            return super.sense(sensor);
        }

        @Override
        public void drawLight(){
            float fract = efficiency;
            smoothLight = Mathf.lerpDelta(smoothLight, fract, 0.08f);
            Drawf.light(x, y, (90f + Mathf.absin(5, 5f)) * smoothLight, Tmp.c1.set(lightColor).lerp(Color.scarlet, heat), 0.6f * smoothLight);
        }

        @Override
        public void draw(){
            super.draw();

            Draw.color(coolColor, hotColor, heat);
            Fill.rect(x, y, size * tilesize, size * tilesize);

            if(heat > flashThreshold){
                flash += (1f + ((heat - flashThreshold) / (1f - flashThreshold)) * 5.4f) * Time.delta;
                Draw.color(Color.red, Color.yellow, Mathf.absin(flash, 9f, 1f));
                Draw.alpha(0.3f);
                Draw.rect(lightsRegion, x, y);
            }

            Draw.reset();
        }

        @Override
        public void write(Writes write){
            super.write(write);
            write.f(heat);
        }

        @Override
        public void read(Reads read, byte revision){
            super.read(read, revision);
            heat = read.f();
        }
    }
}
