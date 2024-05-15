
# Retro-conception

**Binome 1 : PORTAL Thierry**
**Binome 2 : HUBERT Eliott**

Complétez ce document pour décrire votre projet, les difficultés rencontrées, les design patterns mis en oeuvre, les améliorations possibles, et en quoi la POO vous a été utile.

> **Faites le avec sérieux, ce document ainsi que votre code seront évalués.**
Si vous considérez que votre code est quasi-parfait, je vais chercher les erreurs et en trouver, et cela vous pénalisera.
Si vous êtes critique envers vous-même et que vous expliquez correctement vos difficultés, vous serez "à moitié" pardonné.

Ce n'est pas grave de ne pas avoir été au bout de ce que vous vouliez faire, comportez vous comme un ingénieur qui doit rendre des comptes à son client, et qui doit expliquer pourquoi il n'a pas pu tout faire.
Pour rappel le client n'est pas un developpeur (sinon il ne vous aurait pas payé une fortune pour le faire à sa place), vous devez lui expliquer de manière simple et claire les problèmes rencontrés, en vous justifiant 
>Imaginez que vous avez codé Mortal Kombat 
Ne dites pas "je n'ai pas eu le temps de tout faire", mais plutôt "j'ai préféré me concentrer sur la création des spectaculaires "Finish Him" des personnages car c'est un élément essentiel du gameplay, cependant la difficulté dynamique en fonction de la maîtrise du joueur n'a pas pu être implémentée à temps, compte tenu que les critères de maîtrises sont difficilement modélisables, toutefois nous pourrions envisager d'implémenter que plus le combat dure longtemps, plus les coups portés sont puissants, ce qui est rapide à implémenter et lors d'une mise à jour, nous pourrions remplacer ce système par quelque chose de plus élaboré"

Aussi, en entreprise, vous serez confronté à des programmes très mal codés, et vous allez galérer à les comprendre, vous risquez d'essayer de les corriger et tomber dans les mêmes ecueils que les développeurs précédents.
Pour cela, il est courrant de tenir un jour un Document d'Architecture Technique (DAT) qui explique comment fonctionne le programme, et comment le reprendre ainsi qu'un document de réversibilité qui explique comment reprendre le code de quelqu'un d'autre.
(C'est obligatoire pour les marchés publics de prévoir une réversibilité, c'est à dire que le client peut vous dégager et une autre entreprise doit pouvoir reprendre votre code sans difficulté)
Dans ces documents, il ne s'agit pas de cacher la poussière sous le tapis, il faut être honnête et proposer une vision d'ensemble de votre code, et expliquer pourquoi vous avez fait des choix, et pourquoi vous n'avez pas fait d'autres choix, il est souvent question de compromis (on fait un truc pas ouf pour gagner du temps, mais la qualité du code en pâtit, etc.)
> Vous pouvez dire : "Pour la gestion des collisions, nous utilisons une librairie tierce, toutefois celle-ci ne gère que les collisions entre des rectangles, au fur et à mesure des itérations, des ennemis de grande taille et de forme complexe sont apparus, toutefois, nous avons conservé une hitbox rectangulaire, en résulte que le joueur peut être touché alors que visuellement, il n'est pas en contact avec l'ennemi ; nous avions également envisagé de créer plusieurs hitbox de tailles différentes sur un même ennemi afin de mieux coller à la forme de celui-ci, toutefois, les performances du jeu ont étés trop dégradées"



---
# Partie "Client" (pas trop technique) :

## Objectif du projet

Notre projet consiste en un jeu de tower defense où les joueurs doivent défendre leur terre contre des vagues d'ennemis en construisant et en améliorant diverses tours de défense. Le jeu est composé d'une carte sur laquelle certaines cases sont réservées à la construction de tour avec de l'argent gagné en tuant des ennemis. Les ennemis apparaissent à un endroit de la carte et se dirigent vers un autre endroit. Si un ennemi atteint cet autre endroit, le joueur perd de la vie.
Dans l'idéal, le jeu devrait proposer plusieurs types de tours, plusieurs types d'ennemis, et plusieurs cartes.

## Résultat

Aujourd'hui notre jeu affiche une carte avec des cases réservées à la construction de tours. Nous sommes capables de construire des tours ainsi que de les améliorer. Les ennemis apparaissent à un endroit de la carte et se dirigent vers un autre endroit. Nous avons également implémenté un système de vague d'ennemis encore incomplet. Cependant il nous est 
encore pas possible de tuer des ennemis ainsi que de gagner ou de perdre la partie.

### Améliorations possibles

Nous aurrions aimé implémenter le système pour tuer les ennemis car cela nous aurait permi d'avoir un début de vrai gameplay.

# Partie "Développeur" (plus technique) :


### Implémentations remarquables

[Si pendant votre implémentation, vous trouvez que vous pouvez être particulièrment fiers d'une partie de votre code, décrivez là ici ; par exemple si vous avez généré une carte de manière procédurale, ou à l'aide d'un fichier]

### Faiblesses du code

[C'est ici que vous me dites ce que vous savez que vous avez mal fait, expliquez pourquoi vous avez fait ce choix (manque de temps, manque de compétence, trop pénible à faire, etc.)]

### Difficultés rencontrées

#### 1. [Génération dynamique des ... pour ...]

[Expliquez ici la difficulté rencontrée et comment vous l'avez contournée]

#### 2. [Gestion des collisions]

[Exemple : Nous n'avons pas réussi à gérer les collisions, PARCE QUE, mes objets n'héritaient pas d'une classe commune, car nos objets héirtaient de ... et nos personnages de ...]


### *Design Patterns* mis en oeuvre

#### 1. [Builder]

<pre>
</pre>

---

#### 2. [Decorator]

Le decorator nous a permis d'implémenter facilement notre système de mise à niveau des tours. Cela nous permet de maintenir le code facilement.
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

# Partie pédagogique


### En quoi la POO vous a été utile

[Par exemple, expliquez que vous auriez éprouvé des difficultés à gérer les collisions si vous n'aviez pas utilisé la POO, ou que vous avez pu facilement ajouter des fonctionnalités à votre jeu grâce à la POO
Minimum 10 lignes (personnalisé en fonction de votre projet)]

### Conclusion

[Décrivez ici si vous avez compris un concept particulier que vous n'aviez pas compris en cours, inversement si vous pensiez qu'il était possible de faire qqchose mais que cela ne s'est pas passé comme prévu]