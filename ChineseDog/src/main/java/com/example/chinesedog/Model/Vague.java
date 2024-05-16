package com.example.chinesedog.Model;

import java.util.List;

public class Vague  {

    private int nbVolant;
    private int nbTerrestre;
    private int nbBoss;
    private int niveauVague;
    private int intervalleSpawn;
    //TODO on peut imaginer un attribut chemin pour les ennemis

    public Vague(int nbVolant, int nbTerrestre, int nbBoss, int niveauVague, int intervalleSpawn) {
        this.niveauVague = niveauVague;
        this.intervalleSpawn = intervalleSpawn;
        this.nbVolant = nbVolant;
        this.nbTerrestre = nbTerrestre;
        this.nbBoss = nbBoss;
    }

    public int getNombreEnnemis() {
        return getNbBoss()+getNbTerrestre()+getNbVolant();
    }


    public int getNbVolant() {
        return nbVolant;
    }

    public int getNbTerrestre() {
        return nbTerrestre;
    }

    public int getNbBoss() {
        return nbBoss;
    }


    public int getNiveauVague() {
        return niveauVague;
    }

    public int getIntervalleSpawn() {
        return intervalleSpawn;
    }


}
