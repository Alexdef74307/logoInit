package model.turtleForm;

import java.awt.*;

/**
 * Created by thomas on 11/05/16.
 */


public class Arrow extends Polygon {

    protected static final int rp=10, rb=5; // Taille de la pointe et de la base de la fleche
    protected static final double ratioDegRad = 0.0174533; // Rapport radians/degres (pour la conversion)

    public Arrow(int x, int y, double dir) {

        //Calcule les 3 coins du triangle a partir de
        // la position de la tortue p
        Point p = new Point(x,y);
        Polygon arrow = new Polygon();

        //Calcule des deux bases
        //Angle de la droite
        double theta=ratioDegRad*(-dir);
        //Demi angle au sommet du triangle
        double alpha=Math.atan( (float)rb / (float)rp );
        //Rayon de la fleche
        double r=Math.sqrt( rp*rp + rb*rb );
        //Sens de la fleche
        Point p2=new Point((int) Math.round(p.x+r*Math.cos(theta)),
                (int) Math.round(p.y-r*Math.sin(theta)));
        this.addPoint(p2.x,p2.y);
        this.addPoint((int) Math.round( p2.x-r*Math.cos(theta + alpha) ),
                (int) Math.round( p2.y+r*Math.sin(theta + alpha) ));

        //Base2
        this.addPoint((int) Math.round( p2.x-r*Math.cos(theta - alpha) ),
                (int) Math.round( p2.y+r*Math.sin(theta - alpha) ));

        this.addPoint(p2.x,p2.y);
    }
}
