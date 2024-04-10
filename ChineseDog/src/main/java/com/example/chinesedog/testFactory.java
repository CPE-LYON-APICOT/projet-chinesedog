package com.example.chinesedog;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class testFactory extends Application {

    private static final int TILE_WIDTH = 32;
    private static final int TILE_HEIGHT = 32;

    @Override
    public void start(Stage primaryStage) {
        // Load TMX file
        File file = new File("C:\\Users\\Portal\\Desktop\\Cours\\POO\\Projet\\projet-chinesedog\\ChineseDog\\src\\main\\resources\\com\\example\\chinesedog\\assets\\levels\\testtest.tmx");
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);
            doc.getDocumentElement().normalize();

            // Get map width and height
            int mapWidth = Integer.parseInt(doc.getDocumentElement().getAttribute("width"));
            int mapHeight = Integer.parseInt(doc.getDocumentElement().getAttribute("height"));

            // Create canvas
            Canvas canvas = new Canvas(mapWidth * TILE_WIDTH, mapHeight * TILE_HEIGHT);
            GraphicsContext gc = canvas.getGraphicsContext2D();

            // Loop through tile layers
            NodeList layerNodes = doc.getElementsByTagName("layer");
            for (int i = 0; i < layerNodes.getLength(); i++) {
                Node layerNode = layerNodes.item(i);
                if (layerNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element layerElement = (Element) layerNode;
                    NodeList tileNodes = layerElement.getElementsByTagName("tile");
                    for (int j = 0; j < tileNodes.getLength(); j++) {
                        Node tileNode = tileNodes.item(j);
                        if (tileNode.getNodeType() == Node.ELEMENT_NODE) {
                            Element tileElement = (Element) tileNode;
                            int tileId = Integer.parseInt(tileElement.getAttribute("gid")) - 1;

                            // Draw tile at appropriate position
                            int x = j % mapWidth;
                            int y = j / mapWidth;
                            // Adjust x and y if needed to handle staggered or hexagonal maps
                            gc.drawImage(getTileImage(tileId), x * TILE_WIDTH, y * TILE_HEIGHT);
                        }
                    }
                }
            }

            // Create scene
            StackPane root = new StackPane(canvas);
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Tiled Map Viewer");
            primaryStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private javafx.scene.image.Image getTileImage(int tileId) {
        // Here you should implement your logic to load tileset image and extract appropriate tile
        // This is just a placeholder method, replace it with your own implementation
        // For example, if you have a tileset image with multiple tiles, you would need to calculate the tile's position within the tileset
        // and extract that tile from the tileset image
        // You can use javafx.scene.image.WritableImage for this purpose
        return null;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
