package di.uniba.map.parser;

import di.uniba.map.type.Action;

import di.uniba.map.type.Character;
import di.uniba.map.type.Item;

public class ParserOutput {

    private Action action;

    private Item item;

    private Character character;

    public ParserOutput(Action action, Item item) {
        this.setAction(action);
        this.setItem(item);
    }

    public ParserOutput(Action action, Character character) {
        this.setAction(action);
        this.setCharacter(character);
    }

    public ParserOutput(Action action) {
        this.setAction(action);
    }

    public ParserOutput(Action action, Item item, Character character) {
        this.action = action;
        this.item = item;
        this.character = character;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public Action getAction() {
        return action;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Item getItem() {
        return item;
    }

    public void setCharacter(Character character) {
        this.character = character;
    }

    public Character getCharacter() {
        return character;
    }

}
