import java.util.ArrayList;
import java.util.Hashtable;
import java.util.UUID;

public class Database {
    public final File DATA_DIRECTORY("./db_data");
    public class Query<T>{
        private ArrayList<T> result;

        public ArrayList<T> find(string... keyword){
            for(int i = 0; i < )
        }
    }

    public class DatabaseTable<T extends DatabaseEntry>{
        private Hashtable<UUID, T> rows;
    }
}


