# Password Cracker Factory

## Description du Projet

Ce projet implémente un système modulaire de cassage de mots de passe utilisant le patron de conception **Factory Method** et **Abstract Factory**. L'outil permet de choisir dynamiquement entre deux techniques d'attaque (Brute Force et Dictionnaire) et deux types de cibles (Locale et En ligne).

## Architecture Logicielle

```
                    AbstractCrackerFactory
                           │
           ┌───────────────┼───────────────┐
           │               │               │
    LocalBruteForceFactory │        OnlineBruteForceFactory
    LocalDictionaryFactory │        OnlineDictionaryFactory
                           │
                    CrackerTask ←──────── creates
           ┌───────────────┼───────────────┐
           │               │               │
    BruteForceLocalCrackerTask      BruteForceOnlineCrackerTask
    DictionaryLocalCrackerTask      DictionaryOnlineCrackerTask
                           │
                    AttackStrategy ←──── uses
           ┌───────────────┼───────────────┐
           │               │               │
    BruteForceAttack    DictionaryAttack  │
                           │               │
                AuthenticationTarget ←──── uses
           ┌───────────────┼───────────────┐
           │               │               │
    LocalTarget      OnlineTarget         │
```

### Composants Principaux

1. **CrackerApp** : Point d'entrée principal gérant les arguments de ligne de commande
2. **AbstractCrackerFactory** : Interface pour les fabriques concrètes
3. **AttackStrategy** : Interface pour les stratégies d'attaque
4. **AuthenticationTarget** : Interface pour les cibles d'authentification
5. **CrackerTask** : Interface pour l'exécution des tâches de cassage

## Patrons de Conception Utilisés

### 1. Abstract Factory Pattern
- **Justification** : Permet de créer des familles d'objets liés (stratégie d'attaque + cible) sans spécifier leurs classes concrètes
- **Implémentation** : `AbstractCrackerFactory` avec ses 4 implementations concrètes
- **Avantages** : 
  - Facilite l'ajout de nouvelles combinaisons
  - Garantit la cohérence entre stratégie et cible
  - Découplage du code client

### 2. Strategy Pattern
- **Justification** : Permet de changer dynamiquement l'algorithme d'attaque
- **Implémentation** : `AttackStrategy` avec `BruteForceAttack` et `DictionaryAttack`
- **Avantages** :
  - Extensibilité pour de nouvelles techniques d'attaque
  - Séparation des responsabilités

### 3. Command Pattern (partiel)
- **Justification** : Encapsule les opérations de cassage dans des objets `CrackerTask`
- **Avantages** : Permet l'exécution différée et la composition d'opérations

## Structure du Projet

```
Mini-projet-Password-Cracker/
│
├── php/
│   ├── auth.php
│   └── bienvenue.php
│
├── src/
│   ├── AbstractCrackerFactory.java
│   ├── AttackStrategy.java
│   ├── AuthenticationTarget.java
│   ├── BruteForceAttack.java
│   ├── BruteForceLocalCrackerTask.java
│   ├── BruteForceOnlineCrackerTask.java
│   ├── CrackerApp.java
│   ├── CrackerTask.java
│   ├── DictionaryAttack.java
│   ├── DictionaryLocalCrackerTask.java
│   ├── DictionaryOnlineCrackerTask.java
│   ├── LocalBruteForceFactory.java
│   ├── LocalDictionaryFactory.java
│   ├── LocalTarget.java
│   ├── OnlineBruteForceFactory.java
│   ├── OnlineDictionaryFactory.java
│   ├── OnlineTarget.java
│   └── dictionary.txt
│── DiagrammeDeClasseUML.png
├── README.md
└── commande_a_exécuter.txt
```
## Variantes Implémentées

### 1. Attaque Brute Force Locale
```bash
java CrackerApp --type bruteforce --target local --login admin
```
- Génère toutes les combinaisons possibles (a-z, 0-9)
- Longueur maximale configurable (défaut: 4)
- Teste contre une cible locale avec login/password fixes

### 2. Attaque Brute Force En Ligne
```bash
java CrackerApp --type bruteforce --target online --login admin
```
- Même algorithme que la version locale
- Envoie des requêtes HTTP POST au serveur PHP
- Analyse les réponses JSON pour détecter le succès

### 3. Attaque Dictionnaire Locale
```bash
java CrackerApp --type dictionnary --target local --login admin
```
- Charge le fichier `dictionary.txt`
- Teste chaque mot du dictionnaire
- Cible locale simulée

### 4. Attaque Dictionnaire En Ligne
```bash
java CrackerApp --type dictionnary --target online --login admin
```
- Utilise le même dictionnaire
- Cible le serveur PHP via requêtes HTTP

## Installation et Utilisation

### Prérequis
- Java 11+
- Serveur web avec PHP 7.4+ (pour les attaques en ligne)

### Compilation
```bash
cd src/
javac *.java
```

### Exécution
```bash
# Exemple d'attaque par dictionnaire locale
java CrackerApp --type dictionnary --target local --login admin

# Exemple d'attaque brute force en ligne
java CrackerApp --type bruteforce --target online --login admin
```

### Configuration du serveur PHP
1. Placez les fichiers PHP dans votre serveur web
2. Modifiez l'URL dans `CrackerApp.java` si nécessaire :
   ```java
   String onlineTargetUrl = "http://localhost/projet/php/auth.php";
   ```

## Caractéristiques Techniques

### Algorithme Brute Force
- **Méthode** : BFS (Breadth-First Search) pour génération systématique
- **Alphabet** : `abcdefghijklmnopqrstuvwxyz0123456789`
- **Optimisations** : Queue pour gestion mémoire efficace

### Attaque Dictionnaire
- **Format** : Fichier texte, un mot de passe par ligne
- **Traitement** : Suppression automatique des espaces
- **Gestion d'erreurs** : IOException capturées et loggées

### Communication HTTP
- **Format** : JSON pour les requêtes/réponses
- **Headers** : Content-Type et Accept appropriés
- **Rate Limiting** : Pause de 100ms entre les requêtes

## Mots de Passe de Test

### Cible Locale
- Login: `admin`
- Password: `pass`

### Cible En Ligne (PHP)
- Login: `admin` 
- Password: `pass`

Le mot de passe "pass" est présent dans le dictionnaire pour permettre le test des attaques par dictionnaire.

## Résultats et Performance

### Temps d'Exécution (approximatifs)
- **Dictionnaire** : < 1 seconde (13 mots)
- **Brute Force local** : ~2-3 secondes pour "pass" (longueur 4)
- **Attaques en ligne** : Plus lentes due à la latence réseau

### Logs de Sortie
Le système affiche :
- Type d'attaque lancée
- Tentatives en temps réel
- Temps d'exécution total
- Résultat (succès/échec)

## Pistes d'Amélioration

### Court Terme
1. **Parallélisation** : Multi-threading pour accélérer les attaques
2. **Configuration externe** : Fichier properties pour les paramètres
3. **Logging avancé** : Framework de logs (Log4j, SLF4J)
4. **Validation** : Vérification des arguments d'entrée

### Moyen Terme
1. **Nouvelles stratégies** : Attaques hybrides, Rainbow Tables
2. **Cibles additionnelles** : Base de données, services REST
3. **Métriques** : Statistiques détaillées, taux de réussite
4. **Interface graphique** : JavaFX ou web interface

### Long Terme
1. **Machine Learning** : Prédiction de mots de passe probables
2. **Distribution** : Attaques distribuées sur multiple machines
3. **Sécurité** : Chiffrement des communications, authentification
4. **Compliance** : Respect des réglementations de sécurité
