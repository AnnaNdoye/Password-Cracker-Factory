public interface CrackerFactory {
    // Cette fabrique créera un "attaquant" qui est capable de cracker une cible.
    // Puisque la fabrique "concrète" va décider de la combinaison,
    // la méthode peut simplement retourner l'AttackStrategy avec la Target déjà "intégrée"
    // ou l'AttackStrategy et la Target séparément si la gestion est plus complexe.
    // Pour ce projet, il est plus simple que la fabrique retourne la stratégie d'attaque
    // et qu'elle fournisse elle-même la cible à cette stratégie.
    AttackStrategy createAttackStrategy(String login); // La fabrique pourrait avoir besoin du login pour configurer l'attaque ou la cible
    AuthenticationTarget createTarget();
}