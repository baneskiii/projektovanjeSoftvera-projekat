/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server.thread;

import komunikacija.Operation;
import komunikacija.Receiver;
import komunikacija.Request;
import komunikacija.Response;
import komunikacija.Sender;
import kontroler.Kontroler;
import domen.Gost;
import domen.Grad;
import domen.Korisnik;
import domen.Ocena;
import domen.Rezervacija;
import domen.Soba;
import domen.StavkaRezervacije;
import domen.VrstaSobe;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import server.ServerNit;
import domen.OpstiDomenskiObjekat;

/**
 *
 * @author Bane
 */
public class ProcessClientRequests extends Thread {

    private Socket socket;
    private Sender sender;
    private Receiver receiver;
    private ServerNit server;
    private boolean kraj = false;

    public ProcessClientRequests(Socket socket, ServerNit server) {
        this.socket = socket;
        this.server = server;
        sender = new Sender(socket);
        receiver = new Receiver(socket);
    }

    @Override
    public void run() {
        while (!kraj) {
            try {
                Request request = (Request) receiver.receive();
                Response response = new Response();
                response.setOperacija(request.getOperacija());
                try {
                    switch (request.getOperacija()) {
                        case LOGIN:
                            Korisnik korisnik = (Korisnik) request.getArgument();
                            OpstiDomenskiObjekat entity = Kontroler.getInstanca().login(korisnik.getUsername(), korisnik.getPassword());
                            if (entity == null) {
                                Exception e = new Exception("Korisnik nema pravo pristupa sistemu");
                                response.setException(e);
                                response.setUspesno(false);
                            } else {
                                if (!Kontroler.getInstanca().getPrijavljeniKorisnici().contains(entity)) {
                                    Kontroler.getInstanca().getPrijavljeniKorisnici().add(entity);
                                    response.setUspesno(true);
                                    response.setPoruka("Uspešna prijava");
                                    response.setRezultat(entity);
                                } else {
                                    Exception e = new Exception("Korisnik je već prijavljen");
                                    response.setException(e);
                                    response.setUspesno(false);
                                }
                            }
                            break;
                        case VRATI_SVE_GRADOVE:
                            List<Grad> gradovi = Kontroler.getInstanca().vratiSveGradove();
                            response.setRezultat(gradovi);
                            response.setUspesno(true);
                            break;
                        case VRATI_SVE_VRSTE_SOBA:
                            List<VrstaSobe> vrsteSoba = Kontroler.getInstanca().vratiSveVrsteSoba();
                            response.setRezultat(vrsteSoba);
                            response.setUspesno(true);
                            break;
                        case SACUVAJ_GOSTA:
                            Gost gost = (Gost) request.getArgument();
                            Kontroler.getInstanca().sacuvajGosta(gost);
                            response.setUspesno(true);
                            break;
                        case VRATI_SVE_GOSTE:
                            List<Gost> gosti = Kontroler.getInstanca().vratiSveGoste();
                            response.setRezultat(gosti);
                            response.setUspesno(true);
                            break;
                        case VRATI_GOSTA:
                            int idGost = (int) request.getArgument();
                            OpstiDomenskiObjekat gGost = Kontroler.getInstanca().dajGosta(idGost);
                            if (gGost == null) {
                                Exception e = new Exception("Ne postoji gost sa prosledjenim id-jem");
                                response.setException(e);
                                response.setUspesno(false);
                            } else {
                                response.setRezultat(gGost);
                                response.setUspesno(true);
                            }
                            break;
                        case IZMENI_GOSTA:
                            Gost go = (Gost) request.getArgument();
                            Kontroler.getInstanca().promeniGosta(go);
                            response.setUspesno(true);
                            break;
                        case OBRISI_GOSTA:
                            Gost gos = (Gost) request.getArgument();
                            Kontroler.getInstanca().obrisiGosta(gos);
                            response.setUspesno(true);
                            break;
                        case VRATI_GOSTE_IME_PREZIME:
                            ArrayList<String> imePrezime = (ArrayList<String>) request.getArgument();
                            List<OpstiDomenskiObjekat> gostii = Kontroler.getInstanca().filterGoste(imePrezime.get(0), imePrezime.get(1));
                            response.setRezultat(gostii);
                            response.setUspesno(true);
                            break;
                        case SACUVAJ_SOBU:
                            Soba soba = (Soba) request.getArgument();
                            Kontroler.getInstanca().sacuvajSobu(soba);
                            response.setUspesno(true);
                            break;
                        case VRATI_SVE_SOBE:
                            List<Soba> sobe = Kontroler.getInstanca().vratiSveSobe();
                            response.setRezultat(sobe);
                            response.setUspesno(true);
                            break;
                        case VRATI_SOBU:
                            int idSoba = (int) request.getArgument();
                            OpstiDomenskiObjekat gSoba = Kontroler.getInstanca().dajSobu(idSoba);
                            if (gSoba == null) {
                                Exception e = new Exception("Ne postoji soba sa prosledjenim id-jem");
                                response.setException(e);
                                response.setUspesno(false);
                            } else {
                                response.setRezultat(gSoba);
                                response.setUspesno(true);
                            }
                            break;
                        case IZMENI_SOBU:
                            Soba so = (Soba) request.getArgument();
                            Kontroler.getInstanca().promeniSobu(so);
                            response.setUspesno(true);
                            break;
                        case VRATI_SOBE_SPRAT:
                            int sprat = (int) request.getArgument();
                            List<OpstiDomenskiObjekat> sobee = Kontroler.getInstanca().filterSobe(sprat);
                            response.setRezultat(sobee);
                            response.setUspesno(true);
                            break;
                        case SACUVAJ_OCENU:
                            Ocena ocena = (Ocena) request.getArgument();
                            Kontroler.getInstanca().sacuvajOcenu(ocena);
                            response.setUspesno(true);
                            break;
                        case SACUVAJ_REZERVACIJU:
                            Rezervacija rezervacija = (Rezervacija) request.getArgument();
                            Kontroler.getInstanca().sacuvajRezervaciju(rezervacija);
                            response.setUspesno(true);
                            break;
                        case VRATI_REZERVACIJE_DATUM_OD:
                            Date datumOd = (Date) request.getArgument();
                            List<OpstiDomenskiObjekat> rezervacijee = Kontroler.getInstanca().filterRezervacije(datumOd);
                            response.setRezultat(rezervacijee);
                            response.setUspesno(true);
                            break;
                        case VRATI_SVE_REZERVACIJE:
                            List<Rezervacija> rezervacije = Kontroler.getInstanca().vratiSveRezervacije();
                            response.setRezultat(rezervacije);
                            response.setUspesno(true);
                            break;
                        case VRATI_MAKS_ID_REZERVACIJA:
                            int id = Kontroler.getInstanca().vratiMaksIDRezervacija();
                            response.setRezultat(id);
                            response.setUspesno(true);
                            break;
                        case VRATI_SVE_STAVKE:
                            List<StavkaRezervacije> stavke = Kontroler.getInstanca().vratiSveStavke();
                            response.setRezultat(stavke);
                            response.setUspesno(true);
                            break;
                        case KRAJ_RADA_KORISNIKA:
                            Korisnik k = (Korisnik) request.getArgument();
                            Kontroler.getInstanca().getPrijavljeniKorisnici().remove(k);
                            kraj = true;
                            break;
                        case KRAJ_RADA_LOGIN:
                            kraj = true;
                            break;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    response.setException(e);
                    response.setUspesno(false);
                }
                sender.posalji(response);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public void kraj() {
        kraj = true;
    }

}
