package encapsulation.assigment_problems;

public class HealthBarDemo {

    static class Character {
        private final int maxHealth;   // fixed when the character is created
        private int health;            // only changed by takeDamage() and heal()

        public Character(int maxHealth) {
            this.maxHealth = maxHealth;
            this.health = maxHealth;   // start at full health
        }

        public void takeDamage(int amount) {
            int newHealth = health - amount;   // calculate first...
            if (newHealth < 0) {
                newHealth = 0;                 // ...then clamp at 0
            }
            health = newHealth;
        }

        public void heal(int amount) {
            int newHealth = health + amount;   // calculate first...
            if (newHealth > maxHealth) {
                newHealth = maxHealth;         // ...then clamp at the maximum
            }
            health = newHealth;
        }

        // Read-only: no setHealth()
        public int getHealth() {
            return health;
        }
    }

    public static void main(String[] args) {
        Character c = new Character(100);

        c.takeDamage(30);
        System.out.println("takeDamage(30)  -> health = " + c.getHealth());

        c.heal(50);
        System.out.println("heal(50)        -> health = " + c.getHealth() + " (capped)");

        c.takeDamage(150);
        System.out.println("takeDamage(150) -> health = " + c.getHealth() + " (floored)");
    }
}