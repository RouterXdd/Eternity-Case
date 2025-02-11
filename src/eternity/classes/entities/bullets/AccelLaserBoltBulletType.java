package eternity.classes.entities.bullets;

import arc.Core;
import arc.graphics.Color;
import arc.graphics.g2d.Draw;
import mindustry.content.Fx;
import mindustry.entities.bullet.BasicBulletType;
import mindustry.gen.Bullet;

public class AccelLaserBoltBulletType extends BasicBulletType {
    public AccelLaserBoltBulletType(float speed, float damage) {
        super(speed, damage);
        this.backColor = Color.valueOf("a9d8ff");
        this.frontColor = Color.valueOf("ffffff");
        this.width = 3.25F;
        this.height = 2F;
        this.hitEffect = Fx.hitLancer;
        this.despawnEffect = Fx.hitLancer;
        this.shootEffect = Fx.none;
        this.smokeEffect = Fx.none;
    }

    public void load() {
        this.frontRegion = Core.atlas.find("circle");
    }

    public void draw(Bullet b) {
        float vel = b.vel().len() * 3.75F;
        Draw.color(this.backColor);
        Draw.rect(this.frontRegion, b.x, b.y, this.width, this.height + vel, b.rotation() - 90.0F);
        Draw.color(this.frontColor);
        Draw.rect(this.frontRegion, b.x, b.y, this.width * 0.625F, this.height * 0.625F + vel / 1.2F, b.rotation() - 90.0F);
    }
}
