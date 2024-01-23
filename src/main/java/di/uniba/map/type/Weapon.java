package di.uniba.map.type;

public class Weapon extends Item {

    private int weaponDamage;
    private boolean humanLock;

    public Weapon(int id, String name, String description, int itemLocation) {
        super(id, name, description, itemLocation);
        this.setDamage(1);
        this.setHumanLock(true);

    }

    public int getDamage() {
        return weaponDamage;
    }

    public void setDamage(int damage) {
        this.weaponDamage = damage;
    }

    public void setHumanLock(boolean humanLock) {
        this.humanLock = humanLock;
    }

    public boolean isHumanLocked() {
        return this.humanLock;
    }

    public void unlockWeapon() {
        this.humanLock = false;
    }

}
