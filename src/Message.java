import java.util.Date;

public class Message extends TableEntry{
    public String message;
    public String sender;
    public String receiver;
    public Date timeStamp;

    public Message(String message, String sender, String receiver){
        this.message = message;
        this.sender = sender;
        this.receiver = receiver;
        timeStamp = new Date();
    }
}