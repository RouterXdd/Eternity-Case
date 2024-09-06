package eternity.classes.blocks.environment;

import arc.Core;
import arc.Events;
import arc.audio.Sound;
import arc.graphics.Color;
import arc.graphics.g2d.Draw;
import arc.graphics.g2d.TextureRegion;
import arc.math.Mathf;
import arc.util.Time;
import mindustry.content.Fx;
import mindustry.entities.Damage;
import mindustry.entities.Effect;
import mindustry.entities.Puddles;
import mindustry.game.EventType;
import mindustry.gen.Building;
import mindustry.gen.Sounds;
import mindustry.world.Block;
import mindustry.world.meta.Env;

import static mindustry.Vars.tilesize;
import static mindustry.Vars.world;

public class ExplosiveCrystal extends Block {
    public Color startColor = new Color(1, 1, 1, 0f);
    public Color endColor = Color.valueOf("4aba31");
    public TextureRegion silhouetteRegion;
    public Effect explodeEffect;
    public Sound explodeSound;
    public float explosionRadius;
    public float explosionDamage;
    public float explosionShake;
    public float explosionShakeDuration;
    public float explosionTime = 4 * 60;
    public ExplosiveCrystal(String name) {
        super(name);
        update = true;
        drawCracks = createRubble = drawTeamOverlay = rebuildable = false;
        envEnabled = Env.any;

        explosionShake = 3f;
        explosionShakeDuration = 10f;

        explosionRadius = 5f;
        explosionDamage = 140;

        explodeEffect = Fx.none;
        explodeSound = Sounds.explosionbig;
    }
    @Override
    public void load(){
        super.load();
        silhouetteRegion = Core.atlas.find(this.name + "-silhouette");
    }
    public class ExplosiveCrystalBuild extends Building {
        float time;
        @Override
        public void updateTile(){
            super.updateTile();
            time += edelta() / explosionTime;
            if (time >= 1 + Mathf.random(0, 0.8f)){
                Effect.shake(explosionShake, explosionShakeDuration, this);
                Damage.damage(team, x, y, explosionRadius * tilesize, explosionDamage);
                explodeEffect.at(this);
                explodeSound.at(this);
                kill();
            }
        }
        @Override
        public void draw(){
            super.draw();

            Draw.color(startColor, endColor, time);
            Draw.rect(silhouetteRegion,x, y);

            Draw.reset();
        }
    }
}
