import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class Chat extends TableEntry{
    public String user1;
    public String user2;
    public ArrayList<String> messages;

    public Chat(String user1, String user2){
        this.user1 = user1;
        this.user2 = user2;

    }

    public void sendMessages(String message, String sender){
        String fixedMessage = "[" + sender + "] " + message;
        messages.add(fixedMessage);
    }

}
