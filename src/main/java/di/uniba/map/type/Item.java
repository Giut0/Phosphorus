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
        this.itemID= id;
        this.itemName = name;
        this.itemDescription = description;
        this.itemLocation = itemLocation;
    }

    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public int getItemLocation() {
        return itemLocation;
    }

    public void setItemLocation(int itemLocation) {
        this.itemLocation = itemLocation;
    }

    public void setItemAction(Action itemAction) {
        this.itemAction = itemAction;
    }

    public void setItemAlias(Set<String> itemAlias) {
        this.itemAlias = itemAlias;
    }

    public void addItemAlias(String alias){
        this.itemAlias.add(alias);
    }

    public Action getItemAction() {
        return itemAction;
    }

    public Set<String> getItemAlias() {
        return itemAlias;
    }

    public abstract void use();
}
