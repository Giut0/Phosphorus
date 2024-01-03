package di.uniba.map.game;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import di.uniba.map.type.Action;
import di.uniba.map.type.Inventory;
import di.uniba.map.type.Room;

public class GameEngine {

    private Map<String, Action> commands;

    private Map<String, Room> rooms;

    private Inventory inventory;

    public GameEngine(Map<String, Action> commands, Map<String, Room> rooms) {
        this.commands = commands;
        this.rooms = rooms;
        this.inventory = new Inventory();
    }

    public GameEngine() {
        this.commands = new HashMap<String, Action>();
        this.rooms = new HashMap<String, Room>();
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
            this.rooms.put(room.getName(), room);
        }
    }

    public Map<String, Room> getRooms() {
        return this.rooms;
    }

    public Map<String, Action> getCommands() {
        return this.commands;
    }

    public Inventory getInventory() {
        return this.inventory;
    }

    public void init() {

    }

}
