import java.io.Serializable;

public class Main {
    public static void main(String[] args) throws Exception{
        class Test implements Serializable {
            public int a = 1;
            public String b = "Hello!";
        }

        System.out.println();


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