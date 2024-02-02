package di.uniba.map.type;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * The Action class represents an action that can be performed in the game.
 */
public class Action {

    private ActionType actionType;
    private String actionName;
    private Set<String> actionAlias;

    /**
     * Constructor for the Action class.
     * 
     * @param type  The type of the action.
     * @param name  The name of the action.
     * @param alias The set of aliases for the action.
     */
    public Action(ActionType type, String name, Set<String> alias) {
        this.setActionType(type);
        this.setActionName(name);
        this.actionAlias = new HashSet<String>(alias);
    }

    /**
     * Constructor for the Action class.
     * 
     * @param type The type of the action.
     * @param name The name of the action.
     */
    public Action(ActionType type, String name) {
        this.actionType = type;
        this.actionName = name;
        this.actionAlias = new HashSet<String>();
    }

    /**
     * Sets the action type.
     * 
     * @param actionType The action type to set.
     */
    public void setActionType(ActionType actionType) {
        this.actionType = actionType;
    }

    /**
     * Gets the action type.
     * 
     * @return The action type.
     */
    public ActionType getActionType() {
        return actionType;
    }

    /**
     * Sets the action name.
     * 
     * @param actionName The action name to set.
     */
    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    /**
     * Gets the action name.
     * 
     * @return The action name.
     */
    public String getActionName() {
        return actionName;
    }

    /**
     * Sets the action aliases.
     * 
     * @param alias The array of aliases to set.
     */
    public void setActionAlias(String[] alias) {
        this.actionAlias = new HashSet<>(Arrays.asList(alias));
    }

    /**
     * Adds an alias to the action.
     * 
     * @param newAlias The new alias to add.
     */
    public void addActionAlias(String newAlias) {
        this.actionAlias.add(newAlias);
    }

    /**
     * Gets the action aliases.
     * 
     * @return The set of action aliases.
     */
    public Set<String> getActionAlias() {
        return actionAlias;
    }
}
