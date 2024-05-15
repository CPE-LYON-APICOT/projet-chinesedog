package com.example.chinesedog.Model;

import java.util.List;

public class Vague  {

    private List<Ennemi> ennemis;
    private int nombreEnnemis;
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

    public List<Ennemi> getListEnnemi() {
        return ennemis;
    }
    public void displayVague() {
        System.out.println("Détails de la vague :");
        System.out.println("Nombre d'ennemis : " + nombreEnnemis);
        System.out.println("Niveau de la vague : " + niveauVague);
        System.out.println("Intervalle de spawn : " + intervalleSpawn);
        System.out.println("Liste des ennemis :");
        for (Ennemi ennemi : ennemis) {
            //System.out.println(" - Ennemi nom = " + ennemi.getNom() + " - Vie ennemi = " + ennemi.getVie() + " - Vitesse = " + ennemi.getVitesse());
            // Affichez d'autres détails d'ennemis si nécessaire
        }
    }

    public Ennemi getEnnemiInList(int index) {
        return ennemis.get(index);
    }

}
