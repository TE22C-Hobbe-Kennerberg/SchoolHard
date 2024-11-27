import java.io.*;
import java.sql.SQLOutput;
import java.util.Arrays;

public class FileHelper {

    // Sends a file via an outputStream.
    public void sendFile(File file, OutputStream outputStream){
        System.out.println("Sending file: " + file.getName());
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            DataOutputStream out = new DataOutputStream(outputStream);

            out.writeLong(file.length());

            // Writes until buffer is empty.
            byte[] buffer = new byte[1024];
            while(fileInputStream.read(buffer) != -1){
                System.out.println("--------SENDING--------");
                System.out.println(Arrays.toString(buffer));
                System.out.println("-----------------------");
                out.write(buffer);
            }
            fileInputStream.close();

        } catch (IOException e) {
            System.out.println("1. Could not read/write file. Please check application permissions.");
        }
    }

    // Reads a file from an inputStream in to a new temporary file.
    public File recieveFile(InputStream inputStream){
        try{
            File file = new File("./temp2.ser");
            FileOutputStream fileWriter = new FileOutputStream(file);
            DataInputStream in = new DataInputStream(inputStream);

            long size = in.readLong();
            byte[] buffer = new byte[1024];
            // Reads until size bytes has been read.
            while(size > 0){
                System.out.println("--------RECIEVED--------");
                System.out.println(Arrays.toString(buffer));
                System.out.println("------------------------");
                size -= in.read(buffer);
                fileWriter.write(buffer);
            }
            fileWriter.flush();
            fileWriter.close();
            return file;
        }
        catch(Exception e){
            return null;
        }
    }

    public void writeObjectToFile(Object object, File file){
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            ObjectOutputStream out = new ObjectOutputStream(fileOutputStream);

            out.writeObject(object);

            out.flush();
            out.close();
        }catch (NotSerializableException n){

        }

        catch (Exception e){
            System.out.println("1. Could not write file. Please check application permissions.");
            System.out.println(e);
        }
    }

    public Object readObjectFromFile(File file){
        try {
            // Returns null if the file is empty.
            if(file.length() == 0){
                return null;
            }

            FileInputStream fileInputStream = new FileInputStream(file);

            ObjectInputStream in = new ObjectInputStream(fileInputStream);

            Object result = in.readObject();

            in.close();
            fileInputStream.close();

            System.out.println(file);
            return result;

        }catch (Exception e){
            System.out.println("2. Could not read file. Please check application permissions.");
            System.out.println("File not found :" + file);
            System.out.println(e);
            throw new RuntimeException(e);
            //return null;
        }
    }
}
