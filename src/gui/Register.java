package gui;

import java.awt.GridLayout;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import behindTheScenes.Driver;
import behindTheScenes.Manager;
import behindTheScenes.Passenger;
import behindTheScenes.User;
import behindTheScenes.Validations;

public class Register extends Frame {

    private TextField usernameField, nameField, emailField;
    private JTextField passwordField;
    private String className;

    public Register(String text) {
        super("Sign Up");
        className = text;

        setLayout(new GridLayout(5, 2));

        //name label
        Label nameLabel = new Label("name:");
        add(nameLabel);

        //name field
        nameField = new TextField();
        add(nameField);

        //username label
        Label usernameLabel = new Label("Username:");
        add(usernameLabel);

        //username field
        usernameField = new TextField();
        add(usernameField);

        //email label
        Label emailLabel = new Label("email:");
        add(emailLabel);

        //email field
        emailField = new TextField();
        add(emailField);

        // Password label
        Label passwordLabel = new Label("Password:");
        add(passwordLabel);

        //Password field
        passwordField = new JPasswordField();
        passwordField.setFont(new P1());
        add(passwordField);

        //Sign in button
        Button signUpButton = new Button("Sign Up");
        signUpButton.addActionListener(e -> signUp());
        add(signUpButton);

        //Sign up button
        Button LoginButton = new Button("Already have an account? Login?");
        LoginButton.addActionListener(e -> {
            dispose();
            new Login(className);
        });
        add(LoginButton);
    }

    private void signUp() {
        
        String username = usernameField.getText().trim();
        String password = passwordField.getText().trim();
        String email = emailField.getText().trim();
        String name = nameField.getText().trim();

        if (!(Validations.valid(password, "pass") && Validations.valid(email, "email") && Validations.valid(name, "name"))) {
            return;
        }

        User user;
        switch (className) {
            case Passenger.className:
                if (!Validations.valid(username, "puser")) {
                    return;
                }
                user = Passenger.register(name, email, username, password, Passenger.instances, Passenger.savedPath, Passenger.className);
                break;
            case Manager.className:
            if (!Validations.valid(username, "muser")) {
                    return;
                }
                user = Manager.register(name, email, username, password, Manager.instances, Manager.savedPath, Manager.className);
                break;
            case Driver.className:
            if (!Validations.valid(username, "duser")) {
                    return;
                }
                user = Driver.register(name, email, username, password, Driver.instances, Driver.savedPath, Driver.className);
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