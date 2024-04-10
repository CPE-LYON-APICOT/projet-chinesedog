package com.example.chinesedog.Model;

import java.util.List;

public class Vague  {

        private List<Ennemi> ennemis;
        private int nombreEnnemis;
        private int niveauVague;
        private int intervalleSpawn;
        //TODO on peut imaginer un attribut chemin pour les ennemis

        public Vague(int nombreEnnemis, int niveauVague, int intervalleSpawn, List<Ennemi> ennemis) {
            this.nombreEnnemis = nombreEnnemis;
            this.niveauVague = niveauVague;
            this.intervalleSpawn = intervalleSpawn;
            this.ennemis = ennemis;
        }

        public int getNombreEnnemis() {
            return nombreEnnemis;
        }

        public int getNiveauVague() {
            return niveauVague;
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
            System.out.println(" - Ennemi nom = " + ennemi.getNom() + " - Vie ennemi = " + ennemi.getVie() + " - Vitesse = " + ennemi.getVitesse());
            // Affichez d'autres détails d'ennemis si nécessaire
        }
    }

    public Ennemi getEnnemiInList(int index) {
        return ennemis.get(index);
    }

}
