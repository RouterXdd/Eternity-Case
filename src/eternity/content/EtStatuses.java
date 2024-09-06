package eternity.content;

import arc.graphics.Color;
import eternity.classes.entities.statuses.GhostStatusEffect;
import eternity.graphic.EternityPal;
import mindustry.entities.effect.MultiEffect;
import mindustry.entities.effect.WaveEffect;
import mindustry.graphics.Pal;
import mindustry.type.StatusEffect;

public class EtStatuses {
    public static StatusEffect enrage, detected, infected, ghosted;
    public static void load(){
        enrage = new StatusEffect("enrage"){{
            color = EternityPal.oblivionColor;
            speedMultiplier = reloadMultiplier = 1.6f;
            damageMultiplier = 1.3f;
            permanent = true;
            applyEffect = new MultiEffect(
                    EternityFx.enrageSpikes,
                    new WaveEffect(){{
                        sizeTo = 110;
                        lifetime = 65;
                        strokeFrom = 3.5f;
                        colorFrom = EternityPal.oblivionColor;
                    }}
            );
        }};
        detected = new StatusEffect("detected"){{
            color = Pal.gray;
        }};
        infected = new StatusEffect("infected"){{
            color = Color.valueOf("209d2a");
            damage = 1.5f;
        }};
        ghosted = new GhostStatusEffect("ghosted"){{
            color = Color.valueOf("b25bdb");
        }};
    }
}
