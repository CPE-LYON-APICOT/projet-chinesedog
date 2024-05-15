package com.example.chinesedog.Model;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

import java.util.List;

public abstract class Enemy {
    private List<String> waypoints;
    private Rectangle pane;
    private int currentWaypointIndex = 0;
    private double vitesse;
    private int vie;
    private int armure;
    private int resistanceMagique;


    public Enemy(List<String> waypoints , double vitesse, int vie, int armure, int resistanceMagique) {
        this.waypoints = waypoints;
        this.vitesse = vitesse;
        this.vie = vie;
        this.armure = armure;
        this.resistanceMagique = resistanceMagique;
    }

    public List<String> getWaypoints() {
        return waypoints;
    }

    public void setWaypoints(List<String> waypoints) {
        this.waypoints = waypoints;
    }

    public Rectangle getPane() {
        return pane;
    }

    public void setPane(Rectangle pane) {
        this.pane = pane;
    }

    public String getNextWaypoint() {
        if (currentWaypointIndex >= waypoints.size()) {
            currentWaypointIndex = waypoints.size() - 1;
        }

        return waypoints.get(currentWaypointIndex);
    }

    public int getCurrentWaypointIndex() {
        return currentWaypointIndex;
    }

    public void setCurrentWaypointIndex(int currentWaypointIndex) {
        this.currentWaypointIndex = currentWaypointIndex;
    }

    public double getVitesse() {
        return vitesse;
    }

}