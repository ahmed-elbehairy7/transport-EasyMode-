package behindTheScenes;

import java.util.ArrayList;

public class Ticket extends Savable {

    public static ArrayList<Savable> instances = new ArrayList<>();
    public final static String csvHeader = "id,passengerId,tripId";
    public final static String savedPath = "tickets.csv";
    public final static String className = "Ticket";    

    public int passengerId;
    public int tripId;
    
    public Ticket(int passengerId, int tripId) {
        this.id = generateId(Ticket.instances);
        this.passengerId = passengerId;
        this.tripId = tripId;

        Ticket.instances.add(this);
    }

    public Ticket(int id, int passengerId, int tripId) {
        this.id = id;
        this.passengerId = passengerId;
        this.tripId = tripId;

        Ticket.instances.add(this);
    }
    
    @Override
    public String toCsv() {
        return this.id + "," + this.passengerId + "," + tripId;
    }

    public static String _listTickets(int passengerId) {
        String text = "";
        Ticket ticket;
        for (short i = 0; i < instances.size(); i++) {
            ticket = (Ticket) instances.get(i);
            if (ticket.passengerId == passengerId) {
                text +=  ticket.toString() + "\n";
            }
        }

        return text;
    }

    public static void listTickets(int passengerId) {
        System.out.println(_listTickets(passengerId));
    }
    
    public String toString() {
        Trip trip;

        for (short i = 0; i < Trip.instances.size(); i++) {
            trip = (Trip) Trip.instances.get(i);
            if (trip.id == this.tripId) {
                return trip.toString("Ticket ID: " + this.id + "\nPassenger ID: " + this.passengerId + "\nTrip ID: "
                        + this.tripId + "\n\nTrip Info:\n---\n" + trip.info());
            }
        }
        return "";
    }

    public static void newInstance(String line) {
        String[] data = line.split(",");

        new Ticket(Integer.parseInt(data[0]), Integer.parseInt(data[1]),
                Integer.parseInt(data[2]));

        
        
    }

    public static void removeTicket(int id) {

        for (short i = 0; i < instances.size(); i++) {
            Ticket ticket = (Ticket) instances.get(i);
            if (ticket.id == id) {
                instances.remove(i);
                saveInstances(Ticket.instances, Ticket.savedPath, Ticket.csvHeader);
                Trip trip = (Trip) Trip.getById(ticket.tripId, Trip.instances);
                trip.seats++;
                saveInstances(Trip.instances, Trip.savedPath, Trip.csvHeader);
                
            }
        }

    }

    public static void initiateClass() {
        initiateClass(Ticket.savedPath, Ticket.csvHeader, Ticket.className, Ticket.instances);
    }

    
    
}
