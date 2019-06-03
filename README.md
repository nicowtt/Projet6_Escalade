# OC_Projet6_Escalade

Voici un site communautaire autour de l'escalade qui répond 
aux besoins de l'association **"Les amis de l'escalade".**

Le but est la mise en relation et le partage d'informations. 
Il donnera de la visibilité à l'association et encouragera 
des grimpeurs indépendants à y adhérer.

## Les fonctions:

- F1 : Un utilisateur doit pouvoir consulter les informations des sites
d’escalades (secteurs, voies, longueurs, etc.).

- F2 : Un utilisateur doit pouvoir faire une recherche à l’aide de plusieurs
  critères pour trouver un site de grimpe et consulter le résultat de cette
  recherche. Les critères peuvent être le lieu, la cotation, le nombre de
  secteurs, etc.

- F3 : Un utilisateur doit pouvoir s’inscrire gratuitement sur le site.

- F4 : Un utilisateur connecté doit pouvoir partager des informations sur un
site d’escalade (secteurs, voies, longueurs, etc.).

- F5 : Un utilisateur connecté doit pouvoir laisser des commentaires sur les
pages des sites d’escalade.

- F6 : Un membre de l'association doit pouvoir taguer un site d’escalade
enregistré sur la plateforme comme « Officiel Les amis de l’escalade ».

- F7 : Un membre de l'association doit pouvoir modifier et supprimer un
commentaire.

- F8 : Un utilisateur connecté doit pouvoir enregistrer dans son espace
personnel les topos qu’ils possèdent et préciser si ces derniers sont
disponibles pour être prêtés ou non.
Un topo est défini par un nom, une description, un lieu et une date de
parution.

- F9 : Un utilisateur connecté doit pouvoir consulter la liste des topos
disponibles par d’autres utilisateurs et faire une demande de réservation.
La réservation n’inclut pas les notions de date de début et date de fin.

- F10 : Un utilisateur connecté doit pouvoir accepter une demande de
réservation. Si une réservation est acceptée, automatiquement le topo
devient indisponible. L’utilisateur connecté pourra ensuite de nouveau
changer le statut du topo en « disponible ».
La plateforme se contente de mettre en contact les deux parties pour le
prêt d’un topo (par échange de coordonnées).

Les fonctions en plus:

- F11: L’utilisateur peut supprimer un topo papier. (en cas de perte du doccument par ex)

- F12: Un membre de l’association loggé peut supprimer un site. 

- F13: Un membre de l’association peut détagué un site.


## Les contraintes fonctionnelles

- Vocabulaire de l'escalade utilisé.
- Le site est responsive.
- le site est sécurisé. (aucun mot de passe est stocké en clair dans la BDD)

## Déployment
Ce projet a été réalisé en Java avec Maven. <br>
Utilisation du framework Spring :
 - Spring MVC 
 - Spring tx
 - Spring security 
 - Integration BDD via ORM: Spring JDBC.

Base de donnée: PostgreSQL 9.6.12

Afin de lancer l'application web sur un serveur apache:

- Exécutez la commande maven suivante pour créer le package de déploiement :
    
    ** mvn package **
    
- Copier / coller le fichier **escalade-webapp.war** dans le dossier webapps de tomcat.

- Lancer votre serveur Tomcat et rendez-vous à l'adresse :

    http://localhost:8080/escalade-webapp/home
    

## Contribution

1: clone repositories

2: Créer une nouvelle branche

2: Faite vos évolutions / changements (git checkout -b my-new-feature)

3: "Commit" les évolutions / changements (git commit -am "add some feature")

4: "Push" la nouvelle branche (git push origin my-new-feature)

5: Créer une nouvelle "pull request"

## Auteur
Nicow

Voir mes autres projets :
[ICI](https://github.com/nicowtt?tab=repositories)

*twitter: nicow@nicowtt*



