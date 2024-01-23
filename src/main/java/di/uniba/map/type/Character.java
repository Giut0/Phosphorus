package di.uniba.map.type;

public class Character {

    private int characterId;
    private String characterName;
    private Boolean alive = true;
    private Room currentRoom;
    private String defaultDialog;
    private String mainDialog;
    private boolean completed;

    public Character(int id, String name) {
        this.setCharacterId(id);
        this.setCharacterName(name);
        this.setCurrentRoom(null);
        this.setDefaultDialog("");
        this.setMainDialog("");
        this.setCompleted(false);

    }

    public void setCharacterId(int characterId) {
        this.characterId = characterId;
    }

    public int getCharacterId() {
        return characterId;
    }

    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }

    public String getCharacterName() {
        return characterName;
    }

    public void setAlive(Boolean alive) {
        this.alive = alive;
    }

    public Boolean isAlive() {
        return this.alive;
    }

    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setDefaultDialog(String defaultDialog) {
        this.defaultDialog = defaultDialog;
    }

    public String getDefaultDialog() {
        return defaultDialog;
    }

    public void setMainDialog(String mainDialog) {
        this.mainDialog = mainDialog;
    }

    public String getMainDialog() {
        return mainDialog;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public boolean getCompleted() {
        return this.completed;
    }

}
