public class OnlineDictionaryFactory implements AbstractCrackerFactory {
    private final String targetUrl;
    private final String dictionaryFilePath;

    public OnlineDictionaryFactory(String targetUrl, String dictionaryFilePath) {
        this.targetUrl = targetUrl;
        this.dictionaryFilePath = dictionaryFilePath;
    }

    @Override
    public CrackerTask createCrackerTask() {
        return new DictionaryOnlineCrackerTask(dictionaryFilePath, targetUrl);
    }
}
