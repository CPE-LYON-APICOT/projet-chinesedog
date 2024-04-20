/*
package com.example.chinesedog;

import com.example.chinesedog.Model.*;

import java.util.ArrayList;
import java.util.List;

import static javafx.application.Application.launch;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello, world!");
        List<List<Case>> cases = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            List<Case> ligne = new ArrayList<>();
            for (int j = 0; j < 10; j++) {
                ligne.add(new Case(i, j, TypeCase.H, "C:\\Users\\Portal\\Desktop\\Cours\\POO\\Projet\\projet-chinesedog\\ChineseDog\\src\\main\\java\\com\\example\\chinesedog\\assets\\Texture\\4\\PNG\\mapTile_022.png"));
            }
            cases.add(ligne);
        }

        Carte carte = new Carte(10, 10, cases);
        System.out.println(carte.displayCarte());
        String test = "HHHHHBBHHHHHBBHHHHHCCCHHHTTTTT";
        Carte carte2 = new Carte(5, 6, test);
        Carte carteConverti = carte2.switchStringToCarte(test);
        carteConverti.displayCarte();

        // Test Ennemi/Vague
        List<Ennemi> ennemis = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Ennemi ennemi = new EnnemiTerrestre(100, 10, 10, 10, "testTerrestrial", "C:\\Users\\Portal\\Desktop\\Cours\\POO\\Projet\\projet-chinesedog\\ChineseDog\\src\\main\\java\\com\\example\\chinesedog\\assets\\Enemy\\2\\Foozle_2DC0028_Spire_EnemyPack_2_Ground\\Ground\\Previews\\Scorpion.gif",false);
            System.out.println(ennemi);
            ennemis.add(ennemi);
        }
        Vague vague = new Vague(10, 1, 1, ennemis);
        vague.displayVague();
    }
}*/
