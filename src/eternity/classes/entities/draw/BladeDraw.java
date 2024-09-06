package eternity.classes.entities.draw;

import arc.Core;
import arc.graphics.g2d.Draw;
import arc.graphics.g2d.TextureRegion;
import arc.math.Mathf;
import arc.util.Time;
import arc.util.Tmp;
import mindustry.entities.part.DrawPart;
import mindustry.graphics.Drawf;
import mindustry.graphics.Layer;

public class BladeDraw extends DrawPart {
    public float rotateSpeed;
    public String blade = "sawblade";
    public float layer = Layer.groundUnit - 0.001f;
    public int x, y = 0;
    public TextureRegion sawRegion;
    public BladeDraw(float rotateSpeed){
        this.rotateSpeed = rotateSpeed;
    }
    @Override
    public void draw(PartParams params) {
        Draw.z(layer);
        int i = params.sideOverride == -1 ? 0 : params.sideOverride;
        float sign = (i == 0 ? 1 : -1) * params.sideMultiplier;
        Tmp.v1.set(x * sign, y).rotateRadExact((params.rotation - 90) * Mathf.degRad);
        float
                tx = params.x + Tmp.v1.x,
                ty = params.y + Tmp.v1.y;
        Drawf.spinSprite(sawRegion, tx, ty, Time.time * rotateSpeed);
    }

    @Override
    public void load(String name) {
        sawRegion = Core.atlas.find("eternity-case-" + blade);
    }
}
