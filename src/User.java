import org.json.JSONObject;

public class User extends Serializable {
    public String username;
    public String password;

    @Override protected JSONObject serialize(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("username", username);


        return jsonObject;
    }
}
