package com.example.chinesedog.Model;

import java.util.List;

public class CarteBuilder {
    String carteString;
    String carteStringSansEspace;
    int hauteur;
    int largeur;
    List<List<Case>> cases;

    public CarteBuilder(int hauteur, int largeur) {
        this.hauteur = hauteur;
        this.largeur = largeur;
    }

    public CarteBuilder setCarteString(String carteString) {
        this.carteString = carteString;
        return this;
    }

    public CarteBuilder setCarteStringSansEspace(String carteStringSansEspace) {
        this.carteStringSansEspace = carteStringSansEspace;
        return this;
    }

    public CarteBuilder setCases(List<List<Case>> cases) {
        this.cases = cases;
        return this;
    }

    public Carte build() {
        return new Carte(this);
    }

}
