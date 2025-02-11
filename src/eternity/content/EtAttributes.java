package eternity.content;

import mindustry.world.meta.Attribute;

public class EtAttributes {
    public static Attribute ward, oily, skinted, merc;

    public static void load() {
        ward = Attribute.add("ward");
        oily = Attribute.add("oily");
        skinted = Attribute.add("skinted");
        merc = Attribute.add("merc");
    }
}
