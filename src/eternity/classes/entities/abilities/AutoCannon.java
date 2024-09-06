package eternity.classes.entities.abilities;

import arc.math.Angles;
import arc.util.Nullable;
import arc.util.Time;
import mindustry.content.Fx;
import mindustry.entities.Effect;
import mindustry.entities.Units;
import mindustry.entities.bullet.BulletType;
import mindustry.game.Team;
import mindustry.gen.Unit;

import static mindustry.Vars.state;

public class AutoCannon extends AdvAbility {
    public float reload = 10, range = 70f;
    public Effect shootEffect = Fx.disperseTrail;
    public float x = 0, y = 0;
    public boolean targetGround = true, targetAir = true, targetBuildings = true, targetUnits = true;
    public boolean shootOnBoost = false;
    public BulletType bullet = null;
    float angle = 0;
    float reloadTime;
    public AutoCannon(){
        display = false;
    }
    @Override
    public void update(Unit unit) {
        updateTarget(unit, range);
        reloadTime += Time.delta;
        if (reloadTime >= reload && bullet != null){
            if (shootOnBoost && unit.isFlying()){
                bullet.create(unit, x, y, angle);
                shootEffect.at(x, y, angle);
            } else if (!shootOnBoost){
                bullet.create(unit, x, y, angle);
                shootEffect.at(x, y, angle);
            }
            reloadTime = 0;
        }
    }

    public void updateTarget(Unit unit, float range) {
        Units.closestTarget(unit.team, x, y, range, enemy -> {
            if(enemy != unit && enemy.checkTarget(targetAir, targetGround) && enemy.targetable(unit.team) && enemy.team != unit.team && targetUnits){
                angle = Angles.angle(enemy.x, enemy.y);
            }
            return true;
        }, enemy2 -> {
            if((enemy2.team != Team.derelict || state.rules.coreCapture) && enemy2.team != unit.team && targetBuildings){
                angle = Angles.angle(enemy2.x, enemy2.y);
            }
            return true;
        });
    }
}
