/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view.kontroler;

import komunikacija.Komunikacija;
import domen.Gost;
import domen.Grad;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import view.konstante.Konstante;
import view.koordinator.MainKoordinator;
import view.forme.FrmGost;
import view.forme.util.FormMode;

/**
 *
 * @author Bane
 */
public class GostKontroler {

    private final FrmGost frmGost;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");

    public GostKontroler(FrmGost frmGost) {
        this.frmGost = frmGost;
        addActionListener();
    }

    private void addActionListener() {
        frmGost.addSacuvajBtnActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                save();
            }

            private void save() {
                try {
                    Gost gost = new Gost();
                    gost.setIme(frmGost.getTxtIme().getText().trim());
                    gost.setPrezime(frmGost.getTxtPrezime().getText().trim());
                    gost.setDatumRodjenja(sdf.parse(frmGost.getTxtDatumRodj().getText().trim()));
                    gost.setGrad((Grad) frmGost.getCmbGrad().getSelectedItem());

                    if (gost.getIme().isEmpty() || gost.getPrezime().isEmpty() || gost.getDatumRodjenja() == null || gost.getGrad() == null) {
                        JOptionPane.showMessageDialog(frmGost, "Sistem ne može da zapamti gosta", "Gost greška", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    Komunikacija.getInstanca().sacuvajGosta(gost);
                    JOptionPane.showMessageDialog(frmGost, "Sistem je zapamtio gosta", "Čuvanje gosta", JOptionPane.INFORMATION_MESSAGE);
                    frmGost.dispose();
                } catch (Exception ex) {
                    Logger.getLogger(GostKontroler.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(frmGost, "Sistem ne može da zapamti gosta", "Gost greška", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        frmGost.addOmoguciIzmeneBtnActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setupComponents(FormMode.FORM_EDIT);
            }

        });

        frmGost.addOtkaziBtnActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frmGost.dispose();
            }
        });

