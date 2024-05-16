import gui.MainFrame;
import cli.MainFlow;

public class Main {
    public static void main(String[] args) {
        
        if (args.length == 0) {
            cli();
            return;
        }
        if (args[0].equals("-g") || args[0].equals("--gui")) {
            gui();
            return;
        }
        System.out.println("Usage: java Main [--gui] [-cp path] | [-h]\n\n\nOptions:\n-g, --gui\t start the program in gui mode\n-cp,\t<path>\t path to dist folder where Main.class exists\n\n-h, --help\t show help message");
    }

    private static void gui() {

        new MainFrame();
    }
    
    private static void cli() {

        new MainFlow();
    }
}