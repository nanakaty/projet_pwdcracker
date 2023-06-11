import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class hashing {
    private static final String DICTIONNAIRE = "dictionnaire[min].txt";
    private static final String OUTPUT_FILE = "passwords_hashes.txt";

    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(DICTIONNAIRE));
            BufferedWriter writer = new BufferedWriter(new FileWriter(OUTPUT_FILE));

            String password;
            while ((password = reader.readLine()) != null) {
                String hash = computeHash(password);
                writer.write(password + ";" + hash);
                writer.newLine();
            }

            reader.close();
            writer.close();

            System.out.println("Passwords with hashes are stored in " + OUTPUT_FILE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String computeHash(String input) {
        try {
            MessageDigest instance = MessageDigest.getInstance("SHA-256");
            byte[] valueHashInByte = instance.digest(input.getBytes());
            BigInteger converterToHex = new BigInteger(1, valueHashInByte);
            String hashValueInString = converterToHex.toString(16);

            while (hashValueInString.length() < 64) {
                hashValueInString = "0" + hashValueInString;
            }

            return hashValueInString;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
