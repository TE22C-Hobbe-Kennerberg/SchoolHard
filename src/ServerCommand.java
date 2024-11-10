import javax.xml.catalog.Catalog;
import java.util.ArrayList;

// A way for the client to send commands to the server with parameters.
public class ServerCommand {
    Database db = Database.getInstance();

    // Checks if a user exists.
    public boolean userExists(String username){
        ArrayList<TableEntry> search = db.search("users", username);
        return search.size() == 1;
    }

    // Verifies a hashed password for a user, send true if it matches database.
    public boolean verifyPassword(String username, String password){
        ArrayList<TableEntry> search = db.search("users", username, password);
        return search.size() == 1;
    }

    // Gets chat between 2 users. Can return null.
    public ArrayList<TableEntry> getChat(String user1, String user2){
        return db.search("chats", user1, user2);
    }

    // Creates a new chat.
    public void createChat(String user1, String user2){
        // If it already exists do not create.
        if(!db.search("chats", user1, user2).isEmpty()) return;

        db.createTable("chats", true);
        sendMessage(new Message("", user1, user2));
    }

    public void sendMessage(Message message){
        db.addToTable("chats", message, message.sender, message.receiver);
    }
    // Creates a new user.
    public void createUser(String username, String password){
        db.addToTable("users", new User(username, password));
    }
}
