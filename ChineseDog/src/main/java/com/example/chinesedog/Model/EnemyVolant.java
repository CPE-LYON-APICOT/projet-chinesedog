package com.example.chinesedog.Model;

import com.example.chinesedog.attaquer;

import java.util.List;

public class EnemyVolant extends Enemy implements attaquer {

    private boolean peutAttaquerVolant;
    public EnemyVolant(List<String> waypoints, int vie, double vitesse, int armure, int resistanceMagique) {
        super(waypoints, vitesse, vie, armure, resistanceMagique);
        this.peutAttaquerVolant = true;
    }
    @Override
    public void attaquer() {
        System.out.println("L'ennemi volant attaque");
        // TODO : Ajouter le code pour attaquer une fois la Base codé
    }

    public boolean getPeutAttaquerVolant() {
        return peutAttaquerVolant;
    }
}

