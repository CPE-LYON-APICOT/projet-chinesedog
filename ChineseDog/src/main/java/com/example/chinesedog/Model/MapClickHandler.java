package com.example.chinesedog.Model;

import javafx.animation.TranslateTransition;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class MapClickHandler implements EventHandler<MouseEvent> {
    private final Carte carteConvertie;
    private double imageX;
    private double imageY;
    private final int numRows;
    private final int numCols;
    private final double cellWidth;
    private final double cellHeight;
    private final StackPane root;
    private final ImageView shopView;
    private final String mapSansEspace;

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
                    if (node instanceof Text) {
                        Text text = (Text) node;
                        text.setText("");
                    }
                    else if (node instanceof Button) {
                        Button button = (Button) node;
                        button.setVisible(false);
                    }
                }

                fermerShop(root, shopView);
            }
        }
    }

    private void ouvrirShop(StackPane root, ImageView shopView) {
        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(0.3), shopView);
        translateTransition.setFromX(400);
        translateTransition.setToX(260);
        translateTransition.setFromY(50);
        translateTransition.setToY(30);
        translateTransition.play();
        shopView.setVisible(true);

        // Parcourir les enfants du StackPane
        for (int i = 0; i < root.getChildren().size(); i++) {
            ImageView imageView = (ImageView) root.getChildren().get(i);
            if (!imageView.isVisible()) {
                imageView.setVisible(true);
            }
        }
    }

    private void fermerShop(StackPane root, ImageView shopView) {
        // Utilisation de la même instance de l'ImageView du magasin
        System.out.println("Fermer shop");
        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(0.3), shopView);
        translateTransition.setFromX(260); // Position initiale sur l'axe X (au centre)
        translateTransition.setToX(400);   // Position finale sur l'axe X (à droite, hors de l'écran)
        translateTransition.play();
        for (int i = 1; i < 5; i++) {
            ImageView imageView = (ImageView) root.getChildren().get(i);
            imageView.setVisible(false);
        }

    }

    public double getImageX() {
        return imageX;
    }
    public double getImageY() {
        return imageY;
    }
    public void setImageX(double imageX) {
        this.imageX = imageX;
    }
    public void setImageY(double imageY) {
        this.imageY = imageY;
    }
}
