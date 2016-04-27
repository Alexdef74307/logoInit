package controller;

import model.TortueModel;

public class Controller {
	
	protected TortueModel tm;

	public Controller(TortueModel tm) {
		this.tm = tm;
	}
	
	public void actionPerformed(String action, int v) {
		// actions des boutons du haut
		
		switch(action) {
			case "Avancer" :
				tm.avancer(v);
				break;
			case "Droite" :
				tm.droite(v);
				break;
			case "Gauche" : 
				tm.gauche(v);
				break;
			case "Lever" :
				tm.leverCrayon();
				break;
			case "Baisser" :
				tm.baisserCrayon();
				break;
			case "Proc1" :
				tm.carre();
				break;
			case "Proc2" :
				tm.poly(60,8);
				break;
			case "Proc3" :
				tm.spiral(50,40,6);
				break;
			default :
				System.err.println("Case undefined");
		}
	}
}
