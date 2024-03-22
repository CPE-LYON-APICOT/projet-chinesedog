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
import java.io.IOException;
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

        Carte carte = new Carte(10, 10, cases);
        System.out.println(carte.displayCarte());
        String test = "HHHHHBBHHHHHBBHHHHHCCCHHHTTTTT";
        Carte carte2 = new Carte(5, 6, test);
        Carte carteConverti = carte2.switchStringToCarte(test);
        carteConverti.displayCarte();

        // Création d'une grille pour afficher les images
        GridPane gridPane = new GridPane();

        // Parcourir la liste de listes d'objets Case
        for (int i = 0; i < carte.gethauteur(); i++) {
            for (int j = 0; j < carte.getlargeur(); j++) {
                Case c = carte.getCases().get(i).get(j);
                // Création de l'image
                Image image = new Image(new File(c.getImagePath()).toURI().toString());
                ImageView imageView = new ImageView(image);
                // Création d'un StackPane pour afficher l'image
                StackPane stackPane = new StackPane();
                stackPane.getChildren().add(imageView);
                // Ajout du StackPane à la grille
                gridPane.add(stackPane, j, i);
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