# Retro-conception

**Binome 1 : PORTAL Thierry**
**Binome 2 : HUBERT Eliott**

Complétez ce document pour décrire votre projet, les difficultés rencontrées, les design patterns mis en oeuvre, les améliorations possibles, et en quoi la POO vous a été utile.

> **Faites le avec sérieux, ce document ainsi que votre code seront évalués.**
Si vous considérez que votre code est quasi-parfait, je vais chercher les erreurs et en trouver, et cela vous pénalisera.
Si vous êtes critique envers vous-même et que vous expliquez correctement vos difficultés, vous serez "à moitié" pardonné.

Ce n'est pas grave de ne pas avoir été au bout de ce que vous vouliez faire, comportez vous comme un ingénieur qui doit rendre des comptes à son client, et qui doit expliquer pourquoi il n'a pas pu tout faire.
Pour rappel le client n'est pas un développeur (sinon il ne vous aurait pas payé une fortune pour le faire à sa place), vous devez lui expliquer de manière simple et claire les problèmes rencontrés, en vous justifiant
>Imaginez que vous avez codé Mortal Kombat
Ne dites pas "je n'ai pas eu le temps de tout faire", mais plutôt "j'ai préféré me concentrer sur la création des spectaculaires "Finish Him" des personnages car c'est un élément essentiel du gameplay, cependant la difficulté dynamique en fonction de la maîtrise du joueur n'a pas pu être implémentée à temps, compte tenu que les critères de maîtrises sont difficilement modélisables, toutefois nous pourrions envisager d'implémenter que plus le combat dure longtemps, plus les coups portés sont puissants, ce qui est rapide à implémenter et lors d'une mise à jour, nous pourrions remplacer ce système par quelque chose de plus élaboré"

Aussi, en entreprise, vous serez confronté à des programmes très mal codés, et vous allez galérer à les comprendre, vous risquez d'essayer de les corriger et tomber dans les mêmes écueils que les développeurs précédents.
Pour cela, il est courant de tenir un jour un Document d'Architecture Technique (DAT) qui explique comment fonctionne le programme, et comment le reprendre ainsi qu'un document de réversibilité qui explique comment reprendre le code de quelqu'un d'autre.
(C'est obligatoire pour les marchés publics de prévoir une réversibilité, c'est à dire que le client peut vous dégager et une autre entreprise doit pouvoir reprendre votre code sans difficulté)
Dans ces documents, il ne s'agit pas de cacher la poussière sous le tapis, il faut être honnête et proposer une vision d'ensemble de votre code, et expliquer pourquoi vous avez fait des choix, et pourquoi vous n'avez pas fait d'autres choix, il est souvent question de compromis (on fait un truc pas ouf pour gagner du temps, mais la qualité du code en pâtit, etc.)
> Vous pouvez dire : "Pour la gestion des collisions, nous utilisons une librairie tierce, toutefois celle-ci ne gère que les collisions entre des rectangles, au fur et à mesure des itérations, des ennemis de grande taille et de forme complexe sont apparus, toutefois, nous avons conservé une hitbox rectangulaire, en résulte que le joueur peut être touché alors que visuellement, il n'est pas en contact avec l'ennemi ; nous avions également envisagé de créer plusieurs hitbox de tailles différentes sur un même ennemi afin de mieux coller à la forme de celui-ci, toutefois, les performances du jeu ont étés trop dégradées"


---
# Partie "Client" (pas trop technique) :

## Objectif du projet

Notre projet consiste en un jeu de tower defense où les joueurs doivent défendre leur terre contre des vagues d'ennemis en construisant et en améliorant diverses tours de défense. Le jeu est composé d'une carte sur laquelle certaines cases sont réservées à la construction de tour avec de l'argent gagné en tuant des ennemis. Les ennemis apparaissent à un endroit de la carte et se dirigent vers un autre endroit. Si un ennemi atteint cet autre endroit, le joueur perd de la vie.
Dans l'idéal, le jeu devrait proposer plusieurs types de tours, plusieurs types d'ennemis, et plusieurs cartes.

## Résultat

