package eternity.classes.blocks.environment;

import arc.graphics.Color;
import arc.math.Mathf;
import arc.util.Time;
import mindustry.content.Blocks;
import mindustry.content.Fx;
import mindustry.entities.Effect;
import mindustry.graphics.Pal;
import mindustry.world.Block;
import mindustry.world.blocks.environment.Floor;

public class AdvFloor extends Floor {
    public Effect effect = Fx.none;
    public Color effectColor;
    public float effectSpacing = 70f;
    public AdvFloor(String name){
        super(name);
        variants = 3;
        effectColor = mapColor;
    }

    public AdvFloor(String name, int variants){
        super(name);
        this.variants = variants;
        effectColor = mapColor;
    }

    public AdvFloor(String name, int variants, Block blendGroup){
        super(name);
        this.variants = variants;
        this.blendGroup = blendGroup;
        effectColor = mapColor;
    }
    @Override
    public void renderUpdate(UpdateRenderState state){
        if(state.tile.block() == Blocks.air && (state.data += Time.delta) >= Mathf.random(20f, effectSpacing)){
            effect.at(state.tile.x, state.tile.y, effectColor);
            state.data = 0f;
        }
    }
}
