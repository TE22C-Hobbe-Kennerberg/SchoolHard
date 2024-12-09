import java.io.*;
import java.net.Socket;

// Connects to the server from the client.x
public class ServerConnection{
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
    public Object sendCommand(ServerCommandManager.Command command){
        // Serializes the command to a file
        ServerCommandManager scm = new ServerCommandManager();
        File file = scm.serialize(command);

        // Sends the file to the server.
        FileHelper fh = new FileHelper();
        try{
            fh.sendFile(file, socket.getOutputStream());
        }
        catch(Exception e){
            // Does not have to do anything.
        }
        return receiveResult();
    }

    // Waits for a response from the server.
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
