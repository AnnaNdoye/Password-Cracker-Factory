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
            System.out.println("Exemple: java CrackerApp --type bruteforce --target online --login admin");
            return;
        }

        AbstractCrackerFactory factory = null;
        
        // OPTIMISATION : Alphabet plus petit et longueur réduite pour les tests rapides
        String alphabet = "abcdefghijklmnopqrstuvwxyz0123456789";
        int maxLen = 4; // Longueur max réduite pour éviter les temps d'exécution trop longs
        
        String dictionaryPath = "dictionary.txt"; 
        String onlineTargetUrl = "http://localhost/projet/php/auth.php";

        // Paramètres de la cible locale (pour les tests)
        String localTargetFixedLogin = "admin";
        String localTargetFixedPassword = "pass"; // Cohérent avec auth.php

        System.out.println("================================= CONFIGURATION ===========================");
        System.out.println("Type d'attaque: " + type);
        System.out.println("Type de cible: " + targetType);
        System.out.println("Login cible: " + login);
        
        if ("bruteforce".equals(type)) {
            System.out.println("Alphabet: " + alphabet);
            System.out.println("Longueur max: " + maxLen);
        } else if ("dictionnary".equals(type)) {
            System.out.println("Fichier dictionnaire: " + dictionaryPath);
        }
        
        if ("online".equals(targetType)) {
            System.out.println("URL cible: " + onlineTargetUrl);
        }
        System.out.println("=========================================================================\n");

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
                System.out.println("Combinaisons valides:");
                System.out.println("  - bruteforce-local");
                System.out.println("  - bruteforce-online");
                System.out.println("  - dictionnary-local");
                System.out.println("  - dictionnary-online");
                return;
        }

        if (factory != null) {
            CrackerTask crackerTask = factory.createCrackerTask();
            System.out.println("Lancement de l'opération de cassage...");
            
            long globalStartTime = System.currentTimeMillis();
            boolean cracked = crackerTask.execute(login);
            long globalEndTime = System.currentTimeMillis();
            
            System.out.println("\n========================== RÉSULTAT FINAL ==========================");
            if (cracked) {
                System.out.println("Opération de cassage RÉUSSIE !");
            } else {
                System.out.println("Opération de cassage ÉCHOUÉE. Mot de passe non trouvé.");
            }
            System.out.println("Temps total d'exécution: " + (globalEndTime - globalStartTime) + " ms");
            System.out.println("=====================================================================");
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
