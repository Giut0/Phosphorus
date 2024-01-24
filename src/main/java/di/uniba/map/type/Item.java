package di.uniba.map.type;

import java.util.Set;

public abstract class Item {

    private int itemID;
    private String itemName;
    private String itemDescription;
    private int itemLocation;
    private Set<String> itemAlias;

    private Action itemAction;

    public Item(int id, String name, String description, int itemLocation) {
        this.setItemID(id);
        this.setItemName(name);
        this.setItemDescription(description);
        this.setItemLocation(itemLocation);
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public int getItemID() {
        return itemID;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemLocation(int itemLocation) {
        this.itemLocation = itemLocation;
    }

    public int getItemLocation() {
        return itemLocation;
    }

    public void setItemAction(Action itemAction) {
        this.itemAction = itemAction;
    }

    public Action getItemAction() {
        return itemAction;
    }

    public void setItemAlias(Set<String> itemAlias) {
        this.itemAlias = itemAlias;
    }

    public void addItemAlias(String alias) {
        this.itemAlias.add(alias);
    }

    public Set<String> getItemAlias() {
        return itemAlias;
    }

}
