import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;


// Receives and sends data to the client ServerConnection.
public class Server {
    private static final int PORT = 8989;

    public static void main(String[] args) throws  IOException{
        startServer();
        //Database db = Database.getInstance();
        //db.start();
    }

    public static void startServer(){

        Random random = new Random();
        try {
            System.out.println("Waiting for client connection.");
            ServerSocket listener = new ServerSocket(PORT);

            Socket client = listener.accept();
            BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
            System.out.println("Connected.");
            while(true){
                String receivedData = reader.readLine();
                if(receivedData.equals("exit")) break;
            }

            client.close();
            listener.close();
        }
        catch (IOException e){
            System.out.println("Could not open stream");
            System.exit(0);
        }
    }

    private class IncomingConnectionHandler{



        private class Connection implements Runnable{
            @Override
            public void run() {

            }
        }
    }

}

