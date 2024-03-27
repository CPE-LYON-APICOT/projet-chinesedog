package com.example.chinesedog;

public class EnnemiTerrestre extends Ennemi implements attaquer {

    private boolean peutAttaquerVolant;
    public EnnemiTerrestre(int vie, int vitesse, int armure, int resistanceMagique, String nom, String imagePath, boolean peutAttaquerVolant) {
        super(vie, vitesse, armure, resistanceMagique, nom, imagePath);
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
