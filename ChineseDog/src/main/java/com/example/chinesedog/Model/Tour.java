package com.example.chinesedog.Model;

public abstract class Tour {
    protected String nom;
    protected int row;
    protected int col;
    protected int niveau;
    protected double prix;
    protected double vitesseAttaque;
    protected double portee;
    protected double degats;

    public Tour(String nom, int row, int col, int niveau, double prix, double vitesseAttaque, double portee, double degats) {
        this.nom = nom;
        this.row = row;
        this.col = col;
        this.niveau = niveau;
        this.prix = prix;
        this.vitesseAttaque = vitesseAttaque;
        this.portee = portee;
        this.degats = degats;
    }

    public String getNom() {
        return nom;
    }
    public int getRow() {
        return row;
    }
    public int getCol() {
        return col;
    }
    public int getNiveau() {
        return niveau;
    }

    public double getPrix() {
        return prix;
    }

    public double getVitesseAttaque() {
        return vitesseAttaque;
    }

    public double getPortee() {
        return portee;
    }

    public double getDegats() {
        return degats;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    public void setRow(int row) {
        this.row = row;
    }
    public void setCol(int col) {
        this.col = col;
    }
    public void setNiveau(int niveau) {
        this.niveau = niveau;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public void setVitesseAttaque(double vitesseAttaque) {
        this.vitesseAttaque = vitesseAttaque;
    }

    public void setPortee(double portee) {
        this.portee = portee;
    }

    public void setDegats(double degats) {
        this.degats = degats;
    }
    public abstract String getDescription();
}
