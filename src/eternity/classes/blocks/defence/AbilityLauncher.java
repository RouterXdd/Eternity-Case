package eternity.classes.blocks.defence;

import arc.graphics.Color;
import arc.util.Nullable;
import eternity.graphic.EternityPal;
import mindustry.entities.bullet.BulletType;
import mindustry.graphics.Pal;
import mindustry.world.Block;
import mindustry.world.meta.BlockGroup;

public class AbilityLauncher extends Block {
    //
    public float reloadTime = 40, preparationTime;
    public Color mainColor = EternityPal.oblivionColor, activateColor = Pal.redDust;
    public float polySize = 5, polySpeed = 2.5f;
    public int polySides = 3;
    public @Nullable BulletType bullet;
    public AbilityLauncher(String name) {
        super(name);
        hasPower = true;
        hasItems = true;
        solid = true;
        update = true;
        group = BlockGroup.projectors;
    }
}
