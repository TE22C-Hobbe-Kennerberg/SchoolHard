public class Main {
    public static void main(String[] args) {
        //UserInterface ui = UserInterface.getInstance();
        //ui.startUI();

        ServerConnection database = ServerConnection.getInstance();
        database.connect();

    }
}