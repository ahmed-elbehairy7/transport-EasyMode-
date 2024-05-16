package gui;

import java.awt.GridLayout;

import javax.swing.JDialog;
import javax.swing.JFrame;

public class Dialog extends JDialog {
    
    public Dialog(JFrame parent, String title) {
        super(parent, title, true);
        setSize(300, 150);
        setLocationRelativeTo(parent);
        setLayout(new GridLayout(3, 2));
    }
}
