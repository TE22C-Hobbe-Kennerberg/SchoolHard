import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;

// Add error handling
// Receives and sends data to the client ServerConnection.
public class Server {
    private static final int PORT = 8989;

    public static void main(String[] args) throws IOException{
        initiateDatabase();
        startServer();

        ServerCommand ch = new ServerCommand();
        ch.command = ch.new UserExists("");


        //IncomingConnectionHandler test = IncomingConnectionHandler.getInstance();
        //test.allowConnection();

    }

    private static void initiateDatabase(){
        Database db = Database.getInstance();
        db.initiate();
    }

    public static void startServer(){
        try {
            IncomingConnectionHandler.allowConnection();
        } catch (IOException e) {
            throw new RuntimeException(e);
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
        static private ArrayList<Connection> connections = new ArrayList<>();

        // Waits for a new connection and then adds it to the ArrayList with all connections.
        public static void allowConnection() throws IOException {
            ServerSocket listener = new ServerSocket(PORT);

            while(true){
                System.out.println("Waiting for client connection");
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
                // Keeps reading commands until the connection dies.
                while(true){
                    receiveCommand();
                }
            }


            // Reads input from client and sends it to be handled.
            private void receiveCommand(){
                try {
                    FileHelper fh = new FileHelper();

                    ServerCommand command = (ServerCommand) fh.readObjectFromFile(fh.recieveFile(socket.getInputStream()));
                    sendResult(command.execute());

                } catch (IOException e) {
                    // If the connection dies.
                    close();
                }
            }

            private void sendResult(Object result){
                try {
                    FileHelper fh = new FileHelper();
                    File file = new File("./temp.ser");
                    fh.sendFile(file, socket.getOutputStream());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
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
        }
    }
}