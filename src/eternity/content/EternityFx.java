package eternity.content;

import arc.graphics.Color;
import arc.graphics.g2d.Fill;
import arc.graphics.g2d.Lines;
import arc.math.Mathf;
import arc.math.Rand;
import arc.math.geom.Vec2;
import eternity.graphic.EternityPal;
import mindustry.entities.Effect;
import mindustry.graphics.*;

import static arc.graphics.g2d.Draw.color;
import static mindustry.Vars.tilesize;

public class EternityFx {
    public static final Rand rand = new Rand();
    public static final Vec2 v = new Vec2();

    public static final Effect
    cultTrail = new Effect(30, e -> {
        for(int i = 0; i < 2; i++){
            color(i == 0 ? EternityPal.darkCultColor : EternityPal.cultColor);

            float m = i == 0 ? 1f : 0.5f;

            float rot = e.rotation + 180f;
            float w = 4.5f * e.fout() * m;
            Fill.square(e.x, e.y, w, rot);
        }

        Drawf.light(e.x, e.y, 60f, EternityPal.cultColor, 0.6f * e.fout());
    }),
    upgradeBlock = new Effect(25, e -> {
        color(Pal.techBlue);
        Fill.square(e.x, e.y, tilesize / 2f * e.rotation + e.fout() * 3f);
    }),

    grandLaserShoot = new Effect(21f, e -> {
        color(EternityPal.grandColor);

        for(int i : Mathf.signs){
            Drawf.tri(e.x, e.y, 4f * e.fout(), 29f, e.rotation + 90f * i);
    }
    }),
    zyconMissileShoot = new Effect(21f, e -> {
        color(EternityPal.zyconColor);

            Drawf.tri(e.x, e.y, 4f, 25f * e.fout(), e.rotation);
    }),
    bloodMissileShoot = new Effect(21f, e -> {
        color(EternityPal.bloodRed);
        Drawf.tri(e.x, e.y, 4f, 20f * e.fout(), e.rotation + 35);
        Drawf.tri(e.x, e.y, 4f, 20f * e.fout(), e.rotation - 35);

        color(EternityPal.bloodRedLight);

        Drawf.tri(e.x, e.y, 4f, 25f * e.fout(), e.rotation);
    }),
    terraCraft = new Effect(40f, 100f, e -> {
        float circleRad = 3f + e.finpow() * 65f;

        color(EternityPal.oblivionColor);
        for(int i = 0; i < 4; i++){
            Drawf.tri(e.x, e.y, 6f, 70f * e.fout(), i*90);
        }

        color();
        for(int i = 0; i < 4; i++){
            Drawf.tri(e.x, e.y, 3f, 20f * e.fout(), i*90);
        }

        Drawf.light(e.x, e.y, circleRad * 1.6f, EternityPal.oblivionColor, e.fout());
    }),
    enrageSpikes = new Effect(65f, 100f, e -> {
        float circleRad = 3f + e.finpow() * 65f;

        color(EternityPal.oblivionColor, Color.clear, e.fin());
        for(int i = 0; i < 2; i++){
            Drawf.tri(e.x, e.y, 6f, 110f * e.fin(), i*180 + 90);
        }

        color(Color.white, Color.clear, e.fin());
        for(int i = 0; i < 2; i++){
            Drawf.tri(e.x, e.y, 3f, 50f * e.fin(), i*180 + 90);
        }

        Drawf.light(e.x, e.y, circleRad * 1.6f, EternityPal.oblivionColor, e.fin());
    });
}
