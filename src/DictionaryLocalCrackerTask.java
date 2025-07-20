public class DictionaryLocalCrackerTask implements CrackerTask {
    private final DictionaryAttack attackStrategy;
    private final LocalTarget target;

    public DictionaryLocalCrackerTask(String dictionaryFilePath, String fixedTargetLogin, String fixedTargetPassword) {
        this.attackStrategy = new DictionaryAttack(dictionaryFilePath);
        this.target = new LocalTarget(fixedTargetLogin, fixedTargetPassword);
    }

    @Override
    public boolean execute(String login) {
        System.out.println("Exécution: Dictionnaire sur cible Locale.");
        return attackStrategy.crackPassword(login, target);
    }
}
