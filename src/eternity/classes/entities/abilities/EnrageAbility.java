package eternity.classes.entities.abilities;

import eternity.content.EtStatuses;
import mindustry.gen.Unit;
import mindustry.type.StatusEffect;

public class EnrageAbility extends AdvAbility{
    public float activatePercent = 35f;
    public StatusEffect status = EtStatuses.enrage;
    public float statusTime = 10;
    @Override
    public void update(Unit unit){
        if (unit.health < unit.maxHealth * activatePercent / 100f) {
            unit.apply(status, statusTime);
        }
    }
}
