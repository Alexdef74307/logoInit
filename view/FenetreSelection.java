package view;

import javax.swing.*;
import java.awt.event.*;

public class FenetreSelection extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JButton modeFlockingButton;

    public FenetreSelection() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

// call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

// call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        modeFlockingButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);
                ModeFlocking fenetre = new ModeFlocking();
                fenetre.setVisible(true);
                dispose();
            }
        });
    }

    private void onOK() {
    // add your code here
        SwingUtilities.invokeLater(new Runnable(){
            public void run(){

                ModeManuel fenetre = new ModeManuel();
                fenetre.setVisible(true);
                dispose();
            }
        });
    }


    private void onCancel() {
    // add your code here if necessary
        SwingUtilities.invokeLater(new Runnable(){
            public void run(){

                ModeRandom fenetre = new ModeRandom();
                fenetre.setVisible(true);
                dispose();
            }
        });
    }

    public static void main(String[] args) {
        FenetreSelection dialog = new FenetreSelection();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
