package di.uniba.map.type;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Action {

    private ActionType actionType;
    private String actionName;
    private Set<String> actionAlias;

    public Action(ActionType type, String name, Set<String> alias) {
        this.setActionType(type);
        this.setActionName(name);;
        this.actionAlias = new HashSet<String>(alias);
    }

    public Action(ActionType type, String name) {
        this.actionType = type;
        this.actionName = name;
        this.actionAlias = new HashSet<String>();
    }

    public void setActionType(ActionType actionType) {
        this.actionType = actionType;
    }

    public ActionType getActionType() {
        return actionType;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public String getActionName() {
        return actionName;
    }

    public void setActionAlias(String[] alias) {
        this.actionAlias = new HashSet<>(Arrays.asList(alias));
    }

    public void addActionAlias(String newAlias) {
        this.actionAlias.add(newAlias);
    }

    public Set<String> getActionAlias() {
        return actionAlias;
    }

}
