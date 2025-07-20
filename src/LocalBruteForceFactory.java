public class LocalBruteForceFactory implements AbstractCrackerFactory {
    private final String fixedTargetLogin;
    private final String fixedTargetPassword;
    private final String alphabet;
    private final int maxPasswordLength;

    public LocalBruteForceFactory(String fixedTargetLogin, String fixedTargetPassword, String alphabet, int maxPasswordLength) {
        this.fixedTargetLogin = fixedTargetLogin;
        this.fixedTargetPassword = fixedTargetPassword;
        this.alphabet = alphabet;
        this.maxPasswordLength = maxPasswordLength;
    }

    @Override
    public CrackerTask createCrackerTask() {
        return new BruteForceLocalCrackerTask(alphabet, maxPasswordLength, fixedTargetLogin, fixedTargetPassword);
    }
}
