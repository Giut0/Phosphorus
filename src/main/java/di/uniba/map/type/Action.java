package di.uniba.map.type;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Action {

    private ActionType commandType;
    private String commandName;
    private Set<String> commandAlias;

    public Action(ActionType type, String name, Set<String> alias) {
        this.commandType = type;
        this.commandName = name;
        this.commandAlias = new HashSet<String>(alias);
    }

    public Action(ActionType type, String name) {
        this.commandType = type;
        this.commandName = name;
        this.commandAlias = new HashSet<String>();
    }

    public void setCommandType(ActionType commandType) {
        this.commandType = commandType;
    }

    public void setCommandName(String commandName) {
        this.commandName = commandName;
    }

    public void setCommandAlias(String[] alias) {
        this.commandAlias = new HashSet<>(Arrays.asList(alias));
    }

    public void addCommandAlias(String newAlias){
        this.commandAlias.add(newAlias);
    }

    public ActionType getCommandType() {
        return commandType;
    }

    public String getCommandName() {
        return commandName;
    }

    public Set<String> getCommandAlias() {
        return commandAlias;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Action other = (Action) obj;
        if (this.commandType != other.commandType) {
            return false;
        }
        return true;
    }
}
