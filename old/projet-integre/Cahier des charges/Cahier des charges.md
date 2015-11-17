# Cahier des charges
## Introduction

Ce présent cahier des charges décrit les fonctionnalités du travail réalisé au cours de cette année 2014-2015. Le but de ce dernier est de réaliser un logiciel proposant un service d’aide pour la gestion des stages et TFE pour le département informatique de gestion de la Haute Ecole Robert Schuman de Libramont. Notre groupe composé de cinq étudiants devra donc collaborer pour implémenter ce logiciel.

## Contexte du projet

Chaque étudiant en informatique de gestion à la HERS doit, au cours de son cursus d'études, réaliser un stage d'une durée qui peut varier. Une fois la démarche de recherche de stage effectuée par un étudiant, des stages seront proposés aux professeurs via le logiciel. Afin de faciliter la tâche aux utilisateurs, une base de données répertoriant les précédents stages sera mise en place et sera mise à jour à chaque nouvelle proposition de stage. Ainsi, les étudiants et professeurs pourront accéder aux données des années antérieures, ce qui pourra motiver la proposition ou l'acceptation de l'une ou l'autre partie.

Avant de proposer un stage, l'étudiant doit suivre une certaine démarche :
- Se documenter sur l'entreprise, ses buts, son fonctionnement, …
- Se documenter sur le travail réalisé par le département 	informatique de l'entreprise
- Se documenter sur les technologies utilisées par le département informatique

Avant de proposer un TFE, l'étudiant doit suivre une certaine démarche :
- Trouver un client ayant besoin d’un produit informatique afin de l’aider dans son travail quotidien.
- Se renseigner sur la faisabilité du travail à réaliser
- Communiquer avec le client pour comprendre le besoin de ce dernier 
Les professeurs, après avoir reçu les propositions de stages ou de TFE, les valident ou non selon différents critères. De même pour le suivi pendant le stage et le suivi du TFE.

L'inconvénient avec la démarche entreprise par les étudiants, c'est qu'il n'y a pas d'historique : certains étudiants ont certainement réalisé leur stage dans les mêmes entreprises.
De même que pour les TFEs, certains thèmes et genres d’applications ont possiblement déjà été proposés ou réalisés par d’autres étudiants.

## Buts du logiciel

Le but du logiciel est de faciliter les démarches des étudiants avant et pendant leur stage, mais aussi tout au long de la réalisation de leur TFE.

Le logiciel permettra de faciliter la communication entre les membres du jury en leurs permettant d’accéder directement à la fiche d’un étudiant.

Le logiciel permettra aux différents membres du jury d’entrer des points et des commentaires pour un étudiant.

Le logiciel permettra aux utilisateurs d’obtenir des informations sur le stage, les technologies utilisées, le lieu...

Le logiciel permettrait en outre de rassembler les informations utiles aux professeurs, jurys et étudiants, ce qui facilite la communication et permet à chaque partie d'être au courant de l'avancement de chaque étudiant dans son cursus (que ce soit stage ou TFE).

Le logiciel proposé vise à aider les étudiants des différentes sections à choisir leur futur stage en connaissant des entreprises grâce aux informations collectées par les étudiants des années précédentes. Il vise également à tenir à jour le déroulement du stage/TFE en proposant d'y insérer des notes de suivi qui seront consultées/validées par les professeurs/jurys.

De plus, les différents jurys de stages et TFE auront la possibilité de noter les étudiants directement sur les suivis postés, mais également de mettre des points pour chaque partie de l'évaluation (parties écrite, orale, suivi, démonstration éventuelle, …).

Enfin, outre ses fonctionnalités, un projet comme celui-ci permettra de faciliter la collaboration entre les professeurs et les étudiants, et proposera une manière structurée et efficace de gérer les données sur les stages et les TFE pour chacune des parties participant tout au long du cursus de chaque étudiant.

## Parties prenantes
### Demandeurs
Ce projet est à l'initiative des professeurs de la section informatique de la Haute Ecole Robert Schuman de Libramont, dans le cadre du projet intégré qui fait partie du  cursus des étudiants de la section informatique.

