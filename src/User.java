import org.json.JSONObject;

import java.io.Serializable;

public class User extends TableEntry {
    public String username;
    public String password;

    public User(String username, String password){
        this.username = username;
        this.password = password;
    }
}
