/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme.kontroler;

import kontroler.Kontroler;
import forme.FrmMain;
import forme.koordinator.MainKoordinator;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author Bane
 */
public class MainKontroler {

    private final FrmMain frmMain;

    public MainKontroler(FrmMain frmMain) {
        this.frmMain = frmMain;
        addActionListener();
    }

    public void openForm() {
        frmMain.setVisible(true);
    }

    private void addActionListener() {
        frmMain.jmiPodesavanjaGostaAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainKoordinator.getInstanca().openSettingsForm();
            }
        });

        frmMain.jmiOSoftveruGostaAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frmMain, "Softver je napravio student Branislav Trajkov.");
            }
        });

        frmMain.addStartBtnActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Kontroler.getInstanca().startujServer();
                frmMain.getBtnStart().setEnabled(false);
                frmMain.getBtnStop().setEnabled(true);
                frmMain.getLabelaStatus().setText("Status: Server je pokrenut");
                frmMain.getLabelaStatus().setForeground(Color.green);
            }
        });

        frmMain.addStopBtnActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Kontroler.getInstanca().stopServer();
                frmMain.getBtnStart().setEnabled(true);
                frmMain.getBtnStop().setEnabled(false);
                frmMain.getLabelaStatus().setText("Status: Server je zaustavljen");
                frmMain.getLabelaStatus().setForeground(Color.red);
            }
        });

    }

    public FrmMain getFrmMain() {
        return frmMain;
    }

}
