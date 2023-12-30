package di.uniba.map.game;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import di.uniba.map.type.Action;
import di.uniba.map.type.Inventory;
import di.uniba.map.type.Room;

public class GameEngine {

    private Set<Action> commands;

    private Set<Room> rooms;

    private Inventory inventory;

    public GameEngine(Set<Action> commands, Set<Room> rooms, Inventory inventory) {
        this.commands = commands;
        this.rooms = rooms;
        this.inventory = inventory;
    }

    public GameEngine() {
        this.commands = new HashSet<Action>();
        this.rooms = new HashSet<Room>();
        this.inventory = new Inventory();
    }

    public void addCommand(Action action) {
        this.commands.add(action);
    }

    public void addCommands(List<Action> actions) {
        this.commands.addAll(commands);
    }

    public void addRooms(List<Room> rooms){
        this.rooms.addAll(rooms);
    }

    public void init() {

    }

}
