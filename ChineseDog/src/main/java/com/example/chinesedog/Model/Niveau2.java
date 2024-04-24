package com.example.chinesedog.Model;

public class Niveau2 extends DecoratorTour {

    public Niveau2(Tour tour) {
        super(tour);
    }

    @Override
    public String getDescription() {
        String description = "Niveau du " + tour.getNom() + " UP ! \n";
        description += "Niveau : " + (tour.getNiveau() + 1) + "\n";
        description += "Prix : " + tour.getPrix() + "\n";
        description += "Vitesse d'attaque : " + tour.getVitesseAttaque() + "\n";
        description += "Portee : " + tour.getPortee() + "\n";
        description += "Degats : " + (tour.getDegats() + 5) + "\n";
        return description;
    }
}
