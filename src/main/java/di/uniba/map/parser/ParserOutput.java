package di.uniba.map.parser;

import di.uniba.map.type.Action;
import di.uniba.map.type.Character;
import di.uniba.map.type.Item;

public class ParserOutput {
    
    private Action action;

    private Item item;
    
    private Character character;

    public ParserOutput(Action Action, Item item) {
        this.action = Action;
        this.item = item;
    }

    public ParserOutput(Action Action, Character character) {
        this.action = Action;
        this.character = character;
    }

    public ParserOutput(Action Action) {
        this.action = Action;
    }

    public ParserOutput(Action Action, Item item, Character character) {
        this.action = Action;
        this.item = item;
        this.character = character;
    }



    public Action getAction() {
        return action;
    }

    public void setAction(Action Action) {
        this.action = Action;
    }

    public Item getObject() {
        return item;
    }

    public void setObject(Item item) {
        this.item = item;
    }

    public Character getCharacter() {
        return character;
    }

    public void setCharacter(Character character) {
        this.character = character;
    }
    
}
