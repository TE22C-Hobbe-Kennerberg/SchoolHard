import java.util.ArrayList;

public class DatabaseConnection {
    public void createUser() {
        ServerConnection server = ServerConnection.getInstance();
        User user = new User("Test", "Test");
        server.sendData(user);

    }
}