### Utilisateurs du produit
Le logiciel sera utilisé par des étudiants, des professeurs, des maitres de stages et par l'administrateur (qui est un professeur).
- **Etudiant :** tout étudiant de la section informatique de la HERS effectuant un stage/une recherche de stage ou son TFE.
- **Professeur** : personne enseignant à la HERS.
- **Promoteur** : professeur chargé de suivre le(s) stage(s) et/ou le(s) TFE(s) d’un étudiant.
- **Maître de stage** : employé de l’entreprise dans laquelle un étudiant effectue un stage chargé de suivre et évaluer l’étudiant au cours de son stage.
- **Président de jury**: la personne interne ou externe à la HERS dont la charge est de noter les étudiants sur leur stage/TFE après consultation avec les autres membres du jury. (à expliquer dans le contexte ou réel perçu)
- **Administrateur**: personne responsable du logiciel.

## Exigences du logiciel
### Exigences fonctionnelles
- Effectuer un backup quotidien et automatique de la base de données.


**Utilisateur :**
	1. Se connecter grâce à son identifiant et son mot de passe.

**Membres :**
	1. Consulter les propositions de stages et les sujets de TFE.
	2. Changer son mot de passe.
	3. Changer son adresse e-mail.
 	4. Consulter la liste des membres.
	5. Recevoir une notification.
	6. Consulter ses notifications.
	7. Accéder à une page du site selon un lien précisé dans une notification.

**Administrateur :**
	1. Créer un profil.
	2. Modifier ou supprimer un profil.
	3. Modifier les droits d’un profil.
	4. Créer un utilisateur avec un profil.
	5. Ajouter, modifier ou supprimer des utilisateurs.
	6. Créer/ajouter une liste d’utilisateurs.
	7. Changer le profil d’un utilisateur.
	8. Ajouter, modifier ou supprimer des critères dans la grille d’évaluation des stages/TFE. ??
	9. Créer les comptes automatiquement en fonction des listes des mails (??)
	10. Envoyer des mails automatiques contenant les mots de passe via le serveur mail.

**Professeur :**
	1. Faire une proposition de stage (entreprise, lieu, sujet, technologies, personne(s) de contact) dans la base de données.
	2. Modifier ou supprimer une proposition de stage.
	3. Permettre aux professeurs de modifier ou supprimer un sujet de TFE.
	4. Valider ou refuser une proposition de stage.
	5. Valider ou refuser un sujet de TFE.            
	6. Suivre un TFE/stage d’un étudiant, il devient donc promoteur.
	7. Créer un calendrier de suivi des stages/TFE à compléter par l’étudiant à des échéances prévues.
	8. Modifier ou supprimer des éléments du calendrier de suivi.

**Président du jury :**
	1. Mettre des points pour les différentes parties de l'évaluation (parties écrite, orale, suivi, démonstration éventuelle, ou autres critères) ;
	2. Modifier ou supprimer une note dans la grille d’évaluation.

**Etudiant :**
	1. Lister les endroits de stages et leurs informations « validés ou non ».
	2. Lister les sujets de TFE validés ou non.
	3. S’ajouter à un stage proposé n’ayant pas d’étudiant associé.
	4. Faire une proposition de stage.
	5. Faire une proposition de sujet de TFE (thème ?).
	6. Poster un travail exigé à l’échéance prévue par le calendrier créé par les professeurs.
	7. Modifier ou supprimer leur travail si la date est inférieure à la date prévue par le calendrier.
	8. Poster un commentaire sur ses suivis de stages, sur son avancement de TFE (possibilité de poser une question directement au promoteur de stage, au professeur concerné). 

**Maître de stage :**
	1. Faire une évaluation de fin de stage

### Exigences non fonctionnelles
1. Langue : français. 
2. Pas de limite au nombre d’utilisateurs. 
3. Un seul administrateur.


## Lexique
### Définitions
- endroit de stage: entreprise et adresse du lieu où est réalisé un stage.