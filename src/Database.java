import org.json.JSONArray;
import org.json.JSONObject;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.UUID;


public class Database {
    private final String path = "./db_data/";
    private final String fileType = ".json";

    private static Database instance;
    private Database(){};
    public static Database getInstance(){
        if(instance == null){
            instance = new Database();
        }
        return instance;
    }

    private HashMap<String, Table> tables = new HashMap<String, Table>();

    public void createTable(){

    }










    // Stores multiple data entries of type T inside a JSON file.
    protected class Table<T extends Serializable> {
        // The file containing all data stored in the table.
        private File file;
        private UUID uuid;

        public Table(){
            uuid = UUID.randomUUID();

            // REMOVE THIS
            uuid = UUID.fromString("08a20118-4735-4952-a94b-e8974f5c1515");
            try{
                file = new File(path + uuid + fileType);
                // If the file does not exist it creates a new empty file with the uuid as name.
                if(!file.exists()){
                    FileWriter fileWriter = new FileWriter(file, true);
                    fileWriter.flush();
                    fileWriter.close();
                }
            } catch(Exception e){
                System.out.println("Could not create table file. Please configure application permissions.");
            }
        }

        private JSONArray getTableArray() throws IOException, SecurityException {
            String fileString;
            JSONArray fileJSONArray;
            try {
                fileString = new String(Files.readAllBytes(file.toPath()));
                fileJSONArray = new JSONArray(fileString);
                return fileJSONArray;
            } catch (OutOfMemoryError outOfMemoryError) {
                System.out.println("YOU ARE USING THIS PROGRAM TOO HARD PLEASE STOP");
                return null;
            }
        }

        // Adds data to the JSON file. Uses JSONArray to be able to loop easier.
        public void add(T data) throws IOException {
            JSONArray combinedData = new JSONArray();

            try{
                // Loops through the current file and adds it to the new JSONArray.
                JSONArray oldData = getTableArray();
                for(int i = 0; i < oldData.length(); i++){
                    combinedData.put(oldData.get(i));
                }
            }catch (Exception e){
                // Add something here??
            }

            JSONObject newData = data.getJSONObject();
            combinedData.put(newData);

            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(combinedData.toString());
            fileWriter.flush();
            fileWriter.close();
        }
    }

    public void start() throws IOException{
        JSONObject json = new JSONObject();
    }


}




