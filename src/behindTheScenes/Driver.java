package behindTheScenes;
import java.util.ArrayList;

public class Driver extends User {

    public static ArrayList<Savable> instances = new ArrayList<>();
    public final static String className = "Driver";
    public final static String savedPath = "drivers.csv";    

    public Driver( String name, String username, String password, String Email) {
        super(generateId(Driver.instances), name, username, password, Email);
        Driver.instances.add(this);

    }

    public Driver(int id, String name, String username, String password, String Email) {
        super(id, name, username, password, Email);
        Driver.instances.add(this);
    }

    public static String _listDrivers() {
        Driver driver;
        String text = "";
        for (short i = 0; i < Driver.instances.size(); i++) {
            driver = (Driver) Driver.instances.get(i);
            text += "\n\nId: " + driver.id + "\nName: " + driver.name + "\n";
        }
        return text;
    }


    public static void listDrivers() {
        System.out.println(_listDrivers());
    }

    public String toString() {
        return "\n==============\nDriver details:\n\nName:      " + this.name + "\nEmail:     " + this.Email
                + "\nusername:  " + this.username
                + "\n==============\n";
    }
    
    public static void initiateClass() {
        initiateClass(Driver.savedPath, Driver.className, Driver.instances);
    }

    public static void newInstance(String line) {
        String data[] = line.split(",");
        new Driver(Integer.parseInt(data[0]), data[1], data[3], data[4], data[2]);
    }
    
}