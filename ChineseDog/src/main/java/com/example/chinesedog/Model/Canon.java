package com.example.chinesedog.Model;

public class Canon extends Tour {

    public Canon(String nom, Integer row, Integer col, int niveau, double prix, double coolDown, long lastAttackTime, double portee, double degats) {
        super(nom, row, col, niveau, prix, coolDown, lastAttackTime, portee, degats);
    }

    @Override
    public String getDescription() {
        String description = "Canon : \n";
        description += "Nom : " + getNom() + "\n";
        description += "Position : " + getRow() + " " + getCol() + "\n";
        description += "Niveau : " + getNiveau() + "\n";
        description += "Prix : " + getPrix() + "\n";
        description += "Vitesse d'attaque : " + getVitesseAttaque() + "\n";
        description += "Portée : " + getPortee() + "\n";
        description += "Dégats : " + getDegats() + "\n";
        return description;
    }
}
