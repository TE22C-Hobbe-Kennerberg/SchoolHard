import java.util.ArrayList;

public class DatabaseConnection {
    public class Query<T extends  DatabaseEntry> {
        private ArrayList<T> result;

        public ArrayList<T> find(Class<T> type, String... keyword) {
            return null;
        }
    }
}
