package com.example.chinesedog.Model;

import java.util.List;

public class EnemyVolantFactory implements EnemyFactory {
    @Override
    public Enemy createEnemy(List<String> waypoints, int vie, double vitesse, int armure, int resistanceMagique) {
        return new EnemyVolant(waypoints, vie, vitesse, armure, resistanceMagique);
    }
}
