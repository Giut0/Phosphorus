package di.uniba.map.type;

public class Weapon extends Item {

    private int weaponDamage;
    private boolean humanLock;

    public Weapon(int id, String name, String description, int itemLocation) {
        super(id, name, description, itemLocation);
        this.weaponDamage = 1;
        this.humanLock = true;

    }

    public int getDamage() {
        return weaponDamage;
    }

    public void setDamage(int damage) {
        this.weaponDamage = damage;
    }

    public boolean isHumanLocked(){
        return this.humanLock;
    }

    public void unlockWeapon(){
        this.humanLock = false;
    }

    @Override
    public void use() {
        // TODO USO PER ARMA
    }

}
