package eternity.classes.blocks.turrets.cult;

import arc.Core;
import arc.util.Nullable;
import mindustry.Vars;
import mindustry.game.Team;
import mindustry.world.*;
import mindustry.world.blocks.defense.turrets.Turret;

public class ReplaceTurret extends Turret {
    public ReplaceTurret(String name) {
        super(name);
    }
    public @Nullable Block replaceBlock = null;

    public boolean canReplace(Block other) {
        if (other.alwaysReplace) return true;
        return replaceBlock == null ? super.canReplace(other) : replaceBlock == other;
    }

    @Override
    public boolean canPlaceOn(Tile tile, Team team, int rotation) {
        if (tile == null) return false;
        if (Vars.state.isEditor() || replaceBlock == null || Vars.state.rules.infiniteResources) return true;

        tile.getLinkedTilesAs(this, tempTiles);
        return tempTiles.contains(o -> o.block() == replaceBlock);
    }

    @Override
    public void drawPlace(int x, int y, int rotation, boolean valid) {
        if (!valid && replaceBlock != null)
            drawPlaceText(Core.bundle.format("place-turret-on") + replaceBlock.emoji() + replaceBlock.localizedName, x, y, false);

        super.drawPlace(x, y, rotation, valid);
    }
}
