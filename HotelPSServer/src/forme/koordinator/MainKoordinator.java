/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme.koordinator;

import forme.FrmMain;
import forme.FrmPodesavanja;
import forme.kontroler.MainKontroler;
import forme.kontroler.PodesavanjaKontroler;

/**
 *
 * @author Bane
 */
public class MainKoordinator {
    private static MainKoordinator instanca;
    private final MainKontroler mainController;

    private MainKoordinator() {
        mainController = new MainKontroler(new FrmMain());
    }

    public static MainKoordinator getInstanca() {
        if(instanca == null){
            instanca = new MainKoordinator();
        }
        return instanca;
    }
    
    public void openMainForm(){
        mainController.openForm();
    }
    
    public void openSettingsForm() {
        PodesavanjaKontroler settingsController = new PodesavanjaKontroler(new FrmPodesavanja(mainController.getFrmMain(), true));
        settingsController.openForm();
    }

    public MainKontroler getMainController() {
        return mainController;
    }

    
    
    
}
