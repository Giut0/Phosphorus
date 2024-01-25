package di.uniba.map.type;

/**
 * The Weapon class represents a weapon in the game.
 * It extends the Item class, adding a weapon damage attribute and a human lock
 * attribute.
 */
public class Weapon extends Item {

    private int weaponDamage;
    private boolean humanLock;

    /**
     * Constructor for the Weapon class.
     * 
     * @param id           The ID of the weapon.
     * @param name         The name of the weapon.
     * @param description  The description of the weapon.
     * @param itemLocation The location of the weapon.
     */
    public Weapon(int id, String name, String description, int itemLocation) {
        super(id, name, description, itemLocation);
        this.setDamage(1);
        this.setHumanLock(true);
    }

    /**
     * Gets the damage of the weapon.
     * 
     * @return The damage of the weapon.
     */
    public int getDamage() {
        return weaponDamage;
    }

    /**
     * Sets the damage of the weapon.
     * 
     * @param damage The damage to set.
     */
    public void setDamage(int damage) {
        this.weaponDamage = damage;
    }

    /**
     * Sets the human lock status of the weapon.
     * 
     * @param humanLock The human lock status to set.
     */
    public void setHumanLock(boolean humanLock) {
        this.humanLock = humanLock;
    }

    /**
     * Gets the human lock status of the weapon.
     * 
     * @return The human lock status of the weapon.
     */
    public boolean isHumanLocked() {
        return this.humanLock;
    }

    /**
     * Unlocks the weapon.
     */
    public void unlockWeapon() {
        this.humanLock = false;
    }
}
