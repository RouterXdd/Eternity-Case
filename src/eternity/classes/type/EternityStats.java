package eternity.classes.type;

import arc.Core;
import arc.struct.Seq;
import mindustry.world.meta.Stat;
import mindustry.world.meta.StatCat;

import java.util.Locale;

public class EternityStats implements Comparable<Stat>{
    public static final Seq<EternityStats> allStats = new Seq<>();

    public static final Stat
            shootsReq = new Stat("shoots-req", EternityStatCat.requirements),
            hitsReq = new Stat("hits-req", EternityStatCat.requirements);
    public final StatCat category;
    public final String name;
    public final int id;

    public EternityStats(String name, StatCat category){
        this.category = category;
        this.name = name;
        id = allStats.size;
        allStats.add(this);
    }

    public EternityStats(String name){
        this(name, StatCat.general);
    }

    public String localized(){
        return Core.bundle.get("stat." + name.toLowerCase(Locale.ROOT));
    }

    @Override
    public String toString(){
        return name;
    }

    @Override
    public int compareTo(Stat o){
        return id - o.id;
    }
}
