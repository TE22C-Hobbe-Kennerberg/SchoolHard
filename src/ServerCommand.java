// A way for the client to send commands to the server with parameters.
public class ServerCommand {
    Database db = Database.getInstance();

    // Checks if a user exists.
    public boolean userExists(String username){
        db.getTable(username);
        return false;
    }

    // Verifies password for a user, send true if it matches database.
    public boolean verifyPassword(String username, String password){
        return db.getTable(username, password) != null;
    }

    // Gets chat between 2 users.
    public Chat getChat(String user1, String user2){

        return new Chat();
    }

    // Creates a new chat.
    public void createChat(String user1, String user2){

    }

    // Creates a new user.
    public void createUser(String username, String password){

    }
}
