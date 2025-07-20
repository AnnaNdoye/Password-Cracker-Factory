import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class OnlineTarget implements AuthenticationTarget {
    private final String targetUrl;

    public OnlineTarget(String targetUrl) {
        this.targetUrl = targetUrl;
    }

    @Override
    public boolean authenticate(String login, String password) {
        HttpURLConnection conn = null;
        try {
            URL url = URI.create(targetUrl).toURL();
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json");
            conn.setRequestProperty("User-Agent", "Java-PasswordCracker/1.0");
            conn.setDoOutput(true);
            conn.setConnectTimeout(5000); // 5 secondes de timeout
            conn.setReadTimeout(10000);   // 10 secondes de timeout

            String jsonInputString = "{\"login\": \"" + login + "\", \"password\": \"" + password + "\"}";
            System.out.println("Tentative d'authentification en ligne - Login: " + login + ", Password: " + password);

            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = jsonInputString.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
                os.flush();
            }

            int responseCode = conn.getResponseCode();
            
            if (responseCode == HttpURLConnection.HTTP_OK) {
                try (java.io.InputStream is = conn.getInputStream();
                        java.util.Scanner s = new java.util.Scanner(is, StandardCharsets.UTF_8).useDelimiter("\\A")) {                    
                }
            } else {
                
                // Lire le message d'erreur si disponible
                try (java.io.InputStream errorStream = conn.getErrorStream()) {
                    if (errorStream != null) {
                        try (java.util.Scanner s = new java.util.Scanner(errorStream, StandardCharsets.UTF_8).useDelimiter("\\A")) {
                            String errorBody = s.hasNext() ? s.next() : "";
                            System.out.println("Erreur du serveur: " + errorBody);
                        }
                    }
                }
            }
            
        } catch (IOException e) {
            System.err.println("Erreur de connexion à la cible en ligne: " + e.getMessage());
            // Ne pas imprimer la stack trace complète pour éviter le spam lors des attaques
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
            try {
                Thread.sleep(200); // Pause entre les requêtes pour éviter de surcharger le serveur
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        return false;
    }
}