package di.uniba.map.type;

import java.util.HashSet;
import java.util.Set;

/**
 * The Inventory class represents a collection of items in the game.
 */
public class Inventory {

    private Set<Item> inventory;

    /**
     * Constructor for the Inventory class.
     * Initializes the inventory as an empty set of items.
     */
    public Inventory() {
        inventory = new HashSet<Item>();
    }

    /**
     * Sets the items in the inventory.
     * 
     * @param inv The set of items to set in the inventory.
     */
    public void setItems(Set<Item> inv) {
        this.inventory = inv;
    }

    /**
     * Gets the items in the inventory.
     * 
     * @return The set of items in the inventory.
     */
    public Set<Item> getItems() {
        return this.inventory;
    }

    /**
     * Adds an item to the inventory.
     * 
     * @param item The item to add to the inventory.
     */
    public void addItem(Item item) {
        this.inventory.add(item);
    }

    /**
     * Removes an item from the inventory.
     * 
     * @param item The item to remove from the inventory.
     */
    public void removeItem(Item item) {
        this.inventory.remove(item);
    }

    /**
     * Checks if an item is in the inventory.
     * 
     * @param item The item to check for in the inventory.
     * @return A boolean indicating whether the item is in the inventory.
     */
    public boolean contains(Item item) {
        return this.inventory.contains(item);
    }

    /**
     * Checks if an item with a specific name is in the inventory.
     * 
     * @param itemName The name of the item to check for in the inventory.
     * @return A boolean indicating whether an item with the specified name is in
     *         the inventory.
     */
    public boolean contains(String itemName) {
        for (Item item : inventory) {
            if (item.getItemName().equals(itemName)) {
                return true;
            }
        }
        return false;
    }
}
