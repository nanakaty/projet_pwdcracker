import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class dictionnarycracker {
    public static void main(String[] args) {
        String passwordHash = "5baeb78464a76b6e118cb8ce32c5e8aece2d9531bd7e4075d6742ec042750e40";
        String dictionaryFile = "passwords_hashes.txt";
        
        String password = dictionaryAttack(passwordHash, dictionaryFile);
        
        if (password != null) {
            System.out.println("Le mot de passe est : " + password);
        } else {
            System.out.println("Le mot de passe ne figure pas dans le dictionnaire.");
        }
    }
    
    public static String dictionaryAttack(String passwordHash, String dictionaryFile) {
        try (BufferedReader br = new BufferedReader(new FileReader(dictionaryFile))) {
            String line;
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(";");
                String word = parts[0].trim();
                
                byte[] wordBytes = word.getBytes();
                byte[] hashBytes = md.digest(wordBytes);
                String computedHash = bytesToHexString(hashBytes);
                
                if (computedHash.equals(passwordHash)) {
                    return word;
                }
            }
        } catch (IOException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        
        return null;
    }
    
    public static String bytesToHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        
        return sb.toString();
    }
}
