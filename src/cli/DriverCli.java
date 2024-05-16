package cli;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import behindTheScenes.*;

public class DriverCli extends UserCli {

    public DriverCli() {
        super(Driver.instances, Driver.savedPath, Driver.className);
        Driver.instances.add(
                new Driver(
                        this.id,
                        this.name,
                        this.username,
                        this.password,
                        this.Email));
    }

    public static void listDrivers() {
        try {
            BufferedReader bf = new BufferedReader(new FileReader("drivers.csv"));
        bf.readLine();
        String line = bf.readLine();
        while ((line = bf.readLine()) != null) {
            String[] driverData = line.split(",");
            System.out.println("\n\nId: " + driverData[0] + "\nName: " + driverData[1]);
        }
        }
        catch (IOException e) {
            System.out.println("There was an error while lising drivers");
        }
        
    }

    public void startFlow() {
        /*
        If you log in with a driver credentials you are directed to the drivers profile with some basic information about the driver and the trips that are assigned to him by the manager. 
        */

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n\nPlease choose one of the following:\n\n(P) Personal info\n(S) Show assigned trips\n(Q) Quit\n\n");

            switch (scanner.nextLine().toUpperCase()) {
                case "P":
                    System.out.println(toString());
                    break;
                case "S":
                     System.out.println("\n\nHere's the trips assigned to you: \n");
                     for (short i = 0; i < Trip.instances.size(); i++) {
                         Trip trip = (Trip) Trip.instances.get(i);
                         if (this.id ==trip.driverId) {
                             System.out.println(trip.toString(trip.info()));
                         }
                     }
                     break;
                 case "Q":
                     return;
                default:
                    break;
            }
        }

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