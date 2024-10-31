import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ServerConnection {
    public static final String SERVER_IP = "127.0.0.1";
    public static final int SERVER_PORT = 8989;
    // Singleton pattern
    private static ServerConnection instance;
    private ServerConnection(){}

    public static ServerConnection getInstance() {
        if(instance == null){
            instance = new ServerConnection();
        }
        return instance;
    }

    public void connect(){
        try{
            Socket socket = new Socket(SERVER_IP, SERVER_PORT);
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String response = reader.readLine();

            System.out.println(response);

            socket.close();
        }
        catch(IOException e){
            System.out.println("Could not connect to server. Please restart the program.");
            System.exit(0);
        }

    }

    //public void query<>("")
}
