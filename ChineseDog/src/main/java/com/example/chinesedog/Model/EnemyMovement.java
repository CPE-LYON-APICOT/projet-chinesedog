package com.example.chinesedog.Model;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class EnemyMovement extends Application {

    private static final int SCENE_WIDTH = 656;
    private static final int SCENE_HEIGHT = 679;
    private static final int ENEMY_WIDTH = 20;
    private static final int ENEMY_HEIGHT = 20;
    private static final int ENEMY_SPEED = 1;
    public List<Tour> towers = new ArrayList<>();
    private List<Enemy> enemies = new ArrayList<>();
    private Vague vague;
    String map = "HHHHHC30HHTHHHHHTC29C28C27C26HHHHHHHHTC25HHTHHTHHHC24HHC06C07C08C09HHHC23HHC05HTC10HHHC22THC04HC12C11HHHC21HTC03HC13HHHTC20HHC02HC14C15C16C17C18C19HHC01HHTHHHHH";

    public EnemyMovement(List<Tour> towers) {
        this.towers = towers;
    }
    @Override
    public void start(Stage primaryStage) throws InterruptedException {
        Pane root = new Pane();
        Scene scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Enemy Movement Example");
        primaryStage.show();


/*Create enemies*/

        spawnEnemies(root);


/* Timeline to update enemy positions*/

        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(16), event -> moveEnemies(root, enemies)));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

    }

    private void waveGenerator(){
           vague = new Vague(15, 1, 30);
    }

    public List<Enemy> spawnEnemies(Pane root) {
        Random random = new Random();
        waveGenerator();
        List<String> waypoints = generatesWaypoints();
        String[] parts = waypoints.getFirst().split(",");
        double targetX = Double.parseDouble(parts[0]);
        double targetY = Double.parseDouble(parts[1]);
        for (int i = 0; i < vague.getNombreEnnemis(); i++) {
            EnemyFactory VolantFactory = new EnemyVolantFactory();
            Enemy enemy = VolantFactory.createEnemy(waypoints, 70, 1, 1, 1);
            enemy.setPane(new Rectangle());
            enemy.getPane().setWidth(ENEMY_WIDTH);
            enemy.getPane().setHeight(ENEMY_HEIGHT);
            enemy.getPane().setFill(Color.RED);

            enemy.getPane().setTranslateX(-323 + targetX);
            enemy.getPane().setTranslateY(-339 + targetY + 50 + i * vague.getIntervalleSpawn());

/*            System.out.println("Target X: " + targetX);
            System.out.println("Target Y: " + targetY);*/

            enemies.add(enemy);
            root.getChildren().add(enemy.getPane());
        }
        return enemies;
    }

    public void checkAndAttackEnemies(Enemy enemy) {
        for (Tour tour : towers) {
            List<Integer> coorTour = tour.getCoordonnees();
            List<Integer> coorEnnemi = getCoordonnees(enemy);
/*            System.out.println("Check des coordonnées de la tour: " + coorTour + " et de l'ennemi: " + coorEnnemi);*/
            int distance = Math.abs(coorTour.get(0) - coorEnnemi.get(0)) + Math.abs(coorTour.get(1) - coorEnnemi.get(1));
/*            System.out.println("Check de la distance: " + distance);*/
            if (distance <= tour.getPortee() - 2) {
                enemy.setVie((int) (enemy.getVie() - tour.getDegats()));
/*                System.out.println("Attaque ! Dégats de la tour : " + tour.getDegats() +"Vie de l'ennemi: " + enemy.getVie());*/
                if (enemy.getVie() <= 0) {
/*                    System.out.println("Ennemi mort");*/
                    enemy.getPane().setVisible(false);
                }
            }
        }
    }

    public List<Integer> getCoordonnees(Enemy enemy) {
        List<Integer> coorEnnemi = new ArrayList<>();
        coorEnnemi.add((int) (enemy.getPane().getTranslateX() + 320) / 64);
        coorEnnemi.add((int) (enemy.getPane().getTranslateY() + 320) / 64);
        return coorEnnemi;
    }

    private List<String> generatesWaypoints(){
        List<String> waypoints = new ArrayList<>();
        String index;
        int count = 0;
        for (int i = 0; i < map.length(); i++) {

/*Check if the current character matches the target letter*/

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

    public void moveEnemies(Pane root, List<Enemy> ennemies) {

        for (Enemy enemy : ennemies) {
            checkAndAttackEnemies(enemy);
            double currentX = enemy.getPane().getTranslateX();
            double currentY = enemy.getPane().getTranslateY();

            String nextWaypoint = enemy.getNextWaypoint();

            String[] parts = nextWaypoint.split(",");
            double targetX = Double.parseDouble(parts[0]);
            double targetY = Double.parseDouble(parts[1]);

            double newX = currentX;
            double newY = currentY;

/*            System.out.println("Current position: " + currentX + ", " + currentY);*/

            if (currentX == targetX - SCENE_WIDTH/2 && currentY == targetY - SCENE_HEIGHT/2) {
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

            if (targetX - SCENE_WIDTH/2 < Math.round(currentX)) {
                enemy.getPane().setTranslateX(-enemy.getVitesse() + currentX);
            } else if (targetX - SCENE_WIDTH/2 > Math.round(currentX)) {
                enemy.getPane().setTranslateX(enemy.getVitesse() + currentX);
            }

            if (targetY - SCENE_HEIGHT/2 < Math.round(currentY)) {
                enemy.getPane().setTranslateY(-enemy.getVitesse() + currentY);
            } else if (targetY - SCENE_HEIGHT/2 > Math.round(currentY)) {
                enemy.getPane().setTranslateY(enemy.getVitesse() + currentY);
            }
        }
    }

        public static boolean isPaneTouchingPoint(Rectangle pane, double pointX, double pointY) {
            double paneX = pane.getLayoutX();
            double paneY = pane.getLayoutY();
            double paneWidth = pane.getWidth();
            double paneHeight = pane.getHeight();


/*Vérifie si le point est à l'intérieur de la pane ou sur son bord*/

            return (pointX >= paneX && pointX <= paneX + paneWidth &&
                    pointY >= paneY && pointY <= paneY + paneHeight);
        }

        public static void main(String[] args) {
            launch(args);
        }
}

