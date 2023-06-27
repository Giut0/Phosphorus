package di.uniba.map.type;

import java.util.ArrayList;
import java.util.List;

public class Room {

    private final int roomID;
    private String roomName;
    private String roomDescription;
    private String lookDescription;

    private Room south = null;
    private Room north = null;
    private Room east = null;
    private Room west = null;

    private boolean oxygen = true;

    private final List<AdvObject> objects = new ArrayList<>();

    public Room(int id) {
        this.roomID = id;
    }

    public Room(int id, String name, String description) {
        this.roomID = id;
        this.roomName = name;
        this.roomDescription = description;
    }

    public String getName() {
        return roomName;
    }

    public void setName(String name) {
        this.roomName = name;
    }

    public String getDescription() {
        return roomDescription;
    }

    public void setDescription(String description) {
        this.roomDescription = description;
    }

    public boolean isOxygen() {
        return this.oxygen;
    }

    public void setOxygen(boolean oxy) {
        this.oxygen = oxy;
    }

    public Room getSouth() {
        return south;
    }

    public void setSouth(Room south) {
        this.south = south;
    }

    public Room getNorth() {
        return north;
    }

    public void setNorth(Room north) {
        this.north = north;
    }

    public Room getEast() {
        return east;
    }

    public void setEast(Room east) {
        this.east = east;
    }

    public Room getWest() {
        return west;
    }

    public void setWest(Room west) {
        this.west = west;
    }

    public List<AdvObject> getObjects() {
        return objects;
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
        final Room other = (Room) obj;
        if (this.roomID != other.roomID) {
            return false;
        }
        return true;
    }

    public String getLook() {
        return lookDescription;
    }

    public void setLook(String look) {
        this.lookDescription = look;
    }

}
