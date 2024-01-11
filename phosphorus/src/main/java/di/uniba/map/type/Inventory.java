package di.uniba.map.type;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Inventory {

    private Set<Item> inventory;

    public Inventory(){
        inventory = new HashSet<Item>();
    }

    public Set<Item> getInventory() {
        return this.inventory;
    }

    public List<Item> getAdvItemList(){
        List<Item> items = new ArrayList<>();
        items.addAll(this.inventory);
        return items;
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

    public boolean contains(Item item){
        return this.inventory.contains(item);
    }

    public boolean contains(String itemName){
        for (Item item : inventory) {
            if(item.getItemName().equals(itemName)){
                return true;
            }
        }
        return false;
    }

}
