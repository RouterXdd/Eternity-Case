package eternity.classes.type;

import mindustry.world.meta.Env;

public class StellarTankUnit extends StellarUnit{
    public StellarTankUnit(String name) {
        super(name);
        squareShape = true;
        omniMovement = false;
        rotateMoveFirst = true;
        rotateSpeed = 1.3f;
        envDisabled = Env.none;
        speed = 0.8f;
    }
}
