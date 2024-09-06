package eternity.classes.entities.abilities;

import arc.Core;
import arc.graphics.g2d.Draw;
import arc.graphics.g2d.TextureRegion;
import arc.math.Mathf;
import arc.util.Time;
import arc.util.Tmp;
import mindustry.content.Fx;
import mindustry.entities.Effect;
import mindustry.entities.Units;
import mindustry.entities.abilities.Ability;
import mindustry.game.Team;
import mindustry.gen.Unit;
import mindustry.graphics.Drawf;
import mindustry.graphics.Layer;

import static mindustry.Vars.state;

public class SawbladeAbility extends AdvAbility {
    public float damage = 10, range = 15f;
    public Effect cutEffect = Fx.disperseTrail;
    public float x, y;
    public boolean targetGround = true, targetAir = true, hitBuildings = true, hitUnits = true;
    @Override
    public void update(Unit unit){
        Tmp.v1.trns(unit.rotation - 90, x, y).add(unit.x, unit.y);
        float rx = Tmp.v1.x, ry = Tmp.v1.y;
        if(hitUnits){
            Units.nearby(null, rx, ry, range, other -> {
                if(other != unit && other.checkTarget(targetAir, targetGround) && other.targetable(unit.team) && other.team != unit.team){
                    other.damage(damage);
                    cutEffect.at(other, Mathf.random(360));
                }
            });
        }

        if(hitBuildings && targetGround){
            Units.nearbyBuildings(rx, ry, range, b -> {
                if((b.team != Team.derelict || state.rules.coreCapture) && b.team != unit.team){
                    b.damage(damage);
                    cutEffect.at(b, Mathf.random(360));
                }
            });
        }
    }
}
