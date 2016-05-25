package controller;

import model.TortueModel;
import view.ModeRandom;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

/**
 * Created by thomas on 11/05/16.
 */
public class ControllerRandom extends Thread implements ActionListener {

    private int sleepTime;
    private TortueModel model;
    private ModeRandom view;
    private boolean running = true;
    private Random rand = new Random();

    public ControllerRandom(TortueModel model, ModeRandom view) {
        this.model = model;
        this.view = view;
        this.sleepTime = rand.nextInt(250)+500;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String c = actionEvent.getActionCommand();

        switch(c){
            case "Random":
                this.start();
                System.out.println("rand");
                break;
            default:
                break;
        }

    }

    public void run(){
        model.leverCrayon();
        while(running){
            int choix = new Random().nextInt(2);
            switch (choix){
                case 0:
                    model.gauche(rand.nextInt(180)+1);
                    model.avancer(rand.nextInt(45)+15,view.xMaxFeuilleDessin,view.yMaxFeuilleDessin);
                    break;
                case 1:
                    model.droite(rand.nextInt(180)+1);
                    model.avancer(rand.nextInt(45)+15,view.xMaxFeuilleDessin,view.yMaxFeuilleDessin);
                    break;
                default:
                    System.out.println("What are you doing guys ?");
                    break;
            }
            try {
                sleep(sleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
