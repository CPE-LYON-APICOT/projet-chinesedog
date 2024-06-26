package com.example.chinesedog.Model;

import com.example.chinesedog.TypeCase;

import java.util.ArrayList;
import java.util.List;
import java.lang.System;

public class Carte {
    private String carteString;
    private String carteStringSansEspace;
    private int hauteur;
    private int largeur;
    private List<List<Case>> cases;

    public Carte(CarteBuilder builder) {
        this.hauteur = builder.hauteur;
        this.largeur = builder.largeur;
        this.carteString = builder.carteString;
        this.carteStringSansEspace = builder.carteStringSansEspace;
        this.cases = builder.cases;
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
    public Case getCase(int x, int y) {
        return cases.get(x).get(y);
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
        String currentPath = System.getProperty("user.dir");

        List<List<Case>> cases = new ArrayList<>();
        for (int i = 0; i < hauteur; i++) {
            List<Case> ligne = new ArrayList<>();
            for (int j = 0; j < largeur; j++) {
                ligne.add(new Case(i, j, TypeCase.valueOf(carte.substring(i * largeur + j, i * largeur + j + 1)), false));
            }
            cases.add(ligne);
        }
        return new CarteBuilder(hauteur, largeur).setCarteString(carte).setCases(cases).build();
    }

    public int getHauteur() {
        return hauteur;
    }

    public int getLargeur() {
        return largeur;
    }
}
