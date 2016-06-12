package model;

// package logo;

import model.turtleForm.Arrow;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;
import java.util.Random;


/*************************************************************************

	Un petit Logo minimal qui devra etre ameliore par la suite

	Source originale : J. Ferber - 1999-2001

			   Cours de DESS TNI - Montpellier II

	@version 2.0
	@date 25/09/2001

**************************************************************************/


public class TortueModel extends Observable
{

	protected static final int rp=10, rb=5; // Taille de la pointe et de la base de la fleche
	protected static final double ratioDegRad = 0.0174533; // Rapport radians/degres (pour la conversion)
	
	protected ArrayList<Segment> listSegments; // Trace de la tortue

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	private int x, y;

	public int getDir() {
		return dir;
	}

	public int getVitesse() {
		return vitesse;
	}

	public void setDir(int dir) {
		this.dir = dir;
	}

	public void setVitesse(int vitesse) {
		this.vitesse = vitesse;
	}

	private int dir;
	protected boolean crayon; 
	protected int coul;

    private int vitesse = 50;
    public int angleVision = 90;
	
	public void setColor(int n) {coul = n;}
	public int getColor() {return coul;}

	public TortueModel() {
		listSegments = new ArrayList<Segment>();
		reset();
	}

	public void reset() {
		x = 0;
		y = 0;
		dir = -90;
		coul = 0;
		crayon = true;
		listSegments.clear();
		this.notifyObservers();
	}

	public void setPosition(int newX, int newY) {
		x = newX;
		y = newY;
		this.notifyObservers();
	}
	
	public void drawTurtle (Graphics graph,Color color) {
		if (graph==null)
			return;
		
		// Dessine les segments
		for(Iterator it = listSegments.iterator();it.hasNext();) {
			Segment seg = (Segment) it.next();
			seg.drawSegment(graph);
		}

		graph.fillPolygon(new Arrow(x,y,dir));


		graph.setColor(color);

    }

	protected Color decodeColor(int c) {
		switch(c) {
			case 0: return(Color.black);
			case 1: return(Color.blue);
			case 2: return(Color.cyan);
			case 3: return(Color.darkGray);
			case 4: return(Color.red);
			case 5: return(Color.green);
			case 6: return(Color.lightGray);
			case 7: return(Color.magenta);
			case 8: return(Color.orange);
			case 9: return(Color.gray);
			case 10: return(Color.pink);
			case 11: return(Color.yellow);
			default : return(Color.black);
		}
	}

	public void avancer(int dist) {

		int newX = (int) Math.round(x+dist*Math.cos(ratioDegRad*dir));
		int newY = (int) Math.round(y+dist*Math.sin(ratioDegRad*dir));
		
		if (crayon==true) {
			Segment seg = new Segment();
			
			seg.ptStart.x = x;
			seg.ptStart.y = y;
			seg.ptEnd.x = newX;
			seg.ptEnd.y = newY;
			seg.color = decodeColor(coul);
	
			listSegments.add(seg);
		}

		x = newX;
		y = newY;
		this.notifyObservers();
	}

	public double[][] champDeVision(int dist, int champVisible) {
		int newX1 = (int) Math.round(x+dist*Math.cos(ratioDegRad*(dir+champVisible/2)));
		int newX2 = (int) Math.round(x+dist*Math.cos(ratioDegRad*(dir-champVisible/2)));
		int newY1 = (int) Math.round(y + dist * Math.sin(ratioDegRad * (dir + champVisible / 2)));
		int newY2 = (int) Math.round(y + dist * Math.sin(ratioDegRad * (dir - champVisible / 2)));

        double coeffDir1 = Double.MAX_VALUE;
        double coeffDir2 = Double.MAX_VALUE;
        double ordOrigine1 = Double.MAX_VALUE;
        double ordOrigine2 = Double.MAX_VALUE;

		if (newX1 == x && newX2 != x ) {
			coeffDir2 = (newY2 - y) / (newX2 - x);
			ordOrigine2 = y - coeffDir2 * x;
		}
		else if (newX1 != x && newX2 == x ) {
			coeffDir1 = (newY1 - y) / (newX1 - x);
			ordOrigine1 = y - coeffDir1 * x;
		}
		else if (newX1 == x && newX2 == x ) {

		}
		else {
			coeffDir1 = (newY1 - y) / (newX1 - x);
			coeffDir2 = (newY2 - y) / (newX2 - x);
			ordOrigine1 = y - coeffDir1 * x;
			ordOrigine2 = y - coeffDir2 * x;
		}
        double[][] droites = new double[][] {
                {coeffDir1, ordOrigine1},
                {coeffDir2, ordOrigine2}
        };
        return droites;
	}

