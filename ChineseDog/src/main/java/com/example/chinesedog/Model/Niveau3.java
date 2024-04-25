package com.example.chinesedog.Model;

public class Niveau3 extends DecoratorTour {

    public Niveau3(Tour tour) {
        super(tour);
        setNouveauxAttributs();
    }

    private void setNouveauxAttributs() {
        int nouveauxDegats = (int) (tour.getDegats() * 1.5); // Augmentation de 20%
        tour.setDegats(nouveauxDegats);
        tour.setNiveau(tour.getNiveau() + 1);
        tour.setVitesseAttaque(tour.getVitesseAttaque() + 2);
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
