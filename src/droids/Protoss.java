package droids;

public class Protoss extends Droid {
    private boolean usedAbility;

    public Protoss(String name) {
        super(name, 100, 25);
        this.usedAbility = false;
    }

    public String getName() {
        return super.getName();
    }

    public void takeDamage(int damage) {
        super.takeDamage(damage);
        if (this.getHealth() < 20 && !usedAbility) {
            regenerate();
            usedAbility = true;
        }
    }

    private void regenerate() {
        this.setHealth(this.getHealth() + 50);
        if (this.getHealth() > 100) this.setHealth(100);
        System.out.println(this.getName() + " regenerating 50 hp");
    }

    @Override
    public boolean canRegenerate() {
        return true;
    }
}