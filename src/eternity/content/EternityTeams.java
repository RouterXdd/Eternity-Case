package eternity.content;

import arc.graphics.Color;
import eternity.graphic.EternityPal;
import mindustry.game.Team;
import mindustry.graphics.Pal;

public class EternityTeams {
    public static Team
            risen, cultist, malachite;

    public static void load() {
        risen = newTeam(24, "risen", Color.valueOf("22397d"));
        cultist = newTeam(25, "cultist", EternityPal.cultColor);
        malachite = newTeam(26, "malachite", Color.valueOf("4cd84f"));
    }

    //modify any of 256 teams' properties
    private static Team newTeam(int id, String name, Color color) {
        Team team = Team.get(id);
        team.name = name;
        team.color.set(color);

        team.palette[0] = color;
        team.palette[1] = color.cpy().mul(0.75f);
        team.palette[2] = color.cpy().mul(0.5f);

        for(int i = 0; i < 3; i++){
            team.palettei[i] = team.palette[i].rgba();
        }

        return team;
    }
}
