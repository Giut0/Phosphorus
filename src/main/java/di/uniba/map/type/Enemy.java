package di.uniba.map.type;

/**
 * The Enemy class represents an enemy character in the game.
 * It extends the Character class, adding an attack damage attribute.
 */
public class Enemy extends Character {

    private int attackDamage;

    /**
     * Constructor for the Enemy class.
     * 
     * @param id           The ID of the enemy.
     * @param name         The name of the enemy.
     * @param attackDamage The attack damage of the enemy.
     */
    public Enemy(int id, String name, int attackDamage) {
        super(id, name);
        this.setAttackDamage(attackDamage);
    }

    /**
     * Constructor for the Enemy class.
     * Sets the attack damage to 1 by default.
     * 
     * @param id   The ID of the enemy.
     * @param name The name of the enemy.
     */
    public Enemy(int id, String name) {
        super(id, name);
        this.attackDamage = 1;
    }

    /**
     * Sets the attack damage of the enemy.
     * 
     * @param attackDamage The attack damage to set.
     */
    public void setAttackDamage(int attackDamage) {
        this.attackDamage = attackDamage;
    }

    /**
     * Gets the attack damage of the enemy.
     * 
     * @return The attack damage of the enemy.
     */
    public int getAttackDamage() {
        return attackDamage;
    }
}
