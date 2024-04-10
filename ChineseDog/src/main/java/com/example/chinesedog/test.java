package com.example.chinesedog;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.embed.swing.SwingFXUtils;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javafx.scene.control.Button;
import javafx.util.Duration;
import javafx.animation.TranslateTransition;

public class test extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
        String currentPath = System.getProperty("user.dir");
        // Load TMX file
        // Charger les images
        BufferedImage herbe = ImageIO.read(new File(currentPath + "\\src\\main\\resources\\com\\example\\chinesedog\\assets\\Texture\\4\\PNG\\mapTile_022.png"));
        BufferedImage pierre = ImageIO.read(new File(currentPath + "\\src\\main\\resources\\com\\example\\chinesedog\\assets\\Texture\\4\\PNG\\mapTile_027.png"));
        BufferedImage chemin = ImageIO.read(new File(currentPath + "\\src\\main\\resources\\com\\example\\chinesedog\\assets\\Texture\\4\\PNG\\mapTile_082.png"));

        int numRows = 10; // Nombre de lignes dans votre carte
        int numCols = 10; // Nombre de colonnes dans votre carte

        String map = "HHHHHCHHTH HHHHTCCCCH HHHHHHHTCH HTHHTHHHCH HCCCCHHHCH HCHTCHHHCT HCHCCHHHCH TCHCHHHTCH HCHCCCCCCH HCHHTHHHHH";
        String mapSansEspace = "HHHHHCHHTHHHHHTCCCCHHHHHHHHTCHHTHHTHHHCHHCCCCHHHCHHCHTCHHHCTHCHCCHHHCHTCHCHHHTCHHCHCCCCCCHHCHHTHHHHH";


        // Créer une nouvelle image avec une largeur et une hauteur suffisamment grandes pour contenir les deux images
        int combinedWidth = herbe.getWidth() * numRows;
        int combinedHeight = herbe.getHeight() * numCols;
        int currentX = 0;
        int currentY = 0;

        BufferedImage combinedImage = new BufferedImage(combinedWidth, combinedHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = combinedImage.createGraphics();

        for (int i = 0; i < map.length(); i++) {
            char c = map.charAt(i);
            System.out.println(c);
            switch (c) {
                case 'H':
                    g2d.drawImage(herbe, currentX, currentY, null);
                    currentX += herbe.getWidth();
                    break;
                case 'C':
                    g2d.drawImage(chemin, currentX, currentY, null);
                    currentX += chemin.getWidth();
                    break;
                case 'T':
                    g2d.drawImage(pierre, currentX, currentY, null);
                    currentX += pierre.getWidth();
                    break;
                case ' ':
                    currentX = 0;
                    currentY += herbe.getHeight();
                    break;
            }
        }
        g2d.dispose();
        // Enregistrer l'image combinée
        File outputFile = new File("combined_image.png");
        ImageIO.write(combinedImage, "PNG", outputFile);

        // Convertir l'image combinée en Image JavaFX
        Image fxImage = SwingFXUtils.toFXImage(combinedImage, null);

        // Créer un ImageView pour afficher l'image
        ImageView imageView = new ImageView(fxImage);

        // Créer une pile (StackPane) pour contenir l'ImageView
        StackPane root = new StackPane();
        Scene scene = new Scene(root, combinedWidth, combinedHeight);
        root.getChildren().add(imageView);
        ImageView shopView = createShopView(root);
        shopView.setVisible(false);
        // Ajoute un gestionnaire d'événements de clic à l'ImageView
        imageView.setOnMouseClicked(event -> {
            // Obtenir les coordonnées du clic
            double mouseX = event.getX();
            double mouseY = event.getY();

            // Calculer l'index de la case sélectionnée
            int row = (int) (mouseY / herbe.getHeight());
            int col = (int) (mouseX / herbe.getWidth());
            int numCase = (numRows * row) + col;
            // Vérifier si la case sélectionnée est dans les limites de votre carte
            if (row >= 0 && row < numRows && col >= 0 && col < numCols) {
                System.out.println("Case sélectionnée : Ligne " + row + ", Colonne " + col);
                for (int i = 0; i < map.length(); i++) {
                    if (i == numCase) {
                        System.out.println(mapSansEspace.charAt(i));
                        if (mapSansEspace.charAt(i) == 'T') {
                            ouvrirShop(root, shopView);
                        }
                        else {
                            fermerShop(root, shopView);
                        }
                    }
                }
            }
        });

        primaryStage.setScene(scene);
        primaryStage.setTitle("Image combinée");

        primaryStage.show();
    }

    private void ouvrirShop(StackPane root, ImageView shopView) {
        // Utilisation de la même instance de l'ImageView du magasin
        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(1), shopView);
        translateTransition.setFromX(400); // Position initiale sur l'axe X (à droite)
        translateTransition.setToX(260);    // Position finale sur l'axe X (au centre)
        translateTransition.setFromY(50); // Position initiale sur l'axe X (à droite)
        translateTransition.setToY(30);    // Position finale sur l'axe X (au centre)
        translateTransition.play();
        shopView.setVisible(true);
    }

    private ImageView createShopView(StackPane root) {
        String currentPath = System.getProperty("user.dir");
        ImageView shopView = new ImageView(new Image("file:///" + currentPath + "/src/main/resources/com/example/chinesedog/assets/shop/side.png"));
        root.getChildren().add(shopView);
        return shopView;
    }

    private void fermerShop(StackPane root, ImageView shopView) {
        // Utilisation de la même instance de l'ImageView du magasin
        System.out.println("Fermer shop");
        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(1), shopView);
        translateTransition.setFromX(260); // Position initiale sur l'axe X (au centre)
        translateTransition.setToX(400);   // Position finale sur l'axe X (à droite, hors de l'écran)
        translateTransition.play();
        shopView.setVisible(false);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
