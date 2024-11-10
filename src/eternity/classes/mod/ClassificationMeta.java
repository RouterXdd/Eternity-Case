package eternity.classes.mod;

import arc.struct.ObjectMap;
import mindustry.ctype.UnlockableContent;

import static eternity.classes.mod.Classification.*;
import static eternity.content.EtBlocks.*;
import static eternity.content.EtUnits.*;

public class ClassificationMeta {
    private static final ObjectMap<Object, Classification> map = new ObjectMap<>();

    public ClassificationMeta() {
    }

    public static void put(Object content, Classification classif) {
        map.put(content, classif);
        if (content instanceof UnlockableContent unlockable) {
            unlockable.description = unlockable.description + "\n[gray]Class:[] " + "[#" + classif.color + "]" + classif.localizedName + "[]";
        }

    }
    public static void init() {
    }
}
