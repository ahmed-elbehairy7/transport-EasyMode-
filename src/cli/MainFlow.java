package cli;

import java.util.Scanner;

import behindTheScenes.Driver;
import behindTheScenes.Manager;
import behindTheScenes.Passenger;

public class MainFlow {
    public MainFlow() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please choose a user type:\n\n(P) Passenger:\n(M) Manager:\n(D) Driver:\n\n");

        Passenger.initiateClass();
        Manager.initiateClass();
        Driver.initiateClass();
        switch (scanner.nextLine().toUpperCase()) {
            case "P":
                PassengerCli passenger = new PassengerCli();
                passenger.startFlow();
                break;
            case "M":
                ManagerCli manager = new ManagerCli();
                manager.startFlow();
                 break;
            case "D":
                DriverCli driver = new DriverCli();
                driver.startFlow();
                break;
        
            default:
                System.out.println("\nInvalid user type!");
                break;
        }
    }
}
