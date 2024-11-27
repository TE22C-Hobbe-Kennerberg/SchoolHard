import java.io.*;
import java.lang.reflect.Field;
import java.util.*;


public class Database implements Serializable{
    private final String databasePath = "./db_data/";
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
        createRequired();

        File[] schemaFiles = new File(databasePath).listFiles();
        if(schemaFiles == null) return;

        for(File schema : schemaFiles){
            String schemaName = schema.getName();
            schemas.put(schemaName, new HashMap<>());

            // Gets current schema folder.
            File[] tableFiles = new File(databasePath + schemaName + "/").listFiles();

            // If the schema folder is empty for some reason.
            if(tableFiles == null) continue;

            // For every table file in the current schema folder.
            for(File table : tableFiles){
                // Creates a new table with set uuid and loads the data from file.
                UUID uuid = UUID.fromString(table.getName().substring(0, table.getName().length()-fileType.length()));
                Table tempTable = new Table(schemaName, uuid);
                tempTable.initiate();

                schemas.get(schemaName).put(tempTable.uuid, tempTable);
            }
        }
    }

    // Creates the folder for the database data according to path variable.
    private void createFolder(){
        new File(databasePath).mkdir();
    }
    private void createRequired(){
        createSchema("users");
        createTable("users");

        createSchema("chats");
    }

    // Creates a schema if it does not already exist.
    private void createSchema(String name){
        boolean schemaExists = new File(databasePath + name).mkdir();

        if(!schemaExists){
            schemas.put(name, new HashMap<>());
        }

    }

    // Gets all the table entries that contains ALL the keywords.
    public ArrayList<TableEntry> search(String schema, String ... keywords){
        ArrayList<TableEntry> result = new ArrayList<>();

        // Searches all tables in selected schema.
        for(Table table : schemas.get(schema).values()){
             ArrayList<TableEntry> search = table.search(keywords);
             result.addAll(search);
        }

        return result;
    }

    // Gets the first table containing matching keywords.
    private Table findTable(String schema, String ... keywords){

        // Searches all tables in selected schema.
        for(Table table : schemas.get(schema).values()){
            ArrayList<TableEntry> search = table.search(keywords);
            if(!search.isEmpty()) return table;
        }

        return null;
    }

    // Adds a table to a schema.
    public void createTable(String schema){
        Table table = new Table(schema);
        schemas.get(schema).put(table.uuid, table);
    }
    // Adds a table to a schema with a set UUID.
    public void createTable(String schema, UUID uuid){
        Table table = new Table(schema, uuid);
        schemas.get(schema).put(table.uuid, table);
    }


    //Adds something to a table inside a schema that also has the matching keywords.
    public void addToTable(String schema, TableEntry data, String... keywords){
        Table table = findTable(schema, keywords);
        if(table != null) table.add(data);
    }

    public void sendMessage(Message message, String user1, String user2){
        Table table = (Table) findTable("chats", user1, user2);
        table.add(message);
    }

    // Stores multiple data entries of type T inside a JSON file.
    protected class Table {
        // The file containing all data stored in the table.
        private File file;
        private UUID uuid;

        private ArrayList<TableEntry> contents;

        public Table(String schema){

            contents = new ArrayList<>();
            schema = schema + "/";


            uuid = UUID.randomUUID();
            file = new File(databasePath + schema + uuid + fileType);

            // Create an empty file with the uuid as file name.
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("Could not write to file. Please check application permissions.");
                System.exit(-1);
            }
        }
        // Create a table with a set UUID.
        public Table(String schema, UUID uuid){
            schema = schema + "/";
            this.uuid = uuid;
            file = new File(databasePath + schema + uuid + fileType);
            initiate();
        }

        // Loads all data from file.
        public void initiate(){
            FileHelper fh = new FileHelper();
            Table target = (Table) fh.readObjectFromFile(file);
            if(target != null){
                readVariables(target);
            }
        }

        // Saves all data to a file in the schema.
        public void save(){
            FileHelper fh = new FileHelper();
            fh.writeObjectToFile(this, file);
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
        }

        public void add(TableEntry data){
            contents.add(data);
        }
        // Searches through the table for any variable matching
        public ArrayList<TableEntry> search(String... keywords){
            ArrayList<TableEntry> result = new ArrayList<>();

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

                }
                // If every keyword has a match add this item to the result.
                if(matchingKeywords == variables.size()){
                    result.add(item);
                }
            }
            return result;

        }
    }
}