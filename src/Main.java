import java.io.*;
import java.lang.reflect.Field;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws Exception{

        class Test implements Serializable{
            public int a = 1;
            public String b = "Hello!";
        }

        Test test = new Test();

        Field[] fields = test.getClass().getFields();
        System.out.println(Arrays.toString(fields));
        Object a = fields[1].get(test);

        System.out.println(a);


        //PasswordHelper pw = new PasswordHelper();
        //pw.getHash("testing");

        //ServerConnection server = ServerConnection.getInstance();
        //server.connect();

        //UserInterface ui = UserInterface.getInstance();
        //ui.startUI();

        //ServerConnection database = ServerConnection.getInstance();
        //database.connect();

    }
}