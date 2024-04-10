package com.example.chinesedog.Model;

import javafx.scene.layout.Pane;

import java.util.List;

class Enemy {
    private List<String> waypoints;
    private Pane pane;
    private int currentWaypointIndex = 0;

    public List<String> getWaypoints() {
        return waypoints;
    }

    public void setWaypoints(List<String> waypoints) {
        this.waypoints = waypoints;
    }

    public Pane getPane() {
        return pane;
    }

    public void setPane(Pane pane) {
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


}