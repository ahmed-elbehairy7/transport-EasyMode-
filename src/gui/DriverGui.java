package gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import behindTheScenes.Trip;
import behindTheScenes.User;

public class DriverGui extends UserGui {
    private Button personalInfoButton, listAssignedTripsButton;
    private TextArea outputArea;

    public DriverGui(User Driver) {
        super("Driver", Driver);
        setLayout(new BorderLayout());

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2, 1));

        //Personal info
        personalInfoButton = new Button("Personl info");
        personalInfoButton.addActionListener(e -> personalInfo());
        buttonPanel.add(personalInfoButton);

        //List assigned trips
        listAssignedTripsButton = new Button("List assigned trips");
        listAssignedTripsButton.addActionListener(e -> listAssignedTrips());
        buttonPanel.add(listAssignedTripsButton);

        add(buttonPanel, BorderLayout.WEST);

        outputArea = new TextArea();
        JScrollPane scrollPane = new JScrollPane(outputArea);
        add(scrollPane, BorderLayout.CENTER);

    }
    
    private void personalInfo() {
        outputArea.setText(user.toString());
    }

    private void listAssignedTrips() {
        outputArea.setText("\n\nHere's the trips assigned to you: \n");
        for (short i = 0; i < Trip.instances.size(); i++) {
            Trip trip = (Trip) Trip.instances.get(i);
            if (user.id ==trip.driverId) {
                outputArea.append(trip.toString(trip.info()));
            }
        }
    }

}
