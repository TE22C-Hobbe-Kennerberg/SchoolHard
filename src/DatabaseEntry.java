import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.UUID;
import org.json.JSONObject;

// Class for everything that is supposed to be stored in the database.
public abstract class DatabaseEntry {
    // Creates an uuid for each entry in the database so each entry can be identified.
    public final UUID uuid = UUID.randomUUID();

    // Forces every child to implement a serialization method to send to database.
    public abstract JSONObject serialize();


    // Returns a file containing the current the value of variables that are serialized in the serialize function.
    public File convertToJSON(){
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
