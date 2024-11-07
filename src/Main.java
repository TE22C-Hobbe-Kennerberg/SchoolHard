import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception{


        class Test{
            int a = 1;
            int b = 2;
        }

        Test test = new Test();
        try {
            FileOutputStream fo = new FileOutputStream("./test.txt");
            ObjectOutputStream oo = new ObjectOutputStream(fo);
            oo.write(test.a);

            fo.close();
            oo.close();
        }catch (Exception e){
            throw e;
        }
        FileInputStream fi = new FileInputStream("./test.txt");
        ObjectInputStream oi = new ObjectInputStream(fi);

        System.out.println(oi.readObject());


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