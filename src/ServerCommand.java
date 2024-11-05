import org.json.JSONArray;

// Gets input from Server and executes functions depending on input.
public class ServerCommand {
    Database db = Database.getInstance();

    // Checks if a user exists.
    public boolean userExists(String username){
        db.getTable(username);
        return false;
    }

    // Verifies password for a user, send true if it matches database.
    public boolean verifyPassword(String username, String password){
        return db.getTable(username, password).length() > 0;
    }

    // Gets chat between 2 users.
    public JSONArray getChat(String user1, String user2){
        JSONArray data = db.getTable(user1, user2);
        return new JSONArray();
    }

    // Creates a new chat.
    public void createChat(){

    }

    // Creates a new user.
    public void createUser(){

    }
}
