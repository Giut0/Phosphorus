package di.uniba.map.type;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * The Room class represents a room in the game.
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
     * Constructor for the Room class.
     * @param id The ID of the room.
     * @param name The name of the room.
     * @param description The description of the room.
     * @param lookDescription The look description of the room.
     * @param floorNumber The floor number of the room.
     * @param oxy The oxygen status of the room.
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
     * Constructor for the Room class.
     * @param id The ID of the room.
     */
    public Room(int id) {
        this.roomID = id;
        this.completed = false;
    }
  
    /**
     * Gets the room ID.
     * @return The room ID.
     */
    public int getRoomID() {
        return this.roomID;
    }

    /**
     * Sets the room name.
     * @param name The room name to set.
     */
    public void setRoomName(String name) {
        this.roomName = name;
    }

    /**
     * Gets the room name.
     * @return The room name.
     */
    public String getRoomName() {
        return roomName;
    }

    /**
     * Sets the room description.
     * @param description The room description to set.
     */
    public void setRoomDescription(String description) {
        this.roomDescription = description;
    }

    /**
     * Gets the room description.
     * @return The room description.
     */
    public String getRoomDescription() {
        return roomDescription;
    }

    /**
     * Sets the look description of the room.
     * @param lookDescription The look description to set.
     */
    public void setLookDescription(String lookDescription) {
        this.lookDescription = lookDescription;
    }

    /**
     * Gets the look description of the room.
     * @return The look description of the room.
     */
    public String getLookDescription() {
        return this.lookDescription;
    }

    /**
     * Sets the adjacent rooms of the room.
     * @param north The room to the north.
     * @param south The room to the south.
     * @param east The room to the east.
     * @param west The room to the west.
     */
    public void setAdjacentRooms(Integer north, Integer south, Integer east, Integer west) {
        this.setNorth(north);
        this.setSouth(south);
        this.setEast(east);
        this.setWest(west);
    }

    /**
     * Sets the room to the south.
     * @param south The room to the south.
     */
    public void setSouth(Integer south) {
        this.south = south;
    }

    /**
     * Gets the room to the south.
     * @return The room to the south.
     */
    public Integer getSouth() {
        return south;
    }

    /**
     * Sets the room to the north.
     * @param north The room to the north.
     */
    public void setNorth(Integer north) {
        this.north = north;
    }

    /**
     * Gets the room to the north.
     * @return The room to the north.
     */
    public Integer getNorth() {
        return north;
    }

    /**
     * Sets the room to the east.
     * @param east The room to the east.
     */
    public void setEast(Integer east) {
        this.east = east;
    }

    /**
     * Gets the room to the east.
     * @return The room to the east.
     */
    public Integer getEast() {
        return east;
    }

    /**
     * Sets the room to the west.
     * @param west The room to the west.
     */
    public void setWest(Integer west) {
        this.west = west;
    }

    /**
     * Gets the room to the west.
     * @return The room to the west.
     */
    public Integer getWest() {
        return west;
    }

    /**
     * Sets the floor number of the room.
     * @param floorNumber The floor number to set.
     */
    public void setFloorNumber(int floorNumber) {
        this.floorNumber = floorNumber;
    }

    /**
     * Gets the floor number of the room.
     * @return The floor number of the room.
     */
    public int getFloorNumber() {
        return floorNumber;
    }

    /**
     * Sets the locked status of the room.
     * @param locked The locked status to set.
     */
    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    /**
     * Gets the locked status of the room.
     * @return The locked status of the room.
     */
    public boolean getLocked() {
        return locked;
    }

    /**
     * Sets the password required status of the room.
     * @param passwordRequired The password required status to set.
     */
    public void setPasswordRequired(boolean passwordRequired) {
        this.passwordRequired = passwordRequired;
    }

    /**
     * Gets the password required status of the room.
     * @return The password required status of the room.
     */
    public boolean getPasswordRequired() {
        return this.passwordRequired;
    }

    /**
     * Sets the oxygen status of the room.
     * @param oxy The oxygen status to set.
     */
    public void setOxygen(boolean oxy) {
        this.oxygen = oxy;
    }

    /**
     * Gets the oxygen status of the room.
     * @return The oxygen status of the room.
     */
    public boolean isOxygen() {
        return this.oxygen;
    }

    /**
     * Sets the completed status of the room.
     * @param completed The completed status to set.
     */
    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    /**
     * Gets the completed status of the room.
     * @return The completed status of the room.
     */
    public boolean isCompleted() {
        return this.completed;
    }

    /**
     * Sets the items in the room.
     * @param items The list of items to set in the room.
     */
    public void setRoomItems(List<Item> items) {
        for (Item item : items) {
            this.roomItems.add(item);
        }
    }

    /**
     * Gets the items in the room.
     * @return The list of items in the room.
     */
    public List<Item> getRoomItems() {
        return this.roomItems;
    }

    /**
     * Adds an item to the room.
     * @param item The item to add to the room.
     */
    public void addItem(Item item) {
        this.roomItems.add(item);
    }

    /**
     * Removes an item from the room.
     * @param itemName The name of the item to remove from the room.
     */
    public void removeItem(String itemName) {

        Iterator<Item> iter = this.roomItems.iterator();
        while (iter.hasNext()) {
            Item item = iter.next();
            if (item.getItemName().toLowerCase().equals(itemName.toLowerCase())) {
                iter.remove();
            }
        }
    }

    /**
     * Checks if an item is in the room.
     * @param item The item to check for in the room.
     * @return A boolean indicating whether the item is in the room.
     */
    public boolean containItem(Item item) {

        for (Item it : this.getRoomItems()) {
            if (it.getItemID() == item.getItemID()) {
                return true;
            }
        }

        return false;
    }

    /**
     * Sets the characters in the room.
     * @param characters The list of characters to set in the room.
     */
    public void setRoomCharacters(List<Character> characters) {
        this.roomCharacters = characters;
    }

    /**
     * Gets the characters in the room.
     * @return The list of characters in the room.
     */
    public List<Character> getRoomCharacters() {
        return this.roomCharacters;
    }

    /**
     * Adds a character to the room.
     * @param character The character to add to the room.
     */
    public void addRoomCharacter(Character character) {
        this.roomCharacters.add(character);
    }

    /**
     * Removes a character from the room.
     * @param character The character to remove from the room.
     */
    public void removeRoomCharacter(Character character) {
        this.roomCharacters.remove(character);
    }

}
