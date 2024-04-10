package com.example.chinesedog.Model;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EnemyMovement extends Application {

    private static final int SCENE_WIDTH = 1900;
    private static final int SCENE_HEIGHT = 1000;
    private static final int ENEMY_RADIUS = 10;
    private static final int ENEMY_SPEED = 2;

    private List<Circle> enemies = new ArrayList<>();

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
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(16), event -> moveEnemies()));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    private void spawnEnemies(Pane root) {
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            Circle enemy = new Circle(ENEMY_RADIUS, Color.RED);
            enemy.relocate(random.nextDouble() * (SCENE_WIDTH - 2 * ENEMY_RADIUS),
                    random.nextDouble() * (SCENE_HEIGHT - 2 * ENEMY_RADIUS));
            enemies.add(enemy);
            root.getChildren().add(enemy);
        }
    }

    private void moveEnemies() {
        for (Circle enemy : enemies) {
            // Get current position
            double currentX = enemy.getLayoutX();
            double currentY = enemy.getLayoutY();

            // Calculate new position
            double newX = currentX + (ENEMY_SPEED * Math.random() - ENEMY_SPEED / 2);
            double newY = currentY + (ENEMY_SPEED * Math.random() - ENEMY_SPEED / 2);

            // Ensure enemies stay within the scene bounds
            newX = clamp(newX, 0, SCENE_WIDTH - ENEMY_RADIUS * 2);
            newY = clamp(newY, 0, SCENE_HEIGHT - ENEMY_RADIUS * 2);

            // Update enemy position
            enemy.relocate(newX, newY);
        }
    }

    // Helper method to ensure a value stays within a certain range
    private double clamp(double value, double min, double max) {
        return Math.max(min, Math.min(max, value));
    }

    public static void main(String[] args) {
        launch(args);
    }
}