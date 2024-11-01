import org.json.JSONObject;

public class Student extends Person {
    int startYear;
    public Student(String firstName, String lastName, int startYear){
        this.firstName = firstName;
        this.lastName = lastName;
        this.startYear = startYear;
    }

    @Override protected JSONObject serialize(){
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("firstName", firstName);
        jsonObject.put("lastName", lastName);
        jsonObject.put("startYear", startYear);

        return jsonObject;
    }
}