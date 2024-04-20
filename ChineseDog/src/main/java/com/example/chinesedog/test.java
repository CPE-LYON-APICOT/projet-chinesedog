package com.example.chinesedog;

import com.example.chinesedog.Model.*;
import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.awt.*;
import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;

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
        Carte carte = new Carte(numRows, numCols, map, mapSansEspace);
        List<List<Case>> cases = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            List<Case> ligne = new ArrayList<>();
            for (int j = 0; j < 10; j++) {
                ligne.add(new Case(i, j, TypeCase.valueOf(String.valueOf(mapSansEspace.charAt(i * 10 + j))), false));
                System.out.print(ligne.get(j).getType());
            }
            cases.add(ligne);
        }

        Carte carteConverti = carte.switchStringToCarte(mapSansEspace);
        carteConverti.displayCarte();

       /// Test Ennemi/Vague
        List<Ennemi> ennemis = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Ennemi ennemi = new EnnemiTerrestre(100, 10, 10, 10, "testTerrestrial", "C:\\Users\\Portal\\Desktop\\Cours\\POO\\Projet\\projet-chinesedog\\ChineseDog\\src\\main\\java\\com\\example\\chinesedog\\assets\\Enemy\\2\\Foozle_2DC0028_Spire_EnemyPack_2_Ground\\Ground\\Previews\\Scorpion.gif",false);
            System.out.println(ennemi);
            ennemis.add(ennemi);
        }
        Vague vague = new Vague(10, 1, 1, ennemis);
        vague.displayVague();

        Tour canon = new Canon("Canon", 1, 50, 10, 3, 10);
        System.out.println("Description = \n" + canon.getDescription());
        canon = new Niveau2(canon);
        System.out.println("\n\nDescription = \n" + canon.getDescription());

        // Créer une nouvelle image avec une largeur et une hauteur suffisamment grandes pour contenir les deux images
        int combinedWidth = herbe.getWidth() * numRows;
        int combinedHeight = herbe.getHeight() * numCols;
        int currentX = 0;
        int currentY = 0;

        BufferedImage combinedImage = new BufferedImage(combinedWidth, combinedHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = combinedImage.createGraphics();

        for (int i = 0; i < map.length(); i++) {
            char c = map.charAt(i);
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
        ImageView shopView = new ImageView(new Image("file:///" + currentPath + "/src/main/resources/com/example/chinesedog/assets/shop/side.png"));

        // Créer une instance de MapClickHandler
        MapClickHandler clickHandler = new MapClickHandler(0,0, numRows, numCols, herbe.getWidth(), herbe.getHeight(), root, shopView, mapSansEspace);
        scene.setOnMouseClicked(clickHandler);

        createShopView(root, createItemsShop(currentPath, root, clickHandler.getImageX(), clickHandler.getImageY(), clickHandler), shopView);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Image combinée");

        primaryStage.show();
    }

    private void createShopView(StackPane root, ImageView[] itemViews, ImageView shopView) {
        if (root.getChildren().contains(shopView)) {
            return;
        }
        shopView.setVisible(false);
        root.getChildren().addAll(shopView, itemViews[0], itemViews[1], itemViews[2]);
    }

    private ImageView[] createItemsShop(String currentPath, StackPane root, Double imageX, Double imageY, MapClickHandler clickHandler) {
        Image canon1 = new Image("file:///" + currentPath + "/src/main/resources/com/example/chinesedog/assets/Tower/5/tower_2.png");
        Image canon2 = new Image("file:///" + currentPath + "/src/main/resources/com/example/chinesedog/assets/Tower/5/tower_1.png");
        Image canon3 = new Image("file:///" + currentPath + "/src/main/resources/com/example/chinesedog/assets/Tower/5/tower_3.png");

        ImageView canon1View = new ImageView(canon1);
        ImageView canon2View = new ImageView(canon2);
        ImageView canon3View = new ImageView(canon3);
        System.out.println("imageX = " + imageX);
        System.out.println("imageY = " + imageY);
        canon1View.setTranslateX(295);
        canon1View.setTranslateY(-265);
        canon1View.setVisible(false);
        canon1View.setOnMouseClicked(event -> {
            System.out.println("Clic sur l'élément du magasin 1 !");
            System.out.println("imageX = " + imageX);
            System.out.println("imageY = " + imageY);
            buyTower(canon1View, root, clickHandler.getImageX(), clickHandler.getImageY());
            event.consume();
        });

        canon2View.setTranslateX(230);
        canon2View.setTranslateY(-265);
        canon2View.setVisible(false);
        canon2View.setOnMouseClicked(event -> {
            System.out.println("Clic sur l'élément du magasin 2 !");
            System.out.println("imageX = " + imageX);
            System.out.println("imageY = " + imageY);
            buyTower(canon2View, root, clickHandler.getImageX(), clickHandler.getImageY());
            event.consume();
        });

        canon3View.setTranslateX(230);
        canon3View.setTranslateY(-200);
        canon3View.setVisible(false);
        canon3View.setOnMouseClicked(event -> {
            System.out.println("Clic sur l'élément du magasin 3 !");
            System.out.println("imageX = " + imageX);
            System.out.println("imageY = " + imageY);
            buyTower(canon3View, root, clickHandler.getImageX(), clickHandler.getImageY());
            event.consume();
        });

        return new ImageView[]{canon1View, canon2View, canon3View};

    }

    private void buyTower(ImageView achat, StackPane root, double imageX, double imageY) {
        ImageView imageView = new ImageView(achat.getImage());
        System.out.println("Achat d'une tour !");
        imageView.setTranslateY(imageX - 352);
        imageView.setTranslateX(imageY - 352);
        System.out.println("Position de la tour : " + imageX + ", " + imageY);
        imageView.setVisible(true);
        fermerShop(root);
        root.getChildren().add(imageView);
    }

    public void fermerShop(StackPane root) {
        root.getChildren().get(1).setVisible(false);
        root.getChildren().get(2).setVisible(false);
        root.getChildren().get(3).setVisible(false);
        root.getChildren().get(4).setVisible(false);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
