package di.uniba.map.type;

import java.util.ArrayList;
import java.util.List;

public class Character {

    private int characterId;
    private String characterName;
    private String characterDescription;
    private Boolean alive = true;
    private Room currentRoom;
    private List<String> dialogs = new ArrayList<>();

    public Character(int id, String name, String desc, Room room, List<String> dialog) {
        this.characterId = id;
        this.characterName = name;
        this.characterDescription = desc;
        this.currentRoom = room;
        this.dialogs = dialog;

    }

    public Character(int id, String name, String desc, Room room) {
        this.characterId = id;
        this.characterName = name;
        this.characterDescription = desc;
        this.currentRoom = room;
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

    public List<String> getDialogs() {
        return dialogs;
    }

    public void setDialogs(List<String> dialogs) {
        this.dialogs = dialogs;
    }


}
