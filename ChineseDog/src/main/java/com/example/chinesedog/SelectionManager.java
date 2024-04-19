package com.example.chinesedog;

import javafx.animation.ScaleTransition;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class SelectionManager {
    private static ImageView selectedCanon;
    private static Rectangle selectionIndicator;

    public static ImageView selectCanon(ImageView imageView, StackPane root) {
        deselectCanon();

        // Créer une transition d'échelle
        ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(0.05), imageView);
        scaleTransition.setByX(0.2);
        scaleTransition.setByY(0.2);
        scaleTransition.setCycleCount(2);
        scaleTransition.setAutoReverse(true);

        // Démarrer la transition
        scaleTransition.play();

        // Mettre à jour la référence du canon sélectionné
        selectedCanon = imageView;

        // Afficher l'indicateur de sélection
/*        showSelectionIndicator(imageView, root);*/

        return selectedCanon;
    }

    public static void deselectCanon() {
        // Supprimer l'indicateur de sélection
        if (selectionIndicator != null && selectedCanon != null) {
            selectedCanon.setEffect(null);
            selectedCanon = null;
            selectionIndicator = null;
        }
    }
/*
    private static void showSelectionIndicator(ImageView imageView, StackPane root) {
        // Créer un rectangle rouge autour de l'image sélectionnée
        selectionIndicator = new Rectangle(imageView.getBoundsInLocal().getWidth() + 10, imageView.getBoundsInLocal().getHeight() + 10);
        selectionIndicator.setFill(Color.TRANSPARENT);
        selectionIndicator.setStroke(Color.RED);
        selectionIndicator.setArcWidth(10);
        selectionIndicator.setArcHeight(10);

        // Convertir les coordonnées de l'image de son système de coordonnées local au système de coordonnées de la scène
       Bounds boundsInScene = imageView.localToScene(imageView.getBoundsInLocal());

        // Capturer le contenu graphique du rectangle dans une image
        WritableImage writableImage = new WritableImage((int) selectionIndicator.getWidth(), (int) selectionIndicator.getHeight());
        SnapshotParameters params = new SnapshotParameters();
        selectionIndicator.snapshot(params, writableImage);
        ImageView selectionImageView = new ImageView(writableImage);

        selectionImageView.setX(boundsInScene.getMinX());
        selectionImageView.setY(boundsInScene.getMinY());
        System.out.println("Bounds in scene: " + boundsInScene.getMinX());
        System.out.println("Bounds in scene: " + boundsInScene.getMinY());

        // Ajouter l'indicateur de sélection au parent du StackPane
        root.getChildren().addAll(selectionImageView);
    }*/




}
