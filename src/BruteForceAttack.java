import java.util.LinkedList;
import java.util.Queue;

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
        long startTime = System.currentTimeMillis();

        // Algorithme de génération de combinaisons (BFS pour la simplicité)
        Queue<String> queue = new LinkedList<>();
        queue.add(""); // Commencez avec une chaîne vide

        while (!queue.isEmpty()) {
            String currentPassword = queue.poll();

            if (currentPassword.length() > maxPasswordLength) {
                continue; // Ne pas dépasser la longueur maximale
            }

            if (!currentPassword.isEmpty() && target.authenticate(login, currentPassword)) {
                long endTime = System.currentTimeMillis();
                System.out.println("Mot de passe trouvé: " + currentPassword);
                System.out.println("Temps écoulé: " + (endTime - startTime) + " ms");
                return true; // Mot de passe trouvé
            }

            if (currentPassword.length() < maxPasswordLength) {
                for (char c : alphabet.toCharArray()) {
                    queue.add(currentPassword + c);
                }
            }
        }

        long endTime = System.currentTimeMillis();
        System.out.println("Mot de passe non trouvé après Brute Force.");
        System.out.println("Temps écoulé: " + (endTime - startTime) + " ms");
        return false;
    }
}
