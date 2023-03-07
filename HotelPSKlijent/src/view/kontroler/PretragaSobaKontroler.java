/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view.kontroler;

import komunikacija.Komunikacija;
import domen.Soba;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import view.konstante.Konstante;
import view.koordinator.MainKoordinator;
import view.forme.FrmPretragaSoba;
import view.forme.komponenta.tabele.SobaTableModel;

/**
 *
 * @author Bane
 */
public class PretragaSobaKontroler {

    private final FrmPretragaSoba frmPretragaSoba;
    private SobaTableModel stm;

    public PretragaSobaKontroler(FrmPretragaSoba frmPretragaSoba) {
        this.frmPretragaSoba = frmPretragaSoba;
        addActionListener();
    }

    private void addActionListener() {
        frmPretragaSoba.getBtnDetaljiAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = frmPretragaSoba.getTabelaSoba().getSelectedRow();
                if (row >= 0) {
                    Soba soba = ((SobaTableModel) frmPretragaSoba.getTabelaSoba().getModel()).getSobaAt(row);
                    MainKoordinator.getInstanca().addParam(Konstante.PARAM_SOBA, soba);
                    MainKoordinator.getInstanca().openSobaDetailsForm();
                } else {
                    JOptionPane.showMessageDialog(frmPretragaSoba, "Sistem ne može da učita sobu", "Soba greška", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        frmPretragaSoba.getBtnFiltrirajAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String sprat = frmPretragaSoba.getTxtSprat().getText().trim();

                    if (sprat.isEmpty()) {
                        JOptionPane.showMessageDialog(frmPretragaSoba, "Morate uneti sprat", "Soba greška", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    List<Soba> sobe = Komunikacija.getInstanca().vratiSobeSprat(Integer.parseInt(sprat));
                    if (sobe.isEmpty()) {
                        fillTabelaSobe();
                        JOptionPane.showMessageDialog(frmPretragaSoba, "Sistem ne može da nađe sobe po zadatoj vrednosti", "Soba greška", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    JOptionPane.showMessageDialog(frmPretragaSoba, "Sistem je našao sobe po zadatoj vrednosti");
                    stm = (SobaTableModel) frmPretragaSoba.getTabelaSoba().getModel();
                    stm.setSobe(sobe);
                } catch (Exception ex) {
                    Logger.getLogger(SobaKontroler.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(frmPretragaSoba, "Sistem ne može da nađe sobe po zadatoj vrednosti", "Soba greška", JOptionPane.ERROR_MESSAGE);
                }
            }

        });

        frmPretragaSoba.addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                fillTabelaSobe();
            }

        });

    }

    public void openForm() {
        frmPretragaSoba.setLocationRelativeTo(MainKoordinator.getInstanca().getMainController().getFrmMain());
        prepareView();
        frmPretragaSoba.setVisible(true);
    }

    private void prepareView() {
        frmPretragaSoba.setTitle("Pretraga soba");
    }

    private void fillTabelaSobe() {
        try {
            List<Soba> sobe = Komunikacija.getInstanca().vratiSveSobe();
            stm = new SobaTableModel(sobe);
            frmPretragaSoba.getTabelaSoba().setModel(stm);
        } catch (Exception ex) {
            Logger.getLogger(PretragaSobaKontroler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
