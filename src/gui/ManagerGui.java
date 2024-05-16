package gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import behindTheScenes.Driver;
import behindTheScenes.Trip;
import behindTheScenes.User;
import behindTheScenes.Vehicle;

public class ManagerGui extends UserGui {
    private Button ListTripsButton, addTripButton, removeTripButton, editTripButton, assignDriverButton, addVehicleButton, addEmployeeButton;
    private TextArea outputArea;

    public ManagerGui(User Manager) {
        super("Manager", Manager);
        setLayout(new BorderLayout());

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(7, 1));

        //List all trips
        ListTripsButton = new Button("List trips");
        ListTripsButton.addActionListener(e -> listTrips());
        buttonPanel.add(ListTripsButton);

        //Add a trip
        addTripButton = new Button("Add a trip");
        addTripButton.addActionListener(e -> addTrip());
        buttonPanel.add(addTripButton);

        //Remove a trip
        removeTripButton = new Button("Remove a trip");
        removeTripButton.addActionListener(e -> removeTrip());
        buttonPanel.add(removeTripButton);

        //edit a trip
        editTripButton = new Button("Edit a trip");
        editTripButton.addActionListener(e -> editTrip());
        buttonPanel.add(editTripButton);

        //Assign a driver
        assignDriverButton = new Button("Assign a driver");
        assignDriverButton.addActionListener(e -> assignDriver());
        buttonPanel.add(assignDriverButton);

        //add a vihecle
        addVehicleButton = new Button("Add a vehicle");
        addVehicleButton.addActionListener(e -> addVehicle());
        buttonPanel.add(addVehicleButton);

        //Add an employee
        addEmployeeButton = new Button("Add an amployee");
        addEmployeeButton.addActionListener(e -> addEmployee());
        buttonPanel.add(addEmployeeButton);

        add(buttonPanel, BorderLayout.WEST);

        outputArea = new TextArea();
        JScrollPane scrollPane = new JScrollPane(outputArea);
        add(scrollPane, BorderLayout.CENTER);

    }
    
    private void listTrips() {
        outputArea.setText(Trip._listInstances(Trip.instances));
    }
    
    private void addTrip() {

        String Type = Data.getDialog("Type: ", "tType");
        String Source = Data.getDialog("Source: ", "string");
        String Destination = Data.getDialog("Destination", "string");
        String Stops = Data.getDialog("Stops: ", "int");
        String Seats = Data.getDialog("Seats: ", "int");
        String Price = Data.getDialog("Price", "int");
        String DriverId = Data.getDialog("DriverId", "int");
        String Cycle = Data.getDialog("Cycle", "cycle");

        new Trip(Type, Source, Destination, Integer.parseInt(Stops), Integer.parseInt(Seats), Integer.parseInt(Price), Integer.parseInt(DriverId), Cycle).writeInstance(Trip.savedPath);
    }

    private void removeTrip() {
        listTrips();

        String id = Data.getDialog("Please type the id of the trip you want to remove: ", "int");

        Trip.removeInstance(Integer.parseInt(id), Trip.instances, Trip.savedPath, Trip.csvHeader);

    }
    
    private void editTrip() {

        listTrips();

        int id = Integer.parseInt(Data.getDialog("Please type the id of the trip you want to edit: ", "int"));

        Trip trip = (Trip) Trip.getById(id, Trip.instances);
        String prompt = "New value:\n";
        while (true) {
            outputArea.setText(trip.toString(trip.allInfo()));
            outputArea.append(
                    "\n\nPlease choose what to edit:\n\n(0) Source\n(1) Destination\n(2) Type\n(3) Stops\n(4) Seats\n(5) Price\n(6) Driver\n(S) Save and exit\n(Q) Quit without saving\n\n");

            switch (JOptionPane.showInputDialog("Please choose what to edit: ").toUpperCase()) {
                case "0":
                    trip.source = Data.getDialog(prompt, "name");
                    break;
                case "1":
                    trip.destination = Data.getDialog(prompt, "name");
                    break;
                case "2":
                    trip.type = Data.getDialog(prompt, "tType");
                    break;
                case "3":
                    trip.stops = Integer.parseInt(Data.getDialog(prompt, "int"));
                    break;
                case "4":
                    trip.seats = Integer.parseInt(Data.getDialog(prompt, "int"));
                    break;
                case "5":
                    trip.price = Integer.parseInt(Data.getDialog(prompt, "int"));
                    break;
                case "6":
                    trip.driverId = Integer.parseInt(Data.getDialog(prompt, "int"));
                    break;
                case "S":
                    Trip.saveInstances(Trip.instances, Trip.savedPath, Trip.csvHeader);
                    return;
                case "Q":
                    Trip.getSaved(Trip.instances, Trip.savedPath, Trip.className, Trip.csvHeader);
                    return;
                default:
                    break;
            }
        }
    }
    
    private void assignDriver() {
        
        listTrips();

        int id = Integer.parseInt(Data.getDialog("Please type the id of the trip you want to edit: ", "int"));

        Trip trip = (Trip) Trip.getById(id, Trip.instances);

        outputArea.setText("\n\nChoosed Trip:\n");
        outputArea.append(trip.toString(trip.allInfo()));
        outputArea.append("\n\n\nAvailable drivers:\n");
        outputArea.append(Driver._listDrivers());
        trip.driverId = Integer.parseInt(Data.getDialog("\nNew Driver Id: ", "int"));
        Trip.saveInstances(Trip.instances, Trip.savedPath, Trip.csvHeader);

    }

    private void addVehicle() {
        String Type = Data.getDialog("Type: ", "name");
        int Capacity = Integer.parseInt(Data.getDialog("Capacity: ", "int"));
        String LicensePlate = Data.getDialog("License Plate: ", "name");

        new Vehicle(Type, Capacity, LicensePlate);
        Vehicle.saveInstances(Vehicle.instances, Vehicle.savedPath, Vehicle.csvHeader);
    }

    private void addEmployee() {
        String Name = Data.getDialog("Name: ", "string");
        String Email = Data.getDialog("Email: ", "email");
        String Username = Data.getDialog("Username: ", "duser");
        String password = Data.getDialog("Password: ", "pass");
        new Driver(Name, Username, password, Email);
        Driver.saveInstances(Driver.instances, Driver.savedPath, Driver.csvHeader);
    }

}
