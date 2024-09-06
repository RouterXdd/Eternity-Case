package eternity.classes.entities.bullets;

import arc.Events;
import arc.util.Tmp;
import mindustry.entities.bullet.BasicBulletType;
import mindustry.game.EventType;
import mindustry.gen.Bullet;
import mindustry.gen.Healthc;
import mindustry.gen.Hitboxc;
import mindustry.gen.Unit;

public class NotLethalBasicBulletType extends BasicBulletType {
    static final EventType.UnitDamageEvent bulletDamageEvent = new EventType.UnitDamageEvent();
    @Override
    public void hitEntity(Bullet b, Hitboxc entity, float health) {
        boolean wasDead = entity instanceof Unit u && u.dead;

        if(entity instanceof Healthc h){
            if (b.damage >= h.health()) {
                if (pierceArmor) {
                    h.damagePierce(b.damage);
                } else {
                    h.damage(b.damage);
                }
            }
        }

        if(entity instanceof Unit unit){
            Tmp.v3.set(unit).sub(b).nor().scl(knockback * 80f);
            if(impact) Tmp.v3.setAngle(b.rotation() + (knockback < 0 ? 180f : 0f));
            unit.impulse(Tmp.v3);
            unit.apply(status, statusDuration);

            Events.fire(bulletDamageEvent.set(unit, b));
        }

        if(!wasDead && entity instanceof Unit unit && unit.dead){
            Events.fire(new EventType.UnitBulletDestroyEvent(unit, b));
        }

        handlePierce(b, health, entity.x(), entity.y());
    }
}
