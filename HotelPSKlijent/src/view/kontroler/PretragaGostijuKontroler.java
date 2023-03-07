/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view.kontroler;

import komunikacija.Komunikacija;
import domen.Gost;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import view.konstante.Konstante;
import view.koordinator.MainKoordinator;
import view.forme.FrmPretragaGostiju;
import view.forme.komponenta.tabele.GostTableModel;

/**
 *
 * @author Bane
 */
public class PretragaGostijuKontroler {

    private final FrmPretragaGostiju frmPretragaGostiju;
    private GostTableModel gtm;

    public PretragaGostijuKontroler(FrmPretragaGostiju frmPretragaGostiju) {
        this.frmPretragaGostiju = frmPretragaGostiju;
        addActionListener();
    }

    private void addActionListener() {
        frmPretragaGostiju.getBtnDetaljiAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = frmPretragaGostiju.getTabelaGostiju().getSelectedRow();
                if (row >= 0) {
                    Gost gost = ((GostTableModel) frmPretragaGostiju.getTabelaGostiju().getModel()).getGostAt(row);
                    MainKoordinator.getInstanca().addParam(Konstante.PARAM_GOST, gost);
                    MainKoordinator.getInstanca().openGostDetailsForm();
                } else {
                    JOptionPane.showMessageDialog(frmPretragaGostiju, "Sistem ne može da učita gosta", "Gost greška", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        frmPretragaGostiju.getBtnFiltrirajAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String ime = frmPretragaGostiju.getTxtIme().getText().trim();
                    String prezime = frmPretragaGostiju.getTxtPrezime().getText().trim();
                    if(ime.isEmpty() || prezime.isEmpty()){
                        JOptionPane.showMessageDialog(frmPretragaGostiju, "Morate uneti ime i prezime", "Gost greška", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    List<Gost> gosti = Komunikacija.getInstanca().vratiGosteImePrezime(ime, prezime);
                    if(gosti.isEmpty()){
                        fillTabelaGosti();
                        JOptionPane.showMessageDialog(frmPretragaGostiju, "Sistem ne može da nađe goste po zadatoj vrednosti", "Gost greška", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    JOptionPane.showMessageDialog(frmPretragaGostiju, "Sistem je našao goste po zadatoj vrednosti");
                    gtm = (GostTableModel) frmPretragaGostiju.getTabelaGostiju().getModel();
                    gtm.setGosti(gosti);
                } catch (Exception ex) {
                    Logger.getLogger(PretragaGostijuKontroler.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(frmPretragaGostiju, "Sistem ne može da nađe goste po zadatoj vrednosti", "Gost greška", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        frmPretragaGostiju.addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                fillTabelaGosti();
            }
            

        });

    }

    public void openForm() {
        frmPretragaGostiju.setLocationRelativeTo(MainKoordinator.getInstanca().getMainController().getFrmMain());
        prepareView();
        frmPretragaGostiju.setVisible(true);
    }

    private void prepareView() {
        frmPretragaGostiju.setTitle("Pretraga gostiju");
    }

    private void fillTabelaGosti() {
        try {
            List<Gost> gosti = Komunikacija.getInstanca().vratiSveGoste();
            gtm = new GostTableModel(gosti);
            frmPretragaGostiju.getTabelaGostiju().setModel(gtm);
        } catch (Exception ex) {
            Logger.getLogger(PretragaGostijuKontroler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
