package eternity.classes.mod;

import arc.Core;
import arc.graphics.Color;
import eternity.graphic.EternityPal;
import mindustry.Vars;
import mindustry.graphics.Pal;

public enum Classification {
    ruin("ruined", Color.valueOf("5fa1da")),
    cycle("cycle", Color.valueOf("b3f4ff")),
    malachite("malachite", EternityPal.malachiteColor),
    cult("cultist", EternityPal.cultColor),
    polygon("polygon", Pal.lightishGray),
    misc("misc", Color.valueOf("eac2a9")),
    i("null", Pal.darkestGray);

    public static final Classification[] all = values();
    public final String name;
    public String localizedName;
    public final Color color;

    public static void init() {
        if (!Vars.headless) {
            Classification[] var0 = all;
            int var1 = var0.length;

            for(int var2 = 0; var2 < var1; ++var2) {
                Classification classif = var0[var2];
                classif.localizedName = Core.bundle.format("classification." + classif.name, classif.color);
            }

        }
    }

    private Classification(String name, Color color) {
        this.name = name;
        this.color = color.cpy();
    }

    public String toString() {
        return this.localizedName;
    }
}
