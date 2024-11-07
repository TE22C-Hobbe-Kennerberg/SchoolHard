import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;


public class DatabaseCopy {
    private final String path = "./db_data/";

    private static DatabaseCopy instance;
    private DatabaseCopy(){};
    public static DatabaseCopy getInstance(){
        if(instance == null){
            instance = new DatabaseCopy();
        }
        return instance;
    }

    private HashMap<String, HashMap<UUID, Table<? extends Serializable>>> schemas = new HashMap<>();
    private HashMap<UUID, Table<? extends  Serializable>> tables = new HashMap<>();


    public void loadAllTables(){

    }

    public void close(){
        for(Table<? extends Serializable> table : tables.values()){
            // Close all streams.
        }
    }
//    // Gets all the tables storing the correct type and also contains the ALL keywords.
//    public JSONArray getTable(String schema, String ... keywords){
//        for()
//    }



    public <T extends Serializable> void createTable(String schema){
        Table<T> table = new Table<>(schema);
        tables.put(table.uuid, table);
    }


    // Stores multiple data entries of type T inside a JSON file.
    protected class Table<T extends Serializable> {
        // The file containing all data stored in the table.
        private File file;
        private UUID uuid;
        private ArrayList<T> contents;

        private boolean loaded;

        public Table(String schema){
            contents = new ArrayList<T>();
            uuid = UUID.randomUUID();

            // REMOVE THIS
            uuid = UUID.fromString("08a20118-4735-4952-a94b-e8974f5c1515");
            try{
                file = new File(path +  uuid + ".ser" );
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

        private void readFromFile(){

        }

        private void writeToFile(){
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                ObjectOutputStream out = new ObjectOutputStream(fileOutputStream);

               // out.write(contents);

                fileOutputStream.close();
                out.close();
            }catch (IOException e){
                System.out.println("Could not create file. Please fix application permissions.");
            }

        }

        protected void close(){

        }

    }




}




