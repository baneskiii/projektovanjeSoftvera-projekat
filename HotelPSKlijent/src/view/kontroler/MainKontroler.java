/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view.kontroler;

import komunikacija.Komunikacija;
import domen.Korisnik;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import view.konstante.Konstante;
import view.koordinator.MainKoordinator;
import view.forme.FrmMain;

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
        Korisnik korisnik = (Korisnik) MainKoordinator.getInstanca().getParam(Konstante.CURRENT_USER);
        frmMain.getLabelaUlogovaniKorisnik().setText(korisnik.getIme() + " " + korisnik.getPrezime());
        frmMain.setVisible(true);
    }

    private void addActionListener() {
        frmMain.jmiDodajGostaAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainKoordinator.getInstanca().openAddGuestForm();
            }
        });

        frmMain.jmiPretraziGosteAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainKoordinator.getInstanca().openSearchGuestForm();
            }
        });

        frmMain.jmiDodajSobuAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainKoordinator.getInstanca().openAddRoomForm();
            }
        });

        frmMain.jmiPretraziSobeAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainKoordinator.getInstanca().openSearchRoomForm();
            }
        });

        frmMain.jmiDodajRezervacijuAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainKoordinator.getInstanca().openAddReservationForm();
            }
        });

        frmMain.jmiPretraziRezervacijuAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainKoordinator.getInstanca().openSearchReservationForm();
            }
        });

        frmMain.jmiSoftverAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frmMain, "Softver je napravio student Branislav Trajkov.");
            }
        });

        frmMain.addKrajBtnAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Komunikacija.getInstanca().krajRada();
                    Korisnik k = (Korisnik) MainKoordinator.getInstanca().getParam(Konstante.CURRENT_USER);
                    JOptionPane.showMessageDialog(frmMain, "Doviđenja, " + k.getIme() + " " + k.getPrezime() + ". Prijatan dan!");
                    System.exit(0);
                } catch (Exception ex) {
                    Logger.getLogger(MainKontroler.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        frmMain.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    Komunikacija.getInstanca().krajRada();
                    Korisnik k = (Korisnik) MainKoordinator.getInstanca().getParam(Konstante.CURRENT_USER);
                    JOptionPane.showMessageDialog(frmMain, "Doviđenja, " + k.getIme() + " " + k.getPrezime() + ". Prijatan dan!");
                    System.exit(0);
                } catch (Exception ex) {
                    Logger.getLogger(MainKontroler.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        });

    }

    public FrmMain getFrmMain() {
        return frmMain;
    }

}
