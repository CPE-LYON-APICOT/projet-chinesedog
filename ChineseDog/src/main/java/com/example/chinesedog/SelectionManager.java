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

}
