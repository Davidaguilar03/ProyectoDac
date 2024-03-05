import co.edu.uptc.controllers.*;;

public class App {
    public static void main(String[] args) throws Exception {
        Control control = new Control();
        control.run(Integer.parseInt(args[0]),Integer.parseInt(args[1]),args[2],args[3].charAt(0));
        
    }
}
