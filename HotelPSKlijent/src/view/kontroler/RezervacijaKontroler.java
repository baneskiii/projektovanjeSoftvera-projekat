/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view.kontroler;

import komunikacija.Komunikacija;
import domen.Gost;
import domen.Rezervacija;
import domen.Soba;
import domen.StavkaRezervacije;
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
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import view.konstante.Konstante;
import view.koordinator.MainKoordinator;
import view.forme.FrmRezervacija;
import view.forme.komponenta.tabele.RezervacijaTableModel;
import view.forme.komponenta.tabele.StavkaRezervacijeTableModel;
import view.forme.util.FormMode;

/**
 *
 * @author Bane
 */
public class RezervacijaKontroler {

    private final FrmRezervacija frmRezervacija;
    private StavkaRezervacijeTableModel srtm;
    private int rb = 1;
    SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");

    public RezervacijaKontroler(FrmRezervacija frmRezervacija) {
        this.frmRezervacija = frmRezervacija;
        addActionListener();
    }

    private void addActionListener() {
        frmRezervacija.addBtnDodajActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addItem();
            }

            private void addItem() {
                try {
                    StavkaRezervacije sr = new StavkaRezervacije();
                    Gost gost = (Gost) frmRezervacija.getCmbGost().getSelectedItem();
                    Soba soba = (Soba) frmRezervacija.getCmbSoba().getSelectedItem();
                    if (soba.isStatus()) {
                        JOptionPane.showMessageDialog(frmRezervacija, "Ova soba je zauzeta", "Rezervacija greška", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    Rezervacija rezervacija = new Rezervacija();
                    int rezervacijaID = Komunikacija.getInstanca().vratiMaksIDRezervacija();
                    rezervacija.setRezervacijaID(rezervacijaID);
                    sr.setRezervacija(rezervacija);
                    sr.setGost(gost);
                    sr.setSoba(soba);
                    sr.setRbStavke(rb);
                    srtm = (StavkaRezervacijeTableModel) frmRezervacija.getTabelaStavki().getModel();
                    List<StavkaRezervacije> stavke = srtm.getStavke();
                    for (StavkaRezervacije stavkaRezervacije : stavke) {
                        if (stavkaRezervacije.getGost().getGostID() == gost.getGostID()) {
                            JOptionPane.showMessageDialog(frmRezervacija, "Ovaj gost je već dodat", "Rezervacija greška", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                    }
                    srtm.dodajStavku(sr);
                    rb += 1;
                } catch (Exception ex) {
                    ex.printStackTrace();
                    Logger.getLogger(RezervacijaKontroler.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        frmRezervacija.addBtnObrisiActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeItem();
            }

            private void removeItem() {
                int row = frmRezervacija.getTabelaStavki().getSelectedRow();
                srtm = (StavkaRezervacijeTableModel) frmRezervacija.getTabelaStavki().getModel();
                if (row >= 0) {
                    srtm.obrisiStavku(row);
                    rb = 1;
                    List<StavkaRezervacije> stavke = srtm.getStavke();
                    for (StavkaRezervacije stavkaRezervacije : stavke) {
                        stavkaRezervacije.setRbStavke(rb);
                        rb += 1;
                    }
                    srtm.refresh();
                } else {
                    JOptionPane.showMessageDialog(frmRezervacija, "Morate izabrati stavku", "Rezervacija greška", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        frmRezervacija.addBtnOtkaziActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frmRezervacija.dispose();
            }
        });

        frmRezervacija.addBtnSacuvajActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                save();
            }

            private void save() {
                try {
                    int id = Komunikacija.getInstanca().vratiMaksIDRezervacija();
                    SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
                    Date datumOd = sdf.parse(frmRezervacija.getTxtDatumOd().getText().trim());
                    Date datumDo = sdf.parse(frmRezervacija.getTxtDatumDo().getText().trim());
                    Gost ugovarac = (Gost) frmRezervacija.getCmbUgovarac().getSelectedItem();
                    srtm = (StavkaRezervacijeTableModel) frmRezervacija.getTabelaStavki().getModel();
                    List<StavkaRezervacije> stavke = srtm.getStavke();
                    if (datumOd == null || datumDo == null || stavke.size() == 0) {
                        JOptionPane.showMessageDialog(frmRezervacija, "Sistem ne može da zapamti rezervaciju", "Rezervacija greška", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    Rezervacija rezervacija = new Rezervacija(id, datumOd, datumDo, ugovarac, stavke);
                    Komunikacija.getInstanca().sacuvajRezervaciju(rezervacija);
                    JOptionPane.showMessageDialog(frmRezervacija, "Sistem je zapamtio rezervaciju", "Čuvanje rezervacije", JOptionPane.INFORMATION_MESSAGE);
                    frmRezervacija.dispose();
                } catch (Exception ex) {
                    Logger.getLogger(RezervacijaKontroler.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(frmRezervacija, "Sistem ne može da zapamti rezervaciju", "Rezervacija greška", JOptionPane.ERROR_MESSAGE);
                }

            }
        });

    }

    public void openForm(FormMode formMode) {
        if (formMode.equals(FormMode.FORM_VIEW)) {
            frmRezervacija.addWindowListener(new WindowAdapter() {
                @Override
                public void windowOpened(WindowEvent e) {
                    JOptionPane.showMessageDialog(frmRezervacija, "Sistem je učitao rezervaciju");
                }

            });
        }
        frmRezervacija.setLocationRelativeTo(MainKoordinator.getInstanca().getMainController().getFrmMain());
        prepareView(formMode);
        frmRezervacija.setVisible(true);
    }

    private void prepareView(FormMode formMode) {
        List<StavkaRezervacije> stavke = new ArrayList<>();
        srtm = new StavkaRezervacijeTableModel(stavke);
        frmRezervacija.getTabelaStavki().setModel(srtm);

        try {
            fillCbUgovarac();
            fillCbGost();
            fillCbSoba();
            setupComponents(formMode);
        } catch (Exception ex) {
            Logger.getLogger(RezervacijaKontroler.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void fillCbUgovarac() throws Exception {
        frmRezervacija.getCmbUgovarac().removeAllItems();
        List<Gost> ugovaraci = Komunikacija.getInstanca().vratiSveGoste();
        frmRezervacija.getCmbUgovarac().setModel(new DefaultComboBoxModel<>(ugovaraci.toArray()));
    }

    private void fillCbGost() throws Exception {
        frmRezervacija.getCmbGost().removeAllItems();
        List<Gost> gosti = Komunikacija.getInstanca().vratiSveGoste();
        frmRezervacija.getCmbGost().setModel(new DefaultComboBoxModel<>(gosti.toArray()));
    }

    private void fillCbSoba() throws Exception {
        frmRezervacija.getCmbSoba().removeAllItems();
        List<Soba> sobe = Komunikacija.getInstanca().vratiSveSobe();
        frmRezervacija.getCmbSoba().setModel(new DefaultComboBoxModel<>(sobe.toArray()));
    }

    private void setupComponents(FormMode formMode) throws Exception {
        switch (formMode) {
            case FORM_ADD:
                frmRezervacija.getBtnDodaj().setEnabled(true);
                frmRezervacija.getBtnObrisi().setEnabled(true);
                frmRezervacija.getBtnOtkazi().setEnabled(true);
                frmRezervacija.getBtnSacuvaj().setEnabled(true);

                frmRezervacija.getTxtID().setEnabled(false);
                frmRezervacija.getTxtID().setVisible(false);
                frmRezervacija.getLabelaID().setVisible(false);
                frmRezervacija.getTxtDatumOd().setEnabled(true);
                frmRezervacija.getTxtDatumDo().setEnabled(true);
                frmRezervacija.getCmbUgovarac().setEnabled(true);
                frmRezervacija.getTabelaStavki().setEnabled(true);
                frmRezervacija.getCmbGost().setEnabled(true);
                frmRezervacija.getCmbSoba().setEnabled(true);
                frmRezervacija.getTabelaStavki().setEnabled(true);
                break;
            case FORM_VIEW:
                frmRezervacija.getBtnDodaj().setEnabled(false);
                frmRezervacija.getBtnObrisi().setEnabled(false);
                frmRezervacija.getBtnOtkazi().setEnabled(true);
                frmRezervacija.getBtnSacuvaj().setEnabled(false);

                frmRezervacija.getTxtID().setEnabled(false);
                frmRezervacija.getTxtID().setVisible(false);
                frmRezervacija.getLabelaID().setVisible(false);
                frmRezervacija.getTxtDatumOd().setEnabled(false);
                frmRezervacija.getTxtDatumDo().setEnabled(false);
                frmRezervacija.getCmbUgovarac().setEnabled(false);
                frmRezervacija.getTabelaStavki().setEnabled(false);
                frmRezervacija.getCmbGost().setEnabled(false);
                frmRezervacija.getCmbSoba().setEnabled(false);
                frmRezervacija.getTabelaStavki().setEnabled(false);

                Rezervacija rezervacija = (Rezervacija) MainKoordinator.getInstanca().getParam(Konstante.PARAM_REZERVACIJA);
                frmRezervacija.getTxtID().setText(rezervacija.getRezervacijaID() + "");
                frmRezervacija.getTxtDatumOd().setText(sdf.format(rezervacija.getDatumOd()));
                frmRezervacija.getTxtDatumDo().setText(sdf.format(rezervacija.getDatumDo()));
                List<Gost> gosti = Komunikacija.getInstanca().vratiSveGoste();
                int index = 0;
                for (Gost gost : gosti) {
                    if (gost.getGostID() == rezervacija.getGost().getGostID()) {
                        break;
                    }
                    index += 1;
                }
                System.out.println(index);
                frmRezervacija.getCmbUgovarac().setSelectedIndex(index);
                List<StavkaRezervacije> stavke = Komunikacija.getInstanca().vratiSveStavke();
                StavkaRezervacijeTableModel srtm = (StavkaRezervacijeTableModel) frmRezervacija.getTabelaStavki().getModel();
                for (StavkaRezervacije stavkaRezervacije : stavke) {
                    if (stavkaRezervacije.getRezervacija().getRezervacijaID() == rezervacija.getRezervacijaID()) {
                        srtm.dodajStavku(stavkaRezervacije);
                    }
                }
                break;
        }
    }

}
