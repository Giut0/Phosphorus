package di.uniba.map.game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import di.uniba.map.type.Action;
import di.uniba.map.type.Inventory;
import di.uniba.map.type.Item;
import di.uniba.map.type.Room;

/**
 * The GameEngine class provides methods to manage game actions, rooms, and
 * inventory.
 */
public class GameEngine {

    private Map<String, Action> actions;
    private Map<Integer, Room> rooms;
    private Room currentRoom;
    private Inventory inventory;

    /**
     * Constructor for the GameEngine class.
     * 
     * @param commands The game actions.
     * @param rooms    The game rooms.
     */
    public GameEngine(Map<String, Action> commands, Map<Integer, Room> rooms) {
        this.actions = commands;
        this.rooms = rooms;
        this.inventory = new Inventory();
    }

    /**
     * Default constructor for the GameEngine class.
     */
    public GameEngine() {
        this.actions = new HashMap<String, Action>();
        this.rooms = new HashMap<Integer, Room>();
        this.inventory = new Inventory();
    }

    /**
     * Adds an action to the game.
     * 
     * @param action The action to add.
     */
    public void addActions(Action action) {
        this.actions.put(action.getActionName(), action);
    }

    /**
     * Adds a list of actions to the game.
     * 
     * @param actions The actions to add.
     */
    public void addActions(List<Action> actions) {
        for (Action action : actions) {
            this.actions.put(action.getActionName(), action);
        }
    }

    /**
     * Returns the game actions as a list.
     * 
     * @return The game actions.
     */
    public List<Action> getCommandsAsList() {
        List<Action> actions = new ArrayList<>();
        actions.addAll(this.actions.values());
        return actions;
    }

    /**
     * Adds a list of rooms to the game.
     * 
     * @param list The rooms to add.
     */
    public void addRooms(List<Room> list) {
        for (Room room : list) {
            this.rooms.put(room.getRoomID(), room);
        }
    }

    /**
     * Returns the game rooms.
     * 
     * @return The game rooms.
     */
    public Map<Integer, Room> getRooms() {
        return this.rooms;
    }

    /**
     * Returns the game rooms as a list.
     * 
     * @return The game rooms.
     */
    public List<Room> getRoomsAsList() {
        List<Room> rooms = new ArrayList<>();
        rooms.addAll(this.rooms.values());
        return rooms;
    }

    /**
     * Returns the game inventory.
     * 
     * @return The game inventory.
     */
    public Inventory getInventory() {
        return this.inventory;
    }

    /**
     * Adds an item to the game inventory.
     * 
     * @param item The item to add.
     */
    public void addItem(Item item) {
        this.inventory.addItem(item);
    }

    /**
     * Returns the current room.
     * 
     * @return The current room.
     */
    public Room getCurrentRoom() {
        return currentRoom;
    }

    /**
     * Sets the current room.
     * 
     * @param currentRoom The room to set as the current room.
     */
    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }
}
