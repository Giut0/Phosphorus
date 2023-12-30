package di.uniba.map.type;

public abstract class Item {
    private int itemID;
    private String itemName;
    private String itemDescription;

    public Item(int id, String name, String description) {
        this.itemID= id;
        this.itemName = name;
        this.itemDescription = description;
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

    public abstract void use();
}
