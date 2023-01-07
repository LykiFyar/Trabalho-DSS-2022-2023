import ui.F1ManagerUI;

public class Main {
    public static void main(String[] args) {
        try {
            new F1ManagerUI().run();
        }
        catch (Exception e) {
            System.out.println("Não foi possível arrancar: "+e.getMessage());
        }
    }
}
