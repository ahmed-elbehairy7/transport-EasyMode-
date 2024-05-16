package behindTheScenes;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class Validations {

    public static boolean valid(String text, String type) {
        switch (type) {
            case "string":
                return validString(text);
            case "name":
                return validName(text);
            case "email":
                return validEmail(text);
            case "pass":
                return validPass(text);
            case "puser":
                return validUsername(text, Passenger.instances);
            case "muser":
                return validUsername(text, Manager.instances);
            case "duser":
                return validUsername(text, Driver.instances);
            case "tType":
                return validTripType(text);
            case "cycle":
                return validCycle(text);
            case "int":
                return validInt(text);
            default:
                return false;
        }
    }

    private static boolean validTripType(String text) {
        return text.equals("internal") || text.equals("external");
    }
    private static boolean validCycle(String text) {
        return text.equals("one-way") || text.equals("round-trip");
    }

    private static boolean validEmail(String email) {
        return Pattern.compile(
                "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9]))\\.){3}(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9])|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])")
                .matcher(email).matches();
    }
    
    private static boolean validPass(String pass) {
        return Pattern.compile(
                "(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$")
                .matcher(pass).matches();
    }

    private static boolean validUsername(String username, ArrayList<Savable> instances) {
        for (short i = 0; i < instances.size(); i++) {
            if (((User) instances.get(i)).username.equals(username)) {
                return false;
            }
        }
        return true;
    }

    private static boolean validName(String text) {
        return text.length() > 2;
    }

    private static boolean validString(String text) {
        return text.length() > 4;
    }

    private static boolean validInt(String text) {
        try {
            Integer.parseInt(text);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}