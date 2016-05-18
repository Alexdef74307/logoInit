package controller;

import view.ModeManuel;
import model.TortueModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by thomas on 11/05/16.
 */
public class ControllerManuel implements ActionListener {
    private TortueModel model;
    private ModeManuel view;

    public ControllerManuel(TortueModel model, ModeManuel view) {
        this.model = model;
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String c = actionEvent.getActionCommand();
        System.out.println("test");
        switch(c){

            case "Avancer" :
                System.out.println("command avancer");
                try {
                    int v = Integer.parseInt(view.getInputValue());
                    model.avancer(v);
                } catch (NumberFormatException ex){
                    System.err.println("ce n'est pas un nombre : " + view.getInputValue());
                }
                break;
            case "Droite" :
                System.out.println("command droite");
                try {
                    int v = Integer.parseInt(view.getInputValue());
                    model.droite(v);
                } catch (NumberFormatException ex){
                    System.err.println("ce n'est pas un nombre : " + view.getInputValue());
                }
                break;
            case "Gauche" :
                System.out.println("command gauche");
                try {
                    int v = Integer.parseInt(view.getInputValue());
                    model.gauche(v);
                } catch (NumberFormatException ex){
                    System.err.println("ce n'est pas un nombre : " + view.getInputValue());
                }
                break;
            case "Lever" :
                System.out.println("command lever");
                model.leverCrayon();
                break;
            case "Baisser" :
                System.out.println("command baisser");
                model.baisserCrayon();
                break;
            case "Proc1" :
                System.out.println("command proc1");
                model.carre();
                break;
            case "Proc2" :
                System.out.println("command proc2");
                model.poly(60, 8);
                break;
            case "Proc3" :
                System.out.println("command proc3");
                model.spiral(50, 40, 6);
                break;
            case "Effacer" :
                System.out.println("command effacer");
                view.effacer();
                break;
            case "Quitter" :
                System.out.println("command quitter");
                view.quitter();
            default:
                break;
        }
    }
}
