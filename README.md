# Cours_J2EE
Cours d'application Java à Unilasalle Amiens

# Features core

- [ ] Account
  - [X] `/accounts`
    - [X] POST => Ajout de nouveau objets
    - [X] PATCH => Mise à jour des objets
    - [X] GET => Liste des objets
    - [X] DELETE => Connexion refusée
  - [X] `/accounts/{pk}`
    - [X] POST => Methode non autorisée
    - [X] PATCH => Mise à jour des objets
    - [X] GET => Affichage de l'objet
    - [X] DELETE => Suppression de l'objet
  - [ ] `/accounts?{variable}={valeur}` (filter)
    - [ ] POST => Méthode non autorisée
    - [ ] PATCH => Mise à jour avec les données dans la requête
    - [X] GET => Affichage des objets correspondant
    - [X] DELETE => Suppression des objets correspondant
- [ ] Address
  - [ ] Sauvegarde avec un compte
  - [ ] Vérification de l'adresse
- [ ] Bank account
  - [ ] Sauvegarde à part d'un compte
  - [ ] Nécessite un `account` existant
  - [ ] Suppression CASCADE avec les `accounts`
  - [ ] Liaison One-to-Many avec `accounts`
  - [ ] Vérification du format de l'IBAN

Avancement : 12/25