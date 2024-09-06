package eternity.classes.type;

import arc.graphics.Color;
import mindustry.type.UnitType;
import mindustry.type.ammo.PowerAmmoType;
import mindustry.world.meta.Env;

public class StellarUnit extends UnitType {
    public StellarUnit(String name) {
        super(name);
        outlineColor = Color.valueOf("2b2b4e");
        drawCell = false;
        ammoType = new PowerAmmoType(1500);
    }
}
