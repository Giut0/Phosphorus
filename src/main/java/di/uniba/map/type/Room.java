package di.uniba.map.type;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents a location or room within game world.
 * Each room serves as a distinct space where interactions occur and holds
 * various attributes
 * describing its characteristics and contents.
 */
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

    private boolean locked;

    private boolean oxygen;
    private boolean visible;
    private boolean completed;

    private Map<String, Item> advItems = new HashMap<>();
    private List<Character> characters = new ArrayList<>();

    /**
     * Constructs a Room object with a given ID.
     *
     * @param id The unique identifier for the room.
     */
    public Room(int id) {
        this.roomID = id;
        this.completed = false;
    }

    /**
     * Constructs a Room object with specified attributes.
     *
     * @param id              The unique identifier for the room.
     * @param name            The name of the room.
     * @param description     The description of the room.
     * @param lookDescription The visual description of the room.
     * @param floorNumber     The floor number where the room is located.
     * @param visible         Determines if the room is visible.
     * @param oxy             Indicates if the room has oxygen.
     */
    public Room(int id, String name, String description, String lookDescription, int floorNumber, boolean visible,
            boolean oxy) {
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

    /**
     * Sets the adjacent rooms to the current room.
     *
     * @param north The room ID in the north direction.
     * @param south The room ID in the south direction.
     * @param east  The room ID in the east direction.
     * @param west  The room ID in the west direction.
     */
    public void setAdjacentRooms(Integer north, Integer south, Integer east, Integer west) {
        this.setNorth(north);
        this.setSouth(south);
        this.setEast(east);
        this.setWest(west);
    }

    /**
     * Retrieves the unique identifier of the room.
     *
     * @return The unique room identifier.
     */
    public int getRoomID() {
        return this.roomID;
    }

    /**
     * Retrieves the name of the room.
     *
     * @return The name of the room.
     */
    public String getName() {
        return roomName;
    }

    public void removeItem(String itemName){
        this.advItems.remove(itemName.toLowerCase());
    }

    /**
     * Sets the name of the room.
     *
     * @param name The new name for the room.
     */
    public void setName(String name) {
        this.roomName = name;
    }

    /**
     * Retrieves the description of the room.
     *
     * @return The description of the room.
     */
    public String getDescription() {
        return roomDescription;
    }

    /**
     * Sets the description of the room.
     *
     * @param name The new description for the room.
     */
    public void setDescription(String description) {
        this.roomDescription = description;
    }

    /**
     * Retrieves the floor number of the room.
     *
     * @return The floor number of the room.
     */
    public int getFloorNumber() {
        return floorNumber;
    }

    /**
     * Sets the floor number of the room.
     *
     * @param name The new floor number for the room.
     */
    public void setFloorNumber(int floorNumber) {
        this.floorNumber = floorNumber;
    }

    /**
     * Checks if the room contains breathable oxygen.
     *
     * @return True if the room has oxygen, otherwise false.
     */
    public boolean isOxygen() {
        return this.oxygen;
    }

    /**
     * Sets the presence of breathable oxygen in the room.
     *
     * @param oxy True if the room has oxygen, otherwise false.
     */
    public void setOxygen(boolean oxy) {
        this.oxygen = oxy;
    }

    public boolean isVisible() {
        return this.visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public boolean isCompleted() {
        return this.completed;
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

    public void setAdvItems(List<Item> items) {
        for (Item item : items) {
            this.advItems.put(item.getItemName(), item);
        }

    }

    public Map<String, Item> getAdvItems() {
        return advItems;
    }

    public List<Item> getAdvItemsAList(){
        List<Item> items = new ArrayList<>();
        items.addAll(this.advItems.values());
        return items;
    }

    public void addAdvItem(Item item) {
        this.advItems.put(item.getItemName(), item);
    }

    /**
     * Adds a character to the room.
     *
     * @param character The character to be added.
     */
    public void setCharacters(List<Character> characters) {
        this.characters = characters;
    }

    public List<Character> getCharacters() {
        return this.characters;
    }

    /**
     * Removes a character from the room.
     *
     * @param character The character to be removed.
     */
    public void removeCharacter(Character character) {
        this.characters.remove(character);
    }

    public void addCharacter(Character character) {
        this.characters.add(character);
    }

    /**
     * Retrieves the visual description of the room.
     *
     * @return The visual description of the room.
     */
    public String getLook() {
        return lookDescription;
    }

    /**
     * Sets the visual description of the room.
     *
     * @param look The visual description of the room.
     */
    public void setLook(String look) {
        this.lookDescription = look;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public boolean getLocked() {
        return locked;
    }

    /**
     * Compares this Room object to another object for equality.
     *
     * @param obj The object to compare with.
     * @return True if both objects are equal, false otherwise.
     */
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
