package di.uniba.map.type;

import java.util.HashSet;
import java.util.Set;

public class Inventory {

    private Set<Item> inventory;

    public Inventory(){
        inventory = new HashSet<Item>();
    }

    public Set<Item> getInventory() {
        return this.inventory;
    }

    public void setInventory(Set<Item> inv) {
        this.inventory = inv;
    }

    public void addAvdItem(Item item) {
        this.inventory.add(item);
    }

    public void removeAvdItem(Item item) {
        this.inventory.remove(item);
    }

}
