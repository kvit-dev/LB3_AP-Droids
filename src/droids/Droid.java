package droids;

public class Droid {
    private String name;
    private int health;
    private int damage;

    public Droid(String name, int health, int damage) {
        this.name = name;
        this.health = health;
        this.damage = damage;
    }

    public String getName() {return name;}
    public int getHealth() {return health;}
    public int getDamage() {return damage;}

    public void setHealth(int health) {this.health = health;}
    public void setDamage(int damage) {this.damage = damage;}

    public boolean isAlive() {return health > 0;}

    public void takeDamage(int damage) {
        this.health -= damage;
        if (this.health < 0) {
            this.health = 0;
        }
    }

    public boolean canRegenerate() {
        return false;
    }
    public boolean canUseStimpack() {
        return false;
    }
}
