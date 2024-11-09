import java.io.*;
import java.lang.reflect.Field;
import java.util.*;


public class Database {
    private final String path = "./db_data/";
    private final String fileType = ".ser";

    private static Database instance;
    private Database(){};
    public static Database getInstance(){
        if(instance == null){
            instance = new Database();
        }
        return instance;
    }

    private HashMap<String, HashMap<UUID, Table>> schemas = new HashMap<>();

    // Loads all the tables with data.
    public void initiate(){
        createFolder();
        createRequiredSchemas();

        File[] schemaFiles = new File(path).listFiles();
        if(schemaFiles == null) return;

        for(File schema : schemaFiles){
            String schemaName = schema.getName();
            schemas.put(schemaName, new HashMap<>());


            // Gets current schema folder.
            File[] tableFiles = new File(path + schemaName + "/").listFiles();

            // If the schema folder is empty for some reason.
            if(tableFiles == null) continue;

            // For every table file in the current schema folder.
            for(File table : tableFiles){
                // Creates a new table without and loads the data from file.
                Table tempTable = new Table(schemaName, false);
                tempTable.initiate();

                schemas.get(schemaName).put(tempTable.uuid, tempTable);
            }
        }
    }

    // Creates the folder for the database data according to path variable.
    private void createFolder(){
        new File(path).mkdir();
    }
    private void createRequiredSchemas(){
        createSchema("users");
        createSchema("chats");
    }

    // Creates a schema if it does not already exist.
    private void createSchema(String name){
        boolean schemaExists = new File(path + name).mkdir();

        if(!schemaExists){
            schemas.put(name, new HashMap<>());
            System.out.println("1");
        }

    }

    // Gets all the tables storing the correct type and also contains the ALL keywords.
    public TableEntry getTable(String schema, String ... keywords){
        File[] files = new File(path + schema + "/").listFiles();

        return new User("H", "k");
    }


    // Stores multiple data entries of type T inside a JSON file.
    protected class Table {
        // The file containing all data stored in the table.
        private File file;
        private UUID uuid;

        private ArrayList<TableEntry> contents;


        public Table(String schema, boolean createFile){
            contents = new ArrayList<>();


            schema = schema + "/";

            // REMOVE THIS
            uuid = UUID.fromString("08a20118-4735-4952-a94b-e8974f5c1515");


            if(createFile){
                uuid = UUID.randomUUID();
                file = new File(path + schema + uuid + fileType);
                // If the file does not exist it creates a new empty file with the uuid as name.
                if(!file.exists()){
                    try{
                        FileWriter fileWriter = new FileWriter(file, true);
                        fileWriter.flush();
                        fileWriter.close();
                    }
                    catch (Exception e){
                        System.out.println("Could not write to file. Please check application permissions.");
                    }
                }
                // If the file does exist, load data from the file.
                else{
                    initiate();
                }
            }

        }

        // Loads all data from file.
        public void initiate(){
            try {
                FileInputStream fileInputStream = new FileInputStream(path);
                ObjectInputStream in = new ObjectInputStream(fileInputStream);

                Table target = (Table) in.readObject();
                readVariables(target);
                in.close();
                fileInputStream.close();
            }catch (Exception e){
                System.out.println("Could not read file. Please check application permissions.");
            }
        }

        // Saves all data to a file in the schema.
        public void save(){
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                ObjectOutputStream out = new ObjectOutputStream(fileOutputStream);

                out.writeObject(this);
                out.close();
                fileOutputStream.close();
            }catch (Exception e){
                System.out.println("Could not write file. Please fix application permissions.");
            }

        }

        // Reads variables from another table and replaces its own fields with the targets.
        private void readVariables(Table target){
            // Gets the fields from the class.
            Field[] fields =  target.getClass().getFields();
            for(Field field : fields){
                try{
                    field.set(this, field.get(target));

                }catch (Exception e){
                    // Nothing needs to happen.
                }
            }

            ArrayList<Object> currentVars;
        }


        // Searches through the table for any variable matching
        public ArrayList<TableEntry> search(String... keywords){
            ArrayList<TableEntry> result = new ArrayList<>();
            List<String> keywordList = Arrays.asList(keywords);
            // For every item.
            for(TableEntry item : contents){

                // For every keyword there needs to be at least one match.
                int matchingKeywords = 0;
                ArrayList<Object> variables = item.getVariables();
                keywordloop:
                for(String keyword : keywords){
                    // For every
                    for(Object var : variables){
                        if(keyword.equals(var.toString())){
                            matchingKeywords++;
                            // Looks for next keyword.
                            break keywordloop;
                        }
                    }
                    // If every keyword has a match add this item to the result.
                    if(matchingKeywords == variables.size()){
                        result.add(item);
                    }
                }
            }
            return result;

        }
    }
}




