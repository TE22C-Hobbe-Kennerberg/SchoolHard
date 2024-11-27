import java.io.Serializable;

public class Main {
    public static void main(String[] args) throws Exception{
        class Test implements Serializable {
            public int a = 1;
            public String b = "Hello!";
        }

        System.out.println("Connecting to server");
        ServerConnection srv = ServerConnection.getInstance();
        srv.connect();

        System.out.println("1");
        ServerCommand cmd = new ServerCommand();
        System.out.println("2");

        cmd.command = cmd.new CreateUser("Shobben", "123");
        System.out.println("3");

        cmd.send();
        System.out.println("4");

        cmd.command = cmd.new UserExists("Shobben");
        System.out.println("User exists: " +  cmd.send());




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