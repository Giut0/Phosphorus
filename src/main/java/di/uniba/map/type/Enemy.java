package di.uniba.map.type;

public class Enemy extends Character {

    private int attackDamage;

    public Enemy(int id, String name, int attackDamage) {
        super(id, name);
        this.setAttackDamage(attackDamage);
    }

    public Enemy(int id, String name) {
        super(id, name);
        this.attackDamage = 1;
    }

    public void setAttackDamage(int attackDamage) {
        this.attackDamage = attackDamage;
    }

    public int getAttackDamage() {
        return attackDamage;
    }

}
