package eternity.content;

import mindustry.world.meta.Attribute;

public class EtAttributes {
    public static Attribute ward;

    public static void load() {
        ward = Attribute.add("ward");
    }
}
