package di.uniba.map.type;

import java.util.HashSet;
import java.util.Set;

public class Inventory {

    private Set<Item> inventory;

    public Inventory() {
        inventory = new HashSet<Item>();
    }

    public void setItems(Set<Item> inv) {
        this.inventory = inv;
    }

    public Set<Item> getItems() {
        return this.inventory;
    }

    public void addItem(Item item) {
        this.inventory.add(item);
    }

    public void removeItem(Item item) {
        this.inventory.remove(item);
    }

    public boolean contains(Item item) {
        return this.inventory.contains(item);
    }

    public boolean contains(String itemName) {
        for (Item item : inventory) {
            if (item.getItemName().equals(itemName)) {
                return true;
            }
        }
        return false;
    }

}
