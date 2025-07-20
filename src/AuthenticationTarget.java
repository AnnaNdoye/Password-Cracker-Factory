public interface AuthenticationTarget {
    boolean authenticate(String login, String password);
}