package di.uniba.map.type;

import java.util.ArrayList;
import java.util.List;

public class Room {

    private final int roomID;
    private String roomName;
    private String roomDescription;
    private String lookDescription;

    private Integer south;
    private Integer north;
    private Integer east;
    private Integer west;

    private int floorNumber;

    private boolean oxygen;
    private boolean visible;
    private boolean completed;

    private List<AdvObject> objects = new ArrayList<>();
    private List<Character> characters = new ArrayList<>();

    public Room(int id) {
        this.roomID = id;
        this.completed = false;
    }

    public Room(int id, String name, String description, String lookDescription, int floorNumber, boolean visible,  boolean oxy) {
        this.roomID = id;
        this.setName(name);
        this.setDescription(lookDescription);
        this.setLook(lookDescription);
        this.setFloorNumber(floorNumber);
        this.setVisible(visible);
        this.setOxygen(oxy);
        this.setAdjacentRooms(null, null, null, null);
        this.completed = false;
    }

    public void setAdjacentRooms(Integer north, Integer south, Integer east, Integer west){
        this.setNorth(north);
        this.setSouth(south);
        this.setEast(east);
        this.setWest(west);
    } 

    public int getRoomID(){
        return this.roomID;
    }

    public String getName() {
        return roomName;
    }

    public void setName(String name) {
        this.roomName = name;
    }

    public String getDescription() {
        return roomDescription;
    }

    public void setDescription(String description) {
        this.roomDescription = description;
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public void setFloorNumber(int floorNumber) {
        this.floorNumber = floorNumber;
    }

    public boolean isOxygen() {
        return this.oxygen;
    }

    public void setOxygen(boolean oxy) {
        this.oxygen = oxy;
    }

    public boolean isVisible() {
        return this.visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public void isCompleted(boolean completed) {
        this.completed = completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public Integer getSouth() {
        return south;
    }

    public void setSouth(Integer south) {
        this.south = south;
    }

    public Integer getNorth() {
        return north;
    }

    public void setNorth(Integer north) {
        this.north = north;
    }

    public Integer getEast() {
        return east;
    }

    public void setEast(Integer east) {
        this.east = east;
    }

    public Integer getWest() {
        return west;
    }

    public void setWest(Integer west) {
        this.west = west;
    }

    public void setObjects(List<AdvObject> objects) {
        this.objects = objects;
    }

    public List<AdvObject> getObjects() {
        return objects;
    }

    public void setCharacters(List<Character> characters) {
        this.characters = characters;
    }

    public List<Character> getCharacters() {
        return characters;
    }

    public void removeCharacter(Character character) {
        this.characters.remove(character);
    }

    public void addCharacter(Character character) {
        this.characters.add(character);
    }

    public String getLook() {
        return lookDescription;
    }

    public void setLook(String look) {
        this.lookDescription = look;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Room other = (Room) obj;
        if (this.roomID != other.roomID) {
            return false;
        }
        return true;
    }

}
