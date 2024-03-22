package com.example.chinesedog;

import java.util.ArrayList;
import java.util.List;

public class Carte {
    private String carteString;
    private int hauteur;
    private int largeur;
    private List<List<Case>> cases;

    public Carte(int hauteur, int largeur, List<List<Case>> cases) {
        this.largeur = largeur;
        this.hauteur = hauteur;
        this.cases = cases;
    }

    public Carte(int hauteur, int largeur, String carteString) {
        this.largeur = largeur;
        this.hauteur = hauteur;
        this.carteString = carteString;
    }

    public int gethauteur() {
        return hauteur;
    }
    public int getlargeur() {
        return largeur;
    }
    public List<List<Case>> getCases() {
        return cases;
    }

    public boolean displayCarte() {
        for (int i = 0; i < hauteur; i++) {
            for (int j = 0; j < largeur; j++) {
                System.out.print(cases.get(i).get(j).getType());
            }
            System.out.println();
        }
        return false;
    }

    public Carte switchStringToCarte (String carte) {
        List<List<Case>> cases = new ArrayList<>();
        for (int i = 0; i < hauteur; i++) {
            List<Case> ligne = new ArrayList<>();
            for (int j = 0; j < largeur; j++) {
                String path = String.valueOf(TypeCase.valueOf(carte.substring(i * largeur + j, i * largeur + j + 1)));
                if (path.equals("H")) {
                    path = "C:\\Users\\Portal\\Desktop\\Cours\\POO\\Projet\\projet-chinesedog\\ChineseDog\\src\\main\\java\\com\\example\\chinesedog\\assets\\Texture\\4\\PNG\\mapTile_022.png";
                } else if (path.equals("C")) {
                    path = "C:\\Users\\Portal\\Desktop\\Cours\\POO\\Projet\\projet-chinesedog\\ChineseDog\\src\\main\\java\\com\\example\\chinesedog\\assets\\Texture\\4\\PNG\\mapTile_087.png";
                } else if (path.equals("B")) {
                    path = "C:\\Users\\Portal\\Desktop\\Cours\\POO\\Projet\\projet-chinesedog\\ChineseDog\\src\\main\\java\\com\\example\\chinesedog\\assets\\Texture\\4\\PNG\\mapTile_020.png";
                } else if (path.equals("T")) {
                    path = "C:\\Users\\Portal\\Desktop\\Cours\\POO\\Projet\\projet-chinesedog\\ChineseDog\\src\\main\\java\\com\\example\\chinesedog\\assets\\Texture\\4\\PNG\\mapTile_027.png";
                }
                ligne.add(new Case(i, j, TypeCase.valueOf(carte.substring(i * largeur + j, i * largeur + j + 1)), path));
            }
            cases.add(ligne);
        }
        return new Carte(hauteur, largeur, cases);
    }
}
