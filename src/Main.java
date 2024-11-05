

public class Main {
    public static void main(String[] args) {


        class Test<T>{

        }

        Test<String> t = new Test<>();

        System.out.printf(t.getClass().toString());



        //PasswordHelper pw = new PasswordHelper();
        //pw.getHash("testing");

        //ServerConnection server = ServerConnection.getInstance();
        //server.connect();

        //UserInterface ui = UserInterface.getInstance();
        //ui.startUI();

        //ServerConnection database = ServerConnection.getInstance();
        //database.connect();

    }
}