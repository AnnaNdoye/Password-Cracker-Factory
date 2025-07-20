public class DictionaryOnlineCrackerTask implements CrackerTask {
    private final DictionaryAttack attackStrategy;
    private final OnlineTarget target;

    public DictionaryOnlineCrackerTask(String dictionaryFilePath, String targetUrl) {
        this.attackStrategy = new DictionaryAttack(dictionaryFilePath);
        this.target = new OnlineTarget(targetUrl);
    }

    @Override
    public boolean execute(String login) {
        System.out.println("Exécution: Dictionnaire sur cible En Ligne.");
        return attackStrategy.crackPassword(login, target);
    }
}
