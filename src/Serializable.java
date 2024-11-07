import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.UUID;
import org.json.JSONObject;



// Class for everything that is supposed to be stored in the database.
public abstract class Serializable {

    // What is being serialized so it can be identified by database.
    public enum Type{
        USER,
        MESSAGE
    }


    // Creates an uuid for each entry in the database so each entry can be identified.
    public final UUID uuid = UUID.randomUUID();

    // Forces every child to implement a serialization method to send to database.
    protected abstract JSONObject serialize();
    public final Type type;

    public Serializable(){
        this.type = forceSetType();
    }

    // Forces an assigning of type.
    protected abstract Type forceSetType();


    // Returns a JSONObject with the key set as the uuid.
    // The value is another JSONObject containing all data.
    // So each entry will have its own uuid.
    public JSONObject getJSONObject(){
        JSONObject data = serialize();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(uuid.toString(), data);
        return jsonObject;
    }


    // Returns a file containing the current value of variables that are serialized in the serialize function.
    public File getJSONFile(){
        // Puts the UUID into the JSONObject.
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("UUID", uuid);

        // Puts all data from the serialized variables into the JSONObject.
        JSONObject serializedVariables = serialize();
        jsonObject.put("data", serializedVariables);

        // Writes to the file that will be returned.
        File file;
        try{
            file = new File(uuid.toString() + ".json");
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(jsonObject.toString());

            fileWriter.flush();
            fileWriter.close();
            return file;
        }
        catch (IOException e){
            System.out.println("File can not be created. Please fix program permissions.");
            System.exit(-1);
            return null;
        }
    }




}
