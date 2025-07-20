import java.util.HashMap;
import java.util.Map;

public class CrackerApp {
    public static void main(String[] args) {
        // Parse les arguments de la ligne de commande
        Map<String, String> params = parseArgs(args);

        String type = params.get("--type");
        String targetType = params.get("--target");
        String login = params.get("--login");

        if (type == null || targetType == null || login == null) {
            System.out.println("Usage: java CrackerApp --type <bruteforce|dictionnary> --target <local|online> --login <username>");
            // Ajoutez d'autres paramètres si nécessaire, comme l'alphabet ou la longueur max pour le brute force, ou le chemin du dictionnaire.
            return;
        }

        AbstractCrackerFactory factory = null;
        String alphabet = "abcdefghijklmnopqrstuvwxyz0123456789"; // Alphabet par défaut
        int maxLen = 4; // Longueur max par défaut pour Brute Force
        String dictionaryPath = "dictionary.txt"; // Chemin du dictionnaire par défaut
        String onlineTargetUrl = "http://localhost/projet/php/auth.php"; // URL de la cible en ligne

        // Paramètres de la cible locale (pour les tests)
        String localTargetFixedLogin = "admin";
        String localTargetFixedPassword = "pass"; // Mettez un mot de passe court pour tester rapidement le brute force local

        switch (type + "-" + targetType) {
            case "bruteforce-local":
                factory = new LocalBruteForceFactory(localTargetFixedLogin, localTargetFixedPassword, alphabet, maxLen);
                break;
            case "bruteforce-online":
                factory = new OnlineBruteForceFactory(onlineTargetUrl, alphabet, maxLen);
                break;
            case "dictionnary-local":
                factory = new LocalDictionaryFactory(localTargetFixedLogin, localTargetFixedPassword, dictionaryPath);
                break;
            case "dictionnary-online":
                factory = new OnlineDictionaryFactory(onlineTargetUrl, dictionaryPath);
                break;
            default:
                System.out.println("Combinaison type-cible non reconnue: " + type + "-" + targetType);
                return;
        }

        if (factory != null) {
            CrackerTask crackerTask = factory.createCrackerTask();
            System.out.println("Lancement de l'opération de cassage...");
            boolean cracked = crackerTask.execute(login);
            if (cracked) {
                System.out.println("Opération de cassage réussie !");
            } else {
                System.out.println("Opération de cassage échouée. Mot de passe non trouvé.");
            }
        }
    }

    private static Map<String, String> parseArgs(String[] args) {
        Map<String, String> params = new HashMap<>();
        for (int i = 0; i < args.length; i += 2) {
            if (i + 1 < args.length) {
                params.put(args[i], args[i + 1]);
            }
        }
        return params;
    }
}
