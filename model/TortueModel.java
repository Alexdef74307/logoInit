package model;

// package logo;

import model.turtleForm.Arrow;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;


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
	
	protected int x, y;	
	protected int dir;	
	protected boolean crayon; 
	protected int coul;
	
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

	public void champDeVision(int dist, int champVisible) {

		System.out.println("Angle du champ de vision \n ----------------------------------------------------------");
		System.out.println(dir+champVisible/2);
		System.out.println(dir-champVisible/2);
		System.out.println("----------------------------------------------------------");

		int newX1 = (int) Math.round(x+dist*Math.cos(ratioDegRad*(dir+champVisible/2)));
		int newX2 = (int) Math.round(x+dist*Math.cos(ratioDegRad*(dir-champVisible/2)));
		int newY1 = (int) Math.round(y + dist * Math.sin(ratioDegRad * (dir + champVisible / 2)));
		int newY2 = (int) Math.round(y + dist * Math.sin(ratioDegRad * (dir - champVisible / 2)));

		if (newX1 == x && newX2 != x ) {
			double coeffDir2 = (newY2 - y) / (newX2 - x);
			double ordOrigine2 = y - coeffDir2 * x;

			System.out.println("Equation des droites du champ de vision \n ----------------------------------------------------------");
			System.out.println("Equation de la droite1 : x = " + x);
			System.out.println("Equation de la droite2 : " + coeffDir2 + "x + " + ordOrigine2);
			System.out.println("----------------------------------------------------------");
		}
		else if (newX1 != x && newX2 == x ) {
			double coeffDir1 = (newY1 - y) / (newX1 - x);
			double ordOrigine1 = y - coeffDir1 * x;

			System.out.println("Equation des droites du champ de vision \n ----------------------------------------------------------");
			System.out.println("Equation de la droite1 : " + coeffDir1 + "x + " + ordOrigine1);
			System.out.println("Equation de la droite2 : x = " + x);
			System.out.println("----------------------------------------------------------");
		}
		else if (newX1 == x && newX2 == x ) {

		}
		else {

			double coeffDir1 = (newY1 - y) / (newX1 - x);
			double coeffDir2 = (newY2 - y) / (newX2 - x);

			double ordOrigine1 = y - coeffDir1 * x;
			double ordOrigine2 = y - coeffDir2 * x;

			System.out.println("Equation des droites du champ de vision \n ----------------------------------------------------------");
			System.out.println("Equation de la droite1 : " + coeffDir1 + "x + " + ordOrigine1);
			System.out.println("Equation de la droite2 : " + coeffDir2 + "x + " + ordOrigine2);
			System.out.println("----------------------------------------------------------");
		}
	}

	public void avancer(int dist, int xMaxFeuilleDessin, int yMaxFeuilleDessin) {
		int newX = (int) Math.round(x+dist*Math.cos(ratioDegRad*dir));
		int newY = (int) Math.round(y+dist*Math.sin(ratioDegRad*dir));

		champDeVision(dist, 90);

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
