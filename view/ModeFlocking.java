package view;


// package logo;

import controller.ControllerFlocking;
import controller.ControllerRandom;
import model.FeuilleModel;
import model.TortueModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

public class ModeFlocking extends JFrame implements Observer {
    public static final Dimension VGAP = new Dimension(1,5);
    public static final Dimension HGAP = new Dimension(5,1);

    private TortueModel courante;
    private FeuilleModel feuilleModel;
    private FeuilleDessin feuille;
    private JTextField inputValue;
    private ControllerFlocking c;
    public int xMaxFeuilleDessin = 600;
    public int yMaxFeuilleDessin = 400;


    /**
     * @param args
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable(){
            public void run(){

                ModeManuel fenetre = new ModeManuel();
                fenetre.setVisible(true);
            }
        });

    }

    public void quitter() {
        System.exit(0);
    }

    public ModeFlocking() {
        super("un logo tout simple");
        logoInit();

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent arg0) {
                super.windowClosing(arg0);
                System.exit(0);
            }
        });
    }

    public void logoInit() {
        getContentPane().setLayout(new BorderLayout(10,10));
        feuilleModel = new FeuilleModel();
        feuille = new FeuilleDessin(feuilleModel); //500, 400);
        feuille.setBackground(Color.white);
        feuille.setSize(new Dimension(xMaxFeuilleDessin,yMaxFeuilleDessin));
        feuille.setPreferredSize(new Dimension(xMaxFeuilleDessin,yMaxFeuilleDessin));

        getContentPane().add(feuille,"Center");

        // Definition des Tortues
        int nbTortue = new Random().nextInt(15)+5;
        System.out.println(nbTortue);
        for(int i =0;i<nbTortue;i++){
            System.out.println("test");
            // Creation de la TortueModel
            TortueModel TortueModel = new TortueModel();
            int xDepart = new Random().nextInt(this.xMaxFeuilleDessin);
            int yDepart = new Random().nextInt(this.yMaxFeuilleDessin);
            System.out.println(("x :" + xDepart + ",y :" + yDepart ));
            // Deplacement de la TortueModel au centre de la feuille
            TortueModel.setPosition(xDepart,yDepart);

            courante = TortueModel;
            courante.addObserver(this);
            feuilleModel.addTortueModel(TortueModel);
            feuilleModel.addTortueModel(TortueModel);
            // Création du controller
            this.c = new ControllerFlocking(courante,this);
            c.start();
        }
        // Boutons
        JToolBar toolBar = new JToolBar();
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(toolBar);

        getContentPane().add(buttonPanel,"North");

        addButton(toolBar,"Effacer","Nouveau dessin","/icons/index.png");

        //addButton(toolBar, "Random", "Random", null);

        String[] colorStrings = {"noir", "bleu", "cyan","gris fonce","rouge",
                "vert", "gris clair", "magenta", "orange",
                "gris", "rose", "jaune"};

        // Create the combo box
        toolBar.add(Box.createRigidArea(HGAP));
        JLabel colorLabel = new JLabel("   Couleur: ");
        toolBar.add(colorLabel);
        JComboBox colorList = new JComboBox(colorStrings);
        toolBar.add(colorList);

        colorList.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JComboBox cb = (JComboBox)e.getSource();
                int n = cb.getSelectedIndex();
                courante.setColor(n);
            }
        });


        // Menus
        JMenuBar menubar=new JMenuBar();
        setJMenuBar(menubar);	// on installe le menu bar
        JMenu menuFile=new JMenu("File"); // on installe le premier menu
        menubar.add(menuFile);

        addMenuItem(menuFile, "Effacer", "Effacer", KeyEvent.VK_N);
        addMenuItem(menuFile, "Quitter", "Quitter", KeyEvent.VK_Q);

        JMenu menuCommandes=new JMenu("Commandes"); // on installe le premier menu
        menubar.add(menuCommandes);
        addMenuItem(menuCommandes, "Avancer", "Avancer", -1);
        addMenuItem(menuCommandes, "Droite", "Droite", -1);
        addMenuItem(menuCommandes, "Gauche", "Gauche", -1);
        addMenuItem(menuCommandes, "Lever Crayon", "Lever", -1);
        addMenuItem(menuCommandes, "Baisser Crayon", "Baisser", -1);

        JMenu menuHelp=new JMenu("Aide"); // on installe le premier menu
        menubar.add(menuHelp);
        addMenuItem(menuHelp, "Aide", "Help", -1);
        addMenuItem(menuHelp, "A propos", "About", -1);

        setDefaultCloseOperation(EXIT_ON_CLOSE);



        // les boutons du bas
        JPanel p2 = new JPanel(new GridLayout());
        JButton b20 = new JButton("Proc1");
        p2.add(b20);
        b20.addActionListener(c);
        JButton b21 = new JButton("Proc2");
        p2.add(b21);
        b21.addActionListener(c);
        JButton b22 = new JButton("Proc3");
        p2.add(b22);
        b22.addActionListener(c);

        getContentPane().add(p2,"South");



        pack();
        setVisible(true);
    }

    public String getInputValue(){
        String s = inputValue.getText();
        return(s);
    }

    // efface tout et reinitialise la feuille
    public void effacer() {
        feuilleModel.reset();
        feuille.repaint();

        // Replace la TortueModel au centre
        Dimension size = feuille.getSize();
        System.out.println(courante);
        courante.setPosition(size.width/2, size.height/2);
    }

    //utilitaires pour installer des boutons et des menus
    public void addButton(JComponent p, String name, String tooltiptext, String imageName) {
        JButton b;
        if ((imageName == null) || (imageName.equals(""))) {
            b = (JButton)p.add(new JButton(name));
        }
        else {
            java.net.URL u = this.getClass().getResource(imageName);
            if (u != null) {
                ImageIcon im = new ImageIcon (u);
                b = (JButton) p.add(new JButton(im));
            }
            else
                b = (JButton) p.add(new JButton(name));
            b.setActionCommand(name);
        }

        b.setToolTipText(tooltiptext);
        b.setBorder(BorderFactory.createRaisedBevelBorder());
        b.setMargin(new Insets(0,0,0,0));
        b.addActionListener(c);
    }

    public void addMenuItem(JMenu m, String label, String command, int key) {
        JMenuItem menuItem;
        menuItem = new JMenuItem(label);
        m.add(menuItem);

        menuItem.setActionCommand(command);
        menuItem.addActionListener(this.c);
        if (key > 0) {
            if (key != KeyEvent.VK_DELETE)
                menuItem.setAccelerator(KeyStroke.getKeyStroke(key, Event.CTRL_MASK, false));
            else
                menuItem.setAccelerator(KeyStroke.getKeyStroke(key, 0, false));
        }
    }

    @Override
    public void update(Observable observable, Object o) {
        feuille.repaint();
    }
}
