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
        ServerCommandManager scm = new ServerCommandManager();

        System.out.println("2");
        ServerCommandManager.Command command;
        command = scm.new CreateUser("Shobben", "123");

        System.out.println("3");
        scm.send(command);

        System.out.println("4");
        command = scm.new UserExists("Shobben");

        command = scm.new UserExists("Shobben");
        System.out.println("User exists: " +  scm.send(command));




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