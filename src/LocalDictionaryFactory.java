public class LocalDictionaryFactory implements AbstractCrackerFactory {
    private final String fixedTargetLogin;
    private final String fixedTargetPassword;
    private final String dictionaryFilePath;

    public LocalDictionaryFactory(String fixedTargetLogin, String fixedTargetPassword, String dictionaryFilePath) {
        this.fixedTargetLogin = fixedTargetLogin;
        this.fixedTargetPassword = fixedTargetPassword;
        this.dictionaryFilePath = dictionaryFilePath;
    }

    @Override
    public CrackerTask createCrackerTask() {
        return new DictionaryLocalCrackerTask(dictionaryFilePath, fixedTargetLogin, fixedTargetPassword);
    }
}