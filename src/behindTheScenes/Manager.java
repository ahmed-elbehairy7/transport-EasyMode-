package behindTheScenes;

import java.util.ArrayList;

public class Manager extends User {

    public static ArrayList<Savable> instances = new ArrayList<>();
    public final static String className = "Manager";
    public final static String savedPath = "managers.csv";    

    public Manager(int id, String name, String username, String password, String Email) {
        super(id, name, username, password, Email);
        Manager.instances.add(this);
    }
    
    public static void initiateClass() {
        initiateClass(Manager.savedPath, Manager.className, Manager.instances);
    }

    public static void newInstance(String line) {
        String data[] = line.split(",");
        new Manager(Integer.parseInt(data[0]), data[1], data[3], data[4], data[2]);
    }
    

}