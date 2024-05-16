package gui;

import java.awt.GridLayout;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import behindTheScenes.Driver;
import behindTheScenes.Manager;
import behindTheScenes.Passenger;
import behindTheScenes.User;
import behindTheScenes.Validations;

public class Login extends Frame {

    private TextField usernameField;
    private JTextField passwordField;
    private String className;

    public Login(String text) {
        super("Login");
        className = text;

        setLayout(new GridLayout(3, 2));

        //username label
        Label usernameLabel = new Label("Username:");
        add(usernameLabel);

        //username field
        usernameField = new TextField();
        add(usernameField);

        // Password label
        Label passwordLabel = new Label("Password:");
        add(passwordLabel);

        //Password field
        passwordField = new JPasswordField();
        passwordField.setFont(new P1());
        add(passwordField);

        //Sign in button
        Button signInButton = new Button("Sign In");
        signInButton.addActionListener(e -> signIn());
        add(signInButton);

        //Sign up button
        Button signUp = new Button("New user? sign up?");
        signUp.addActionListener(e -> {
            dispose();
            new Register(className);
        });
        add(signUp);
    }

    private void signIn() {
        
        String username = usernameField.getText().trim();
        String password = passwordField.getText().trim();

        if (!(Validations.valid(username, "string") && Validations.valid(password, "pass"))) {
            return;
        }

        User user;
        switch (className) {
            case Passenger.className:
                user = Passenger.login(username, password, Passenger.instances);
                break;
            case Manager.className:
                user = Manager.login(username, password, Manager.instances);
                break;
            case Driver.className:
                user = Driver.login(username, password, Driver.instances);
                break;
            default:
                user = new User();
                break;
        }

        if (!user.success) {
            return;
        }

        switch (className) {
            case Passenger.className:
                dispose();
                new PassengerGui(user);
                break;
            case Manager.className:
                dispose();
                new ManagerGui(user);
                break;
            case Driver.className:
                dispose();
                new DriverGui(user);
                break;
        }
        

        
        
    }
}