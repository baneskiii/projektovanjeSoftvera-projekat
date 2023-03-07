/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view.koordinator;

import domen.Korisnik;
import java.util.HashMap;
import java.util.Map;
import view.kontroler.GostKontroler;
import view.kontroler.LoginKontroler;
import view.kontroler.MainKontroler;
import view.kontroler.RezervacijaKontroler;
import view.kontroler.SobaKontroler;
import view.kontroler.PretragaGostijuKontroler;
import view.kontroler.PretragaRezervacijaKontroler;
import view.kontroler.PretragaSobaKontroler;
import view.forme.FrmGost;
import view.forme.FrmLogin;
import view.forme.FrmMain;
import view.forme.FrmPretragaGostiju;
import view.forme.FrmPretragaRezervacija;
import view.forme.FrmPretragaSoba;
import view.forme.FrmRezervacija;
import view.forme.FrmSoba;
import view.forme.util.FormMode;

/**
 *
 * @author Bane
 */
public class MainKoordinator {

    private static MainKoordinator instanca;

    private final MainKontroler mainController;
    private final Map<String, Object> params;

    private MainKoordinator() {
        mainController = new MainKontroler(new FrmMain());
        params = new HashMap<>();
    }

    public static MainKoordinator getInstanca() {
        if (instanca == null) {
            instanca = new MainKoordinator();
        }
        return instanca;
    }

    public void addParam(String name, Object key) {
        params.put(name, key);
    }

    public Object getParam(String name) {
        return params.get(name);
    }

    public MainKontroler getMainController() {
        return mainController;
    }

    public void openLoginForm() {
        LoginKontroler loginController = new LoginKontroler(new FrmLogin());
        loginController.openForm();
    }

    public void openMainForm() {
        mainController.openForm();
    }

    public void openAddGuestForm() {
        GostKontroler guestController = new GostKontroler(new FrmGost(mainController.getFrmMain(), true));
        guestController.openForm(FormMode.FORM_ADD);
    }

    public void openSearchGuestForm() {
        FrmPretragaGostiju frmPretragaGostiju = new FrmPretragaGostiju(mainController.getFrmMain(), true);
        PretragaGostijuKontroler searchGuestController = new PretragaGostijuKontroler(frmPretragaGostiju);
        searchGuestController.openForm();
    }

    public void openGostDetailsForm() {
        FrmGost frmGost = new FrmGost(mainController.getFrmMain(), true);
        GostKontroler guestController = new GostKontroler(frmGost);
        guestController.openForm(FormMode.FORM_VIEW);
    }

    public void openAddRoomForm() {
        SobaKontroler roomController = new SobaKontroler(new FrmSoba(mainController.getFrmMain(), true));
        roomController.openForm(FormMode.FORM_ADD);
    }

    public void openSearchRoomForm() {
        FrmPretragaSoba frmPretragaSoba = new FrmPretragaSoba(mainController.getFrmMain(), true);
        PretragaSobaKontroler searchRoomController = new PretragaSobaKontroler(frmPretragaSoba);
        searchRoomController.openForm();
    }

    public void openSobaDetailsForm() {
        FrmSoba frmSoba = new FrmSoba(mainController.getFrmMain(), true);
        SobaKontroler roomController = new SobaKontroler(frmSoba);
        roomController.openForm(FormMode.FORM_VIEW);
    }
    
    public void openAddReservationForm(){
        RezervacijaKontroler reservationController = new RezervacijaKontroler(new FrmRezervacija(mainController.getFrmMain(), true));
        reservationController.openForm(FormMode.FORM_ADD);
    }
    
    public void openSearchReservationForm(){
        FrmPretragaRezervacija frmPretragaRezervacija = new FrmPretragaRezervacija(mainController.getFrmMain(), true);
        PretragaRezervacijaKontroler searchReservationController = new PretragaRezervacijaKontroler(frmPretragaRezervacija);
        searchReservationController.openForm();
    }

    public void openRezervacijaDetailsForm() {
        FrmRezervacija frmRezervacija = new FrmRezervacija(mainController.getFrmMain(), true);
        RezervacijaKontroler reservationController = new RezervacijaKontroler(frmRezervacija);
        reservationController.openForm(FormMode.FORM_VIEW);
    }
    
}
