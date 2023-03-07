/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view.kontroler;

import komunikacija.Komunikacija;
import domen.Gost;
import domen.Ocena;
import domen.Soba;
import domen.VrstaSobe;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.ParseException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import view.konstante.Konstante;
import view.koordinator.MainKoordinator;
import view.forme.FrmSoba;
import view.forme.util.FormMode;

/**
 *
 * @author Bane
 */
public class SobaKontroler {

    private final FrmSoba frmSoba;

    public SobaKontroler(FrmSoba frmSoba) {
        this.frmSoba = frmSoba;
        addActionListener();
    }

    private void addActionListener() {
        frmSoba.addSacuvajBtnActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                save();
            }

            private void save() {
                try {
                    Soba soba = new Soba();
                    soba.setSprat(Integer.parseInt(frmSoba.getTxtSprat().getText().trim()));
                    soba.setStatus(frmSoba.getCbZauzeta().isSelected());
                    soba.setVrstaSobe((VrstaSobe) frmSoba.getCmbVrstaSobe().getSelectedItem());

                    if (soba.getSprat() == 0 || soba.getVrstaSobe() == null) {
                        JOptionPane.showMessageDialog(frmSoba, "Sistem ne može da zapamti sobu", "Soba greška", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    Komunikacija.getInstanca().sacuvajSobu(soba);
                    JOptionPane.showMessageDialog(frmSoba, "Sistem je zapamtio sobu", "Čuvanje sobe", JOptionPane.INFORMATION_MESSAGE);
                    frmSoba.dispose();
                } catch (Exception ex) {
                    Logger.getLogger(SobaKontroler.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(frmSoba, "Sistem ne može da zapamti sobu", "Soba greška", JOptionPane.ERROR_MESSAGE);
                }

            }
        });

        frmSoba.addOmoguciIzmeneBtnActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setupComponents(FormMode.FORM_EDIT);
            }

        });

        frmSoba.addOtkaziBtnActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frmSoba.dispose();
            }
        });

        frmSoba.addIzmeniBtnActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                edit();
            }

            private void edit() {
                try {
                    Soba soba = makeSobaFromForm();
                    if (soba == null) {
                        JOptionPane.showMessageDialog(frmSoba, "Sistem ne može da zapamti sobu", "Soba greška", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    Komunikacija.getInstanca().izmeniSobu(soba);
                    JOptionPane.showMessageDialog(frmSoba, "Sistem je zapamtio sobu", "Izmena sobe", JOptionPane.INFORMATION_MESSAGE);
                    frmSoba.dispose();
                } catch (Exception ex) {
                    Logger.getLogger(SobaKontroler.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(frmSoba, "Sistem ne može da zapamti sobu", "Soba greška", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        frmSoba.addOceniBtnActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                grade();
            }

            private void grade() {
                try {
                    Soba soba = makeSobaFromForm();
                    if (soba == null) {
                        JOptionPane.showMessageDialog(frmSoba, "Sistem ne može da zapamti ocenu sobe", "Soba greška", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    Gost gost = (Gost) frmSoba.getCmbGost().getSelectedItem();
                    String o = frmSoba.getTxtOcena().getText().trim();
                    if (o.isEmpty()) {
                        JOptionPane.showMessageDialog(frmSoba, "Sistem ne može da zapamti ocenu sobe", "Soba greška", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    int oc = Integer.parseInt(o);
                    if(oc <= 0 || oc >= 6){
                        JOptionPane.showMessageDialog(frmSoba, "Ocena mora biti u rasponu od 1 do 5", "Soba greška", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    Ocena ocena = new Ocena();
                    ocena.setGost(gost);
                    ocena.setSoba(soba);
                    ocena.setOcenaSobe(oc);
                    Komunikacija.getInstanca().sacuvajOcenu(ocena);
                    JOptionPane.showMessageDialog(frmSoba, "Sistem je zapamtio ocenu sobe", "Ocena sobe", JOptionPane.INFORMATION_MESSAGE);
                    frmSoba.dispose();
                } catch (Exception ex) {
                    Logger.getLogger(SobaKontroler.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(frmSoba, "Sistem ne može da zapamti ocenu sobe", "Soba greška", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

    }

    public void openForm(FormMode formMode) {
        if (formMode.equals(FormMode.FORM_VIEW)) {
            frmSoba.addWindowListener(new WindowAdapter() {
                @Override
                public void windowOpened(WindowEvent e) {
                    JOptionPane.showMessageDialog(frmSoba, "Sistem je učitao sobu");
                }

            });
        }
        frmSoba.setLocationRelativeTo(MainKoordinator.getInstanca().getMainController().getFrmMain());
        prepareView(formMode);
        frmSoba.setVisible(true);
    }

    private void prepareView(FormMode formMode) {
        try {
            fillCbVrstaSobe();
            fillCbGost();
        } catch (Exception ex) {
            Logger.getLogger(SobaKontroler.class.getName()).log(Level.SEVERE, null, ex);
        }
        setupComponents(formMode);
    }

    private void fillCbVrstaSobe() throws Exception {
        frmSoba.getCmbVrstaSobe().removeAllItems();
        List<VrstaSobe> vrsteSoba = Komunikacija.getInstanca().vratiSveVrsteSoba();
        frmSoba.getCmbVrstaSobe().setModel(new DefaultComboBoxModel<>(vrsteSoba.toArray()));
    }

    private void fillCbGost() throws Exception {
        frmSoba.getCmbGost().removeAllItems();
        List<Gost> gosti = Komunikacija.getInstanca().vratiSveGoste();
        frmSoba.getCmbGost().setModel(new DefaultComboBoxModel<>(gosti.toArray()));
    }

    private void setupComponents(FormMode formMode) {
        switch (formMode) {
            case FORM_ADD:
                frmSoba.getBtnOtkazi().setEnabled(true);
                frmSoba.getBtnIzmeni().setEnabled(false);
                frmSoba.getBtnOmoguciIzmene().setEnabled(false);
                frmSoba.getBtnSacuvaj().setEnabled(true);
                frmSoba.getBtnOceni().setEnabled(false);
                
                frmSoba.getLabelaGost().setVisible(false);
                frmSoba.getLabelaOcena().setVisible(false);
                frmSoba.getCmbGost().setVisible(false);
                frmSoba.getTxtOcena().setVisible(false);
                frmSoba.getTxtID().setVisible(false);
                frmSoba.getLabelaID().setVisible(false);
                frmSoba.getTxtID().setEnabled(false);
                frmSoba.getTxtSprat().setEnabled(true);
                frmSoba.getCbZauzeta().setEnabled(true);
                frmSoba.getCmbVrstaSobe().setEnabled(true);
                break;
            case FORM_VIEW:
                frmSoba.getBtnOtkazi().setEnabled(true);
                frmSoba.getBtnIzmeni().setEnabled(false);
                frmSoba.getBtnOmoguciIzmene().setEnabled(true);
                frmSoba.getBtnSacuvaj().setEnabled(false);
                frmSoba.getBtnOceni().setEnabled(false);
                
                frmSoba.getLabelaGost().setVisible(false);
                frmSoba.getLabelaOcena().setVisible(false);
                frmSoba.getCmbGost().setVisible(false);
                frmSoba.getTxtOcena().setVisible(false);
                frmSoba.getTxtID().setVisible(false);
                frmSoba.getLabelaID().setVisible(false);
                frmSoba.getTxtID().setEnabled(false);
                frmSoba.getTxtSprat().setEnabled(false);
                frmSoba.getCbZauzeta().setEnabled(false);
                frmSoba.getCmbVrstaSobe().setEnabled(false);

                Soba soba = (Soba) MainKoordinator.getInstanca().getParam(Konstante.PARAM_SOBA);
                frmSoba.getTxtID().setText(soba.getSobaID() + "");
                frmSoba.getTxtSprat().setText(soba.getSprat() + "");
                frmSoba.getCbZauzeta().setSelected(soba.isStatus());
                frmSoba.getCmbVrstaSobe().setSelectedItem(soba.getVrstaSobe());
                break;
            case FORM_EDIT:
                frmSoba.getBtnOtkazi().setEnabled(true);
                frmSoba.getBtnIzmeni().setEnabled(true);
                frmSoba.getBtnOmoguciIzmene().setEnabled(false);
                frmSoba.getBtnSacuvaj().setEnabled(false);
                frmSoba.getBtnOceni().setEnabled(true);
                
                frmSoba.getLabelaGost().setVisible(true);
                frmSoba.getLabelaOcena().setVisible(true);
                frmSoba.getCmbGost().setVisible(true);
                frmSoba.getTxtOcena().setVisible(true);
                frmSoba.getTxtID().setVisible(false);
                frmSoba.getLabelaID().setVisible(false);
                frmSoba.getTxtID().setEnabled(false);
                frmSoba.getTxtSprat().setEnabled(true);
                frmSoba.getCbZauzeta().setEnabled(true);
                frmSoba.getCmbVrstaSobe().setEnabled(true);
                break;
        }
    }

    private Soba makeSobaFromForm() {
        Soba soba = new Soba();
        soba.setSobaID(Integer.parseInt(frmSoba.getTxtID().getText().trim()));
        soba.setSprat(Integer.parseInt(frmSoba.getTxtSprat().getText().trim()));
        soba.setStatus(frmSoba.getCbZauzeta().isSelected());
        soba.setVrstaSobe((VrstaSobe) frmSoba.getCmbVrstaSobe().getSelectedItem());
        if (soba.getSprat() == 0 || soba.getVrstaSobe() == null) {
            return null;
        }
        return soba;
    }

}
