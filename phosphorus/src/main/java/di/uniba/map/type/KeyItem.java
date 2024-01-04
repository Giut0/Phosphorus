package di.uniba.map.type;

public class KeyItem extends Item {

    private boolean keyUsed;

    public KeyItem(int id, String name, String description, int itemLocation) {
        super(id, name, description, itemLocation);
        this.keyUsed = false;
        
    }

    public void setKeyUsed(boolean keyUsed) {
        this.keyUsed = keyUsed;
    }

    public boolean isKeyUsed(){
        return this.keyUsed;
    }

    @Override
    public void use() {
        // TODO USO PER KEY
    }

}
