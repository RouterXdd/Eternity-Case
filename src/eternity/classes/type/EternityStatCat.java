package eternity.classes.type;

import arc.Core;
import arc.struct.Seq;
import mindustry.world.meta.StatCat;

public class EternityStatCat implements Comparable<StatCat>{
    public static final Seq<EternityStatCat> all = new Seq<>();

    public static final StatCat

            requirements = new StatCat("requirements");

    public final String name;
    public final int id;

    public EternityStatCat(String name){
        this.name = name;
        id = all.size;
        all.add(this);
    }

    public String localized(){
        return Core.bundle.get("category." + name);
    }

    @Override
    public String toString(){
        return name;
    }

    @Override
    public int compareTo(mindustry.world.meta.StatCat o){
        return id - o.id;
    }
}
