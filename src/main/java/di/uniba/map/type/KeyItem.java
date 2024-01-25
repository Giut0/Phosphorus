package di.uniba.map.type;

/**
 * The KeyItem class represents a key item in the game.
 * It extends the Item class, adding a keyUsed attribute.
 */
public class KeyItem extends Item {

    private boolean keyUsed;

    /**
     * Constructor for the KeyItem class.
     * 
     * @param id           The ID of the key item.
     * @param name         The name of the key item.
     * @param description  The description of the key item.
     * @param itemLocation The location of the key item.
     */
    public KeyItem(int id, String name, String description, int itemLocation) {
        super(id, name, description, itemLocation);
        this.setKeyUsed(false);
    }

    /**
     * Sets the key used status of the key item.
     * 
     * @param keyUsed The key used status to set.
     */
    public void setKeyUsed(boolean keyUsed) {
        this.keyUsed = keyUsed;
    }

    /**
     * Gets the key used status of the key item.
     * 
     * @return The key used status of the key item.
     */
    public boolean isKeyUsed() {
        return this.keyUsed;
    }
}
