package droids;

public class Terran extends Droid {
    private boolean usedAbility;

    public Terran(String name) {
        super(name, 100, 25);
        this.usedAbility = false;
    }

    public String getName() {
        return super.getName();
    }

    public void takeDamage(int damage) {
        super.takeDamage(damage);
        if (this.getHealth() < 45 && !usedAbility) {
            stimulant();
        }
    }

    public void stimulant() {
        this.setDamage(this.getDamage() + 20);
        System.out.println(this.getName() + " is using a stimpack to deal more damage");
        usedAbility = true;
    }

    public boolean canUseStimpack() {
        return true;
    }
}