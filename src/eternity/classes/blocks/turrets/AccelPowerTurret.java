package eternity.classes.blocks.turrets;

import arc.graphics.Color;
import arc.graphics.g2d.Draw;
import arc.graphics.g2d.Lines;
import arc.math.Angles;
import arc.math.Mathf;
import arc.math.geom.Vec2;
import arc.util.Time;
import arc.util.Tmp;
import arc.util.io.Reads;
import arc.util.io.Writes;
import mindustry.entities.Lightning;
import mindustry.entities.Mover;
import mindustry.entities.bullet.BulletType;
import mindustry.entities.part.RegionPart;
import mindustry.graphics.Layer;
import mindustry.world.blocks.defense.turrets.PowerTurret;
import mindustry.world.blocks.defense.turrets.Turret;
import mindustry.world.draw.DrawTurret;

public class AccelPowerTurret extends PowerTurret {
    public float barBaseY;
    public float barBaseX;
    public float barLength;
    public float barStroke = 1.5F;
    public Color[] barColors = new Color[]{Color.valueOf("91c2cb"), Color.valueOf("ccffff")};
    public float maxSpeedMul = 13.0F;
    public float speedInc = 0.2F;
    public float speedDec = 0.05F;
    public float accInc = 2.5F;
    public boolean lightning;
    public Color lightningColor = Color.valueOf("a9d8ff");
    public int baseLightningLength;
    public int lightningLengthDec;
    public float lightningThreshold;
    public float baseLightningDamage;
    public float lightningDamageDec;

    public AccelPowerTurret(String name) {
        super(name);
        recoils = 2;
        drawer = new DrawTurret("light-"){{
                for(int i = 0; i < 2; i ++){
                    int f = i;
                    parts.add(new RegionPart("-barrel-" + (i == 0 ? "l" : "r")){{
                        progress = PartProgress.recoil;
                        recoilIndex = f;
                        under = true;
                        moveY = -2f;
                    }});
                }
            }
            @Override
            public void drawTurret(Turret block, TurretBuild build) {
                if(!(build instanceof AccelPowerTurretBuild t)) return;
                Vec2 v = Tmp.v1;
                Draw.z(Layer.turret);
                super.drawTurret(block, build);
                if (t.speedAc() > 1.001F) {
                    v.trns(t.drawrot(), barBaseX, barBaseY).add(t.recoilOffset);
                    Draw.color(barColors[0], barColors[1], t.heat);
                    Lines.stroke(barStroke);
                    Lines.lineAngle(t.x + v.x, t.y + v.y, t.rotation, t.speedf() * barLength, false);
                    Draw.reset();
                }
            }
        };
    }

    public class AccelPowerTurretBuild extends PowerTurretBuild {
        public float speed = 1.0F;

        @Override
        public void updateTile() {
            if (!isShooting()) {
                changeSpeed(-speedDec * Time.delta);
            }

            super.updateTile();
        }


        @Override
        public void shoot(BulletType type) {
            super.shoot(type);
            float
                    lightX = x + Angles.trnsx(rotation - 90, shootX, shootY),
                    lightY = y + Angles.trnsy(rotation - 90, shootX, shootY);
            changeSpeed(speedInc);
            if (lightning && speedAc() < lightningThreshold){
                Lightning.create(team, lightningColor, baseLightningDamage - lightningDamageDec * speedAc(), lightX, lightY, rotation, baseLightningLength - (int)((speedAc() - 1.0F) * (float)lightningLengthDec));
            }
        }
        @Override
        protected void bullet(BulletType type, float xOffset, float yOffset, float angleOffset, Mover mover){
            super.bullet(type, xOffset, yOffset, angleOffset + Mathf.range(inaccuracy + type.inaccuracy + speedf() * accInc), mover);
        }

        protected float baseReloadSpeed() {
            return efficiency() * speed;
        }

        public void changeSpeed(float amount) {
            speed = Mathf.clamp(speed + amount, 1.0F, maxSpeedMul);
        }

        public float speedf() {
            return (speed - 1.0F) / (maxSpeedMul - 1.0F);
        }
        public float speedAc() {
            return speed;
        }
        @Override
        public void write(Writes write) {
            super.write(write);

            write.f(speed);
        }

        @Override
        public void read(Reads read, byte revision) {
            super.read(read, revision);

            speed = read.f();
        }
    }
}
