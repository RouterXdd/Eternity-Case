package eternity.content;

import mindustry.ctype.ContentType;
import mindustry.ctype.UnlockableContent;

public class EtTechTreeRoots {
    public static UnlockableContent
    miscRoot, stellarRoot, cultRoot;
    public static void load(){
        miscRoot = new UnlockableContent("book-misc") {
            @Override
            public ContentType getContentType() {
                return ContentType.effect_UNUSED;
            }
            {alwaysUnlocked = true;}
        };
        stellarRoot = new UnlockableContent("book-stellar") {
            @Override
            public ContentType getContentType() {
                return ContentType.effect_UNUSED;
            }
            {alwaysUnlocked = true;}
        };
        cultRoot = new UnlockableContent("book-cult") {
            @Override
            public ContentType getContentType() {
                return ContentType.effect_UNUSED;
            }
            {alwaysUnlocked = true;}
        };
    }
}