        frmGost.addObrisiBtnActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                delete();
            }

            private void delete() {
                try {
                    Gost gost = makeGostFromForm();
                    if (gost == null) {
                        JOptionPane.showMessageDialog(frmGost, "Sistem ne može da obriše gosta", "Gost greška", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    Komunikacija.getInstanca().obrisiGosta(gost);
                    JOptionPane.showMessageDialog(frmGost, "Sistem je obrisao podatke o gostu", "Brisanje gosta", JOptionPane.INFORMATION_MESSAGE);
                    frmGost.dispose();
                } catch (Exception ex) {
                    Logger.getLogger(GostKontroler.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(frmGost, "Sistem ne može da obriše gosta", "Gost greška", JOptionPane.ERROR_MESSAGE);
                }
            }

        });

        frmGost.addIzmeniBtnActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                edit();
            }

            private void edit() {
                try {
                    Gost gost = makeGostFromForm();
                    if (gost == null) {
                        JOptionPane.showMessageDialog(frmGost, "Sistem ne može da zapamti gosta", "Gost greška", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    Komunikacija.getInstanca().izmeniGosta(gost);
                    JOptionPane.showMessageDialog(frmGost, "Sistem je zapamtio gosta", "Izmena gosta", JOptionPane.INFORMATION_MESSAGE);
                    frmGost.dispose();
                } catch (Exception ex) {
                    Logger.getLogger(GostKontroler.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(frmGost, "Sistem ne može da zapamti gosta", "Gost greška", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

    }

    public void openForm(FormMode formMode) {
        if (formMode.equals(FormMode.FORM_VIEW)) {
            frmGost.addWindowListener(new WindowAdapter() {
                @Override
                public void windowOpened(WindowEvent e) {
                    JOptionPane.showMessageDialog(frmGost, "Sistem je učitao gosta");
                }

            });
        }

        frmGost.setLocationRelativeTo(MainKoordinator.getInstanca().getMainController().getFrmMain());
        prepareView(formMode);
        frmGost.setVisible(true);
    }

    private void prepareView(FormMode formMode) {
        try {
            fillCbGrad();
        } catch (Exception ex) {
            Logger.getLogger(GostKontroler.class.getName()).log(Level.SEVERE, null, ex);
        }
        setupComponents(formMode);
    }

    private void fillCbGrad() throws Exception {
        frmGost.getCmbGrad().removeAllItems();
        List<Grad> gradovi = Komunikacija.getInstanca().vratiSveGradove();
        frmGost.getCmbGrad().setModel(new DefaultComboBoxModel<>(gradovi.toArray()));
    }

    private void setupComponents(FormMode formMode) {
        switch (formMode) {
            case FORM_ADD:
                frmGost.getBtnOtkazi().setEnabled(true);
                frmGost.getBtnObrisi().setEnabled(false);
                frmGost.getBtnIzmeni().setEnabled(false);
                frmGost.getBtnOmoguciIzmene().setEnabled(false);
                frmGost.getBtnSacuvaj().setEnabled(true);

                frmGost.getTxtID().setEnabled(false);
                frmGost.getTxtIme().setEnabled(true);
                frmGost.getTxtPrezime().setEnabled(true);
                frmGost.getTxtDatumRodj().setEnabled(true);
                frmGost.getCmbGrad().setEnabled(true);
                break;
            case FORM_VIEW:
                frmGost.getBtnOtkazi().setEnabled(true);
                frmGost.getBtnObrisi().setEnabled(true);
                frmGost.getBtnIzmeni().setEnabled(false);
                frmGost.getBtnOmoguciIzmene().setEnabled(true);
                frmGost.getBtnSacuvaj().setEnabled(false);

                frmGost.getTxtID().setEnabled(false);
                frmGost.getTxtIme().setEnabled(false);
                frmGost.getTxtPrezime().setEnabled(false);
                frmGost.getTxtDatumRodj().setEnabled(false);
                frmGost.getCmbGrad().setEnabled(false);

                Gost gost = (Gost) MainKoordinator.getInstanca().getParam(Konstante.PARAM_GOST);
                frmGost.getTxtID().setText(gost.getGostID() + "");
                frmGost.getTxtIme().setText(gost.getIme());
                frmGost.getTxtPrezime().setText(gost.getPrezime());
                frmGost.getTxtDatumRodj().setText(sdf.format(gost.getDatumRodjenja()));
                frmGost.getCmbGrad().setSelectedItem(gost.getGrad());
                break;
            case FORM_EDIT:
                frmGost.getBtnOtkazi().setEnabled(true);
                frmGost.getBtnObrisi().setEnabled(true);
                frmGost.getBtnIzmeni().setEnabled(true);
                frmGost.getBtnOmoguciIzmene().setEnabled(false);
                frmGost.getBtnSacuvaj().setEnabled(false);

                frmGost.getTxtID().setEnabled(false);
                frmGost.getTxtIme().setEnabled(true);
                frmGost.getTxtPrezime().setEnabled(true);
                frmGost.getTxtDatumRodj().setEnabled(true);
                frmGost.getCmbGrad().setEnabled(true);
                break;
        }
    }

    private Gost makeGostFromForm() throws ParseException {
        Gost gost = new Gost();
        gost.setGostID(Integer.parseInt(frmGost.getTxtID().getText().trim()));
        gost.setIme(frmGost.getTxtIme().getText().trim());
        gost.setPrezime(frmGost.getTxtPrezime().getText().trim());
        gost.setDatumRodjenja(sdf.parse(frmGost.getTxtDatumRodj().getText().trim()));
        gost.setGrad((Grad) frmGost.getCmbGrad().getSelectedItem());
        if (gost.getIme().isEmpty() || gost.getPrezime().isEmpty() || gost.getDatumRodjenja() == null || gost.getGrad() == null) {
            return null;
        }
        return gost;
    }

}
