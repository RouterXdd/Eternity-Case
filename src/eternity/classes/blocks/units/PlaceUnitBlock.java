package eternity.classes.blocks.units;

import arc.Core;
import mindustry.Vars;
import mindustry.entities.Units;
import mindustry.game.Team;
import mindustry.gen.Building;
import mindustry.gen.Unit;
import mindustry.type.UnitType;
import mindustry.world.Block;
import mindustry.world.Tile;

import static mindustry.content.StatusEffects.*;

public class PlaceUnitBlock extends Block {
    public UnitType unitType;
    public PlaceUnitBlock(String name) {
        super(name);
        rotate = true;
        update = true;
    }
    @Override
    public boolean canPlaceOn(Tile tile, Team team, int rotation) {
        if (tile == null) return false;
        return Units.canCreate(team, unitType);
    }
    @Override
    public void drawPlace(int x, int y, int rotation, boolean valid) {
        super.drawPlace(x, y, rotation, valid);
        if (!Units.canCreate(Vars.player.team(), unitType)) drawPlaceText(Core.bundle.format("bar.unitcapreach"), x, y, false);
    }
    public class PlaceUnitBlockBuild extends Building {
        @Override
        public void updateTile() {
            super.updateTile();
            if (unitType != null) {
                Unit unit = unitType.create(team);
                unit.set(x, y);
                unit.rotation = rotation * 90f;
                unit.add();
                tileOn().remove();
            }
        }
    }
}
