import java.io.*;
import java.net.Socket;
import java.util.Scanner;

// A way of connecting to the server.
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

            while(true){
                Scanner scanner = new Scanner(System.in);
                String input = scanner.nextLine();

                PrintWriter sender = new PrintWriter(socket.getOutputStream(), true);
                sender.println(input);
                if(input.equals("quit")){
                    break;
                }
            }

            socket.close();
        }
        catch(IOException e){
            System.out.println("Could not connect to server. Please restart the program.");
            System.exit(0);
        }

    }

    public <T> void  sendData(T data) {
        System.out.println();
    }
}
