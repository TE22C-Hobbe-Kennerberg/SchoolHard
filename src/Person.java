import org.json.JSONObject;

public abstract class Person extends DatabaseEntry {
    public String firstName;
    public String lastName;

    @Override protected JSONObject serialize(){
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("firstName", firstName);
        jsonObject.put("lastName", lastName);

        return jsonObject;
    }
}
