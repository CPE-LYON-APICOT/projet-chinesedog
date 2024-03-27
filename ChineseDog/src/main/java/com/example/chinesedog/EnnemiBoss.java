package com.example.chinesedog;

public class EnnemiBoss extends Ennemi implements attaquer {

    private boolean peutAttaquerVolant;
    public EnnemiBoss(int vie, int vitesse, int armure, int resistanceMagique, String nom, String imagePath, boolean peutAttaquerVolant) {
        super(vie, vitesse, armure, resistanceMagique, nom, imagePath);
        this.peutAttaquerVolant = true;
    }
    @Override
    public void attaquer() {
        System.out.println("L'ennemi volant attaque");
        // TODO : Ajouter le code pour attaquer une fois la Base codé
    }

    public void attaqueSpeciale() {
        System.out.println("L'ennemi boss attaque spécialement");
        // TODO : Ajouter le code pour attaquer une fois la Base codé
    }

    public boolean getPeutAttaquerVolant() {
        return peutAttaquerVolant;
    }
}
