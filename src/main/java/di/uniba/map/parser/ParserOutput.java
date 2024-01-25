package di.uniba.map.parser;

import di.uniba.map.type.Action;

import di.uniba.map.type.Character;
import di.uniba.map.type.Item;

/**
 * The ParserOutput class represents the result of parsing a user's input.
 */
public class ParserOutput {

    private Action action;
    private Item item;
    private Character character;

    /**
     * Constructor for the ParserOutput class.
     * 
     * @param action The action parsed from the user's input.
     * @param item   The item parsed from the user's input.
     */
    public ParserOutput(Action action, Item item) {
        this.setAction(action);
        this.setItem(item);
    }

    /**
     * Constructor for the ParserOutput class.
     * 
     * @param action    The action parsed from the user's input.
     * @param character The character parsed from the user's input.
     */
    public ParserOutput(Action action, Character character) {
        this.setAction(action);
        this.setCharacter(character);
    }

    /**
     * Constructor for the ParserOutput class.
     * 
     * @param action The action parsed from the user's input.
     */
    public ParserOutput(Action action) {
        this.setAction(action);
    }

    /**
     * Constructor for the ParserOutput class.
     * 
     * @param action    The action parsed from the user's input.
     * @param item      The item parsed from the user's input.
     * @param character The character parsed from the user's input.
     */
    public ParserOutput(Action action, Item item, Character character) {
        this.action = action;
        this.item = item;
        this.character = character;
    }

    /**
     * Sets the action.
     * 
     * @param action The action to set.
     */
    public void setAction(Action action) {
        this.action = action;
    }

    /**
     * Gets the action.
     * 
     * @return The action.
     */
    public Action getAction() {
        return action;
    }

    /**
     * Sets the item.
     * 
     * @param item The item to set.
     */
    public void setItem(Item item) {
        this.item = item;
    }

    /**
     * Gets the item.
     * 
     * @return The item.
     */
    public Item getItem() {
        return item;
    }

    /**
     * Sets the character.
     * 
     * @param character The character to set.
     */
    public void setCharacter(Character character) {
        this.character = character;
    }

    /**
     * Gets the character.
     * 
     * @return The character.
     */
    public Character getCharacter() {
        return character;
    }
}
