package gui;

import javax.swing.JLabel;

public class Label extends JLabel {
    public Label(String text) {
        super(text);
        // this.setSize(150, 30);
        setFont(new P1());
        setHorizontalTextPosition(CENTER);
        setVerticalTextPosition(CENTER);
    }
}
