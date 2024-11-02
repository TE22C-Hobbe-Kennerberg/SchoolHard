import org.json.JSONObject;

public class User extends Serializable {
    public String username;
    public String password;

    public User(String username, String password){
        this.username = username;
        this.password = password;
    }

    @Override protected JSONObject serialize(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("username", username);
        jsonObject.put("password", password);


        return jsonObject;
    }
}
