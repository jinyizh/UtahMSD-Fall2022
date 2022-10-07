import Rooms.Cell;
import Rooms.Room;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {


        // Create the house...
        Room entrance = new Room("Foyer", "A dusty entrance hall.");
        Room grandHall = new Room("Grand Hall", "A large room made for dancing.");
        Room cell = new Cell();

        entrance.addConnection(grandHall);
        entrance.addConnection(cell);

        // Start the game...
        Room currentRoom = entrance;

        System.out.println("Welcome to Adventure!");

        Scanner sc = new Scanner(System.in);

        boolean done = false;
        while (!done) {
            currentRoom.print();
            System.out.println("What would you like to do?");
            String command = sc.nextLine();

            if (Character.isDigit(command.charAt(0))) {
                int doorNum = Integer.parseInt(command);
                Room newRoom = currentRoom.goThroughDoor(doorNum);
                if (newRoom != null) {
                    currentRoom = newRoom;
                }
            }
        }
    }
}