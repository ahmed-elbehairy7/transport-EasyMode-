package behindTheScenes;

import java.util.ArrayList;
import java.util.Scanner;

public class Trip extends Savable {

    public static ArrayList<Savable> instances = new ArrayList<>();
    public final static String csvHeader = "id,type,source,destination,stops,seats,price,driverId,cycle";
    public final static String savedPath = "trips.csv";
    public final static String className = "Trip";

    public String type;
    public String source;
    public String destination;
    public String cycle;
    public int stops;
    public int seats;
    public int price;
    public int driverId;

    public Trip(String type, String source, String destination, int stops, int seats, int price, int driverId,
            String cycle) {
        super();
        this.id = generateId(Trip.instances);
        this.type = type;
        this.source = source;
        this.destination = destination;
        this.stops = stops;
        this.seats = seats;
        this.price = price;
        this.cycle = cycle;
        this.driverId = driverId;
        Trip.instances.add(this);
    }
    
    public Trip(int id, String type, String source, String destination, int stops, int seats, int price, int driverId, String cycle) {
        super();
        this.index = instances.size();
        this.id = id;
        this.type = type;
        this.source = source;
        this.destination = destination;
        this.stops = stops;
        this.seats = seats;
        this.price = price;
        this.cycle = cycle;
        this.driverId = driverId;
        Trip.instances.add(this);
    }

    public static void initiateClass() {
        initiateClass(Trip.savedPath,Trip.csvHeader, Trip.className, Trip.instances);

    }

    
    public static void newInstance(String line) {

        String[] data = line.split(",");

        new Trip(Integer.parseInt(data[0]), data[1], data[2], data[3], Integer.parseInt(data[4]),
                Integer.parseInt(data[5]), Integer.parseInt(data[6]), Integer.parseInt(data[7]), data[8]);
    }

    public String toCsv() {
        return this.id + "," + this.type + "," + this.source + "," + this.destination + "," + this.stops + ","
                + this.seats + "," + this.price + "," + this.driverId + "," + this.cycle;
    }

    public String allInfo() {
        return "ID: " + this.id + "\nSource: " + this.source + "\nDestination: " + this.destination + "\nStops: "
                + this.stops + "\nSeats: " + this.seats + "\nDriver: " + this.driverId + "\nType: " + this.type
                + "\nPrice: " + this.price;
    }
    
    public void assignDriver() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n\nChoosed Trip:\n");
        System.out.println(toString(allInfo()));
        System.out.println("Please press enter to view list of available drivers");
        scanner.nextLine();
        Driver.listDrivers();
        this.driverId = Integer.parseInt(Savable._getData(scanner, "\nNew Driver Id: ", "int"));
        saveInstances(Trip.instances, Trip.savedPath, Trip.csvHeader);

    }

    public void editTrip() {
        Scanner scanner = new Scanner(System.in);
        String prompt = "\nNew Value: ";
        while (true) {
            System.out.println(toString(allInfo()));
            System.out.println(
                    "Please choose what to edit:\n\n(0) Source\n(1) Destination\n(2) Type\n(3) Stops\n(4) Seats\n(5) Price\n(6) Driver\n(S) Save and exit\n(Q) Quit without saving\n\n");

            switch (scanner.nextLine().toUpperCase()) {
                case "0":
                    this.source = Savable.getData(scanner, prompt);
                    break;
                case "1":
                    this.destination = Savable.getData(scanner, prompt);
                    break;
                case "2":
                    this.type = Savable._getData(scanner, prompt, "tType");
                    break;
                case "3":
                    this.stops = Integer.parseInt(Savable._getData(scanner, prompt, "int"));
                    break;
                case "4":
                    this.seats = Integer.parseInt(Savable._getData(scanner, prompt, "int"));
                    break;
                case "5":
                    this.price = Integer.parseInt(Savable._getData(scanner, prompt, "int"));
                    break;
                case "6":
                    this.driverId = Integer.parseInt(Savable._getData(scanner, prompt, "int"));
                    break;
                case "S":
                    saveInstances(Trip.instances, Trip.savedPath, Trip.csvHeader);
                    return;
                case "Q":
                    getSaved(Trip.instances, Trip.savedPath, Trip.className, Trip.csvHeader);
                    return;
                default:
                    break;
            }
        }
    }

    public String info() {
        return "Index: " + this.index + "\nID: " + this.id + "\nSource: " + this.source + "\nDestination: " + this.destination + "\nStops: "
                + this.stops + "\nCycle: " + this.cycle;
    }
    
    public String toString() {
        return "==============\n" + info() + "\n==============\n";
    }

    public String toString(String info) {
        return "==============\n" + info + "\n==============\n";
    }
}
