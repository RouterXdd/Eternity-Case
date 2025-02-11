package eternity.classes.blocks.turrets.cult;

import arc.math.Angles;
import arc.math.Mathf;
import arc.struct.ObjectMap;
import arc.struct.Seq;
import arc.util.Nullable;
import arc.util.Tmp;
import arc.util.io.Reads;
import arc.util.io.Writes;
import mindustry.content.Bullets;
import mindustry.entities.bullet.BulletType;
import mindustry.gen.Bullet;
import mindustry.world.blocks.defense.turrets.Turret;
import mindustry.world.consumers.*;
import mindustry.world.meta.*;

public class ContinuousReplaceTurret extends ReplaceTurret{
    public BulletType shootType = Bullets.placeholder;
    /** Speed at which the turret can change its bullet "aim" distance. This is only used for point laser bullets. */
    public float aimChangeSpeed = Float.POSITIVE_INFINITY;

    public ContinuousReplaceTurret(String name){
        super(name);

        coolantMultiplier = 1f;
        envEnabled |= Env.space;
        displayAmmoMultiplier = false;
    }

    @Override
    public void setStats(){
        super.setStats();

        stats.add(Stat.ammo, StatValues.ammo(ObjectMap.of(this, shootType)));
        stats.remove(Stat.reload);
        stats.remove(Stat.inaccuracy);
    }

    //TODO LaserTurret shared code
    public class ContinuousTurretBuild extends Turret.TurretBuild {
        public Seq<Turret.BulletEntry> bullets = new Seq<>();
        public float lastLength = size * 4f;

        @Override
        protected void updateCooling(){
            //TODO how does coolant work here, if at all?
        }

        @Override
        public BulletType useAmmo(){
            //nothing used directly
            return shootType;
        }

        @Override
        public boolean hasAmmo(){
            //TODO update ammo in unit so it corresponds to liquids
            return canConsume();
        }

        @Override
        public boolean shouldConsume(){
            return isShooting();
        }

        @Override
        public BulletType peekAmmo(){
            return shootType;
        }

        @Override
        public void updateTile(){
            super.updateTile();

            //TODO unclean way of calculating ammo fraction to display
            float ammoFract = efficiency;
            if(findConsumer(f -> f instanceof ConsumeLiquidBase) instanceof ConsumeLiquid cons){
                ammoFract = Math.min(ammoFract, liquids.get(cons.liquid) / liquidCapacity);
            }

            unit.ammo(unit.type().ammoCapacity * ammoFract);

            bullets.removeAll(b -> !b.bullet.isAdded() || b.bullet.type == null || b.bullet.owner != this);

            if(bullets.any()){
                for(var entry : bullets){
                    updateBullet(entry);
                }

                wasShooting = true;
                heat = 1f;
                curRecoil = recoil;
            }
        }

        protected void updateBullet(Turret.BulletEntry entry){
            float
                    bulletX = x + Angles.trnsx(rotation - 90, shootX + entry.x, shootY + entry.y),
                    bulletY = y + Angles.trnsy(rotation - 90, shootX + entry.x, shootY + entry.y),
                    angle = rotation + entry.rotation;

            entry.bullet.rotation(angle);
            entry.bullet.set(bulletX, bulletY);

            //target length of laser
            float shootLength = Math.min(dst(targetPos), range);
            //current length of laser
            float curLength = dst(entry.bullet.aimX, entry.bullet.aimY);
            //resulting length of the bullet (smoothed)
            float resultLength = Mathf.approachDelta(curLength, shootLength, aimChangeSpeed);
            //actual aim end point based on length
            Tmp.v1.trns(rotation, lastLength = resultLength).add(x, y);

            entry.bullet.aimX = Tmp.v1.x;
            entry.bullet.aimY = Tmp.v1.y;

            if(isShooting() && hasAmmo()){
                entry.bullet.time = entry.bullet.lifetime * entry.bullet.type.optimalLifeFract * shootWarmup;
                entry.bullet.keepAlive = true;
            }
        }

        @Override
        protected void updateReload(){
            //continuous turrets don't have a concept of reload, they are always firing when possible
        }

        @Override
        protected void updateShooting(){
            if(bullets.any()){
                return;
            }

            if(canConsume() && !charging() && shootWarmup >= minWarmup){
                shoot(peekAmmo());
            }
        }

        @Override
        protected void turnToTarget(float targetRot){
            rotation = Angles.moveToward(rotation, targetRot, efficiency * rotateSpeed * delta());
        }

        @Override
        protected void handleBullet(@Nullable Bullet bullet, float offsetX, float offsetY, float angleOffset){
            if(bullet != null){
                bullets.add(new Turret.BulletEntry(bullet, offsetX, offsetY, angleOffset, 0f));

                //make sure the length updates to the last set value
                Tmp.v1.trns(rotation, shootY + lastLength).add(x, y);
                bullet.aimX = Tmp.v1.x;
                bullet.aimY = Tmp.v1.y;
            }
        }

        @Override
        public boolean shouldActiveSound(){
            return bullets.any();
        }

        @Override
        public float activeSoundVolume(){
            return 1f;
        }

        @Override
        public byte version(){
            return 3;
        }

        @Override
        public void write(Writes write){
            super.write(write);

            write.f(lastLength);
        }

        @Override
        public void read(Reads read, byte revision){
            super.read(read, revision);

            if(revision >= 3){
                lastLength = read.f();
            }
        }
    }
}
