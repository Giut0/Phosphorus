package di.uniba.map.type;

import java.util.Set;

/**
 * The Item class represents an item in the game.
 * This is an abstract class and cannot be instantiated directly.
 */
public abstract class Item {

    private int itemID;
    private String itemName;
    private String itemDescription;
    private int itemLocation;
    private Set<String> itemAlias;
    private Action itemAction;

    /**
     * Constructor for the Item class.
     * 
     * @param id           The ID of the item.
     * @param name         The name of the item.
     * @param description  The description of the item.
     * @param itemLocation The location of the item.
     */
    public Item(int id, String name, String description, int itemLocation) {
        this.setItemID(id);
        this.setItemName(name);
        this.setItemDescription(description);
        this.setItemLocation(itemLocation);
    }

    /**
     * Sets the item ID.
     * 
     * @param itemID The item ID to set.
     */
    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    /**
     * Gets the item ID.
     * 
     * @return The item ID.
     */
    public int getItemID() {
        return itemID;
    }

    /**
     * Sets the item name.
     * 
     * @param itemName The item name to set.
     */
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    /**
     * Gets the item name.
     * 
     * @return The item name.
     */
    public String getItemName() {
        return itemName;
    }

    /**
     * Sets the item description.
     * 
     * @param itemDescription The item description to set.
     */
    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    /**
     * Gets the item description.
     * 
     * @return The item description.
     */
    public String getItemDescription() {
        return itemDescription;
    }

    /**
     * Sets the item location.
     * 
     * @param itemLocation The item location to set.
     */
    public void setItemLocation(int itemLocation) {
        this.itemLocation = itemLocation;
    }

    /**
     * Gets the item location.
     * 
     * @return The item location.
     */
    public int getItemLocation() {
        return itemLocation;
    }

    /**
     * Sets the item action.
     * 
     * @param itemAction The item action to set.
     */
    public void setItemAction(Action itemAction) {
        this.itemAction = itemAction;
    }

    /**
     * Gets the item action.
     * 
     * @return The item action.
     */
    public Action getItemAction() {
        return itemAction;
    }

    /**
     * Sets the item aliases.
     * 
     * @param itemAlias The set of aliases to set.
     */
    public void setItemAlias(Set<String> itemAlias) {
        this.itemAlias = itemAlias;
    }

    /**
     * Adds an alias to the item.
     * 
     * @param alias The alias to add.
     */
    public void addItemAlias(String alias) {
        this.itemAlias.add(alias);
    }

    /**
     * Gets the item aliases.
     * 
     * @return The set of item aliases.
     */
    public Set<String> getItemAlias() {
        return itemAlias;
    }
}
