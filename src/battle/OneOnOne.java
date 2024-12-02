package battle;

import battle.arena.Arena;
import droids.Droid;

import java.util.List;

public class OneOnOne extends Battle {
    public OneOnOne(Arena arena, List<Droid> team1, List<Droid> team2) {
        super(arena, team1, team2);
    }

    @Override
    public void attack(Droid attacker, Droid target, List<Droid> targetTeam, List<String> battleLog) {
        super.attack(attacker, target, targetTeam, battleLog);
    }
}