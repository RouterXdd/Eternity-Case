package eternity.content;

import arc.audio.Sound;
import mindustry.Vars;

public class EtSounds {
    public static Sound
            zbosonShoot;
    public static void load() {
        zbosonShoot = Vars.tree.loadSound("zboson-shoot");
    }
}
