package battle;

import battle.arena.Arena;
import droids.Droid;
import utils.Colors;

import java.util.List;

public abstract class Battle {
    protected Arena arena;
    protected List<Droid> team1;
    protected List<Droid> team2;

    public Battle(Arena arena, List<Droid> team1, List<Droid> team2) {
        this.arena = arena;
        this.team1 = team1;
        this.team2 = team2;
    }

    public void attack(Droid attacker, Droid target, List<Droid> targetTeam, List<String> battleLog) {
        if (target.isAlive()) {
            target.takeDamage(attacker.getDamage());
            String attackMessage = attacker.getName() + " attacks " +
                    target.getName() + " and deals " + attacker.getDamage() + " damage. " +
                    target.getName()+ " current health: " + target.getHealth();

            battleLog.add(attackMessage);
            System.out.println(attackMessage);

            if (target.canRegenerate()) {
                target.takeDamage(0);
            }
            if (target.canUseStimpack()) {
                target.takeDamage(0);
            }

            if (!target.isAlive()) {
                String deathMessage = target.getName() + " has been destroyed!";
                battleLog.add(deathMessage);
                System.out.println(deathMessage);
                targetTeam.remove(target);
                arena.removeDroid(target.getName());
            }
        }
    }

    public void startBattle(List<String> battleLog) {
        battleLog.add(Colors.CYAN_BOLD + "\nA BATTLE HAS BEGUN!" + Colors.RESET);

        for (Droid droid : team1) {
            arena.placeDroidRandomly(droid.getName());
        }
        for (Droid droid : team2) {
            arena.placeDroidRandomly(droid.getName());
        }

        arena.displayArena();
        boolean isBattleStarted = checkVicinity(team1, team2);
        int moveCounter = 0;

        while (!team1.isEmpty() && !team2.isEmpty()) {
            if (!isBattleStarted) {
                moveAndCheck(team1);
                moveAndCheck(team2);
                arena.displayArena();
                isBattleStarted = checkVicinity(team1, team2);
            }

            if (isBattleStarted) {
                whoAttacks(battleLog);
            } else {
                moveCounter++;
                if (moveCounter > 10) {
                    System.out.println(Colors.RED + "No attacks possible, battle is stalemated!" + Colors.RESET);
                    break;
                }
            }
        }

        String winMessage;
        if (team1.isEmpty()) {
            winMessage = Colors.CYAN_BOLD + "!TEAM 2 WINS!" + Colors.RESET;
        } else {
            winMessage = Colors.CYAN_BOLD + "!TEAM 1 WINS!" + Colors.RESET;
        }
        System.out.println(winMessage);
        battleLog.add(winMessage);
    }

    private void whoAttacks(List<String> battleLog) {
        boolean team1Turn = true;

        while (!team1.isEmpty() && !team2.isEmpty()) {
            List<Droid> attackers = team1Turn ? team1 : team2;
            List<Droid> defenders = team1Turn ? team2 : team1;

            for (Droid attacker : attackers) {
                if (!defenders.isEmpty()) {
                    performAttack(attacker, defenders, battleLog);

                    removeDestroyedDroids(team1);
                    removeDestroyedDroids(team2);
                    if (team1.isEmpty() || team2.isEmpty()) {
                        break;
                    }
                    if (!checkVicinity(team1, team2)) {
                        moveAndCheck(team1);
                        moveAndCheck(team2);
                        arena.displayArena();
                    }
                }
            }
            team1Turn = !team1Turn;
        }
    }

    private void performAttack(Droid attacker, List<Droid> targetTeam, List<String> battleLog) {
        for (Droid target : targetTeam) {
            if (arena.areAdjacent(attacker.getName(), target.getName())) {
                if (arena.isHitSuccessful()) {
                    attack(attacker, target, targetTeam, battleLog);
                } else {
                    String fogMessage = attacker.getName() + " missed the attack on " + target.getName() + " due to fog!";
                    battleLog.add(fogMessage);
                    System.out.println(fogMessage);
                }
                removeDestroyedDroids(targetTeam);
                return;
            }
        }
    }
    
    private boolean checkVicinity(List<Droid> team1, List<Droid> team2) {
        for (Droid droid1 : team1) {
            for (Droid droid2 : team2) {
                if (arena.areAdjacent(droid1.getName(), droid2.getName())) {
                    return true;
                }
            }
        }
        return false;
    }

    private void moveAndCheck(List<Droid> team) {
        for (Droid droid : team) {
            if (droid.isAlive()) {
                String name = droid.getName();
                arena.moveDroidRandomly(name);
            }
        }
    }

    private void removeDestroyedDroids(List<Droid> team) {
        team.removeIf(droid -> !droid.isAlive());
    }
}