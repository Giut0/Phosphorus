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

    private Map<String, Action> commands;

    private Map<Integer, Room> rooms;

    private Room currentRoom;

    private Inventory inventory;

    public GameEngine(Map<String, Action> commands, Map<Integer, Room> rooms) {
        this.commands = commands;
        this.rooms = rooms;
        this.inventory = new Inventory();
    }

    public GameEngine() {
        this.commands = new HashMap<String, Action>();
        this.rooms = new HashMap<Integer, Room>();
        this.inventory = new Inventory();
    }

    public void addCommand(Action action) {
        this.commands.put(action.getCommandName(), action);
    }

    public void addCommands(List<Action> actions) {
        for (Action action : actions) {
            this.commands.put(action.getCommandName(), action);
        }
    }

    public void addRooms(List<Room> list) {

        for (Room room : list) {
            this.rooms.put(room.getRoomID(), room);
        }
    }

    public Map<Integer, Room> getRooms() {
        return this.rooms;
    }

    public Map<String, Action> getCommands() {
        return this.commands;
    }

    public List<Action> getCommandsAsList() {
        List<Action> actions = new ArrayList<>();
        actions.addAll(this.commands.values());
        return actions;
    }

    public List<Room> getRoomsAsList() {
        List<Room> rooms = new ArrayList<>();
        rooms.addAll(this.rooms.values());
        return rooms;
    }

    public Inventory getInventory() {
        return this.inventory;
    }
    
    public void addItem(Item item){
        this.inventory.addAvdItem(item);
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }
}
