import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class BruteForceAttack {
    public static void main(String[] args) {
        String passwordHash = "d7a1b2c64852438bab1eedaccd4b7d55efe90a5ebd90dfc09cd3cd745133c5db";
        String password = bruteForceAttack(passwordHash);

        if (password != null) {
            System.out.println("Le mot de passe est : " + password);
        } else {
            System.out.println("Le mot de passe n'a pas été trouvé.");
        }
    }

    public static String bruteForceAttack(String passwordHash) {
        int passwordLength = 5;

        for (int i = 0; i < 26; i++) {
            char c1 = (char) (97 + i);
            for (int j = 0; j < 26; j++) {
                char c2 = (char) (97 + j);
                for (int k = 0; k < 26; k++) {
                    char c3 = (char) (97 + k);
                    for (int l = 0; l < 26; l++) {
                        char c4 = (char) (97 + l);
                        for (int m = 0; m < 26; m++) {
                            char c5 = (char) (97 + m);
                            String guess = "" + c1 + c2 + c3 + c4 + c5;
                            String hashGuess = getSHA256Hash(guess);
                            if (hashGuess.equals(passwordHash)) {
                                return guess;
                            }
                        }
                    }
                }
            }
        }

        return null;
    }

    private static String getSHA256Hash(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = md.digest(input.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hashBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
}
