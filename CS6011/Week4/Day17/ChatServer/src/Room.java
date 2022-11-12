import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

public class Room {
    private static HashMap<String, Room> map = new HashMap<>(); // # rooms exist
    private String name;
    public ArrayList<Socket> clients = new ArrayList<>(); // clients connect to room object

    public static HashMap<String, Room> getMap() {
        return Room.map;
    }

    public static void setMap(String name, Room room) {
        Room.map.put(name, room);
    }

    public synchronized static Room getRoom(String name) { // create room when client joins
        if(!Room.getMap().containsKey(name)) { // prevent duplicated rooms
            Room room = new Room(name);
            Room.setMap(name, room); // put created room into map
            return room;
        } else { // room exists
            return Room.getMap().get(name);
        }
    }

    private Room(String name) { // constructor is only allowed to be accessed though getRoom;
        this.name = name;
    }

    public void setClient(Socket client) { // client in room
        this.clients.add(client);
    }

    public ArrayList<Socket> getClients() {
        return this.clients;
    }
}
