package di.uniba.map.type;

import java.util.HashSet;
import java.util.Set;

public class Inventory {

    private Set<AdvObject> inventory = new HashSet<AdvObject>();

    public Set<AdvObject> getInventory() {
        return this.inventory;
    }

    public void setInventory(Set<AdvObject> inv) {
        this.inventory = inv;
    }

    public void addAvdObj(AdvObject obj) {
        this.inventory.add(obj);
    }

    public void removeAvdObj(AdvObject obj) {
        this.inventory.remove(obj);
    }

}
