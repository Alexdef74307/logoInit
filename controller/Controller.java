package controller;

import view.SimpleLogo;
import model.TortueModel;
import model.TortueModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by thomas on 11/05/16.
 */
public class Controller implements ActionListener {
    private TortueModel model;
    private SimpleLogo view;

    public Controller(TortueModel model, SimpleLogo view) {
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
                    System.out.println("avancer :" + v);
                    model.avancer(v);
                } catch (NumberFormatException ex){
                    System.err.println("ce n'est pas un nombre : " + view.getInputValue());
                }
                break;
            case "Droite" :
                try {
                    int v = Integer.parseInt(view.getInputValue());
                    model.droite(v);
                } catch (NumberFormatException ex){
                    System.err.println("ce n'est pas un nombre : " + view.getInputValue());
                }
                break;
            case "Gauche" :
                try {
                    int v = Integer.parseInt(view.getInputValue());
                    model.gauche(v);
                } catch (NumberFormatException ex){
                    System.err.println("ce n'est pas un nombre : " + view.getInputValue());
                }
                break;
            case "Lever" :
                model.leverCrayon();
                break;
            case "Baisser" :
                model.baisserCrayon();
                break;
            case "Proc1" :
                model.carre();
                break;
            case "Proc2" :
                model.poly(60, 8);
                break;
            case "Proc3" :
                model.spiral(50, 40, 6);
                break;
            case "Quitter" :
             //   view.quitter();
            default:
                break;
        }
    }
}
