package eternity.classes.entities.abilities;

import arc.util.Nullable;
import arc.util.Time;
import mindustry.Vars;
import mindustry.content.Fx;
import mindustry.entities.Effect;
import mindustry.entities.abilities.Ability;
import mindustry.gen.Unit;
import mindustry.type.ItemStack;
import mindustry.world.modules.ItemModule;

public class ItemProductionAbility extends AdvAbility {
    public float produceTime = 12;
    public @Nullable ItemStack[] produceItems;
    public Effect produceEffect = Fx.producesmoke;

    protected float timer;
    @Override
    public void update(Unit unit) {
        timer += Time.delta;
        if (timer >= produceTime * 60 && produceItems != null){
            timer = 0;
            produceEffect.at(unit.x,unit.y);
            if (Vars.state.teams.get(unit.team()).core() != null) {
                ItemModule items = Vars.state.teams.get(unit.team()).core().items();
                if (items != null) {
                    for (ItemStack ItemStack : produceItems) {
                        if (items.get(ItemStack.item) < Vars.state.teams.get(unit.team()).core().getMaximumAccepted(ItemStack.item))
                            items.add(ItemStack.item, ItemStack.amount);
                    }
                }
            }
        }
    }
}
