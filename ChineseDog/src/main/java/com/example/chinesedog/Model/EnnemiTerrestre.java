package com.example.chinesedog.Model;

import com.example.chinesedog.attaquer;

import java.util.List;

public class EnnemiTerrestre extends Enemy implements attaquer {

    private boolean peutAttaquerVolant;
    public EnnemiTerrestre(List<String> waypoints, int vie, int vitesse, int armure, int resistanceMagique) {
        super(waypoints, vitesse, vie, armure, resistanceMagique);
        this.peutAttaquerVolant = false;
    }
    @Override
    public void attaquer() {
        System.out.println("L'ennemi terrestre attaque");
        // TODO : Ajouter le code pour attaquer une fois la Base codé
    }

    public boolean getPeutAttaquerVolant() {
        return peutAttaquerVolant;
    }

}

