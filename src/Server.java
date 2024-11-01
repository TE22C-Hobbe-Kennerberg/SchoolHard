import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class Server {
    private static final int PORT = 8989;

    public static void main(String[] args) throws  IOException{
        //startServer();
        Database db = Database.getInstance();
        db.start();
    }

    public static void startServer(){

        Random random = new Random();
        try {
            System.out.println("Waiting for client connection.");
            ServerSocket listener = new ServerSocket(PORT);

            Socket client = listener.accept();
            System.out.println("Connected.");

            PrintWriter out = new PrintWriter(client.getOutputStream(), true);
            out.println("Random number: " + random.nextInt(0, 101));
            System.out.println("Sent data.");

            client.close();
            listener.close();
            System.out.println("Closed connection.");

        }
        catch (IOException e){
            System.out.println("Could not open stream");
            System.exit(0);
        }
    }
}

