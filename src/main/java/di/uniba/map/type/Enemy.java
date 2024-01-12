package di.uniba.map.type;

public class Enemy extends Character{
    
    private int attackDamage;

    public Enemy(int id, String name, String desc, Room room, String mainDialog, String defDialog, int attackDamage) {
        super(id, name, desc, room, mainDialog, defDialog);
        this.attackDamage = attackDamage;
    }

    public Enemy(int id, String name, String desc, Room room, int attackDamage) {
        super(id, name, desc, room);
        this.attackDamage = attackDamage;
    }

    public Enemy(int id, String name, String desc, int attackDamage) {
        super(id, name, desc);
        this.attackDamage = attackDamage;
    }

    public Enemy(int id, String name, String desc){
        super(id, name, desc);
        this.attackDamage = 1;
    }

    public int getAttackDamage() {
        return attackDamage;
    }

    public void setAttackDamage(int attackDamage) {
        this.attackDamage = attackDamage;
    }

}
