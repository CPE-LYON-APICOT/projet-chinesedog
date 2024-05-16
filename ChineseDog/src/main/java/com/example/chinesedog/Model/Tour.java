package com.example.chinesedog.Model;

import java.util.ArrayList;
import java.util.List;

public abstract class Tour {
    protected String nom;
    protected int row;
    protected int col;
    protected int niveau;
    protected double prix;
    protected double coolDown;
    protected long lastAttackTime;
    protected double portee;
    protected double degats;

    public Tour(String nom, int row, int col, int niveau, double prix, double coolDown, long lastAttackTime, double portee, double degats) {
        this.nom = nom;
        this.row = row;
        this.col = col;
        this.niveau = niveau;
        this.prix = prix;
        this.coolDown = coolDown;
        this.lastAttackTime = System.currentTimeMillis();
        this.portee = portee;
        this.degats = degats;
    }

    public boolean canAttack() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastAttackTime >= coolDown) {
            lastAttackTime = currentTime;
            return true;
        }
        return false;
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
        return coolDown;
    }
    public long getLastAttackTime() {
        return lastAttackTime;
    }

    public double getPortee() {
        return portee;
    }

    public double getDegats() {
        return degats;
    }
    public List<Integer> getCoordonnees() {
        List<Integer> coordonnees = new ArrayList<>();
        coordonnees.add(row);
        coordonnees.add(col);
        return coordonnees;
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

    public void setVitesseAttaque(double coolDown) {
        this.coolDown = coolDown;
    }
    public void setLastAttackTime(long lastAttackTime) {
        this.lastAttackTime = lastAttackTime;
    }

    public void setPortee(double portee) {
        this.portee = portee;
    }

    public void setDegats(double degats) {
        this.degats = degats;
    }
    public abstract String getDescription();

    public void setTour(Tour tour) {
        this.nom = tour.getNom();
        this.row = tour.getRow();
        this.col = tour.getCol();
        this.niveau = tour.getNiveau();
        this.prix = tour.getPrix();
        this.coolDown = tour.getVitesseAttaque();
        this.lastAttackTime = tour.getLastAttackTime();
        this.portee = tour.getPortee();
        this.degats = tour.getDegats();
    }
}
