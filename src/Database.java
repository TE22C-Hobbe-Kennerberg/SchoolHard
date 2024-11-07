import org.json.JSONArray;
import org.json.JSONObject;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.util.ArrayList;
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

    private HashMap<String, HashMap<UUID, Table<? extends Serializable>>> schemas = new HashMap<>();
    private HashMap<UUID, Table<? extends  Serializable>> tables = new HashMap<>();



    // Gets all the tables storing the correct type and also contains the ALL keywords.
    public JSONArray getTable(String schema, String ... keywords){
        File[] files = new File(path).listFiles();

        // Returns empty array if there are no files.
        if(files == null) return new JSONArray();

        // Loops through all the files.
        // Checks for type first, if it matches it looks for keywords
        JSONArray j = new JSONArray();
        for (File f : files){

        }
        return new JSONArray();
    }

    // Gets data from a file.
    private JSONArray getData(File file){
        return new JSONArray();
    }

    private JSONObject searchFile(String ... keywords){
        return new JSONObject();
    }

    public <T extends Serializable> void createTable(String schema){
        Table<T> table = new Table<>(schema);
        tables.put(table.uuid, table);
    }


    // Stores multiple data entries of type T inside a JSON file.
    protected class Table<T extends Serializable> {
        // The file containing all data stored in the table.
        private File file;
        private UUID uuid;

        private ArrayList<T> tableContents;


        public Table(String schema){
            tableContents = new ArrayList<T>();
            uuid = UUID.randomUUID();

            // REMOVE THIS
            uuid = UUID.fromString("08a20118-4735-4952-a94b-e8974f5c1515");
            try{
                file = new File(path +  uuid + fileType);
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


//        // Creates a JSONArray object with the corresponding data stored in the file.
//        private JSONArray retrieveData(){
//            String fileString;
//            JSONArray fileJSONArray;
//            try {
//                fileString = new String(Files.readAllBytes(file.toPath()));
//                fileJSONArray = new JSONArray(fileString);
//                return fileJSONArray;
//            } catch (OutOfMemoryError outOfMemoryError) {
//                // If the file is > 2GB in size.
//                System.out.println("YOU ARE USING THIS PROGRAM TOO HARD PLEASE STOP");
//                return null;
//            }catch (Exception e){
//                // If there is nothing to retrieve.
//                return null;
//            }
//        }
//
//
//        // Adds data to the JSON file. Uses JSONArray to be able to loop easier.
//        public <T> void add(T data) throws IOException {
//            JSONArray combinedData = new JSONArray();
//
//            // Loops through the tables file and adds old data to the new JSONArray.
//            JSONArray oldData = retrieveData();
//            if(oldData != null){
//                for(int i = 0; i < oldData.length(); i++){
//                    combinedData.put(oldData.get(i));
//                }
//            }
//
//            JSONObject newData = data.getJSONObject();
//            combinedData.put(newData);
//
//            FileWriter fileWriter = new FileWriter(file);
//            fileWriter.write(combinedData.toString());
//            fileWriter.flush();
//            fileWriter.close();
//        }
    }




}




