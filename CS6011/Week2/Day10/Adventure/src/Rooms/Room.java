package Rooms;

import Items.Item;

import java.util.ArrayList;

public class Room {
    private String name_;
    private String description_;
    ArrayList<Item> items_;
    ArrayList<Room> doors_ = new ArrayList<>();

    public Room(String name, String description) {
        name_ = name;
        description_ = description;
    }

    public void print() {
        System.out.println("You are in the " + name_ + ", " + description_);
        System.out.println("You see the following doors: ");

        int num = 1;
        for (Room r : doors_) {
            System.out.println("    " + num + " - " + r.name_);
            num++;
        }
        if (items_.size() > 0) {
            System.out.println("You see the following items: ");
            for (Item item : items_) {
                System.out.println("    " + item);
            }
        }
    }

    public void addConnection(Room room) {
        doors_.add(room);
        room.doors_.add(this);
//        if (!room.doors_.contains(this)) {
//            room.addConnection(this);
//        }
    }

    public Room goThroughDoor(int doorNum) {
        // doorNum comes in 1-based
        // doors_is 0-based
        doorNum -= 1;

        if (doorNum > doors_.size() || doorNum < 0) {
            System.out.println("There is no such door");
            return null;
        } else {
            return doors_.get(doorNum);
        }
    }

    public void addItem(Item item) {
        items_.add(item);
    }

//    public void openDoor() {
//
//    }
}
