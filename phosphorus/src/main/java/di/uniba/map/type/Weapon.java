package di.uniba.map.type;

public class Weapon extends Item {

    private int weaponDamage;
    private boolean humanLock;

    public Weapon(int id, String name, String description, int damage) {
        super(id, name, description);
        this.weaponDamage = damage;
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
