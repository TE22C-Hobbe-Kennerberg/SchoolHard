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
    private Socket socket;
    public static ServerConnection getInstance() {
        if(instance == null){
            instance = new ServerConnection();
        }
        return instance;
    }

    public void connect(){
        try{
            socket = new Socket(SERVER_IP, SERVER_PORT);

        }
        catch(IOException e){
            System.out.println("Could not connect to server. Please restart the program.");
            System.exit(0);
        }

    }

    // Sends a command and returns the result.
    public Object sendCommand(ServerCommand command){
        File file = command.serialize();
        FileHelper fh = new FileHelper();
        try{
            fh.sendFile(file, socket.getOutputStream());
        }
        catch(Exception e){
            // Does not have to do anything.
        }
        return receiveResult();
    }

    public Object receiveResult(){
        FileHelper fh = new FileHelper();
        try {
            File file = fh.recieveFile(socket.getInputStream());
            return fh.readObjectFromFile(file);
        } catch (IOException e) {
            // Does not have to do anything.
            return null;
        }
    }



}
