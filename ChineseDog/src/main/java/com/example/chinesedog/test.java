package com.example.chinesedog;

import com.example.chinesedog.Model.*;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Node;
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

import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.geometry.Insets;
import javafx.util.Duration;

public class test extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
        String currentPath = System.getProperty("user.dir");
        // Load TMX file
        // Charger les images
        BufferedImage herbe = ImageIO.read(test.class.getResourceAsStream("/com/example/chinesedog/assets/Texture/4/PNG/mapTile_022.png"));
        BufferedImage pierre = ImageIO.read(test.class.getResourceAsStream("/com/example/chinesedog/assets/Texture/4/PNG/mapTile_027.png"));
        BufferedImage chemin = ImageIO.read(test.class.getResourceAsStream("/com/example/chinesedog/assets/Texture/4/PNG/mapTile_082.png"));

        int numRows = 10; // Nombre de lignes dans votre carte
        int numCols = 10; // Nombre de colonnes dans votre carte

        String map = "HHHHHCHTHH HHHHTCCCCH HHHHHHHTCH HHHHTHHHCH TCCCCHHHCT HCHTCHHHCH HCHCCHHHCH TCHCHHHTCH HCHCCCCCCH HCHHTHHHHH";
        String mapSansEspace = "HHHHHCHTHHHHHHTCCCCHHHHHHHHTCHHHHHTHHHCHTCCCCHHHCTHCHTCHHHCHHCHCCHHHCHTCHCHHHTCHHCHCCCCCCHHCHHTHHHHH";
        Carte carte = new CarteBuilder(numRows, numCols).setCarteString(map).setCarteStringSansEspace(mapSansEspace).build();

        Carte carteConverti = carte.switchStringToCarte(mapSansEspace);
        carteConverti.displayCarte();

        /// Test Ennemi/Vague
