package eternity.classes.blocks.storage;

import arc.Core;
import arc.graphics.Color;
import arc.graphics.g2d.Draw;
import arc.graphics.g2d.TextureRegion;
import arc.scene.ui.layout.Table;
import arc.struct.Seq;
import arc.util.Nullable;
import arc.util.io.*;
import eternity.content.EtStatuses;
import mindustry.Vars;
import mindustry.content.Fx;
import mindustry.entities.Units;
import mindustry.game.Team;
import mindustry.gen.Player;
import mindustry.gen.Unit;
import mindustry.graphics.Layer;
import mindustry.logic.LAccess;
import mindustry.type.UnitType;
import mindustry.ui.Styles;
import mindustry.world.Tile;
import mindustry.world.blocks.ItemSelection;
import mindustry.world.blocks.storage.CoreBlock;

import static mindustry.Vars.net;
import static mindustry.Vars.state;

public class CommandCore extends CoreBlock {
    public Seq<CommandCoreUnit> plans = new Seq<>(4);
    public TextureRegion[] typeRegion = new TextureRegion[5];
    public CommandCore(String name) {
        super(name);
        squareSprite = false;
        configurable = true;
        config(Integer.class, (CommandCoreBuild tile, Integer i) -> {
            if(!configurable) return;

            if(tile.currentPlan == i) return;
            tile.currentPlan = i < 0 || i >= plans.size ? -1 : i;
        });

        config(UnitType.class, (CommandCoreBuild tile, UnitType val) -> {
            if(!configurable) return;

            int next = plans.indexOf(p -> p.unit == val);
            if(tile.currentPlan == next) return;
            tile.currentPlan = next;
        });
    }
    @Override
    public TextureRegion[] icons(){
        return new TextureRegion[]{region,teamRegions[Team.sharded.id]};
    }
    @Override
    public void load(){
        super.load();
        for (int i = 0; i < typeRegion.length; i++) {
            typeRegion[i] = Core.atlas.find(name + "-" + i);
        }
    }
    public static class CommandCoreUnit{
        public UnitType unit;
        public int number;

        public CommandCoreUnit(UnitType unit, int number){
            this.unit = unit;
            this.number = number;

        }

        CommandCoreUnit(){}
    }
    public class CommandCoreBuild extends CoreBuild {
        public int currentPlan = -1;
        @Override
        public Object senseObject(LAccess sensor) {
            if (sensor == LAccess.config) return currentPlan == -1 ? null : plans.get(currentPlan).unit;
            return super.senseObject(sensor);
        }
        @Override
        public void buildConfiguration(Table table) {
            Seq<UnitType> units = Seq.with(plans).map(u -> u.unit).retainAll(u -> u.unlockedNow() && !u.isBanned());

            if (units.any()) {
                ItemSelection.buildTable(CommandCore.this, table, units, () -> currentPlan == -1 ? null : plans.get(currentPlan).unit, unit -> configure(plans.indexOf(u -> u.unit == unit)), selectionRows, selectionColumns);
            } else {
                table.table(Styles.black3, t -> t.add("@none").color(Color.lightGray));
            }
        }
        @Override
        public Object config(){
            return currentPlan;
        }
        @Override
        public void draw(){
            super.draw();
            if (currentPlan != -1) {
                CommandCoreUnit plan = plans.get(currentPlan);
                Draw.rect(typeRegion[plan.number], x, y);
            } else {
                Draw.rect(typeRegion[0], x, y);
            }
            Draw.color(this.team.color);
            Draw.rect(teamRegion, x, y);
        }
        @Override
        public void updateTile() {
            super.updateTile();
            Units.nearbyEnemies(this.team, x,y,(fogRadius * 8f) / 2f, u -> {
                u.apply(EtStatuses.detected, 5);
            });
            if (!configurable) {
                currentPlan = 0;
            }

            if (currentPlan < 0 || currentPlan >= plans.size) {
                currentPlan = -1;
            }
            if(currentPlan != -1) unitType = coreUnit();
        }
        public @Nullable UnitType coreUnit(){
            return currentPlan == - 1 ? null : plans.get(currentPlan).unit;
        }
        @Override
        public void write(Writes write){
            super.write(write);
            write.s(currentPlan);
        }

        @Override
        public void read(Reads read, byte revision){
            super.read(read, revision);
            currentPlan = read.s();
        }
    }
}
