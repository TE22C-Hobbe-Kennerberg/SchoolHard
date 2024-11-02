import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;


// Receives and sends data to the client ServerConnection.
public class Server {
    private static final int PORT = 8989;

    public static void main(String[] args) throws  IOException{
        //startServer();

        IncomingConnectionHandler test = IncomingConnectionHandler.getInstance();
        test.allowConnection();

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

    private static class IncomingConnectionHandler{

        private static IncomingConnectionHandler instance;
        private IncomingConnectionHandler(){}
        public static IncomingConnectionHandler getInstance(){
            if(instance == null){
                instance = new IncomingConnectionHandler();
            }
            return instance;
        }


        private ArrayList<Connection> connections = new ArrayList<Connection>();


        public void allowConnection() throws IOException {
            ServerSocket listener = new ServerSocket(PORT);

            while(true){
                System.out.println("Waiting for connection");
                connections.add(new Connection(listener.accept()));
                System.out.println("HERE!");

            }
        }

        private class Connection extends Thread{
            private Socket socket;
            private BufferedReader input;
            private PrintWriter output;

            boolean openConnection;
            public Connection(Socket socket) throws IOException {
                input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                output = new PrintWriter(socket.getOutputStream());
                this.socket = socket;
                start();
            }

            @Override
            public void run() {
                System.out.println("Connection accepted.");

                while(true){
                    try {
                        String data = input.readLine();
                        System.out.println("[Client]" + data);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }

            }
        }
    }

}

