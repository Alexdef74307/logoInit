package controller;

import model.FeuilleModel;
import model.TortueModel;
import view.ModeFlocking;
import view.ModeRandom;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

/**
 * Created by thomas on 11/05/16.
 */
public class ControllerFlocking extends Thread implements ActionListener {

    private int sleepTime;
    private TortueModel model;
    private ModeFlocking view;
    private boolean running = true;
    private Random rand = new Random();
    private FeuilleModel fModel;

    public ControllerFlocking(TortueModel model, FeuilleModel fm, ModeFlocking view) {
        this.model = model;
        this.view = view;
        this.fModel = fm;
        this.sleepTime = 500;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String c = actionEvent.getActionCommand();

        switch(c){
            case "Random":
                this.start();
                break;
            default:
                break;
        }

    }

    public void run(){
        model.leverCrayon();
        while(running){
//            int choix = new Random().nextInt(2);
//            switch (choix){
//                case 0:
//                    model.gauche(rand.nextInt(180)+1);
//                    model.avancer(rand.nextInt(45)+15,view.xMaxFeuilleDessin,view.yMaxFeuilleDessin);
//                    break;
//                case 1:
//                    model.droite(rand.nextInt(180)+1);
//                    model.avancer(rand.nextInt(45)+15,view.xMaxFeuilleDessin,view.yMaxFeuilleDessin);
//                    break;
//                default:
//                    System.out.println("What are you doing guys ?");
//                    break;
//            }
            model.avancer(fModel.estDansChampVision(model, model.vitesse, model.angleVision), view.xMaxFeuilleDessin, view.yMaxFeuilleDessin);
            try {
                sleep(sleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