/*        List<Ennemi> ennemis = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Ennemi ennemi = new EnnemiTerrestre(100, 10, 10, 10, "testTerrestrial", "C:\\Users\\Portal\\Desktop\\Cours\\POO\\Projet\\projet-chinesedog\\ChineseDog\\src\\main\\java\\com\\example\\chinesedog\\assets\\Enemy\\2\\Foozle_2DC0028_Spire_EnemyPack_2_Ground\\Ground\\Previews\\Scorpion.gif",false);
            System.out.println(ennemi);
            ennemis.add(ennemi);
        }
        Vague vague = new Vague(10, 1, 1, ennemis);
        vague.displayVague();*/

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

        List<Tour> tours = new ArrayList<>();

        // Créer une pile (StackPane) pour contenir l'ImageView
        StackPane root = new StackPane();
        Scene scene = new Scene(root, combinedWidth, combinedHeight);

        root.getChildren().add(imageView);
        ImageView shopView = new ImageView(new Image(test.class.getResourceAsStream("/com/example/chinesedog/assets/shop/side.png")));

        // Créer une instance de MapClickHandler
        MapClickHandler clickHandler = new MapClickHandler(0,0, numRows, numCols, herbe.getWidth(), herbe.getHeight(), root, shopView, mapSansEspace, carteConverti);
        scene.setOnMouseClicked(clickHandler);

        createShopView(root, createItemsShop(currentPath, root, clickHandler.getImageX(), clickHandler.getImageY(), clickHandler, carteConverti), shopView);
        // Création d'un rectangle blanc
        Rectangle rectangle = new Rectangle(200, 250, Color.WHITE);
        rectangle.setStroke(Color.BLACK); // Ajout d'une bordure noire

        // Création d'un texte à afficher
        Text text = new Text();
        text.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        text.setFill(Color.BLACK);

        // Création d'un bouton
        Button button = new Button("Upgrade");
        button.setVisible(false);
        StackPane.setAlignment(rectangle, javafx.geometry.Pos.TOP_LEFT);
        StackPane.setAlignment(text, javafx.geometry.Pos.TOP_LEFT);
        StackPane.setAlignment(button, javafx.geometry.Pos.TOP_LEFT);
        StackPane.setMargin(button, new Insets(210, 0, 0, 60));
        root.getChildren().addAll(rectangle, text, button);

        EnemyMovement enemyMovement = new EnemyMovement(tours);

        enemyMovement.spawnEnemies(root);


        /* Timeline to update enemy positions*/

        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(16), event -> enemyMovement.moveEnemies(root)));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();




        primaryStage.setScene(scene);
        primaryStage.setTitle("Tower Defense");
        primaryStage.show();
    }

    private void createShopView(StackPane root, ImageView[] itemViews, ImageView shopView) {
        if (root.getChildren().contains(shopView)) {
            return;
        }
        shopView.setVisible(false);
        root.getChildren().addAll(shopView, itemViews[0], itemViews[1], itemViews[2]);
    }

    private ImageView[] createItemsShop(String currentPath, StackPane root, Double imageX, Double imageY, MapClickHandler clickHandler, Carte map) {
        Tour canonVert = new Canon("Canon Vert",-1,-1,1, 50, 10, 3, 10);
        Tour canonJaune = new Canon("Canon Jaune",-1,-1,1, 100, 10, 5, 8.5);
        Tour canonRouge = new Canon("Canon Rouge",-1,-1,1, 225, 8, 3, 25);

        Image canon1 = new Image(test.class.getResourceAsStream("/com/example/chinesedog/assets/Tower/5/tower_1.png"));
        Image canon2 = new Image(test.class.getResourceAsStream("/com/example/chinesedog/assets/Tower/5/tower_2.png"));
        Image canon3 = new Image(test.class.getResourceAsStream("/com/example/chinesedog/assets/Tower/5/tower_3.png"));

        ImageView canon1View = new ImageView(canon1);
        ImageView canon2View = new ImageView(canon2);
        ImageView canon3View = new ImageView(canon3);
        System.out.println("imageX = " + imageX);
        System.out.println("imageY = " + imageY);

        canon1View.setTranslateX(230);
        canon1View.setTranslateY(-265);
        canon1View.setVisible(false);
        canon1View.setOnMouseClicked(event -> {
            System.out.println("Clic sur l'élément du magasin 1 !");
            System.out.println("imageX = " + imageX);
            System.out.println("imageY = " + imageY);
            buyTower(canon1View, root, clickHandler.getImageX(), clickHandler.getImageY(), canonVert, map);
            event.consume();
        });

        canon2View.setTranslateX(295);
        canon2View.setTranslateY(-265);
        canon2View.setVisible(false);
        canon2View.setOnMouseClicked(event -> {
            System.out.println("Clic sur l'élément du magasin 2 !");
            System.out.println("imageX = " + imageX);
            System.out.println("imageY = " + imageY);
            buyTower(canon2View, root, clickHandler.getImageX(), clickHandler.getImageY(), canonJaune, map);
            event.consume();
        });

        canon3View.setTranslateX(230);
        canon3View.setTranslateY(-200);
        canon3View.setVisible(false);
        canon3View.setOnMouseClicked(event -> {
            System.out.println("Clic sur l'élément du magasin 3 !");
            System.out.println("imageX = " + imageX);
            System.out.println("imageY = " + imageY);
            buyTower(canon3View, root, clickHandler.getImageX(), clickHandler.getImageY(), canonRouge, map);
            event.consume();
        });

        return new ImageView[]{canon1View, canon2View, canon3View};

    }

    private void buyTower(ImageView achat, StackPane root, double imageX, double imageY, Tour tour, Carte map) {
        ImageView imageView = new ImageView(achat.getImage());
        System.out.println("Achat d'une tour !");
        imageView.setTranslateY(imageX - 352);
        imageView.setTranslateX(imageY - 352);
        tour.setRow(((int) imageX / 64) - 1);
        tour.setCol(((int) imageY / 64) - 1);
        map.getCase(tour.getRow(), tour.getCol()).setIsOccupied(true);
        imageView.setVisible(true);
        updateText(root, tour);

        imageView.setOnMouseClicked(event -> {
            updateText(root, tour);
            event.consume();
        });
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

    public void updateText(StackPane root, Tour tour) {
        for (Node node : root.getChildren()) {
            if (node instanceof Text text) {
                text.setText(tour.getDescription());
            }
            else if (node instanceof Button button) {
                button.setVisible(true);
                ButtonClickHandler buttonClickHandler = new ButtonClickHandler(tour, root);
                button.setOnAction(buttonClickHandler);
            }
        }
    }
}