package di.uniba.map.type;

public class Character {

    private int characterId;
    private String characterName;
    private String characterDescription;
    private Boolean alive = true;

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

}
