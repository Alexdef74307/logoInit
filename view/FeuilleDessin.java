package view;

// package logo;

import model.FeuilleModel;
import model.TortueModel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;

/**
 * Titre :        Logo
 * Description :  Un exemple de programme graphique utilisant la celebre TortueModelModel Logo
 * Copyright :    Copyright (c) 2000
 * Societe :      LIRMM
 * @author J. Ferber
 * @version 2.0
 */

public class FeuilleDessin extends JPanel {
	private FeuilleModel feuilleModel;

	public FeuilleDessin(FeuilleModel feuilleModel) {
		this.feuilleModel = feuilleModel;
	}





	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		Color c = g.getColor();
		
		Dimension dim = getSize();
		g.setColor(Color.white);
		g.fillRect(0,0,dim.width, dim.height);
		g.setColor(c);

		showTurtles(g);
	}
	
	public void showTurtles(Graphics g) {

		for(Iterator it = feuilleModel.tortueModels.iterator();it.hasNext();) {
			TortueModel t = (TortueModel) it.next();
			t.drawTurtle(g,Color.GREEN);
		}
	}

}
