package eternity.classes.blocks.turrets.cult;

import arc.struct.ObjectMap;
import mindustry.entities.bullet.BulletType;
import mindustry.logic.LAccess;
import mindustry.world.meta.Stat;
import mindustry.world.meta.StatValues;

public class PowerReplaceTurret extends ReplaceTurret{
    public BulletType shootType;

    public PowerReplaceTurret(String name){
        super(name);
        hasPower = true;
    }

    @Override
    public void setStats(){
        super.setStats();
        stats.add(Stat.ammo, StatValues.ammo(ObjectMap.of(this, shootType)));
    }
    @Override
    public void setBars(){
        super.setBars();
    }

    public void limitRange(float margin){
        limitRange(shootType, margin);
    }

    public class PowerReplaceTurretBuild extends TurretBuild {

        @Override
        public void updateTile(){
            unit.ammo(power.status * unit.type().ammoCapacity);

            super.updateTile();
        }

        @Override
        public double sense(LAccess sensor){
            return switch(sensor){
                case ammo -> power.status;
                case ammoCapacity -> 1;
                default -> super.sense(sensor);
            };
        }

        @Override
        public BulletType useAmmo(){
            //nothing used directly
            return shootType;
        }
        @Override
        public boolean hasAmmo(){
            //you can always rotate, but never shoot if there's no power
            return true;
        }

        @Override
        public BulletType peekAmmo(){
            return shootType;
        }
    }
}
