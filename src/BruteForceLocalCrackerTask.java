public class BruteForceLocalCrackerTask implements CrackerTask {
    private final BruteForceAttack attackStrategy;
    private final LocalTarget target;

    public BruteForceLocalCrackerTask(String alphabet, int maxPasswordLength, String fixedTargetLogin, String fixedTargetPassword) {
        this.attackStrategy = new BruteForceAttack(alphabet, maxPasswordLength);
        this.target = new LocalTarget(fixedTargetLogin, fixedTargetPassword);
    }

    @Override
    public boolean execute(String login) {
        System.out.println("Exécution: Brute Force sur cible Locale.");
        return attackStrategy.crackPassword(login, target);
    }
}
