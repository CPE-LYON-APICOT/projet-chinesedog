package com.example.chinesedog.Model;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

public class ButtonClickHandler implements EventHandler<ActionEvent> {

    private final Tour tour;
    private final StackPane root;

    public ButtonClickHandler(Tour tour, StackPane root) {
        this.tour = tour;
        this.root = root;
    }

    @Override
    public void handle(ActionEvent event) {
        upgradeTower(tour);
        updateText();
        event.consume();
    }

    private void upgradeTower(Tour tour) {
        System.out.println("Niveau de la tour : " + tour.getNiveau());
        if (tour.getNiveau() == 1) {
            tour = new Niveau2(tour);
            System.out.println("La tour a été mise à niveau 2 avec succès !");
        }
        else if (tour.getNiveau() == 2) {
            tour = new Niveau3(tour);
            System.out.println("La tour a été mise à niveau 3 avec succès !");
        }
        else {
            System.out.println("La tour est déjà au niveau maximum.");
        }
    }

    private void updateText() {
        for (Node node : root.getChildren()) {
            if (node instanceof Text text) {
                text.setText(tour.getDescription());
                break;
            }
        }
    }

}
