public class OnlineBruteForceFactory implements AbstractCrackerFactory {
    private final String targetUrl;
    private final String alphabet;
    private final int maxPasswordLength;

    public OnlineBruteForceFactory(String targetUrl, String alphabet, int maxPasswordLength) {
        this.targetUrl = targetUrl;
        this.alphabet = alphabet;
        this.maxPasswordLength = maxPasswordLength;
    }

    @Override
    public CrackerTask createCrackerTask() {
        return new BruteForceOnlineCrackerTask(alphabet, maxPasswordLength, targetUrl);
    }
}
