import org.json.JSONArray;

import java.io.*;
import java.util.*;


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

    private HashMap<String, HashMap<UUID, Table<? extends TableEntry>>> schemas = new HashMap<>();
    private HashMap<UUID, Table<? extends  TableEntry>> tables = new HashMap<>();



    // Gets all the tables storing the correct type and also contains the ALL keywords.
    public TableEntry getTable(String schema, String ... keywords){
        File[] files = new File(path + schema + "/").listFiles();


        return new User("H", "k");
    }

    // Gets data from a file.
    private JSONArray getData(File file){
        return new JSONArray();
    }

    // Creates a table and puts in the correct schema.
    public <T extends TableEntry> void createTable(String schema){
        Table<T> table = new Table<>(schema);
        //tables.put(table.uuid, table);
    }


    // Stores multiple data entries of type T inside a JSON file.
    protected class Table<T extends TableEntry> {
        // The file containing all data stored in the table.
        private File file;
        private UUID uuid;

        private ArrayList<T> contents;


        public Table(String schema){
            contents = new ArrayList<T>();
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

        // Searches through the table for any variable matching
        public ArrayList<T> search(String... keywords){
            ArrayList<T> result = new ArrayList<>();
            List<String> keywordList = Arrays.asList(keywords);
            // For every item.
            outerloop:
            for(T item : contents){

                int matchingKeywords = 0;

                // For every variable.
                ArrayList<Object> variables = item.getVariables();
                for(String keyword : keywords){
                    for(Object var : variables){
                        if(keyword.equals(var.toString())){
                            matchingKeywords++;

                            break;
                        }
                    }


                }
            }
            return new ArrayList<T>();

        }
    }
}




