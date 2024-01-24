package di.uniba.map.type;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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

    private boolean passwordRequired;

    private boolean oxygen;
    private boolean completed;

    private List<Item> roomItems = new ArrayList<>();
    private List<Character> roomCharacters = new ArrayList<>();

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
    public Room(int id, String name, String description, String lookDescription, int floorNumber,
            boolean oxy) {
        this.roomID = id;
        this.setRoomName(name);
        this.setRoomDescription(description);
        this.setLookDescription(lookDescription);
        this.setFloorNumber(floorNumber);
        this.setOxygen(oxy);
        this.setAdjacentRooms(null, null, null, null);
        this.completed = false;
    }

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
     * Retrieves the unique identifier of the room.
     *
     * @return The unique room identifier.
     */
    public int getRoomID() {
        return this.roomID;
    }

    /**
     * Sets the name of the room.
     *
     * @param name The new name for the room.
     */
    public void setRoomName(String name) {
        this.roomName = name;
    }

    /**
     * Retrieves the name of the room.
     *
     * @return The name of the room.
     */
    public String getRoomName() {
        return roomName;
    }

    /**
     * Sets the description of the room.
     *
     * @param name The new description for the room.
     */
    public void setRoomDescription(String description) {
        this.roomDescription = description;
    }

    /**
     * Retrieves the description of the room.
     *
     * @return The description of the room.
     */
    public String getRoomDescription() {
        return roomDescription;
    }

    public void setLookDescription(String lookDescription) {
        this.lookDescription = lookDescription;
    }

    /**
     * Retrieves the visual description of the room.
     *
     * @return The visual description of the room.
     */
    public String getLookDescription() {
        return this.lookDescription;
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

    public void setSouth(Integer south) {
        this.south = south;
    }

    public Integer getSouth() {
        return south;
    }

    public void setNorth(Integer north) {
        this.north = north;
    }

    public Integer getNorth() {
        return north;
    }

    public void setEast(Integer east) {
        this.east = east;
    }

    public Integer getEast() {
        return east;
    }

    public void setWest(Integer west) {
        this.west = west;
    }

    public Integer getWest() {
        return west;
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
     * Retrieves the floor number of the room.
     *
     * @return The floor number of the room.
     */
    public int getFloorNumber() {
        return floorNumber;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public boolean getLocked() {
        return locked;
    }

    public void setPasswordRequired(boolean passwordRequired) {
        this.passwordRequired = passwordRequired;
    }

    public boolean getPasswordRequired() {
        return this.passwordRequired;
    }

    /**
     * Sets the presence of breathable oxygen in the room.
     *
     * @param oxy True if the room has oxygen, otherwise false.
     */
    public void setOxygen(boolean oxy) {
        this.oxygen = oxy;
    }

    /**
     * Checks if the room contains breathable oxygen.
     *
     * @return True if the room has oxygen, otherwise false.
     */
    public boolean isOxygen() {
        return this.oxygen;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public boolean isCompleted() {
        return this.completed;
    }

    public void setRoomItems(List<Item> items) {
        for (Item item : items) {
            this.roomItems.add(item);
        }

    }

    public List<Item> getRoomItems() {
        return this.roomItems;
    }

    public void addItem(Item item) {
        this.roomItems.add(item);
    }

    public void removeItem(String itemName) {

        Iterator<Item> iter = this.roomItems.iterator();
        while (iter.hasNext()) {
            Item item = iter.next();
            if (item.getItemName().toLowerCase().equals(itemName.toLowerCase())) {
                iter.remove();
            }
        }
    }

    public boolean containItem(Item item) {

        for (Item it : this.getRoomItems()) {
            if (it.getItemID() == item.getItemID()) {
                return true;
            }
        }

        return false;
    }

    /**
     * Adds a character to the room.
     *
     * @param character The character to be added.
     */
    public void setRoomCharacters(List<Character> characters) {
        this.roomCharacters = characters;
    }

    public List<Character> getRoomCharacters() {
        return this.roomCharacters;
    }

    public void addRoomCharacter(Character character) {
        this.roomCharacters.add(character);
    }

    /**
     * Removes a character from the room.
     *
     * @param character The character to be removed.
     */
    public void removeRoomCharacter(Character character) {
        this.roomCharacters.remove(character);
    }

}
