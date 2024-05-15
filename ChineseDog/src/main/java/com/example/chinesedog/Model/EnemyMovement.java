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

import java.util.*;


public class EnemyMovement {

    private static final int SCENE_WIDTH = 656;
    private static final int SCENE_HEIGHT = 679;
    private static final int ENEMY_WIDTH = 20;
    private static final int ENEMY_HEIGHT = 20;
    private static final int ENEMY_SPEED = 1;
    private int WAVE = 0;


    private List<Enemy> enemies = new ArrayList<>();
    private Vague vague;
    String map = "HHHHHC30HHTHHHHHTC29C28C27C26HHHHHHHHTC25HHTHHTHHHC24HHC06C07C08C09HHHC23HHC05HTC10HHHC22THC04HC12C11HHHC21HTC03HC13HHHTC20HHC02HC14C15C16C17C18C19HHC01HHTHHHHH";


    private void waveGenerator(){
        WAVE ++;

        int nbTerrestre = 5+ WAVE*2;
        int nbVolant = 0;
        int nbBoss = 0;
        if (WAVE > 5){
            nbVolant = 2+ Math.round(WAVE/2);
        }

        if (WAVE % 5 == 0){
            nbBoss = 1;
        }




        vague = new Vague( nbVolant, nbTerrestre, nbBoss, 1, 50);

    }

    public void spawnEnemies(Pane root) {
        waveGenerator();
        List<String> waypoints = generatesWaypoints();
        String[] parts = waypoints.getFirst().split(",");
        double targetX = Double.parseDouble(parts[0]);
        double targetY = Double.parseDouble(parts[1]);
        for (int i = 0; i < vague.getNombreEnnemis(); i++) {
            System.out.println("Nb ennemies: " + WAVE);
            System.out.println("i: " + i);
            if (i < vague.getNbVolant()){
                EnemyFactory VolantFactory = new EnemyVolantFactory();
                Enemy enemy = VolantFactory.createEnemy(waypoints, 100, 1.5, 1, 1);
                enemy.setPane(new Rectangle());
                enemy.getPane().setWidth(ENEMY_WIDTH);
                enemy.getPane().setHeight(ENEMY_HEIGHT);
                enemy.getPane().setFill(Color.YELLOW);

                enemy.getPane().setTranslateX(-323 + targetX);
                enemy.getPane().setTranslateY(-339 + targetY + 50 + i * vague.getIntervalleSpawn());

                System.out.println("Target X: " + targetX);
                System.out.println("Target Y: " + targetY);

                enemies.add(enemy);

            }
            else if (i < vague.getNbTerrestre()+vague.getNbVolant()){
                EnemyFactory TerrestreFactory = new EnemyTerrestreFactory();
                Enemy enemy = TerrestreFactory.createEnemy(waypoints, 100, 1, 1, 1);
                enemy.setPane(new Rectangle());
                enemy.getPane().setWidth(ENEMY_WIDTH);
                enemy.getPane().setHeight(ENEMY_HEIGHT);
                enemy.getPane().setFill(Color.RED);

                enemy.getPane().setTranslateX(-323 + targetX);
                enemy.getPane().setTranslateY(-339 + targetY + 50 + i * vague.getIntervalleSpawn());

                System.out.println("Target X: " + targetX);
                System.out.println("Target Y: " + targetY);

                enemies.add(enemy);

            }
            else {
                EnemyFactory BossFactory = new EnemyBossFactory();
                Enemy enemy = BossFactory.createEnemy(waypoints, 100, 0.8, 1, 1);
                enemy.setPane(new Rectangle());
                enemy.getPane().setWidth(ENEMY_WIDTH);
                enemy.getPane().setHeight(ENEMY_HEIGHT);
                enemy.getPane().setFill(Color.BLUE);



                System.out.println("Target X: " + targetX);
                System.out.println("Target Y: " + targetY);

                enemies.add(enemy);

            }


        }
        Collections.shuffle(enemies);

        for (int index = 0; index < enemies.size(); index++) {
            Enemy enemy = enemies.get(index);

            enemy.getPane().setTranslateX(-323 + targetX);
            enemy.getPane().setTranslateY(-339 + targetY + 50 + index * vague.getIntervalleSpawn());
            root.getChildren().add(enemy.getPane());
        }
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

    public void moveEnemies(Pane root) {
        if (enemies.isEmpty()) {
            spawnEnemies(root);
        }
        for (Enemy enemy : enemies) {


/*Get current position*/

            double currentX = enemy.getPane().getTranslateX();
            double currentY = enemy.getPane().getTranslateY();


/*Calculate new position*/

            String nextWaypoint = enemy.getNextWaypoint();
            System.out.println("Next waypoint: " + nextWaypoint);
            String[] parts = nextWaypoint.split(",");
            double targetX = Double.parseDouble(parts[0]);
            double targetY = Double.parseDouble(parts[1]);

            double newX = currentX;
            double newY = currentY;

            System.out.println("Current position: " + currentX + ", " + currentY);

            if (currentX >= targetX - SCENE_WIDTH/2 - 2 && currentX <= targetX - SCENE_WIDTH/2 + 2 &&
                    currentY >= targetY - SCENE_HEIGHT/2 - 2 && currentY <= targetY - SCENE_HEIGHT/2 + 2) {
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

}

