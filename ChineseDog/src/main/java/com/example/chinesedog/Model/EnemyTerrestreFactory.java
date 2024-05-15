package com.example.chinesedog.Model;

import java.util.List;

public class EnemyTerrestreFactory implements EnemyFactory {
    @Override
    public Enemy createEnemy(List<String> waypoints, int vie, double vitesse, int armure, int resistanceMagique) {
        return new EnemyTerrestre(waypoints, vie, vitesse, armure, resistanceMagique);
    }
}
