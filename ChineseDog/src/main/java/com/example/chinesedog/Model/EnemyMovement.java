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

    private static final int SCENE_WIDTH = 1900;
    private static final int SCENE_HEIGHT = 1000;
    private static final int ENEMY_WIDTH = 20;
    private static final int ENEMY_HEIGHT = 20;
    private static final int ENEMY_SPEED = 2;

    private List<Enemy> enemies = new ArrayList<>();

    @Override
    public void start(Stage primaryStage) {
        Pane root = new Pane();
        Scene scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Enemy Movement Example");
        primaryStage.show();

        // Create enemies
        spawnEnemies(root);

        // Timeline to update enemy positions
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(8), event -> moveEnemies(root)));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    private void spawnEnemies(Pane root) {
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            List<String> waypoints = new ArrayList<>();


            waypoints.add("1200,900");
            waypoints.add("1200,200");
            waypoints.add("1000,200");
            waypoints.add("1000,900");
            waypoints.add("200,900");




            Enemy enemy = new Enemy();
            enemy.setWaypoints(waypoints);
            enemy.setPane(new Pane());
            enemy.getPane().setPrefSize(ENEMY_WIDTH, ENEMY_HEIGHT);
            enemy.getPane().setLayoutX(10 + i*50);
            enemy.getPane().setLayoutY(900);
            enemy.getPane().setStyle("-fx-background-color: rgb("+random.nextInt(256)+","+random.nextInt(256)+","+random.nextInt(256)+");");
            enemies.add(enemy);
            root.getChildren().add(enemy.getPane());
        }
    }

        private void moveEnemies (Pane root) {
            for (Enemy enemy : enemies) {

                // Get current position
                double currentX = enemy.getPane().getLayoutX();
                double currentY = enemy.getPane().getLayoutY();

                // Calculate new position
                String nextWaypoint = enemy.getNextWaypoint();
                System.out.println("Next waypoint: " + nextWaypoint);
                String[] parts = nextWaypoint.split(",");
                double targetX = Double.parseDouble(parts[0]);
                double targetY = Double.parseDouble(parts[1]);

                double newX = currentX;
                double newY = currentY;

                //System.out.println("Current position: " + currentX + ", " + currentY);

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
                    newX -= ENEMY_SPEED;
                } else if (targetX > Math.round(currentX)) {
                    newX += ENEMY_SPEED;
                }

                if (targetY < Math.round(currentY)) {
                    newY -= ENEMY_SPEED;
                } else if (targetY > Math.round(currentY)) {
                    newY += ENEMY_SPEED;
                }





                // Update enemy position
                enemy.getPane().relocate(Math.round(newX), Math.round(newY));
            }
        }

        public static boolean isPaneTouchingPoint(Pane pane, double pointX, double pointY) {
            double paneX = pane.getLayoutX();
            double paneY = pane.getLayoutY();
            double paneWidth = pane.getWidth();
            double paneHeight = pane.getHeight();

            // Vérifie si le point est à l'intérieur de la pane ou sur son bord
            return (pointX >= paneX && pointX <= paneX + paneWidth &&
                    pointY >= paneY && pointY <= paneY + paneHeight);
        }

        public static void main(String[] args) {
            launch(args);
        }
}
