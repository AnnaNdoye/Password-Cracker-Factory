import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class DictionaryAttack implements AttackStrategy {
    private final String dictionaryFilePath;

    public DictionaryAttack(String dictionaryFilePath) {
        this.dictionaryFilePath = dictionaryFilePath;
    }

    @Override
    public boolean crackPassword(String login, AuthenticationTarget target) {
        System.out.println("Démarrage de l'attaque par Dictionnaire pour le login: " + login);
        long startTime = System.currentTimeMillis();

        try (BufferedReader reader = new BufferedReader(new FileReader(dictionaryFilePath))) {
            String passwordCandidate;
            while ((passwordCandidate = reader.readLine()) != null) {
                // Supprimez les espaces blancs de début/fin pour chaque ligne du dictionnaire
                passwordCandidate = passwordCandidate.trim();
                if (target.authenticate(login, passwordCandidate)) {
                    long endTime = System.currentTimeMillis();
                    System.out.println("Mot de passe trouvé: " + passwordCandidate);
                    System.out.println("Temps écoulé: " + (endTime - startTime) + " ms");
                    return true; // Mot de passe trouvé
                }
            }
        } catch (IOException e) {
            System.err.println("Erreur de lecture du fichier dictionnaire: " + e.getMessage());
            return false;
        }

        long endTime = System.currentTimeMillis();
        System.out.println("Mot de passe non trouvé dans le dictionnaire.");
        System.out.println("Temps écoulé: " + (endTime - startTime) + " ms");
        return false;
    }
}
