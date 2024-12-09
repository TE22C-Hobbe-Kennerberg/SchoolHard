import java.io.*;
import java.util.Arrays;

public class FileHelper {

    // Sends a file via an outputStream.
    public void sendFile(File file, OutputStream outputStream){
        System.out.println("Sending file: " + file.getName());
        try {
            int bytesWritten = 0;
            FileInputStream fileInputStream = new FileInputStream(file);
            DataOutputStream out = new DataOutputStream(outputStream);

            out.writeInt((int)file.length());
            // Writes until buffer is empty.
            byte[] buffer = new byte[1024];
            while((bytesWritten = fileInputStream.read(buffer)) != -1){
                System.out.println("--------SENDING " + file.getName() + " " + file.length() + "--------");
                System.out.println(Arrays.toString(buffer));
                System.out.println("-----------------------");
                out.write(buffer, 0, bytesWritten);
                out.write
            }
            fileInputStream.close();

        } catch (IOException e) {
            System.out.println("1. Could not read/write file. Please check application permissions.");
        }
    }

    // Reads a file from an inputStream in to a new temporary file.
    public File recieveFile(InputStream inputStream){
        try{
            int bytesRead = 0;
            File file = new File("./receive_temp.ser");
            FileOutputStream fileOutputStream = new FileOutputStream(file);

            DataInputStream in = new DataInputStream(inputStream);

            int size = in.readInt();
            byte[] buffer = new byte[1024];
            // Reads until size bytes has been read.
            while(size > 0){
                bytesRead = in.read(buffer, 0, Math.min(buffer.length, size));
                System.out.println("Bytes read: " + bytesRead);
                size -= bytesRead;
                fileOutputStream.write(buffer);

                System.out.println("--------RECEIVING "  + size + "--------");
                System.out.println(Arrays.toString(buffer));
                System.out.println("------------------------");
            }
            fileOutputStream.flush();
            fileOutputStream.close();
            return file;
        }
        catch(Exception e){
            System.out.println("SOMETHING WRONG");
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
