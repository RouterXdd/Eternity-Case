package eternity.classes.entities.statuses;

import mindustry.gen.Unit;
import mindustry.type.*;

public class GhostStatusEffect extends StatusEffect {
    public GhostStatusEffect(String name) {
        super(name);
    }
    @Override
    public void update(Unit unit, float time){
        super.update(unit, time);
        if (time <= 0.01f){
            unit.kill();
        }
    }
}
