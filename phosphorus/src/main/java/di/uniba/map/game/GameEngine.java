package di.uniba.map.game;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import di.uniba.map.type.Action;
import di.uniba.map.type.ActionType;
import di.uniba.map.type.Inventory;
import di.uniba.map.type.Room;

public class GameEngine {

    private Set<Action> commands;

    private Set<Room> rooms;

    private Set<Character> characters;

    private Inventory inventory;

    public GameEngine(Set<Action> commands, Set<Room> rooms, Set<Character> characters, Inventory inventory) {
        this.commands = commands;
        this.rooms = rooms;
        this.characters = characters;
        this.inventory = inventory;
    }

    public GameEngine() {
        this.commands = new HashSet<Action>();
        this.rooms = new HashSet<Room>();
        this.characters = new HashSet<Character>();
        this.inventory = new Inventory();
    }

    public void addCommand(Action action) {
        this.commands.add(action);
    }

    public void addCommands(List<Action> actions) {
        this.commands.addAll(commands);
    }

    public void init() {

    }

}
