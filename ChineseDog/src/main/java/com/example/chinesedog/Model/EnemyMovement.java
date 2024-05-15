/*
package com.example.chinesedog.Model;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Bounds;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import javafx.scene.image.Image;


public class EnemyMovement extends Application {

    private static final int SCENE_WIDTH = 656;
    private static final int SCENE_HEIGHT = 679;
    private static final int ENEMY_WIDTH = 20;
    private static final int ENEMY_HEIGHT = 20;
    private static final int ENEMY_SPEED = 1;

    private List<Enemy> enemies = new ArrayList<>();
    private Vague vague;
    String map = "HHHHHC30HHTHHHHHTC29C28C27C26HHHHHHHHTC25HHTHHTHHHC24HHC06C07C08C09HHHC23HHC05HTC10HHHC22THC04HC12C11HHHC21HTC03HC13HHHTC20HHC02HC14C15C16C17C18C19HHC01HHTHHHHH";
    @Override
    public void start(Stage primaryStage) throws InterruptedException {
        Pane root = new Pane();
        Scene scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Enemy Movement Example");
        primaryStage.show();

         */
/*Create enemies*//*

        spawnEnemies(root);

        */
/* Timeline to update enemy positions*//*

        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(16), event -> moveEnemies(root)));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

    }

    private void waveGenerator(){
           vague = new Vague(15, 1, 30);
    }

    private void spawnEnemies(Pane root) {
        Random random = new Random();
        waveGenerator();
        List<String> waypoints = generatesWaypoints();
        String[] parts = waypoints.getFirst().split(",");
        double targetX = Double.parseDouble(parts[0]);
        double targetY = Double.parseDouble(parts[1]);
        for (int i = 0; i < vague.getNombreEnnemis(); i++) {
            Enemy enemy = new Enemy(10, 1, 1, 1);
            enemy.setWaypoints(waypoints);
            enemy.setPane(new Pane());
            enemy.getPane().setPrefSize(ENEMY_WIDTH, ENEMY_HEIGHT);
            enemy.getPane().setLayoutY(targetY + 150 +i * vague.getIntervalleSpawn());
            enemy.getPane().setLayoutX( targetX );
            enemy.getPane().setStyle("-fx-background-color: rgb(200,0,0)");
                "+random.nextInt(256)+","+random.nextInt(256)+","+random.nextInt(256)+"
            enemies.add(enemy);
            root.getChildren().add(enemy.getPane());
        }
        Enemy enemy = new EnnemiVolant(10, 2, 1, 1, true);
        enemy.setWaypoints(waypoints);
        enemy.setPane(new Pane());
        enemy.getPane().setPrefSize(ENEMY_WIDTH, ENEMY_HEIGHT);
        enemy.getPane().setLayoutY(targetY + 150 +20 * vague.getIntervalleSpawn());
        enemy.getPane().setLayoutX( targetX );
        enemy.getPane().setStyle("-fx-background-color: rgb(0,200,0)");
            "+random.nextInt(256)+","+random.nextInt(256)+","+random.nextInt(256)+"
        enemies.add(enemy);
        root.getChildren().add(enemy.getPane());
    }

    private List<String> generatesWaypoints(){
        List<String> waypoints = new ArrayList<>();
        String index;
        int count = 0;
        for (int i = 0; i < map.length(); i++) {
             */
/*Check if the current character matches the target letter*//*

            if (map.charAt(i) == 'C') {
                count++;
            }
        }
        for (int i = 1; i <= count; i++){
            if (i < 10) {
                index = "0" + i;
            } else {
                index = Integer.toString(i);
            }
            int indexPosition = map.indexOf("C"+index);
            if (indexPosition != -1) {
                String newMap = map.substring(0, indexPosition);
                newMap = newMap.replaceAll("[0-9]", "");
                int coord = newMap.length();
                if (coord < 10){
                    int y = (SCENE_HEIGHT / 10)/2;
                    int x = (SCENE_WIDTH / 10)/2 + coord * (SCENE_WIDTH / 10);
                    waypoints.add(x + "," + y);
                }
                else {
                    String coordnumber = Integer.toString(coord);
                    int y = (SCENE_HEIGHT / 10)/2 + Integer.parseInt(coordnumber.substring(0, 1)) * (SCENE_HEIGHT / 10);
                    int x = (SCENE_WIDTH / 10)/2 + Integer.parseInt(coordnumber.substring(1, 2)) * (SCENE_WIDTH / 10);
                    waypoints.add(x + "," + y);
                }
            }
        }
        return waypoints;
    }

    private void moveEnemies (Pane root) {
        for (Enemy enemy : enemies) {

             */
/*Get current position*//*

            double currentX = enemy.getPane().getLayoutX();
            double currentY = enemy.getPane().getLayoutY();

             */
/*Calculate new position*//*

            String nextWaypoint = enemy.getNextWaypoint();
            System.out.println("Next waypoint: " + nextWaypoint);
            String[] parts = nextWaypoint.split(",");
            double targetX = Double.parseDouble(parts[0]);
            double targetY = Double.parseDouble(parts[1]);

            double newX = currentX;
            double newY = currentY;

            System.out.println("Current position: " + currentX + ", " + currentY);

            if (isPaneTouchingPoint(enemy.getPane(), targetX, targetY)) {
                if (enemy.getCurrentWaypointIndex() >= enemy.getWaypoints().size() - 1) {
                    root.getChildren().remove(enemy.getPane());
                    enemies.remove(enemy);
                }
                enemy.setCurrentWaypointIndex(enemy.getCurrentWaypointIndex() + 1);
                nextWaypoint = enemy.getNextWaypoint();
                parts = nextWaypoint.split(",");
                targetX = Double.parseDouble(parts[0]);
                targetY = Double.parseDouble(parts[1]);
            }

            if (targetX < Math.round(currentX)) {
                newX -= enemy.getVitesse();
            } else if (targetX > Math.round(currentX)) {
                newX += enemy.getVitesse();
            }

            if (targetY < Math.round(currentY)) {
                newY -= enemy.getVitesse();
            } else if (targetY > Math.round(currentY)) {
                newY += enemy.getVitesse();
            }





             */
/*Update enemy position*//*

            enemy.getPane().relocate(Math.round(newX), Math.round(newY));
        }
    }

        public static boolean isPaneTouchingPoint(Pane pane, double pointX, double pointY) {
            double paneX = pane.getLayoutX();
            double paneY = pane.getLayoutY();
            double paneWidth = pane.getWidth();
            double paneHeight = pane.getHeight();

             */
/*Vérifie si le point est à l'intérieur de la pane ou sur son bord*//*

            return (pointX >= paneX && pointX <= paneX + paneWidth &&
                    pointY >= paneY && pointY <= paneY + paneHeight);
        }

        public static void main(String[] args) {
            launch(args);
        }
}
*/
