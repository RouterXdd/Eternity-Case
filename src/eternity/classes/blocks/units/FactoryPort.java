package eternity.classes.blocks.units;

import arc.Core;
import arc.Events;
import arc.math.Mathf;
import arc.math.geom.Vec2;
import arc.util.Nullable;
import mindustry.Vars;
import mindustry.ai.UnitCommand;
import mindustry.game.EventType;
import mindustry.gen.Unit;
import mindustry.world.blocks.payloads.UnitPayload;
import mindustry.world.blocks.units.UnitFactory;

public class FactoryPort extends UnitFactory {
    public FactoryPort(String name) {
        super(name);
        configurable = false;
    }
    public class FactoryPortBuild extends UnitFactoryBuild {
        public int currentPlan = -1;
        public @Nullable Vec2 commandPos;
        public @Nullable UnitCommand command;
        @Override
        public void updateTile(){
            if(!configurable){
                currentPlan = 0;
            }

            if(currentPlan < 0 || currentPlan >= plans.size){
                currentPlan = -1;
            }

            if(efficiency > 0 && currentPlan != -1){
                time += edelta() * speedScl * Vars.state.rules.unitBuildSpeed(team);
                progress += edelta() * Vars.state.rules.unitBuildSpeed(team);
                speedScl = Mathf.lerpDelta(speedScl, 1f, 0.05f);
            }else{
                speedScl = Mathf.lerpDelta(speedScl, 0f, 0.05f);
            }

            moveOutPayload();

            if(currentPlan != -1 && payload == null){
                UnitPlan plan = plans.get(currentPlan);

                //make sure to reset plan when the unit got banned after placement
                if(plan.unit.isBanned()){
                    currentPlan = -1;
                    return;
                }

                if(progress >= plan.time){
                    progress %= 1f;

                    Unit unit = plan.unit.create(team);
                    if(unit.isCommandable()){
                        if(commandPos != null){
                            unit.command().commandPosition(commandPos);
                        }

                        unit.command().command(command == null && unit.type.defaultCommand != null ? unit.type.defaultCommand : command);
                    }

                    payload = new UnitPayload(unit);
                    payVector.setZero();
                    consume();
                    Events.fire(new EventType.UnitCreateEvent(payload.unit, this));
                }

                progress = Mathf.clamp(progress, 0, plan.time);
            }else{
                progress = 0f;
            }
        }
    }
}
