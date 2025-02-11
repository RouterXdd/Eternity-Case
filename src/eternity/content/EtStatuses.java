package eternity.content;

import arc.graphics.Color;
import eternity.classes.entities.statuses.GhostStatusEffect;
import eternity.classes.mod.ClassificationMeta;
import eternity.graphic.EternityPal;
import mindustry.entities.Effect;
import mindustry.entities.effect.MultiEffect;
import mindustry.entities.effect.ParticleEffect;
import mindustry.entities.effect.WaveEffect;
import mindustry.graphics.Pal;
import mindustry.type.StatusEffect;

import static eternity.classes.mod.Classification.*;
import static mindustry.content.StatusEffects.*;

public class EtStatuses {
    public static StatusEffect enrage, detected, infected, paralineBurning, ghosted, ancientEMP;
    public static void load(){
        enrage = new StatusEffect("enrage"){{
            color = EternityPal.oblivionColor;
            speedMultiplier = reloadMultiplier = 1.6f;
            damageMultiplier = 1.3f;
            permanent = true;
            applyEffect = new MultiEffect(
                    EtFx.enrageSpikes,
                    new WaveEffect(){{
                        sizeTo = 110;
                        lifetime = 65;
                        strokeFrom = 3.5f;
                        colorFrom = EternityPal.oblivionColor;
                    }}
            );
            ClassificationMeta.put(this, ruin);
        }};
        detected = new StatusEffect("detected"){{
            color = Pal.gray;
            ClassificationMeta.put(this, ruin);
        }};
        infected = new StatusEffect("infected"){{
            color = Color.valueOf("209d2a");
            damage = 1.5f;
            ClassificationMeta.put(this, malachite);
        }};
        paralineBurning = new StatusEffect("paraline-burning"){{
            color = EternityPal.paralineColor;
            damage = 0.25f;
            effect = new MultiEffect(
                    new ParticleEffect(){{
                        particles = 3;
                        length = 10;
                        lifetime = 20;
                        sizeFrom = 2.5f;
                        colorFrom = Color.valueOf("e181ac");
                        colorTo = EternityPal.darkParalineColor;
                    }}
            );
            transitionDamage = 8f;
            ClassificationMeta.put(this, cycle);

            init(() -> {
                opposite(freezing);
            });
        }};
        ghosted = new GhostStatusEffect("ghosted"){{
            color = Color.valueOf("b25bdb");
            ClassificationMeta.put(this, i);
        }};
        ancientEMP = new StatusEffect("ancient-emp"){{
            color = Color.valueOf("f2e878");
            damageMultiplier = 0f;
            reloadMultiplier = 0f;
            speedMultiplier = 0f;
            buildSpeedMultiplier = 0f;
            disarm = true;
            ClassificationMeta.put(this, i);
        }};
    }
}
