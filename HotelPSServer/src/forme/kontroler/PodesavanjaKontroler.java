/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme.kontroler;

import forme.FrmPodesavanja;
import forme.koordinator.MainKoordinator;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import server.Settings;

/**
 *
 * @author Bane
 */
public class PodesavanjaKontroler {

    private final FrmPodesavanja frmSettings;

    public PodesavanjaKontroler(FrmPodesavanja frmSettings) {
        this.frmSettings = frmSettings;
        addActionListener();
    }

    public void openForm() {
        frmSettings.setLocationRelativeTo(MainKoordinator.getInstanca().getMainController().getFrmMain());
        frmSettings.setTitle("Podešavanja");
        frmSettings.setVisible(true);
    }

    private void addActionListener() {
        frmSettings.addSaveBtnActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String url = frmSettings.getTxtURL().getText().trim();
                String username = frmSettings.getTxtUsername().getText().trim();
                String password = new String(frmSettings.getTxtPassword().getPassword()).trim();

                if (url.isEmpty() || username.isEmpty()) {
                    JOptionPane.showMessageDialog(frmSettings, "Url i username moraju biti uneti");
                    return;
                }

                Settings.getInstanca().setURL(url);
                Settings.getInstanca().setUsername(username);
                Settings.getInstanca().setPassword(password);
                Settings.getInstanca().sacuvaj();

                frmSettings.dispose();
            }
        });
    }

}
