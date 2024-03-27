package com.example.chinesedog;

abstract public class Ennemi {

    private String nom;
    private int vie;
    private int vitesse;
    private int armure;
    private int resistanceMagique;
    private String imagePath;

    public Ennemi(int vie, int vitesse, int armure, int resistanceMagique, String nom, String imagePath) {
        this.vie = vie;
        this.vitesse = vitesse;
        this.armure = armure;
        this.resistanceMagique = resistanceMagique;
        this.nom = nom;
        this.imagePath = imagePath;
    }

    public int getVie() {
        return vie;
    }

    public int getVitesse() {
        return vitesse;
    }

    public int getArmure() {
        return armure;
    }

    public int getResistanceMagique() {
        return resistanceMagique;
    }

    public String getNom() {
        return nom;
    }

    public String getImagePath() {
        return imagePath;
    }

}
