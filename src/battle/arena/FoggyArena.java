package battle.arena;

import utils.Colors;

import java.util.Random;

public class FoggyArena extends Arena {
    private Random random = new Random();

    public FoggyArena(int width, int height) {
        super(width, height);
    }

    @Override
    public boolean isHitSuccessful() {
        // 70% ймовірність влучити
        return random.nextInt(100) < 70;
    }

    @Override
    public void displayArena() {
        System.out.println(Colors.PURPLE_BOLD + "\n\tFOGGY ARENA! (Droids might miss due to fog)");
        super.displayArena();
    }
}

