package behindTheScenes;

import java.util.ArrayList;

public class Vehicle extends Savable {

    public static ArrayList<Savable> instances = new ArrayList<>();
    public final static String csvHeader = "type,capacity,licensePlate";
    public final static String savedPath = "vehicles.csv";

    private String type;
    private int capacity;
    private String licensePlate;

    public Vehicle(String type, int capacity, String licensePlate) {
        this.type = type;
        this.capacity = capacity;
        this.licensePlate = licensePlate;

        Vehicle.instances.add(this);
    }

    public void setType(String type) {
        this.type = type;
    }

    public static void newInstance(String line) {

        String[] data = line.split(",");

        new Vehicle(data[0], Integer.parseInt(data[1]), data[2]);
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String toString() {
        return "Type: " + this.type + "\nCapacity: " + this.capacity + "\nLicense Plate: " + this.licensePlate;
    }

    public void displayInfo() {
        System.out.println(this.toString());
    }

    public String toCsv() {
        return this.type + "," + this.capacity + "," + this.licensePlate;
    }

    public static void initiateClass() {
        initiateClass(Vehicle.savedPath, Vehicle.csvHeader, "Vehicles", Vehicle.instances);
    }
}