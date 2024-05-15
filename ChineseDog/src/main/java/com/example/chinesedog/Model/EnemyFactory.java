package com.example.chinesedog.Model;

import java.util.List;

public interface EnemyFactory {
    Enemy createEnemy(List<String> waypoints, int vie, double vitesse, int armure, int resistanceMagique);
}
