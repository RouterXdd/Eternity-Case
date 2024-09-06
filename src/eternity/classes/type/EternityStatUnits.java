package eternity.classes.type;

import arc.Core;
import arc.util.Nullable;
import mindustry.gen.Iconc;
import mindustry.world.meta.StatUnit;

import java.util.Locale;

public class EternityStatUnits {
    public static final StatUnit
    darknessUnits = new StatUnit("darknessUnits", "[purple]" + Iconc.box + "[]");

    public final boolean space;
    public final String name;
    public @Nullable String icon;

    public EternityStatUnits(String name, boolean space){
        this.name = name;
        this.space = space;
    }

    public EternityStatUnits(String name){
        this(name, true);
    }

    public EternityStatUnits(String name, String icon){
        this(name, true);
        this.icon = icon;
    }

    public String localized(){
        return Core.bundle.get("unit." + name.toLowerCase(Locale.ROOT));
    }
}
