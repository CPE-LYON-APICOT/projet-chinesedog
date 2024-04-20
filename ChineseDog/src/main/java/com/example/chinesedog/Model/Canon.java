package com.example.chinesedog.Model;

public class Canon extends Tour {

    public Canon(String nom, int niveau, double prix, double vitesseAttaque, double portee, double degats) {
        super(nom, niveau, prix, vitesseAttaque, portee, degats);
    }

    @Override
    public String getDescription() {
        String description = "Canon : \n";
        description += "Niveau : " + getNiveau() + "\n";
        description += "Prix : " + getPrix() + "\n";
        description += "Vitesse d'attaque : " + getVitesseAttaque() + "\n";
        description += "Portee : " + getPortee() + "\n";
        description += "Degats : " + getDegats() + "\n";
        return description;
    }
}
