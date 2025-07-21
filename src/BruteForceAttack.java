
public class BruteForceAttack implements AttackStrategy {
    private final String alphabet;
    private final int maxPasswordLength;

    public BruteForceAttack(String alphabet, int maxPasswordLength) {
        this.alphabet = alphabet;
        this.maxPasswordLength = maxPasswordLength;
    }

    @Override
    public boolean crackPassword(String login, AuthenticationTarget target) {
        System.out.println("Démarrage de l'attaque Brute Force pour le login: " + login);
        System.out.println("Alphabet utilisé: " + alphabet);
        System.out.println("Longueur maximale: " + maxPasswordLength);
        long startTime = System.currentTimeMillis();

        // OPTIMISATION : Commencer par tester les mots de passe les plus probables du dictionnaire
        // avant de faire le brute force complet
        String[] commonPasswords = {"pass", "admin", "1234", "password", "123", "abc", "test"};
        
        System.out.println("Test des mots de passe d'abord...");
        for (String commonPass : commonPasswords) {
            if (commonPass.length() <= maxPasswordLength) {
                if (target.authenticate(login, commonPass)) {
                    long endTime = System.currentTimeMillis();
                    System.out.println("Mot de passe trouvé (mot courant): " + commonPass);
                    System.out.println("Temps écoulé: " + (endTime - startTime) + " ms");
                    return true;
                }
            }
        }

        System.out.println("Démarrage du brute force systématique...");
        
        // Algorithme de génération de combinaisons optimisé
        // Commence par les mots de passe les plus courts
        for (int length = 1; length <= maxPasswordLength; length++) {
            System.out.println("Génération des mots de passe de longueur " + length + "...");
            
            if (generateAndTestPasswords(login, target, length, startTime)) {
                return true;
            }
            
            // Affichage du progrès
            System.out.println("Longueur " + length + " terminée. Passage à la longueur suivante...");
        }

        long endTime = System.currentTimeMillis();
        System.out.println("Mot de passe non trouvé après Brute Force complet.");
        System.out.println("Temps écoulé: " + (endTime - startTime) + " ms");
        return false;
    }

    private boolean generateAndTestPasswords(String login, AuthenticationTarget target, int length, long startTime) {
        return generateRecursive(login, target, "", length, startTime);
    }

    private boolean generateRecursive(String login, AuthenticationTarget target, String current, int remainingLength, long startTime) {
        if (remainingLength == 0) {
            // Tester le mot de passe généré
            if (target.authenticate(login, current)) {
                long endTime = System.currentTimeMillis();
                System.out.println("Mot de passe trouvé: " + current);
                System.out.println("Temps écoulé: " + (endTime - startTime) + " ms");
                return true;
            }
            return false;
        }

        // Générer récursivement
        for (char c : alphabet.toCharArray()) {
            if (generateRecursive(login, target, current + c, remainingLength - 1, startTime)) {
                return true;
            }
        }
        
        return false;
    }
}
