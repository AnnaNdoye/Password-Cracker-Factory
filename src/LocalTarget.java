public class LocalTarget implements AuthenticationTarget {
    private final String correctLogin;
    private final String correctPassword;

    public LocalTarget(String correctLogin, String correctPassword) {
        this.correctLogin = correctLogin;
        this.correctPassword = correctPassword;
    }

    @Override
    public boolean authenticate(String login, String password) {
        // Pour l'exemple, nous allons print la tentative
        System.out.println("Tentative d'authentification locale - Login: " + login + ", Password: " + password);
        return correctLogin.equals(login) && correctPassword.equals(password);
    }

    public static void main(String[] args) {
        // Exemple d'usage en ligne de commande pour le LocalTarget
        // java LocalTarget admin passer1234
        if (args.length < 2) {
            System.out.println("Usage: java LocalTarget <login_correct> <password_correct>");
            return;
        }
        String fixedLogin = args[0];
        String fixedPassword = args[1];
        LocalTarget target = new LocalTarget(fixedLogin, fixedPassword);

        // Simuler une demande d'authentification (peut être via un scanner ou autre pour le test)
        // Dans le cadre du projet, ce main est juste pour tester le Target en isolation.
        // Le cracker enverra directement des requêtes à l'instance de LocalTarget.
        System.out.println("Cible Locale prête. En attente de tentatives...");
    }
} 
    

