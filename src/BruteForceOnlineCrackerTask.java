public class BruteForceOnlineCrackerTask implements CrackerTask {
    private final BruteForceAttack attackStrategy;
    private final OnlineTarget target;

    public BruteForceOnlineCrackerTask(String alphabet, int maxPasswordLength, String targetUrl) {
        this.attackStrategy = new BruteForceAttack(alphabet, maxPasswordLength);
        this.target = new OnlineTarget(targetUrl);
    }

    @Override
    public boolean execute(String login) {
        System.out.println("Exécution: Brute Force sur cible En Ligne.");
        return attackStrategy.crackPassword(login, target);
    }
}
