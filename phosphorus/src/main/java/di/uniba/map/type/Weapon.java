package di.uniba.map.type;

public class Weapon extends Item {

    private int weaponDamage;

    public Weapon(int id, String name, String description, int damage) {
        super(id, name, description);
        this.weaponDamage = damage;

    }

    public int getDamage() {
        return weaponDamage;
    }

    public void setDamage(int damage) {
        this.weaponDamage = damage;
    }

    @Override
    public void use() {
        // TODO USO PER ARMA
    }

}
