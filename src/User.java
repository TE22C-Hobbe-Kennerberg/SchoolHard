import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

public class User extends TableEntry {
    public String username;
    public String password;
    public ArrayList<String> contactList;

    public User(String username, String password){
        this.username = username;
        this.password = password;
        this.contactList = new ArrayList<>();
    }

}
