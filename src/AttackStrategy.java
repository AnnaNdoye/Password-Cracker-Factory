public interface AttackStrategy {
    // La méthode crackPassword aura besoin d'un moyen d'interagir avec la cible.
    boolean crackPassword(String login, AuthenticationTarget target);
}
