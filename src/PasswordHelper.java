import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordHelper {
    public String getHash(String string){
        try{
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(string.getBytes());
            String hashString = new String(hash, StandardCharsets.UTF_8);

            // Convert bytes to string.
            StringBuilder sb = new StringBuilder();
            for(byte b : hash){
                // 0 padding, width of 2, hexadecimal.
                sb.append(String.format("%02X", b));
            }
            System.out.println(sb.toString().toLowerCase());
            return sb.toString().toLowerCase();
        }catch(NoSuchAlgorithmException e){
            // Should always be correct x.x
            System.out.println("I don't know how you got here...");
            System.exit(-1);
        }

        return null;
    }
}