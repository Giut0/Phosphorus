package di.uniba.map.type;

/**
 * The Character class represents a character in the game.
 */
public class Character {

    private int characterId;
    private String characterName;
    private Boolean alive = true;
    private Room currentRoom;
    private String defaultDialog;
    private String mainDialog;
    private boolean completed;

    /**
     * Constructor for the Character class.
     * 
     * @param id   The ID of the character.
     * @param name The name of the character.
     */
    public Character(int id, String name) {
        this.setCharacterId(id);
        this.setCharacterName(name);
        this.setCurrentRoom(null);
        this.setDefaultDialog("");
        this.setMainDialog("");
        this.setCompleted(false);
    }

    /**
     * Sets the character ID.
     * 
     * @param characterId The character ID to set.
     */
    public void setCharacterId(int characterId) {
        this.characterId = characterId;
    }

    /**
     * Gets the character ID.
     * 
     * @return The character ID.
     */
    public int getCharacterId() {
        return characterId;
    }

    /**
     * Sets the character name.
     * 
     * @param characterName The character name to set.
     */
    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }

    /**
     * Gets the character name.
     * 
     * @return The character name.
     */
    public String getCharacterName() {
        return characterName;
    }

    /**
     * Sets the alive status of the character.
     * 
     * @param alive The alive status to set.
     */
    public void setAlive(Boolean alive) {
        this.alive = alive;
    }

    /**
     * Gets the alive status of the character.
     * 
     * @return The alive status of the character.
     */
    public Boolean isAlive() {
        return this.alive;
    }

    /**
     * Sets the current room of the character.
     * 
     * @param currentRoom The current room to set.
     */
    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    /**
     * Gets the current room of the character.
     * 
     * @return The current room of the character.
     */
    public Room getCurrentRoom() {
        return currentRoom;
    }

    /**
     * Sets the default dialog of the character.
     * 
     * @param defaultDialog The default dialog to set.
     */
    public void setDefaultDialog(String defaultDialog) {
        this.defaultDialog = defaultDialog;
    }

    /**
     * Gets the default dialog of the character.
     * 
     * @return The default dialog of the character.
     */
    public String getDefaultDialog() {
        return defaultDialog;
    }

    /**
     * Sets the main dialog of the character.
     * 
     * @param mainDialog The main dialog to set.
     */
    public void setMainDialog(String mainDialog) {
        this.mainDialog = mainDialog;
    }

    /**
     * Gets the main dialog of the character.
     * 
     * @return The main dialog of the character.
     */
    public String getMainDialog() {
        return mainDialog;
    }

    /**
     * Sets the completed status of the character.
     * 
     * @param completed The completed status to set.
     */
    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    /**
     * Gets the completed status of the character.
     * 
     * @return The completed status of the character.
     */
    public boolean getCompleted() {
        return this.completed;
    }
}
