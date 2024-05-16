package gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import behindTheScenes.Ticket;
import behindTheScenes.Trip;
import behindTheScenes.User;

public class PassengerGui extends UserGui {
    private Button BookATicketButton, listTicketsButton, removeTicketButton;
    private TextArea outputArea;

    public PassengerGui(User passenger) {
        super("Passenger", passenger);
        setLayout(new BorderLayout());

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 1));

        //Book a ticket
        BookATicketButton = new Button("Book a ticket");
        BookATicketButton.addActionListener(e -> bookATicket());
        buttonPanel.add(BookATicketButton);

        //List tickets
        listTicketsButton = new Button("List tickets");
        listTicketsButton.addActionListener(e -> listTickets());
        buttonPanel.add(listTicketsButton);

        //Remove a ticket
        removeTicketButton = new Button("Remove a ticket");
        removeTicketButton.addActionListener(e -> removeTicket());
        buttonPanel.add(removeTicketButton);


        add(buttonPanel, BorderLayout.WEST);

        outputArea = new TextArea();
        JScrollPane scrollPane = new JScrollPane(outputArea);
        add(scrollPane, BorderLayout.CENTER);
        
    }

    private void bookATicket() {
        outputArea.setText("\n\n" + Trip._listInstances(Trip.instances));
        String ans = JOptionPane.showInputDialog("Type the index of the trip you want to select");

        int index = Integer.parseInt(ans);
        Trip trip = (Trip) Trip.instances.get(index);

        if (trip.seats == 0) {
            outputArea.setText(
                    "\n\nSorry, the requested trip doesn't have enough seats, please choose another one! \n\n\n\n");

            bookATicket();
            return;
        }

        outputArea.setText("\n\nHere's a detailed information about the selected trip:\n\n");
        outputArea.append(trip.toString(trip.allInfo()));

        /*
        When the passenger books a ticket (if there are available seats) he is shown a price for the selected ticket(s) and then proceeds to buy them.
        */

        if (JOptionPane.showConfirmDialog(this, "book the ticket?") == 0) {
            new Ticket(user.id, trip.id);
            Ticket.saveInstances(Ticket.instances, Ticket.savedPath, Ticket.csvHeader);
            ((Trip) Trip.instances.get(index)).seats -= 1;
            Trip.saveInstances(Trip.instances, Trip.savedPath, Trip.csvHeader);
            outputArea.setText("Your text had been booked successfully");
        }

    }

    private void listTickets() {
        outputArea.setText("Here's the available tickets\n\n");
        outputArea.append(Ticket._listTickets(user.id));

    }

    private void removeTicket() {
        
        listTickets();

        String ans = JOptionPane.showInputDialog("Please type the id of the ticket you want to delete");

        int id = Integer.parseInt(ans);
        
        for (short i = 0; i < Ticket.instances.size(); i++) {
            if (Ticket.instances.get(i).id == id) {
                Ticket.removeInstance(id, Ticket.instances, Ticket.savedPath, Ticket.csvHeader);
            }
        }

        listTickets();
    }
}
