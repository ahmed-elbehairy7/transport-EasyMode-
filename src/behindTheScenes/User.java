package behindTheScenes;

import java.util.ArrayList;


public class User extends Savable{
    public static String csvHeader = "id,Name,Email,username,password";

    public String name;
    public String username;
    public String password;
    public String Email;
    public boolean success = false;

    public User() {
    }

    public User(int id, String name, String username, String password, String Email) {
        this.id = id;

        this.name = name;
        this.Email = Email;
        this.username = username;
        this.password = password;

    }
    public User(String name, String username, String password, String Email, ArrayList<Savable> instances) {
        this.id = generateId(instances);

        this.name = name;
        this.Email = Email;
        this.username = username;
        this.password = password;

    }
    
    public static User login(String username, String password, ArrayList<Savable> instances) {

        User user;
        for (short i = 0; i < instances.size(); i++) {
            user = ((User) instances.get(i));
            if (!(user.username.equals(username))) {
                continue;
            }
            if (!(user.password.equals(password))) {
                continue;
            }
            user.success = true;
            return user;
        }
        return new User();

    }

    public static User register(String name, String Email, String username, String password, ArrayList<Savable> instances, String savedPath, String className) {
        User user = new User(name, username, password, Email, instances);
        user.writeInstance(savedPath);
        user.success = true;
        return user;
    }
    
    public String toCsv() {
        return this.id + "," + this.name + "," + this.Email + "," + this.username + "," + this.password;
        
    }
   
    public static void initiateClass(String usersFile, String className, ArrayList<Savable> instances) {
        initiateClass(usersFile, User.csvHeader, className, instances);
    }
}