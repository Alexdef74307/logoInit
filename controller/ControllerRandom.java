package controller;

import model.TortueModel;
import view.ModeManuel;
import view.ModeRandom;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by thomas on 11/05/16.
 */
public class ControllerRandom implements ActionListener {

    private TortueModel model;
    private ModeRandom view;

    public ControllerRandom(TortueModel model, ModeRandom view) {
        this.model = model;
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String c = actionEvent.getActionCommand();

        switch(c){
            case "Random":
                System.out.println("rand");
                break;
            default:
                break;
        }

    }
}
