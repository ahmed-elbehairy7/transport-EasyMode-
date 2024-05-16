package cli;

import java.util.Scanner;

import behindTheScenes.*;

public class ManagerCli extends UserCli {

    public ManagerCli() {
        super(Manager.instances, Manager.savedPath, Manager.className);
        Manager.instances.add(
                new Manager(
                        this.id,
                        this.name,
                        this.username,
                        this.password,
                        this.Email));
    
    }

    public void startFlow() {

        /*
        If you log in as a manager you are able to review all trips in the system,you are able to add / remove trips and assign drivers the trips in the system 
        */

        Scanner scanner = new Scanner(System.in);

        while (true) {

            System.out.println(
                    "Please choose one of the following:\n\n(L) List all trips\n(A) Add a trip\n(R) Remove a trip\n(E) Edit a trip\n(D) Assign a driver\n(V) Add a vehicle\n(M) Add an Employer (driver)\n(Q) Quit\n\n");

            switch (scanner.nextLine().toUpperCase()) {
                case "L":
                    Trip.listInstances(Trip.instances);
                    break;
                case "A":
                    addTrip(scanner);
                    break;
                case "R":
                    removeTrip(scanner);
                    break;
                case "E":
                    editTrip(scanner, false);
                    break;
                case "D":
                    editTrip(scanner, true);
                    break;
                case "V":
                    addVehicle(scanner);
                    break;
                case "M":
                    addEmployee(scanner);
                    break;
                case "Q":
                    return;
                default:
                    break;
            }

        }

    }

    public void addEmployee(Scanner scanner) {
        String Name = Savable.getData(scanner, "Name: ");
        String Email = Savable._getData(scanner, "Email: ", "email");
        String Username = Savable._getData(scanner, "Username: ", "duser");
        String password = Savable._getData(scanner, "password: ", "pass");
        new Driver(Name, Username, password, Email);
        Driver.saveInstances(Driver.instances, Driver.savedPath, Driver.csvHeader);
    }

    public void addVehicle(Scanner scanner) {
        
        String Type = Savable.getData(scanner, "Type: ");
        int Capacity = Integer.parseInt(Savable._getData(scanner, "Capacity: ", "int"));
        String LicensePlate = Savable._getData(scanner, "License Plate: ", "name");

        new Vehicle(Type, Capacity, LicensePlate);
        Vehicle.saveInstances(Vehicle.instances, Vehicle.savedPath, Vehicle.csvHeader);
    }

    public void addTrip(Scanner scanner) {

        String source = Savable.getData(scanner, "Source: ");
        String Destination = Savable.getData(scanner, "Destination: ");
        String Type = Savable._getData(scanner, "Type: ", "tType");
        String Stops = Savable._getData(scanner, "Stops: ", "int");
        String Seats = Savable._getData(scanner, "Seats: ", "int");
        String Price = Savable._getData(scanner, "Price: ", "int");
        String DriverId = Savable._getData(scanner, "DriverId: ", "int");
        String cycle = Savable._getData(scanner, "Cycle: ", "cycle");


        Trip trip = new Trip(Type, source, Destination, Integer.parseInt(Stops), Integer.parseInt(Seats), Integer.parseInt(Price), Integer.parseInt(DriverId), cycle);

        trip.writeInstance(Trip.savedPath);

    }

    public void removeTrip(Scanner scanner) {
        System.out.println("Please type the id of the trip you want to remove");
        String id = scanner.nextLine();
        if (id.isEmpty()) {
            return;
        }
        Trip.removeInstance(Integer.parseInt(id), Trip.instances, Trip.savedPath, Trip.csvHeader);
    }
    
    public void editTrip(Scanner scanner, boolean driver) {
        Trip.listInstances(Trip.instances);
        System.out.println("Please type the id of the trip you want to edit: ");
        int id = Integer.parseInt(scanner.nextLine());

        for (short i = 0; i < Trip.instances.size(); i++) {
            Trip trip = (Trip) Trip.instances.get(i);
            if (trip.id != id) {
                continue;
            }
            if (driver) {
                trip.assignDriver();
                return;
            }
            trip.editTrip();
            return;
        }
    }
    
    public static void initiateClass() {
        initiateClass(Manager.savedPath, Manager.className, Manager.instances);
    }

    public static void newInstance(String line) {
        String data[] = line.split(",");
        new Manager(Integer.parseInt(data[0]), data[1], data[3], data[4], data[2]);
    }
    

}