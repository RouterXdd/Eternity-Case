package eternity.classes.blocks.production;

import arc.Events;
import arc.audio.Sound;
import arc.math.Mathf;
import arc.util.Nullable;
import arc.util.Tmp;
import mindustry.content.Fx;
import mindustry.entities.*;
import mindustry.game.EventType;
import mindustry.gen.Sounds;
import mindustry.type.Liquid;
import mindustry.world.Tile;
import mindustry.world.blocks.production.AttributeCrafter;

import static mindustry.Vars.*;

public class OverloadAttributeCrafter extends AttributeCrafter {
    public int explosionRadius = 12;
    public int explosionDamage = 0;
    public Effect explodeEffect = Fx.none;
    public Sound explodeSound = Sounds.none;

    public int explosionPuddles = 10;
    public float explosionPuddleRange = tilesize * 2f;
    public float explosionPuddleAmount = 100f;
    public @Nullable Liquid explosionPuddleLiquid;
    public float explosionMinWarmup = 0f;

    public float explosionShake = 0f, explosionShakeDuration = 6f;
    public OverloadAttributeCrafter(String name) {
        super(name);
    }
    public class OverloadAttributeCrafterBuild extends AttributeCrafterBuild {
        @Override
        public void updateTile() {
            super.updateTile();
            if (items.get(outputItem.item) == itemCapacity){
                kill();
                Events.fire(new EventType.GeneratorPressureExplodeEvent(this));
                createExplosion();
            }
        }
        public boolean shouldExplode(){
            return warmup() >= explosionMinWarmup;
        }
        public void createExplosion(){
            if(shouldExplode()){
                if(explosionDamage > 0){
                    Damage.damage(x, y, explosionRadius * tilesize, explosionDamage);
                }

                explodeEffect.at(this);
                explodeSound.at(this);

                if(explosionPuddleLiquid != null){
                    for(int i = 0; i < explosionPuddles; i++){
                        Tmp.v1.trns(Mathf.random(360f), Mathf.random(explosionPuddleRange));
                        Tile tile = world.tileWorld(x + Tmp.v1.x, y + Tmp.v1.y);
                        Puddles.deposit(tile, explosionPuddleLiquid, explosionPuddleAmount);
                    }
                }

                if(explosionShake > 0){
                    Effect.shake(explosionShake, explosionShakeDuration, this);
                }
            }
        }
    }
}
