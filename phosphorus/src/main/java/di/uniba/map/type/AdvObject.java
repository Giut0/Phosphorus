package di.uniba.map.type;

import java.util.HashSet;
import java.util.Set;

public class AdvObject {

    private int objId;
    private String objName;
    private String objDescription;
    private Set<String> objAlias;

    private Action objAction;

    public AdvObject(int id, String name, String desc, Set<String> alias, Action action) {
        this.objId = id;
        this.objName = name;
        this.objDescription = desc;
        this.objAlias = new HashSet<String>();
        this.objAlias = alias;
        this.objAction = action;
    }

    public int getObjId() {
        return this.objId;
    }

    public String getObjName() {
        return this.objName;
    }

    public String getObjDescription() {
        return this.objDescription;
    }

    public Set<String> getObjAlias() {
        return this.objAlias;
    }

    public Action getObjAction() {
        return this.objAction;
    }

    public void setObjId(int objId) {
        this.objId = objId;
    }

    public void setObjName(String objName) {
        this.objName = objName;
    }

    public void setObjDescription(String objDescription) {
        this.objDescription = objDescription;
    }

    public void setObjAlias(Set<String> objAlias) {
        this.objAlias = objAlias;
    }

    public void setObjAction(Action objAction) {
        this.objAction = objAction;
    }

}
