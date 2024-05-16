package behindTheScenes;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Savable {

    public final static String nTrip = "Trip";
    public final static String nTicket = "Ticket";
    public final static String nVehicle = "Vehicle";
    public final static String nPassenger = "Passenger";
    public final static String nManager = "Manager";
    public final static String nDriver = "Driver";

    
    public int index;
    public int id;

    public Savable() {
    }

    public static int generateId(ArrayList<Savable> instances) {
        if (instances.size() == 0) {
            return 1;
        }
        else {
            return instances.getLast().id + 1;
        }
    }
    
    public String toCsv() {
        return "";
    }

    public String toString() {
        return "";
    }

    public void writeInstance(String savedPath) {
        writeToFile(savedPath, toCsv());
    }

    public static void removeInstance(int id, ArrayList<Savable> instances, String savedPath, String csvHeader) {
        for (short i = 0; i < instances.size(); i++) {
            if (instances.get(i).id == id) {
                instances.remove(i);
                saveInstances(instances, savedPath, csvHeader);
                System.out.println("Item removed successfully");
            }
        }

    }

    public static Savable getById(int id, ArrayList<Savable> instances) {
        for (short i = 0; i < instances.size(); i++) {
            Savable savable = (Savable) instances.get(i);
            if (savable.id == id) {
                return savable;
            }
        }
        return new Savable();
    }

    public static void removeInstance(int id, String className, ArrayList<Savable> instances, String savedPath, String csvHeader) {
        switch (className) {
            case "Trip":
                Trip.removeInstance(id, instances, savedPath, csvHeader);
                break;
            case "Ticket":
                Ticket.removeInstance(id, instances, savedPath, csvHeader);
                break;
            default:
                break;
        }

    }

    public static void initiateClass(String savedPath, String csvHeader, String className, ArrayList<Savable> instances) {
        getSaved(instances, savedPath, className, csvHeader);
    }
    
    public static void saveInstances(ArrayList<Savable> instances, String savedPath, String csvHeader) {
        _writeToFile(savedPath, csvHeader, false);

        for (short i = 0; i < instances.size(); i++) {
            instances.get(i).writeInstance(savedPath);
        }
    }

    private static void newInstance(String line, String className) {
        switch (className) {
            case nTrip:
                Trip.newInstance(line);
                break;
            case nTicket:
                Ticket.newInstance(line);
                break;
            case nVehicle:
                Vehicle.newInstance(line);
                break;
            case nPassenger:
                Passenger.newInstance(line);
                break;
            case nManager:
                Manager.newInstance(line);
                break;
            case nDriver:
                Driver.newInstance(line);
                break;
        
            default:
                break;
        }
    }

    public static void getSaved(ArrayList<Savable> instances, String savedPath, String className, String csvHeader) {
        instances.clear();

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("..\\db\\" + savedPath));
            String line;
            bufferedReader.readLine();
            while ((line = bufferedReader.readLine()) != null) {
                newInstance(line, className);
            }
            bufferedReader.close();
        } catch (FileNotFoundException e) {
            saveInstances(instances, savedPath, csvHeader);
            getSaved(instances, savedPath, className, csvHeader);
        } catch (IOException e) {
            System.out.println("An error occured while getting stored items");
            e.printStackTrace();
        }
    }

    public static String _listInstances(ArrayList<Savable> instances) {
        String text = "";
        for (short i = 0; i < instances.size(); i++) {
            text += (instances.get(i).toString()) + "\n";
        }
        return text;
    }

    public static void listInstances(ArrayList<Savable> instances) {
        System.out.println(_listInstances(instances));
    }

    public static boolean writeToFile(String fileName, String data) {
        return _writeToFile(fileName, data, true);
    }
    
    public static boolean _writeToFile(String fileName, String data, boolean append) {

        try {
            FileWriter fileWriter = new FileWriter("..\\db\\" + fileName, append);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(data);
            bufferedWriter.newLine();
            bufferedWriter.close();
            return true;
        } catch (IOException e) {
            System.out.println("An error occurred while writing to file. { " + fileName + " }");
            e.printStackTrace();
            return false;
        }

    }

    public static String _getData(Scanner scanner, String prompt, String type) {

        String data;
        do {
            System.out.print(prompt);
            data = scanner.nextLine().trim();
        } while (!Validations.valid(data, type));
        return data;
    }

    public static String getData(Scanner scanner, String prompt) {
        return _getData(scanner, prompt, "string");
    }

}