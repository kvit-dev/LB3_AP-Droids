package battle.arena;

import utils.Colors;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Arena {
    private int width;
    private int height;
    private Map<String, int[]> droidPositions;

    public Arena(int width, int height) {
        this.width = width;
        this.height = height;
        this.droidPositions = new HashMap<>();
    }

    public int getWidth() {return width;}
    public int getHeight() {return height;}

    public void placeDroidRandomly(String name) {
        Random random = new Random();
        int x, y;
        do {
            x = random.nextInt(width);
            y = random.nextInt(height);
        } while (isPositionOccupied(x, y));

        droidPositions.put(name, new int[]{y, x});
    }

    public void moveDroidRandomly(String name) {
        if (!droidPositions.containsKey(name)) {
            return;
        }

        int[] currentPos = droidPositions.get(name);
        int x = currentPos[0];
        int y = currentPos[1];

        Random random = new Random();
        int newX, newY;

        do {
            newX = x + random.nextInt(3) - 1;
            newY = y + random.nextInt(3) - 1;
        } while (!isValidPosition(newX, newY) || isPositionOccupied(newX, newY));

        droidPositions.put(name, new int[]{newX, newY});
    }

    private boolean isPositionOccupied(int x, int y) {
        return droidPositions.values().stream()
                .anyMatch(pos -> pos[1] == x && pos[0] == y);
    }

    public void displayArena() {
        System.out.println(Colors.YELLOW_BOLD + "--ARENA--!" + Colors.RESET);

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                boolean droidDisplayed = false;

                for (Map.Entry<String, int[]> entry : droidPositions.entrySet()) {
                    int[] position = entry.getValue();
                    if (position[0] == i && position[1] == j) {
                        System.out.print(entry.getKey().charAt(0) + "  ");
                        droidDisplayed = true;
                        break;
                    }
                }

                if (!droidDisplayed) {
                    System.out.print(".  ");
                }
            }
            System.out.println();
        }
    }

    public boolean areAdjacent(String droidName1, String droidName2) {
        if (!droidPositions.containsKey(droidName1) || !droidPositions.containsKey(droidName2)) {
            return false;
        }

        int[] pos1 = droidPositions.get(droidName1);
        int[] pos2 = droidPositions.get(droidName2);
        int dx = Math.abs(pos1[1] - pos2[1]);
        int dy = Math.abs(pos1[0] - pos2[0]);
        return (dx + dy) == 1;
    }

    public void removeDroid(String name) {
        if (droidPositions.containsKey(name)) {
            droidPositions.remove(name);
            System.out.println("Removed " + name + " from the arena.");
        }
    }

    private boolean isValidPosition(int x, int y) {
        return x >= 0 && x < width && y >= 0 && y < height;
    }

    public boolean isHitSuccessful() {
        return true;
    }
}
