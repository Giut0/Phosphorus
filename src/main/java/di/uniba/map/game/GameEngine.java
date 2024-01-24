package di.uniba.map.game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import di.uniba.map.type.Action;
import di.uniba.map.type.Inventory;
import di.uniba.map.type.Item;
import di.uniba.map.type.Room;

public class GameEngine {

    private Map<String, Action> actions;

    private Map<Integer, Room> rooms;

    private Room currentRoom;

    private Inventory inventory;

    public GameEngine(Map<String, Action> commands, Map<Integer, Room> rooms) {
        this.actions = commands;
        this.rooms = rooms;
        this.inventory = new Inventory();
    }

    public GameEngine() {
        this.actions = new HashMap<String, Action>();
        this.rooms = new HashMap<Integer, Room>();
        this.inventory = new Inventory();
    }

    public void addActions(Action action) {
        this.actions.put(action.getActionName(), action);
    }

    public void addActions(List<Action> actions) {
        for (Action action : actions) {
            this.actions.put(action.getActionName(), action);
        }
    }

    public List<Action> getCommandsAsList() {
        List<Action> actions = new ArrayList<>();
        actions.addAll(this.actions.values());
        return actions;
    }

    public void addRooms(List<Room> list) {

        for (Room room : list) {
            this.rooms.put(room.getRoomID(), room);
        }
    }

    public Map<Integer, Room> getRooms() {
        return this.rooms;
    }

    public List<Room> getRoomsAsList() {
        List<Room> rooms = new ArrayList<>();
        rooms.addAll(this.rooms.values());
        return rooms;
    }

    public Inventory getInventory() {
        return this.inventory;
    }

    public void addItem(Item item) {
        this.inventory.addItem(item);
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }
}
