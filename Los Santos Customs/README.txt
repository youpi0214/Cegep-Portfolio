# Wall-E Project

 Projet d'integration SIM hiver 2020
 controler une voiture télécommandée pouvant à partir de son ordinateur 

## Caractéristiques
 - Déplacement autonome de l'auto tout en évitant les obstacles
 - Suivi d'itinéraire prédéfini tout en évitant les obstacles
 - Résolution de labyrinthe, l'auto est capable de se deplacer et sortir d'un labyrinthe par elle-même
 - Contrôle de l'auto avec une manette ou le clavier d'ordinateur (Contrôle par défaut est au clavier)
 - Affichage vidéo
 - simulation de déplacement sur divers terrains
 - L'auto simule le comportement physique d'une vrai voiture
   - terrain sableux
   - Asphalte
   - terrain boueux
 
 ## Exigences
 - [Télécharger](https://www.oracle.com/java/technologies/javase-jre8-downloads.html) la JRE de Java 8. Ne fonctionnera pas avec une version antérieure et risque d'avoir des bug avec une version ultérieure.
 - Une [voiture](https://www.amazon.ca/-/fr/t%C3%A9l%C3%A9commande-Robotics-lapprentissage-%C3%A9ducatif-%C3%A9lectronique/dp/B078KX3ZL9/ref=sr_1_1?__mk_fr_CA=%C3%85M%C3%85%C5%BD%C3%95%C3%91&keywords=arduino+car+camera&qid=1580421808&sr=8-1), équipée de modules d'ultrasons (viens à part), d'un GPS (à acheter), d'un routeur Wifi et d'une camera

## Utilisation basique
#### Connection de l'auto
  Après la selection du mode à l'ouverture, une fenêtre de connection s'affiche et demande à l'utilisateur de choisir une adresse IP et un port pour se connecter à la voiture.
  En temps normal, une adresse IP et un port par défaut sont déjà sélectionnés. Pour se connecter au robot que nous avons actuellement, c'est suffisant, l'utilisateur peut passer à utiliser le robot
  Si l'utilisateur veut changer l'IP et le port à sa guide, il suffit de cliquer sur la boîte crochetée, et il pourra changer l'IP et le port, mais ce n'est pas nécessaire avec le robot que nous utilisaons actuellement.
#### Utilisation des interfaces de contrôle
Les commandes pour contrôler la voiture se trouvent en bas à gauche de l'écran et les données relatives à l'auto vont s'afficher en temps réel en bas à droite de l'écran. Si de l'aide supplémentaire est requise, des tutoriels sont disponibles en haut de l'écran, dans l'onglet aide
Les touches de clavier sont "Bindées" au contrôle de la voiture à l'avance, donc utiliser les flèches du clavier permet de faire déplacer la voiture lorsqu'on se trouve en contrôle Manuel. Pour le contrôle Automatique, il suffit d'appuyer sur Enter une fois que les deux cooredonnées ont entrées.

## Important : 
Importer correctement les libraires fournies dans le dossier du projet, suffit de configurer le Build Path du projet et de les importer une par une manuellement

## Ajouts du Sprint 4 : 

Contrôle adaptif de la voiture, on peut maintenant décider de la vitesse de la voiture ainsi que tourner en même temps qu'avancer. La classe labyrinthe est fonctionnelle, elle permet de résoudre des labyrinthe qui seront confrontés à la voiture.
