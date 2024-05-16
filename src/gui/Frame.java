package gui;

import java.awt.Color;
import javax.swing.JFrame;

public class Frame extends JFrame {
    
    public Frame(String title) {
        setTitle(title);
        start();
    }
    public Frame() {
        setTitle("Transport Company App");
        start();
    }

    public void start() { 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(720, 720);
        getContentPane().setBackground(Color.WHITE);
        setVisible(true);
    }
}