Aujourd'hui notre jeu affiche une carte avec des cases réservées à la construction de tours. Nous sommes capables de construire des tours ainsi que de les améliorer. Les ennemis apparaissent au début du parcours et se dirigent vers la fin de celui-ci sous forme de vague. Nous avons également implémenté un système de vague d'ennemis qui au fil du temps ajoute de nouveaux monstres. Nos tours tirent sur les monstres et sont capables de les tuer (pas d'animation de tir + bug : les tours tirent sur tous les ennemis).

### Améliorations possibles

Nous aurions ajouter le système d'argent pour acheter les tours, argent qui se serait récupéré en tuant des monstres. De plus, on aurait pu ajouter davantage de monstres et de tours pour diversifier les éléments de jeu. Enfin, nous aurions pu améliorer le système de jeu en ajoutant différentes cartes pour chaque niveau, nous pourrions également penser à ajouter un éditeur de carte afin de créer et modifier facilement celle-ci. Il aurait été intéressant d'ajouter une détection de défaite, lorsque trop de monstres atteignent la fin de la carte.

# Partie "Développeur" (plus technique) :

### Implémentations remarquables


Moi Eliott je suis particulièrement fière de la partie des ennemis de leur déplacements et de leur suivi d’un chemins car cette partie ma pris une grande partie du temps lors de ce projets car je suis passer par de nombreux problème de la difficulté à savoir quelle chemins les ennemis doivent prendre aux ennemis ne bougent pas normalement ou vibrant de gauche à droite lorsqu’il se déplace trop vite ou encore détecter lorsqu’un ennemis arrive à un points de passage j’ai réussi à passer outre ces problèmes et je suis fier d’avoir réussi à faire marcher ce projet.

Pour ma part, moi Thierry, je suis fière de mon decorator
qui permet d’implémenter le système d’amélioration. Il ajoute une nouvelle dimension au jeu avec davantage de stratégie. De plus, le système et de calcule de distance pour permettre aux tourelles de tirer sur l'ennemi le plus proche est quelque chose qui nous a posé des problèmes mais dont nous sommes fières.


### Faiblesses du code

Le système de carte n'est pas très pratique car il faut modifier des chaînes de caractères pour changer la carte. De plus, le système de chaînes de caractères n’est pas très clair pour quelqu’un qui n’a pas la logique de base.

Certains éléments du magasin sont codés en dur, ce qui peut rendre difficile l'ajout de nouveaux éléments. De plus, leur affichage dépend d'un ordre précis dans notre StackPane qui n'est pas très compréhensible si un autre développeur intégrait notre groupe.

Certaines de nos fonctions comportent des signatures à rallonger où l’on passe énormément d’arguments. On pourrait envisager de prendre davantage de temps pour fragmenter les fonctions. Idem pour les fichiers qui contiennent trop de lignes.

La structure du projet n'est probablement pas assez propre et professionnelle.

### Difficultés rencontrées

#### 1. [Génération dynamique des ... pour ...]

Nous avons eu des difficultés à générer dynamiquement les vagues d'ennemis ainsi qu'à leur faire parcourir un chemin prédéfini. Pour parvenir à nos fins, nous avons créé une liste de "waypoint" qui sont des points de passage obligatoire pour les ennemis qui se déplacent vers ce point tant qu'il ne l'ont pas approché.

Nous avons également eu des difficultés pour la gestion des tours, leur affichage et amélioration. Pour y arriver, nous avons créé une classe "Tour" qui contient les attributs de base de chaque tour, et des classes filles qui héritent de cette classe et qui contiennent les attributs spécifiques à chaque tour. Nous avons également utilisé le design pattern "Decorator" pour gérer les améliorations des tours.

#### 2. [Gestion des collisions]

Nous n’avons pas eu de collision à gérer ; cependant si nous avions intégré une animation de tir pour les canons, nous aurions dû intégrer la collision du tir avec un ennemi.


### *Design Patterns* mis en oeuvre

#### 1. [Builder]

Ce pattern est utilisé pour construire des objets complexes étape par étape. Il permet de créer différents types et représentations d'un objet en utilisant le même processus de construction.

<pre>
package com.example.chinesedog.Model;

import java.util.List;

public class CarteBuilder {
    String carteString;
    String carteStringSansEspace;
    int hauteur;
    int largeur;
    List<List<Case>> cases;

    public CarteBuilder(int hauteur, int largeur) {
        this.hauteur = hauteur;
        this.largeur = largeur;
    }

    public CarteBuilder setCarteString(String carteString) {
        this.carteString = carteString;
        return this;
    }

    public CarteBuilder setCarteStringSansEspace(String carteStringSansEspace) {
        this.carteStringSansEspace = carteStringSansEspace;
        return this;
    }

    public CarteBuilder setCases(List<List<Case>> cases) {
        this.cases = cases;
        return this;
    }

    public Carte build() {
        return new Carte(this);
    }

}


Carte carte = new CarteBuilder(numRows, numCols).setCarteString(map).setCarteStringSansEspace(mapSansEspace).build();
</pre>

---

#### 2. [Decorator]

Le décorateur nous a permis d'implémenter facilement notre système de mise à niveau des tours. Cela nous permet de maintenir le code facilement.
Les niveaux permettent d'ajouter de nouveaux comportements à la tour sans modifier le code existant. Notre code reste ouvert à l'extension car il suffit de rajouter une classe
niveauX pour ajouter un nouveau comportement à la tour.

<pre>
private void upgradeTower(Tour tour) {
        System.out.println("Niveau de la tour : " + tour.getNiveau());
        if (tour.getNiveau() == 1) {
            new Niveau2(tour);
            System.out.println("La tour a été mise à niveau 2 avec succès !");
        }
        else if (tour.getNiveau() == 2) {
            new Niveau3(tour);
            System.out.println("La tour a été mise à niveau 3 avec succès !");
        }
        else {
            System.out.println("La tour est déjà au niveau maximum.");
        }
    }
</pre>

#### 3. [Event Handler]

Le design pattern Event Handler nous a permis de gérer les événements liés au clic de souris tels que l’achats,l’améliorations des tour mais égalements pour ouvrir ou fermer le shop en fonction de la case cliquer

<pre>
public MapClickHandler(double imageX, double imageY, int numRows, int numCols, double cellWidth, double cellHeight, StackPane root, ImageView shopView, String mapSansEspace, Carte carteConvertie) {
   this.imageX = imageX;
   this.imageY = imageY;
   this.numRows = numRows;
   this.numCols = numCols;
   this.cellWidth = cellWidth;
   this.cellHeight = cellHeight;
   this.root = root;
   this.shopView = shopView;
   this.mapSansEspace = mapSansEspace;
   this.carteConvertie = carteConvertie;
}

@Override
public void handle(MouseEvent event) {
   double mouseX = event.getX();
   double mouseY = event.getY();

   int row = (int) (mouseY / cellHeight);
   int col = (int) (mouseX / cellWidth);

   setImageX((row + 1) * cellHeight);
   setImageY((col + 1) * cellWidth);

   System.out.println("Case sélectionnée : Ligne " + mouseX + ", Colonne " + mouseY);
   System.out.println("Case sélectionnée : Ligne " + row + ", Colonne " + col);
   System.out.println("Case sélectionnée : Ligne " + imageX + ", Colonne " + imageY);

   // Appeler les méthodes pour ouvrir ou fermer le magasin selon la case sélectionnée
   if (row >= 0 && row < numRows && col >= 0 && col < numCols) {
       Case selectedCase = carteConvertie.getCase(row, col);
       // Récupérez la valeur de l'attribut isOccupied
       boolean isOccupied = selectedCase.getIsOccupied();
       System.out.println("La case sélectionnée est occupée ? : " + isOccupied);
       if (mapSansEspace.charAt(row * numCols + col) == 'T' && !isOccupied) {
           System.out.println("Ouverture du magasin");
           ouvrirShop(root, shopView);
       } else {
           System.out.println("Fermeture du magasin");

           for (Node node : root.getChildren()) {
               if (node instanceof Text text) {
                   text.setText("");
               }
               else if (node instanceof Button button) {
                   button.setVisible(false);
               }
           }

           fermerShop(root, shopView);
       }
   }
}

</pre>

<pre>
public ButtonClickHandler(Tour tour, StackPane root) {
        this.tour = tour;
        this.root = root;
    }

    @Override
    public void handle(ActionEvent event) {
        upgradeTower(tour);
        updateText();
        event.consume();
    }
</pre>

#### 4. [Factory]

Le design pattern de Factory nous à permis de créer des vagues d'ennemis différents facilement et nous permettra de facilement ajouter de nouveaux types d’ennemis.

<pre>
public interface EnemyFactory {
    Enemy createEnemy(List<String> waypoints, int vie, double vitesse, int armure, int resistanceMagique);
}
</pre>
<pre>
public class EnemyTerrestreFactory implements EnemyFactory {
    @Override
    public Enemy createEnemy(List<String> waypoints, int vie, double vitesse, int armure, int resistanceMagique) {
        return new EnemyTerrestre(waypoints, vie, vitesse, armure, resistanceMagique);
    }
}
</pre>

<pre>
public class EnemyVolantFactory implements EnemyFactory {
    @Override
    public Enemy createEnemy(List<String> waypoints, int vie, double vitesse, int armure, int resistanceMagique) {
        return new EnemyVolant(waypoints, vie, vitesse, armure, resistanceMagique);
    }
}

</pre>
<pre>
public class EnemyBossFactory implements EnemyFactory {
    @Override
    public Enemy createEnemy(List<String> waypoints, int vie, double vitesse, int armure, int resistanceMagique) {
        return new EnemyBoss(waypoints, vie, vitesse, armure, resistanceMagique);
    }
}

</pre>

# Partie pédagogique


### En quoi la POO vous a été utile


Dans notre projet de jeu de tower defense, l'utilisation de la programmation orientée objet (POO) s'est avérée être un choix crucial pour plusieurs raisons. Tout d'abord, la POO nous a permis de structurer notre code de manière modulaire et organisée. En créant des classes pour les différentes entités du jeu telles que les tours, les ennemis, etc., nous avons pu séparer les responsabilités et rendre notre code plus facile à comprendre, à maintenir et à étendre. L’utilisation de la POO nous à également faciliter la création des ennemis et leurs gestion (mouvements, détection, etc) car sans il aurait fallu écrire en dur chaque ennemis stocker sa position son chemins si il est à porter etc. La POO nous a offert la flexibilité nécessaire pour ajouter de nouvelles fonctionnalités à notre jeu de manière aisée. Par exemple, lorsque nous avons décidé d'introduire différents types de tours et d'ennemis, nous avons simplement créé de nouvelles classes héritant des fonctionnalités de base, ce qui nous a permis d'étendre facilement notre système sans perturber le fonctionnement existant.

### Conclusion

Mettre en pratique les design pattern nous ont permis de mieux comprendre l'utilité et la pertinence de ceux-ci.
Ce projet nous a également permis de prendre conscience qu’en assemblant nos connaissances nous étions capable de réaliser un jeu assez complet et fonctionnel.

