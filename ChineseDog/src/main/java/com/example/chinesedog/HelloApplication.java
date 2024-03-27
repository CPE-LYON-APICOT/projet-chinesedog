package com.example.chinesedog;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class HelloApplication extends Application {
    @Override
    public void start(Stage primaryStage) {

        List<List<Case>> cases = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            List<Case> ligne = new ArrayList<>();
            for (int j = 0; j < 10; j++) {
                ligne.add(new Case(i, j, TypeCase.H, "C:\\Users\\Portal\\Desktop\\Cours\\POO\\Projet\\projet-chinesedog\\ChineseDog\\src\\main\\java\\com\\example\\chinesedog\\assets\\Texture\\4\\PNG\\mapTile_022.png"));
            }
            cases.add(ligne);
        }

        // Test Ennemi/Vague
        List<Ennemi> ennemis = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Ennemi ennemi = new EnnemiTerrestre(100, 10, 10, 10, "testTerrestrial", "C:\\Users\\Portal\\Desktop\\Cours\\POO\\Projet\\projet-chinesedog\\ChineseDog\\src\\main\\java\\com\\example\\chinesedog\\assets\\Enemy\\2\\Foozle_2DC0028_Spire_EnemyPack_2_Ground\\Ground\\Previews\\Scorpion.gif", false);
            Ennemi ennemivol = new EnnemiVolant(100, 10, 10, 10, "testennemivol", "C:\\Users\\Portal\\Desktop\\Cours\\POO\\Projet\\projet-chinesedog\\ChineseDog\\src\\main\\java\\com\\example\\chinesedog\\assets\\Enemy\\2\\Foozle_2DC0028_Spire_EnemyPack_2_Ground\\Ground\\Previews\\Scorpion.gif", false);
/*            System.out.println(ennemi);*/
            ennemis.add(ennemi);
            ennemis.add(ennemivol);
        }
        Vague vague = new Vague(10, 1, 1, ennemis);
        vague.displayVague();

        Carte carte = new Carte(10, 10, cases);
        String test = "HHHHHHHHCCHHHHCCHHHHCCTHHTCCHHHHCCTHHTCCHHHHCCTHHTCCHHHHCCHH";
        Carte carte2 = new Carte(10, 6, test);
        Carte carteConverti = carte2.switchStringToCarte(test);
/*        carteConverti.displayCarte();*/

        // Création d'une grille pour afficher les images
        GridPane gridPane = new GridPane();

        // Parcourir la liste de listes d'objets Case
        for (int i = 0; i < carteConverti.getHauteur(); i++) {
            List<Case> ligne = carteConverti.getCases().get(i);
            for (int j = 0; j < ligne.size(); j++) {
                Case currentCase = ligne.get(j);
                String imagePath = currentCase.getImagePath();
                Image image = new Image("file:" + imagePath); // Chargement de l'image à partir du chemin d'accès
                ImageView imageView = new ImageView(image); // Création d'une vue d'image
                gridPane.add(imageView, j, i); // Ajout de l'image à la grille
            }
        }

        // Création de la scène et affichage
        Scene scene = new Scene(gridPane, 850, 850);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Affichage des images des cases");
        primaryStage.show();

    }

    public static void main2(String[] args) {
        launch(args);
    }
}