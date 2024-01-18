package di.uniba.map.type;

public class Enemy extends Character{
    
    private int attackDamage;

    public Enemy(int id, String name, Room room, String mainDialog, String defDialog, int attackDamage) {
        super(id, name, room, mainDialog, defDialog);
        this.attackDamage = attackDamage;
    }

    public Enemy(int id, String name, Room room, int attackDamage) {
        super(id, name, room);
        this.attackDamage = attackDamage;
    }

    public Enemy(int id, String name, int attackDamage) {
        super(id, name);
        this.attackDamage = attackDamage;
    }

    public Enemy(int id, String name){
        super(id, name);
        this.attackDamage = 1;
    }

    public int getAttackDamage() {
        return attackDamage;
    }

    public void setAttackDamage(int attackDamage) {
        this.attackDamage = attackDamage;
    }

}
