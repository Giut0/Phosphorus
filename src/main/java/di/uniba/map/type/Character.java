package di.uniba.map.type;

public class Character {

    private int characterId;
    private String characterName;
    private String characterDescription;
    private Boolean alive = true;
    private Room currentRoom;
    private String defaultDialog;
    private String mainDialog;
    private boolean completed;

    public Character(int id, String name, String desc, Room room, String mainDialog, String defaultDialog) {
        this.characterId = id;
        this.characterName = name;
        this.characterDescription = desc;
        this.currentRoom = room;
        this.mainDialog = mainDialog;
        this.defaultDialog = defaultDialog;
        this.completed = false;

    }

    public Character(int id, String name, String desc, Room room) {
        this.characterId = id;
        this.characterName = name;
        this.characterDescription = desc;
        this.currentRoom = room;
        this.completed = false;
    }

    public Character(int id, String name, String desc) {
        this.characterId = id;
        this.characterName = name;
        this.characterDescription = desc;
        

    }

    public int getCharacterId() {
        return characterId;
    }

    public String getCharacterName() {
        return characterName;
    }

    public String getCharacterDescription() {
        return characterDescription;
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

    public void setCharacterDescription(String characterDescription) {
        this.characterDescription = characterDescription;
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
