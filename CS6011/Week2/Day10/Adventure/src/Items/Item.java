package Items;

public class Item {
//    private boolean
    private String name_;
    private String description_;

    public Item(String name, String description) {
        name_ = name;
        description_ = description;
    }

    @Override
    public String toString() {
        return name_;
    }
}
