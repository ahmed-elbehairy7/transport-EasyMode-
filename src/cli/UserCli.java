package cli;

import java.util.ArrayList;
import java.util.Scanner;
import java.lang.System;

import behindTheScenes.*;


public class UserCli {
    public static String csvHeader = "id,Name,Email,username,password";

    public int id;
    public String name;
    public String username;
    public String password;
    public String Email;
    public boolean success = false;
    

    public UserCli(ArrayList<Savable> instances, String savedPath, String className) {
        //Initialize the scanner
        Scanner scanner = new Scanner(System.in);
        
        // Ask the user to register or login
        System.out.print("\nWelcome!\n\nPlease enter 'r' to register, any key to login: ");

        // Get what the user choose
        switch (scanner.nextLine()) {
            case "r":
                register(instances, savedPath, className);
                break;
            default:
                login(instances);
                break;
        }

        if (!this.success) {
            System.out.println("Invalid credentials");
            System.exit(1);
        }

        Trip.initiateClass();
        Vehicle.initiateClass();
        Ticket.initiateClass();
    }

    public void register(ArrayList<Savable> instances, String savedPath, String className) {

        Scanner scanner = new Scanner(System.in);

        String t;
        switch (className) {
            case Passenger.className:
                t = "puser";
                break;
            case Manager.className:
                t = "muser";
                break;
            case Driver.className:
                t = "duser";
            default:
                t = "user";
                break;
        }

        this.name = Savable._getData(scanner, "Enter your name: ", "name");
        this.Email = Savable._getData(scanner, "Enter your email: ", "email");
        this.username = Savable._getData(scanner, "Enter your username: ", t);
        this.password = Savable._getData(scanner, "Enter your password: ", "pass");

        User user = User.register(name, Email, username, password, instances, savedPath, className);
        this.id = user.id;
        this.success = true;
    }
    
    public String toCsv() {
        return this.id + "," + this.name + "," + this.Email + "," + this.username + "," + this.password;
        
    }

    public boolean login(ArrayList<Savable> instances) {

        Scanner scanner = new Scanner(System.in);

        String username = Savable.getData(scanner, "Enter your username: ");
        String password = Savable._getData(scanner, "Enter your password: ", "pass");

        User user = User.login(username, password, instances);
        if (!user.success) {
            return false;
        }
        this.id = user.id;
        this.name = user.name;
        this.Email = user.Email;
        this.username = user.username;
        this.success = true;
        this.password = password;
        return true;
        
    }
   
    public static void initiateClass(String usersFile, String className, ArrayList<Savable> instances) {
        User.initiateClass(usersFile, User.csvHeader, className, instances);
    }
}