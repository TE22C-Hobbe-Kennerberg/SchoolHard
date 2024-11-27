import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;

// A way for the client to send commands to the server with parameters.
public class ServerCommandManager implements Serializable{
    Database db = Database.getInstance();
    ServerConnection srv = ServerConnection.getInstance();

    // Stores the command
    public class Command implements Serializable{
        public Object runCommand(){
            return new Object();
        }
    }

    // Sends the command to the server and returns the result.
    public Object send(Command command){
        System.out.println("here");

        return srv.sendCommand(command);
    }

    // Runs the created command and returns the result.
    public Object execute(Command command){
        return command.runCommand();
    }

    // Serializes the command into a temporary file.
    public File serialize(Command command){
        File file = new File("./temp.ser");
        FileHelper fh = new FileHelper();
        fh.writeObjectToFile(command, file);
        return file;
    }

    // Classes that represents a command for easy transfer between client and server.

    // Checks if a user exists.
    public class UserExists extends Command{
        private String username;

        public UserExists(String username){
            this.username = username;
        }

        public Boolean runCommand() {
            ArrayList<TableEntry> search = db.search("users", username);
            return search.size() == 1;
        }
    }

    // Verifies a hashed password for a user, send true if it matches database.
    public class VerifyPassword extends Command{
        private String username;
        private String password;

        public VerifyPassword(String username, String password){
            this.username = username;
            this.password = password;
        }

        public Boolean runCommand(){
            ArrayList<TableEntry> search = db.search("users", username, password);
            return search.size() == 1;
        }
    }

    // Gets chat between 2 users. Can return null.
    public class GetChat extends Command{
        private String user1;
        private String user2;

        public GetChat(String user1, String user2){
            this.user1 = user1;
            this.user2 = user2;
        }

        public ArrayList<TableEntry> runCommand(){
            return db.search("chats", user1, user2);
        }
    }

    // Creates a new chat.
    public class CreateChat extends Command{
        private String user1;
        private String user2;

        public CreateChat(String user1, String user2){
            this.user1 = user1;
            this.user2 = user2;
        }

        // Acts as void
        public Object runCommand(){
            // If it already exists do not create.
            if(!db.search("chats", user1, user2).isEmpty()) return null;

            // Create a table and sends a message so it can be found later.
            db.createTable("chats");
            ServerCommandManager.this.new SendMessage(new Message("", user1, user2)).runCommand();
            return null;
        }
    }

    // Sends a message.
    public class SendMessage extends Command{
        Message message;

        public SendMessage(Message message){
            this.message = message;
        }

        // Acts as void
        public Object runCommand(){
            db.addToTable("chats", message, message.sender, message.receiver);
            return null;
        }
    }

    // Creates a new user.
    public class CreateUser extends Command{
        String username;
        String password;

        public CreateUser(String username, String password){
            this.username = username;
            this.password = password;
        }

        public void runCommand(String username, String password){
            db.addToTable("users", new User(username, password));
        }
    }
}
