package menu;

import battle.OneOnOne;
import battle.TeamOnTeam;
import battle.arena.Arena;
import battle.arena.FoggyArena;
import droids.Droid;
import droids.Protoss;
import droids.Terran;
import droids.Zerg;
import utils.BattleFile;
import utils.Colors;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {
    private ArrayList<Droid> droids = new ArrayList<>();
    private List<String> battleLog = new ArrayList<>();
    private BattleFile battleFile = new BattleFile(scanner);
    static Scanner scanner = new Scanner(System.in);

    public void createConsoleMenu() {
        Colors.gameTheme();
        while (true) {
            System.out.println(Colors.BLUE_BOLD + "\n\t**CHOOSE ONE**" + Colors.RESET +
                    "\n1. Create a droid" +
                    "\n2. Show the list of created units" +
                    "\n3. Delete a droid" +
                    "\n4. Start a 1v1 battle" +
                    "\n5. Start a Team vs Team battle" +
                    "\n6. Record the battle in a file" +
                    "\n7. Play the battle from the saved file" +
                    "\n8. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    createUnit();
                    break;
                case 2:
                    showUnits();
                    break;
                case 3:
                    deleteUnit();
                    break;
                case 4:
                    prepareBattleOneOnOne();
                    break;
                case 5:
                    prepareBattleTeamOnTeam();
                    break;
                case 6:
                    battleFile.saveBattleFile(battleLog);
                    break;
                case 7:
                    battleFile.loadBattleFile(battleLog);
                    break;
                case 8:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again");
            }
        }
    }

    private boolean isNameUnique(String name) {
        for (Droid droid : droids) {
            if (droid.getName().equalsIgnoreCase(name)) {
                return false;
            }
        }
        return true;
    }

    public void createUnit() {
        Colors.droidStats();
        int type = scanner.nextInt();
        scanner.nextLine();

        System.out.print("\nEnter the droid's name: ");
        String name = scanner.nextLine();

        if (!isNameUnique(name)) {
            System.out.println(Colors.RED + "Droid with name '" + name + "' already exists. Choose a different name!" + Colors.RESET);
            return;
        }

        Droid droid;
        switch (type) {
            case 1:
                droid = new Protoss(name);
                break;
            case 2:
                droid = new Terran(name);
                break;
            case 3:
                droid = new Zerg(name);
                break;
            default:
                System.out.println(Colors.RED + "Invalid type selected." + Colors.RESET);
                return;
        }
        droids.add(droid);
        System.out.println(Colors.GREEN + "Droid " + name + " is created!\n" + Colors.RESET);
    }

    public void showUnits(){
        if (droids.isEmpty()) {
            droids.add(new Protoss("Colt"));
            droids.add(new Protoss("Knave"));
            droids.add(new Terran("Nexis"));
            droids.add(new Terran("Lily"));
            droids.add(new Zerg("Gir"));
            droids.add(new Zerg("Buzz"));
            droids.add(new Protoss("Marvin"));
            droids.add(new Terran("Rudy"));
            droids.add(new Zerg("Zenon"));
            droids.add(new Terran("Dynamo"));
        }

        System.out.println("List of created droids:");
        for (int i = 0; i < droids.size(); i++) {
            System.out.println((i + 1) + ". " + droids.get(i).getName());
        }
    }

    public void deleteUnit() {
        if (droids.isEmpty()) {
            System.out.println(Colors.RED + "No droids in here" + Colors.RESET);
            return;
        }

        showUnits();
        System.out.print("\nEnter the number of the droid to delete: ");
        int index = scanner.nextInt();
        scanner.nextLine();

        if (index > 0 && index <= droids.size()) {
            Droid deleteDroid = droids.remove(index - 1);
            System.out.println(Colors.GREEN + "Droid " + deleteDroid.getName() + " is deleted." + Colors.RESET);
        } else {
            System.out.println(Colors.RED + "Invalid number entered." + Colors.RESET);
        }
    }

    private int getIntInput(String message) {
        System.out.print(message);
        while (!scanner.hasNextInt()) {
            System.out.print(Colors.RED + "Please enter a valid number: " + Colors.RESET);
            scanner.next();
        }
        return scanner.nextInt();
    }

    private Arena chooseArena() {
        System.out.println(Colors.BLUE + "\nCHOOSE ARENA TYPE" + Colors.RESET);

        int arenaType = getIntInput("1. Regular, 2.Foggy: ");
        if (arenaType < 1 || arenaType > 2) {
            System.out.println(Colors.RED + "Wrong choice!" + Colors.RESET);
            return null;
        }

        int arenaW = getIntInput("\nEnter arena width: ");
        int arenaH = getIntInput("Enter arena height: ");

        if (arenaW < 1 || arenaW > 20 || arenaH < 1 || arenaH > 20) {
            System.out.println(Colors.RED + "Wrong arena size! Max 20x20" + Colors.RESET);
            return null;
        }

        if (arenaType == 1) {
            return new Arena(arenaW, arenaH);
        } else {
            return new FoggyArena(arenaW, arenaH);
        }
    }

    public void prepareBattleOneOnOne() {
        if (droids.size() < 2) {
            System.out.println(Colors.RED + "You need 2 droids to start a battle!" + Colors.RESET);
            return;
        }

        showUnits();
        int firstDroidSelect = getIntInput("Select the first droid: ") - 1;
        int secondDroidSelect = getIntInput("Select the second droid: ") - 1;

        if (firstDroidSelect < 0 || firstDroidSelect >= droids.size() || secondDroidSelect < 0 || secondDroidSelect >= droids.size()) {
            System.out.println(Colors.RED + "Invalid droid selection!\n" + Colors.RESET);
            return;
        }
        if (firstDroidSelect == secondDroidSelect) {
            System.out.println(Colors.RED + "There should be two different droids!" + Colors.RESET);
            return;
        }

        Arena arena = chooseArena();
        if (arena == null) {
            return;
        }

        List<Droid> team1 = new ArrayList<>(List.of(droids.get(firstDroidSelect)));
        List<Droid> team2 = new ArrayList<>(List.of(droids.get(secondDroidSelect)));

        OneOnOne battle = new OneOnOne(arena, team1, team2);
        battle.startBattle(battleLog);
    }

    public void prepareBattleTeamOnTeam() {
        if (droids.size() < 2) {
            System.out.println(Colors.RED + "You need at least 2 droids to form teams!" + Colors.RESET);
            return;
        }
        showUnits();

        Arena arena = chooseArena();
        if (arena == null) {
            return;
        }

        if (droids.size() > (arena.getWidth() * arena.getHeight())) {
            System.out.println(Colors.RED + "The arena is too small for " + droids.size() + " droids!" + Colors.RESET);
            return;
        }

        List<Droid> selectedDroids = new ArrayList<>();
        List<Droid> team1 = selectDroidToTeam(1, selectedDroids);
        List<Droid> team2 = selectDroidToTeam(2, selectedDroids);

        if (team1.size() != team2.size()) {
            System.out.println(Colors.RED + "Teams must have an equal number of droids!" + Colors.RESET);
            return;
        }

        TeamOnTeam battle = new TeamOnTeam(arena, team1, team2);
        battle.startBattle(battleLog);
    }

    private List<Droid> selectDroidToTeam(int teamNum, List<Droid> selectedDroids) {
        int teamSize = getIntInput("\nEnter the number of droids in Team " + teamNum + ": ");
        List<Droid> team = new ArrayList<>();

        for (int i = 0; i < teamSize; i++) {
            int index = getIntInput("Select droid " + (i + 1) + " for Team " + teamNum + " by number: ") - 1;
            if (index >= 0 && index < droids.size()) {
                Droid selectedDroid = droids.get(index);

                if (!team.contains(selectedDroid) && !selectedDroids.contains(selectedDroid)) {
                    team.add(selectedDroid);
                    selectedDroids.add(selectedDroid);
                } else {
                    System.out.println(Colors.RED + "This droid has already been selected. Please choose a different droid!" + Colors.RESET);
                    i--;
                }
            } else {
                System.out.println(Colors.RED + "Invalid selection!" + Colors.RESET);
                i--;
            }
        }
        return team;
    }
}

