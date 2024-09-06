package eternity.classes.entities.bullets;

import eternity.content.EtStatuses;
import mindustry.entities.bullet.BasicBulletType;
import mindustry.gen.*;

public class AdvBasicBulletType extends BasicBulletType {
    public float leechAmount = 0f;
    public float leechPercent = 0f;
    public boolean ghosts = false;
    public float ghostTime = 90;
    @Override
    public void hitTile(Bullet b, Building build, float x, float y, float initialHealth, boolean direct){
        super.hitTile(b, build, x, y, initialHealth, direct);
        if(b.owner instanceof Healthc h){
            if (leechPercent > 0) {
                h.heal(b.damage * leechPercent);
            } else {
                h.heal(leechAmount);
            }
        }
    }
    @Override
    public void hitEntity(Bullet b, Hitboxc entity, float health) {
        super.hitEntity(b, entity, health);
        if(b.owner instanceof Healthc h){
            if (!ghosts) {
                if (leechPercent > 0) {
                    h.heal(b.damage * leechPercent);
                } else {
                    h.heal(leechAmount);
                }
            } else {
                if (leechPercent > 0 && b.damage >= h.health()) {
                    h.heal(b.damage * leechPercent);
                } else if (b.damage >= h.health()){
                    h.heal(leechAmount);
                }
            }
        }
        if(entity instanceof Unit unit && ghosts){
            if (b.damage >= unit.health()) {
                unit.team = b.team;
                unit.apply(EtStatuses.ghosted, ghostTime * 60);
            }
        }
    }

}

