package di.uniba.map.type;

public class Character {

    private int characterId;
    private String characterName;
    private Boolean alive = true;
    private Room currentRoom;
    private String defaultDialog;
    private String mainDialog;
    private boolean completed;

    public Character(int id, String name, Room room, String mainDialog, String defaultDialog) {
        this.characterId = id;
        this.characterName = name;
        this.currentRoom = room;
        this.mainDialog = mainDialog;
        this.defaultDialog = defaultDialog;
        this.completed = false;

    }

    public Character(int id, String name, Room room) {
        this.characterId = id;
        this.characterName = name;
        this.currentRoom = room;
        this.completed = false;
    }

    public Character(int id, String name) {
        this.characterId = id;
        this.characterName = name;
        

    }

    public int getCharacterId() {
        return characterId;
    }

    public String getCharacterName() {
        return characterName;
    }

 

    public Boolean isAlive() {
        return this.alive;
    }

    public void setCharacterId(int characterId) {
        this.characterId = characterId;
    }

    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }

    public void setAlive(Boolean alive) {
        this.alive = alive;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    public String getDefaultDialog() {
        return defaultDialog;
    }

    public void setDefaultDialog(String defaultDialog) {
        this.defaultDialog = defaultDialog;
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
    
    public boolean getCompleted(){
        return this.completed;
    }

}
