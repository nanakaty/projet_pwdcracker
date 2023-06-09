import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class BruteForceAttack {
    private static final long MAX_EXECUTION_TIME = 5; // Limite de temps d'exécution en secondes

    public static void main(String[] args) {
        String passwordHash = "30fb30a1e4ccbdc780770d44c175eb28bde824daf6d1d46185cdb9ca122b40c5";
        String password = bruteForceAttack(passwordHash);

        if (password != null) {
            System.out.println("Le mot de passe est : " + password);
        } else {
            System.out.println("Le mot de passe n'a pas été trouvé dans le délai imparti.");
        }
    }

    public static String bruteForceAttack(String passwordHash) {
        String charset = "abcdefghijklmnopqrstuvwxyz0123456789";
        int passwordLength = 5;
        long startTime = System.nanoTime();

        for (String guess : generateGuesses(charset, passwordLength)) {
            String hashGuess = getSHA256Hash(guess);
            if (hashGuess.equals(passwordHash)) {
                return guess;
            }

            long currentTime = System.nanoTime();
            long elapsedTime = TimeUnit.SECONDS.convert(currentTime - startTime, TimeUnit.NANOSECONDS);
            if (elapsedTime >= MAX_EXECUTION_TIME) {
                break;
            }
        }
        return null;
    }

    private static Iterable<String> generateGuesses(String charset, int length) {
        List<String> guesses = new ArrayList<>();
        generateGuessesRecursive(charset, length, "", guesses);
        return guesses;
    }

    private static void generateGuessesRecursive(String charset, int length, String prefix, List<String> guesses) {
        if (length == 0) {
            guesses.add(prefix);
        } else {
            for (char c : charset.toCharArray()) {
                generateGuessesRecursive(charset, length - 1, prefix + c, guesses);
            }
        }
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
