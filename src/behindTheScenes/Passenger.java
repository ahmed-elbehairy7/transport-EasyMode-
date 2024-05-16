package behindTheScenes;

import java.util.ArrayList;

public class Passenger extends User {

    public static ArrayList<Savable> instances = new ArrayList<>();
    public final static String savedPath = "passengers.csv";
    public final static String className = "Passenger";

    public Passenger(int id, String name, String username, String password, String Email) {
        super(id, name, username, password, Email);
        Passenger.instances.add(this);
    }
   
    public static void initiateClass() {
        initiateClass(Passenger.savedPath, Passenger.className, Ticket.instances);
    }

    public static void newInstance(String line) {
        String data[] = line.split(",");
        new Passenger(Integer.parseInt(data[0]), data[1], data[3], data[4], data[2]);
    }
}
