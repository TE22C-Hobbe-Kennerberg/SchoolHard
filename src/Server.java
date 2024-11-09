import javax.xml.crypto.Data;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;

// Add error handling
// Receives and sends data to the client ServerConnection.
public class Server {
    private static final int PORT = 8989;

    public static void main(String[] args) throws  IOException{
        //startServer();

        initiateDatabase();
        //IncomingConnectionHandler test = IncomingConnectionHandler.getInstance();
        //test.allowConnection();

    }

    private static void initiateDatabase(){
        Database db = Database.getInstance();
        db.initiate();
    }

    public static void startServer(){

        Random random = new Random();
        try {
            System.out.println("Waiting for client connection.");
            // Listens for new incoming connections.
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

    private static class IncomingConnectionHandler{

        // Singleton pattern
        private static IncomingConnectionHandler instance;
        private IncomingConnectionHandler(){}
        public static IncomingConnectionHandler getInstance(){
            if(instance == null){
                instance = new IncomingConnectionHandler();
            }
            return instance;
        }

        // All connections.
        private ArrayList<Connection> connections = new ArrayList<Connection>();

        // Waits for a new connection and then adds it to the ArrayList with all connections.
        public void allowConnection() throws IOException {
            ServerSocket listener = new ServerSocket(PORT);

            while(true){
                connections.add(new Connection(listener.accept()));
            }
        }


        // Deletes the connection
        public void deleteConnection(Connection connection){
            System.out.println(connections.size());
            connections.remove(connection);
            System.out.println(connections.size());
        }

        private static class Connection extends Thread{
            private Socket socket;
            private BufferedReader input;
            private PrintWriter output;

            //
            public Connection(Socket socket) throws IOException {
                input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                output = new PrintWriter(socket.getOutputStream());
                this.socket = socket;
                start();
            }

            @Override
            public void run() {
                // Keeps getting input until the connection dies.
                while(true){
                    readInput();
                }
            }


            // Reads input from client and sends it to be handled.
            private void readInput(){
                try {
                    // Reads until "final" is read.
                    String in = input.readLine();

                } catch (IOException e) {
                    // If the connection dies.
                    close();
                }
            }

            private void handleInput(){

            }

            // Closes the connection and
            private void close(){
                try {
                    socket.close();
                } catch (IOException e) {
                   // Does not need to do anything.
                }
                // Removes it from ArrayList with connections.
                IncomingConnectionHandler ch = IncomingConnectionHandler.getInstance();
                ch.deleteConnection(this);

            }


            // Handles all incoming commands from clients.
            private void command(String command){

            }
        }
    }
}


// Add sending files to server.
// Add server/database storing files.
// Add searching database.
//