    public void avancer(ArrayList<TortueModel> visibles, int xMaxFeuilleDessin, int yMaxFeuilleDessin) {
        if (visibles.size() != 0) {
            int vitesseMoyenne = 0;
            int dirMoyenne = 0;

            for (TortueModel temp : visibles) {
                vitesseMoyenne += temp.vitesse;
                dirMoyenne += temp.dir;
            }

            vitesseMoyenne = vitesseMoyenne / visibles.size();
            dirMoyenne = dirMoyenne / visibles.size();
            this.dir = dirMoyenne;

            this.avancer(vitesseMoyenne, xMaxFeuilleDessin, yMaxFeuilleDessin);
        }
        else {
            Random rand = new Random();
			this.dir = rand.nextInt(180) + 1;
            avancer(rand.nextInt(45) + 15, xMaxFeuilleDessin, yMaxFeuilleDessin);
        }
    }

	public void avancer(int dist, int xMaxFeuilleDessin, int yMaxFeuilleDessin) {
		int newX = (int) Math.round(x+dist*Math.cos(ratioDegRad*dir));
		int newY = (int) Math.round(y+dist*Math.sin(ratioDegRad*dir));

		if (crayon==true) {
			Segment seg = new Segment();

			seg.ptStart.x = x;
			seg.ptStart.y = y;
			seg.ptEnd.x = newX;
			seg.ptEnd.y = newY;
			seg.color = decodeColor(coul);

			listSegments.add(seg);
		}
		boolean depasseVal = false;
		if(newX>xMaxFeuilleDessin){
			depasseVal = true;
			newX = newX - xMaxFeuilleDessin;
		}else if(newX<0){
			depasseVal = true;
			newX = newX + xMaxFeuilleDessin;
		}
		if(newY>yMaxFeuilleDessin){
			depasseVal = true;
			newY = newY - yMaxFeuilleDessin;
		}else if(newY<0){
			depasseVal = true;
			newY = newY + yMaxFeuilleDessin;
		}
		x = newX;
		y = newY;
		if(depasseVal){
			this.droite(180);
			this.avancer(dist);
			this.droite(180);
			this.avancer(dist);
		}
		this.notifyObservers();
	}

	public void droite(int ang) {
		dir = (dir + ang) % 360;
		this.notifyObservers();
	}

	public void gauche(int ang) {
		dir = (dir - ang) % 360;
		this.notifyObservers();
	}

	public void baisserCrayon() {
		crayon = true;
		this.notifyObservers();
	}

	public void leverCrayon() {
		crayon = false;
		this.notifyObservers();
	}

	public void couleur(int n) {
		coul = n % 12;
		this.notifyObservers();
	}

	public void couleurSuivante() {
	 	couleur(coul+1);
		this.notifyObservers();
	}

	/** quelques classiques
	 * @param xMaxFeuilleDessin
	 * @param yMaxFeuilleDessin*/

	public void carre(int xMaxFeuilleDessin, int yMaxFeuilleDessin) {
		for (int i=0;i<4;i++) {
			avancer(100,xMaxFeuilleDessin,yMaxFeuilleDessin);
			droite(90);
		}
	}

	public void poly(int n, int a,int xMaxFeuilleDessin, int yMaxFeuilleDessin) {
		for (int j=0;j<a;j++) {
			avancer(n,xMaxFeuilleDessin,yMaxFeuilleDessin);
			droite(360/a);
		}
	}

	public void spiral(int n, int k, int a, int xMaxFeuilleDessin, int yMaxFeuilleDessin) {
		for (int i = 0; i < k; i++) {
			couleur(coul+1);
			avancer(n,xMaxFeuilleDessin,yMaxFeuilleDessin);
			droite(360/a);
			n = n+1;
		}
	}

	public void notifyObservers(){
		this.setChanged();
		super.notifyObservers();
	}
}
