import java.util.ArrayList;

// A way for the client to send commands to the server with parameters.
public class ServerCommandHelper {
    Database db = Database.getInstance();

    Command command;

    // Stores the command
    public class Command{
        public Object runCommand(){
            return new Object();
        }
    }

    // Runs the created command.
    public void execute(){
        sendResult(command.runCommand());
    }

    private void sendResult(Object result){

    }

    // Classes that represents a command for easy transfer between client and server.

    // Checks if a user exists.
    public class UserExists extends Command{
        String username;
        Object result;

        public UserExists(String username){
            this.username = username;
        }

        public boolean runCommand(String username) {
            ArrayList<TableEntry> search = db.search("users", username);
            return search.size() == 1;
        }
    }

    // Verifies a hashed password for a user, send true if it matches database.
    public class VerifyPassword extends Command{
        public boolean runCommand(String username, String password){
            ArrayList<TableEntry> search = db.search("users", username, password);
            return search.size() == 1;
        }
    }

    // Gets chat between 2 users. Can return null.
    public class GetChat extends Command{
        public ArrayList<TableEntry> runCommand(String user1, String user2){
            return db.search("chats", user1, user2);
        }
    }

    // Creates a new chat.
    public class CreateChat extends Command{
        public void runCommand(String user1, String user2){
            // If it already exists do not create.
            if(!db.search("chats", user1, user2).isEmpty()) return;

            // Create a table and sends a message so it can be found later.
            db.createTable("chats", true);
            ServerCommandHelper.this.new SendMessage().runCommand(new Message("", user1, user2));
        }
    }

    // Sends a message.
    public class SendMessage extends Command{
        public void runCommand(Message message){
            db.addToTable("chats", message, message.sender, message.receiver);
        }
    }

    // Creates a new user.
    public class CreateUser extends Command{
        public void runCommand(String username, String password){
            db.addToTable("users", new User(username, password));
        }
    }

}
