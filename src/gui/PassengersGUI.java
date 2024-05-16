package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PassengersGUI extends JFrame {
    private JButton selectTripButton, reviewTicketButton, cancelTicketButton, viewInfoButton;
    private JTextArea outputTextArea;

    public PassengersGUI() {
        setTitle("Passenger Menu");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 1));

        selectTripButton = new JButton("Select Trip");
        selectTripButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                selectTrip();
            }
        });
        buttonPanel.add(selectTripButton);

        reviewTicketButton = new JButton("Review Ticket");
        reviewTicketButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                reviewTicket();
            }
        });
        buttonPanel.add(reviewTicketButton);

        cancelTicketButton = new JButton("Cancel Ticket");
        cancelTicketButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cancelTicket();
            }
        });
        buttonPanel.add(cancelTicketButton);

        viewInfoButton = new JButton("View Info");
        viewInfoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                viewInfo();
            }
        });
        buttonPanel.add(viewInfoButton);

        add(buttonPanel, BorderLayout.WEST);

        outputTextArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(outputTextArea);
        add(scrollPane, BorderLayout.CENTER);
    }

    private void selectTrip() {
        String tripsFilePath = "Trips.txt";
        String passengerTripFilePath = "Ticket.txt";
        try {
            BufferedReader reader = new BufferedReader(new FileReader(tripsFilePath));
            List<String> trips = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                trips.add(line);
            }
            reader.close();
            String[] tripOptions = new String[trips.size()];
            for (int i = 0; i < trips.size(); i++) {
                tripOptions[i] = trips.get(i);
            }
            String choice = (String) JOptionPane.showInputDialog(null, "Select Your Trip:", "Select Trip",
                    JOptionPane.QUESTION_MESSAGE, null, tripOptions, tripOptions[0]);
            if (choice != null) {
                String name = JOptionPane.showInputDialog("For more security, enter your Ticket name as you like:");
                FileWriter writer = new FileWriter(passengerTripFilePath, true);
                writer.write("The Ticket name:" + name + "," + choice + "\n");
                writer.close();
                outputTextArea.append("Your trip has been recorded\n");
            } else {
                outputTextArea.append("No trip selected\n");
            }
        } catch (IOException e) {
            outputTextArea.append("An error occurred while reading or writing the files: " + e.getMessage() + "\n");
        }
    }

    private void reviewTicket() {
        String ticketsFilePath = "Ticket.txt";
        String tripName = JOptionPane.showInputDialog("Enter your Ticket name to review:");
        boolean found = false;
        try (BufferedReader reader = new BufferedReader(new FileReader(ticketsFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains("The Ticket name:" + tripName)) {
                    outputTextArea.append("Your Ticket:\n" + line + "\n");
                    found = true;
                    break;
                }
            }
            if (!found) {
                outputTextArea.append("Your Ticket not found\n");
            }
        } catch (IOException e) {
            outputTextArea.append("An error occurred while reading the file: " + e.getMessage() + "\n");
        }
    }

    private void cancelTicket() {
        String TicketName = JOptionPane.showInputDialog("Enter your Ticket name to cancel:");
        String fileName = "Ticket.txt";
        File inputFile = new File(fileName);
        File tempFile = new File(inputFile.getAbsolutePath() + ".tmp");
        boolean isTripFound = false;
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
                if (currentLine.contains("The Ticket name:" + TicketName)) {
                    isTripFound = true;
                    for (int i = 0; i < 6; i++) {
                        if (reader.readLine() == null) break;
                    }
                } else {
                    writer.write(currentLine);
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            outputTextArea.append("An error occurred: " + e.getMessage() + "\n");
        }
        if (isTripFound) {
            if (!inputFile.delete()) {
                outputTextArea.append("Could not delete the original file\n");
            }
            if (!tempFile.renameTo(inputFile)) {
                outputTextArea.append("Could not rename the temp file\n");
            } else {
                outputTextArea.append("Ticket canceled successfully\n");
            }
        } else {
            outputTextArea.append("This trip not found\n");
            if (!tempFile.delete()) {
                outputTextArea.append("Could not delete the temp file\n");
            }
        }
    }

    private void viewInfo() {
        String userName = JOptionPane.showInputDialog("Enter your username:");
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("Passengers.txt"))) {
            String line;
            boolean found = false;
            while ((line = bufferedReader.readLine()) != null) {
                String[] userData = line.split(",");
                if (userData.length > 2 && userData[2].trim().equals(userName.trim())) {
                    outputTextArea.append("Passenger Name: " + userData[0].trim() + "\n");
                    outputTextArea.append("Passenger Email: " + userData[1].trim() + "\n");
                    outputTextArea.append("Passenger Username: " + userData[2].trim() + "\n");
                    found = true;
                    break;
                }
            }
            if (!found) {
                outputTextArea.append("User not found\n");
            }
        } catch (IOException e) {
            outputTextArea.append("An error occurred while reading the file: " + e.getMessage() + "\n");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new PassengersGUI().setVisible(true);
            }
        });
    }
}