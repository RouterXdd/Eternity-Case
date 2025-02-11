package eternity.classes.blocks.power;

import arc.Core;
import arc.graphics.Color;
import arc.graphics.g2d.Draw;
import arc.graphics.g2d.TextureRegion;
import arc.math.Mathf;
import arc.util.Time;
import mindustry.world.blocks.power.NuclearReactor;

public class NukeRector extends NuclearReactor {
    public TextureRegion silhouetteRegion;
    public NukeRector(String name) {
        super(name);
    }
    @Override
    public void load(){
        super.load();
        silhouetteRegion = Core.atlas.find(this.name + "-silhouette");
    }
    public class NukeRectorBuild extends NuclearReactorBuild {
        @Override
        public void draw(){
            drawer.draw(this);
            Draw.color(coolColor, hotColor, heat);
            Draw.rect(silhouetteRegion,x, y);

            Draw.color(liquids.current().color);
            Draw.alpha(liquids.currentAmount() / liquidCapacity);
            Draw.rect(topRegion, x, y);

            if(heat > flashThreshold){
                flash += (1f + ((heat - flashThreshold) / (1f - flashThreshold)) * 5.4f) * Time.delta;
                Draw.color(Color.red, Color.yellow, Mathf.absin(flash, 9f, 1f));
                Draw.alpha(0.3f);
                Draw.rect(lightsRegion, x, y);
            }

            Draw.reset();
        }
    }
}
