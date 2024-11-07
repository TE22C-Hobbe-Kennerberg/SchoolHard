import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;

public class TableEntry implements Serializable {

    // Gets all the variables from any TableEntry.
    public ArrayList<Object> getVariables(){
        ArrayList<Object> result = new ArrayList<>();

        Field[] fields = this.getClass().getFields();
        // Loops through all fields and adds them to array
        for(Field f : fields){
            try {
                result.add(f.get(this));
            } catch (IllegalAccessException e) {
                // Will not get here. Hopefully.
            }
        }

        return result;
    }


}
