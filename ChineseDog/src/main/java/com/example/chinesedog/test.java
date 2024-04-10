package com.example.chinesedog;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.StackPane;
import javafx.embed.swing.SwingFXUtils;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import java.io.File;

public class test extends Application {

    private static final int TILE_WIDTH = 32;
    private static final int TILE_HEIGHT = 32;

    @Override
    public void start(Stage primaryStage) throws IOException {
        // Load TMX file
        // Charger les images
        BufferedImage image1 = ImageIO.read(new File("C:\\Users\\Portal\\Desktop\\Cours\\POO\\Projet\\projet-chinesedog\\ChineseDog\\src\\main\\resources\\com\\example\\chinesedog\\assets\\Texture\\4\\PNG\\mapTile_024.png"));
        BufferedImage image2 = ImageIO.read(new File("C:\\Users\\Portal\\Desktop\\Cours\\POO\\Projet\\projet-chinesedog\\ChineseDog\\src\\main\\resources\\com\\example\\chinesedog\\assets\\Texture\\4\\PNG\\mapTile_027.png"));

        // Créer une nouvelle image avec une largeur et une hauteur suffisamment grandes pour contenir les deux images
        int combinedWidth = image1.getWidth() + image2.getWidth();
        int combinedHeight = Math.max(image1.getHeight(), image2.getHeight());
        BufferedImage combinedImage = new BufferedImage(combinedWidth, combinedHeight, BufferedImage.TYPE_INT_ARGB);

        // Dessiner les deux images sur l'image combinée
        Graphics2D g2d = combinedImage.createGraphics();
        g2d.drawImage(image1, 0, 0, null);
        g2d.drawImage(image2, image1.getWidth(), 0, null);
        g2d.dispose();

        // Enregistrer l'image combinée
        File outputFile = new File("combined_image.png");
        ImageIO.write(combinedImage, "PNG", outputFile);

        System.out.println("L'image combinée a été enregistrée avec succès.");

        // Dessiner les deux images sur l'image combinée
        BufferedImage graphicsImage = combinedImage.getSubimage(0, 0, image1.getWidth(), combinedHeight);
        graphicsImage.getGraphics().drawImage(image1, 0, 0, null);
        graphicsImage = combinedImage.getSubimage(image1.getWidth(), 0, image2.getWidth(), combinedHeight);
        graphicsImage.getGraphics().drawImage(image2, 0, 0, null);

        // Convertir l'image combinée en Image JavaFX
        Image fxImage = SwingFXUtils.toFXImage(combinedImage, null);

        // Créer un ImageView pour afficher l'image
        ImageView imageView = new ImageView(fxImage);

        // Créer une pile (StackPane) pour contenir l'ImageView
        StackPane root = new StackPane();
        root.getChildren().add(imageView);

        // Créer la scène et afficher la fenêtre
        Scene scene = new Scene(root, combinedWidth, combinedHeight);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Image combinée");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
