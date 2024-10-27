package eternity.classes.entities.abilities;

import eternity.content.EtBlocks;
import mindustry.content.Blocks;
import mindustry.gen.Unit;
import mindustry.world.Tile;

import static mindustry.Vars.*;


public class CrystalLandAbility extends AdvAbility{
    @Override
    public void death(Unit unit){
        if(!net.client()) {
            Tile tile = world.tileWorld(unit.x, unit.y);

            if (tile != null && tile.block() == Blocks.air) tile.setBlock(EtBlocks.malachiteCrystal, unit.team);
        }
    }
}
