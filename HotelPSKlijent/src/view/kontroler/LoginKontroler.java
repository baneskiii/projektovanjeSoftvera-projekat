/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view.kontroler;

import komunikacija.Komunikacija;
import domen.Korisnik;
import java.awt.Color;
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
import view.forme.FrmLogin;

/**
 *
 * @author Bane
 */
public class LoginKontroler {

    private final FrmLogin frmLogin;

    public LoginKontroler(FrmLogin frmLogin) {
        this.frmLogin = frmLogin;
        addActionListener();
    }

    public void openForm() {
        frmLogin.setVisible(true);
        frmLogin.setTitle("Prijava");
    }

    private void addActionListener() {
        frmLogin.loginAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginKorisnik(e);
            }

            private void loginKorisnik(ActionEvent e) {
                resetujFormu();
                try {
                    String username = frmLogin.getTxtUsername().getText().trim();
                    String password = String.copyValueOf(frmLogin.getTxtPassword().getPassword());

                    validacija(username, password);

                    Korisnik korisnik = Komunikacija.getInstanca().login(username, password);
                    JOptionPane.showMessageDialog(frmLogin, "Dobrodošli " + korisnik.getIme() + " " + korisnik.getPrezime(), "Prijava", JOptionPane.INFORMATION_MESSAGE);
                    frmLogin.dispose();
                    MainKoordinator.getInstanca().addParam(Konstante.CURRENT_USER, korisnik);
                    MainKoordinator.getInstanca().openMainForm();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frmLogin, ex.getMessage(), "Login greška", JOptionPane.ERROR_MESSAGE);
                }
            }

            private void resetujFormu() {
                frmLogin.getLabelaUsernameGreska().setText("");
                frmLogin.getLabelaPassGreska().setText("");
            }

            private void validacija(String username, String password) throws Exception {
                String poruka = "";
                if (username.isEmpty()) {
                    frmLogin.getLabelaUsernameGreska().setText("Polje username ne sme biti prazno!");
                    frmLogin.getLabelaUsernameGreska().setForeground(Color.red);
                    poruka += "Polje username ne sme biti prazno!\n";
                }
                if (password.isEmpty()) {
                    frmLogin.getLabelaPassGreska().setText("Polje password ne sme biti prazno!");
                    frmLogin.getLabelaPassGreska().setForeground(Color.red);
                    poruka += "Polje password ne sme biti prazno!\n";
                }
                if (!poruka.isEmpty()) {
                    throw new Exception(poruka);
                }
            }
        });
        
        frmLogin.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    Komunikacija.getInstanca().krajRadaLogin();
                } catch (Exception ex) {
                    Logger.getLogger(LoginKontroler.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        });
        
    }

}
