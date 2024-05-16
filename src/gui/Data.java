package gui;

import javax.swing.JOptionPane;

import behindTheScenes.Validations;

public class Data {

    public static void main() {

    }

    public static String getDialog(String message, String type) {
        String data;
        do {
             data = JOptionPane.showInputDialog(message);
         } while (!Validations.valid(data, type));
         
         return data;
    }
}
