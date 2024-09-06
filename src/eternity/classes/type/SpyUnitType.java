package eternity.classes.type;

import eternity.content.EtStatuses;
import mindustry.game.Team;
import mindustry.gen.Tankc;
import mindustry.gen.Unit;

public class SpyUnitType extends StellarUnit{

    public SpyUnitType(String name) {
        super(name);
        targetable = false;
        hittable = false;
    }
    public boolean targetable(Unit unit, Team targeter){
        return targetable || unit.isShooting() || unit.hasEffect(EtStatuses.detected);
    }

    public boolean hittable(Unit unit){
        return hittable || unit.isShooting() || unit.hasEffect(EtStatuses.detected);
    }
}
