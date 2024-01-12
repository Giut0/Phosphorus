package di.uniba.map.type;

import java.util.List;

public class Enemy extends Character{
    
    private int attackDamage;

    public Enemy(int id, String name, String desc, Room room, List<String> dialog, int attackDamage) {
        super(id, name, desc, room, dialog);
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
