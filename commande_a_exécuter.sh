Plcer ce dossier dans le dossier htdocs de xampp puis se rendre dans src puis taper "javac *.java" pour compiler les fichiers Java

Test 1 - Brute Force Local :
java CrackerApp --type bruteforce --target local --login admin

Test 2 - Dictionary Local :
java CrackerApp --type dictionnary --target local --login admin

Pour les tests en ligne 3 et 4 il faut démarrer php en exécutant la commande "php -S localhost:8000" puis exécuter ses commandes

Test 3 - Brute Force Online :
java CrackerApp --type bruteforce --target online --login admin

Test 4 - Dictionary Online :
java CrackerApp --type dictionnary --target online --login admin
