package com.example.chinesedog.Model;

public abstract class DecoratorTour extends Tour {
    protected Tour tour;

    public DecoratorTour(Tour tour) {
        super(tour.getNom(), tour.getNiveau(), tour.getPrix(), tour.getVitesseAttaque(), tour.getPortee(), tour.getDegats());
        this.tour = tour;
    }

    public Tour getTour() {
        return tour;
    }

    public void setTour(Tour tour) {
        this.tour = tour;
    }

    public abstract String getDescription();
}
