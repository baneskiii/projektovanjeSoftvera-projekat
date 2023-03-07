/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

import kontroler.Kontroler;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import server.thread.ProcessClientRequests;
import domen.OpstiDomenskiObjekat;

/**
 *
 * @author Bane
 */
public class ServerNit extends Thread {

    private List<ProcessClientRequests> klijenti;
    private ServerSocket ss;
    private boolean kraj = false;

    public ServerNit() {
        klijenti = new LinkedList<>();
        try {
            ss = new ServerSocket(9000);
        } catch (IOException ex) {
            Logger.getLogger(ServerNit.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run() {
        while (!kraj) {
            try {
                Socket socket = ss.accept();
                ProcessClientRequests klijent = new ProcessClientRequests(socket, this);
                klijent.start();
                klijenti.add(klijent);
            } catch (IOException ex) {
                Logger.getLogger(ServerNit.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public List<ProcessClientRequests> getKlijenti() {
        return klijenti;
    }

    public void kraj() {
        try {
            for (ProcessClientRequests processClientRequests : klijenti) {
                processClientRequests.kraj();
            }
            Kontroler.getInstanca().setPrijavljeniKorisnici(new ArrayList<>());
            ss.close();
            kraj = true;
        } catch (IOException ex) {
            Logger.getLogger(ServerNit.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
