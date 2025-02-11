package eternity.classes.blocks.turrets.cult;

import arc.*;
import arc.scene.ui.*;
import arc.scene.ui.layout.*;
import arc.struct.*;
import arc.util.io.*;
import mindustry.*;
import mindustry.content.*;
import mindustry.entities.bullet.*;
import mindustry.game.EventType;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.type.*;
import mindustry.ui.*;
import mindustry.world.blocks.defense.turrets.Turret;
import mindustry.world.consumers.*;
import mindustry.world.meta.*;

import static mindustry.Vars.*;

public class ItemReplaceTurret extends ReplaceTurret{
    public ObjectMap<Item, BulletType> ammoTypes = new OrderedMap<>();

    public ItemReplaceTurret(String name){
        super(name);
        hasItems = true;
    }
    public void ammo(Object... objects){
        ammoTypes = OrderedMap.of(objects);
    }

    public void limitRange(){
        limitRange(9f);
    }

    public void limitRange(float margin){
        for(var entry : ammoTypes.entries()){
            limitRange(entry.value, margin);
        }
    }

    @Override
    public void setStats(){
        super.setStats();

        stats.remove(Stat.itemCapacity);
        stats.add(Stat.ammo, StatValues.ammo(ammoTypes));
    }

    @Override
    public void setBars(){
        super.setBars();

        addBar("ammo", (ItemReplaceTurret.ItemReplaceTurretBuild entity) ->
                new Bar(
                        "stat.ammo",
                        Pal.ammo,
                        () -> (float)entity.totalAmmo / maxAmmo
                )
        );
    }

    @Override
    public void init(){
        consume(new ConsumeItemFilter(i -> ammoTypes.containsKey(i)){
            @Override
            public void build(Building build, Table table){
                MultiReqImage image = new MultiReqImage();
                content.items().each(i -> filter.get(i) && i.unlockedNow(),
                        item -> image.add(new ReqImage(new Image(item.uiIcon),
                                () -> build instanceof ItemReplaceTurret.ItemReplaceTurretBuild it && !it.ammo.isEmpty() && ((ItemReplaceTurret.ItemEntry)it.ammo.peek()).item == item)));

                table.add(image).size(8 * 4);
            }

            @Override
            public float efficiency(Building build){
                return build instanceof ItemReplaceTurret.ItemReplaceTurretBuild it && !it.ammo.isEmpty() ? 1f : 0f;
            }

            @Override
            public void display(Stats stats){
            }
        });

        ammoTypes.each((item, type) -> placeOverlapRange = Math.max(placeOverlapRange, range + type.rangeChange + placeOverlapMargin));

        super.init();
    }

    public class ItemReplaceTurretBuild extends TurretBuild {

        @Override
        public void onProximityAdded(){
            super.onProximityAdded();

            if(!hasAmmo() && cheating() && ammoTypes.size > 0){
                handleItem(this, ammoTypes.keys().next());
            }
        }

        @Override
        public void updateTile(){
            unit.ammo((float)unit.type().ammoCapacity * totalAmmo / maxAmmo);

            super.updateTile();
        }

        @Override
        public int acceptStack(Item item, int amount, Teamc source){
            BulletType type = ammoTypes.get(item);

            if(type == null) return 0;

            return Math.min((int)((maxAmmo - totalAmmo) / ammoTypes.get(item).ammoMultiplier), amount);
        }

        @Override
        public void handleStack(Item item, int amount, Teamc source){
            for(int i = 0; i < amount; i++){
                handleItem(null, item);
            }
        }

        //currently can't remove items from turrets.
        @Override
        public int removeStack(Item item, int amount){
            return 0;
        }

        @Override
        public void handleItem(Building source, Item item){

            if(item == Items.pyratite){
                Events.fire(EventType.Trigger.flameAmmo);
            }

            if(totalAmmo == 0){
                Events.fire(EventType.Trigger.resupplyTurret);
            }

            BulletType type = ammoTypes.get(item);
            if(type == null) return;
            totalAmmo += type.ammoMultiplier;

            //find ammo entry by type
            for(int i = 0; i < ammo.size; i++){
                ItemReplaceTurret.ItemEntry entry = (ItemReplaceTurret.ItemEntry)ammo.get(i);

                //if found, put it to the right
                if(entry.item == item){
                    entry.amount += type.ammoMultiplier;
                    ammo.swap(i, ammo.size - 1);
                    return;
                }
            }

            //must not be found
            ammo.add(new ItemReplaceTurret.ItemEntry(item, (int)type.ammoMultiplier));
        }

        @Override
        public boolean acceptItem(Building source, Item item){
            return ammoTypes.get(item) != null && totalAmmo + ammoTypes.get(item).ammoMultiplier <= maxAmmo;
        }

        @Override
        public byte version(){
            return 2;
        }

        @Override
        public void write(Writes write){
            super.write(write);
            write.b(ammo.size);
            for(Turret.AmmoEntry entry : ammo){
                ItemReplaceTurret.ItemEntry i = (ItemReplaceTurret.ItemEntry)entry;
                write.s(i.item.id);
                write.s(i.amount);
            }
        }

        @Override
        public void read(Reads read, byte revision){
            super.read(read, revision);
            ammo.clear();
            totalAmmo = 0;
            int amount = read.ub();
            for(int i = 0; i < amount; i++){
                Item item = Vars.content.item(revision < 2 ? read.ub() : read.s());
                short a = read.s();

                //only add ammo if this is a valid ammo type
                if(item != null && ammoTypes.containsKey(item)){
                    totalAmmo += a;
                    ammo.add(new ItemReplaceTurret.ItemEntry(item, a));
                }
            }
        }
    }

    public class ItemEntry extends Turret.AmmoEntry {
        public Item item;

        ItemEntry(Item item, int amount){
            this.item = item;
            this.amount = amount;
        }

        @Override
        public BulletType type(){
            return ammoTypes.get(item);
        }

        @Override
        public String toString(){
            return "ItemEntry{" +
                    "item=" + item +
                    ", amount=" + amount +
                    '}';
        }
    }
}
