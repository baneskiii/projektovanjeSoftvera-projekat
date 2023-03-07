/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view.kontroler;

import komunikacija.Komunikacija;
import domen.Rezervacija;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import view.konstante.Konstante;
import view.koordinator.MainKoordinator;
import view.forme.FrmPretragaRezervacija;
import view.forme.komponenta.tabele.RezervacijaTableModel;

/**
 *
 * @author Bane
 */
public class PretragaRezervacijaKontroler {

    private final FrmPretragaRezervacija frmPretragaRezervacija;
    private RezervacijaTableModel rtm;

    public PretragaRezervacijaKontroler(FrmPretragaRezervacija frmPretragaRezervacija) {
        this.frmPretragaRezervacija = frmPretragaRezervacija;
        addActionListener();
    }

    private void addActionListener() {
        frmPretragaRezervacija.getBtnDetaljiAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = frmPretragaRezervacija.getTabelaRezervacija().getSelectedRow();
                if (row >= 0) {
                    Rezervacija rezervacija = ((RezervacijaTableModel) frmPretragaRezervacija.getTabelaRezervacija().getModel()).getRezervacijaAt(row);
                    MainKoordinator.getInstanca().addParam(Konstante.PARAM_REZERVACIJA, rezervacija);
                    MainKoordinator.getInstanca().openRezervacijaDetailsForm();
                } else {
                    JOptionPane.showMessageDialog(frmPretragaRezervacija, "Sistem ne može da učita rezervaciju", "Rezervacija greška", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        frmPretragaRezervacija.getBtnFiltrirajAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
                    String datum = frmPretragaRezervacija.getTxtDatumOd().getText().trim();
                    if (datum.isEmpty()) {
                        JOptionPane.showMessageDialog(frmPretragaRezervacija, "Morate uneti datum početka rezervacije", "Rezervacija greška", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    Date datumOd = sdf.parse(datum);
                    List<Rezervacija> rezervacije = Komunikacija.getInstanca().vratiRezervacijeDatumOd(datumOd);
                    if (rezervacije.isEmpty()) {
                        fillTabelaRezervacija();
                        JOptionPane.showMessageDialog(frmPretragaRezervacija, "Sistem ne može da nađe rezervacije po zadatoj vrednosti", "Rezervacija greška", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    JOptionPane.showMessageDialog(frmPretragaRezervacija, "Sistem je našao rezervacije po zadatoj vrednosti");
                    fillTabelaRezervacija();
                    rtm = (RezervacijaTableModel) frmPretragaRezervacija.getTabelaRezervacija().getModel();
                    rtm.setRezervacije(rezervacije);
                } catch (Exception ex) {
                    Logger.getLogger(PretragaRezervacijaKontroler.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(frmPretragaRezervacija, "Sistem ne može da nađe rezervacije po zadatoj vrednosti", "Rezervacija greška", JOptionPane.ERROR_MESSAGE);
                }
            }

        });

        frmPretragaRezervacija.addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                fillTabelaRezervacija();
            }

        });

    }

    public void openForm() {
        frmPretragaRezervacija.setLocationRelativeTo(MainKoordinator.getInstanca().getMainController().getFrmMain());
        prepareView();
        frmPretragaRezervacija.setVisible(true);
    }

    private void prepareView() {
        frmPretragaRezervacija.setTitle("Pretraga rezervacija");
    }

    private void fillTabelaRezervacija() {
        try {
            List<Rezervacija> rezervacije = Komunikacija.getInstanca().vratiSveRezervacije();
            rtm = new RezervacijaTableModel(rezervacije);
            frmPretragaRezervacija.getTabelaRezervacija().setModel(rtm);
        } catch (Exception ex) {
            Logger.getLogger(PretragaRezervacijaKontroler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
