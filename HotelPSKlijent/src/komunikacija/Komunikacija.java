/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package komunikacija;

import domen.Gost;
import domen.Grad;
import domen.Korisnik;
import domen.Ocena;
import domen.Rezervacija;
import domen.Soba;
import domen.StavkaRezervacije;
import domen.VrstaSobe;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import view.konstante.Konstante;
import view.koordinator.MainKoordinator;

/**
 *
 * @author Bane
 */
public class Komunikacija {

    private static Komunikacija instanca;
    Socket socket;
    Sender sender;
    Receiver receiver;

    private Komunikacija() throws IOException {
        socket = new Socket("127.0.0.1", 9000);
        sender = new Sender(socket);
        receiver = new Receiver(socket);
    }

    public static Komunikacija getInstanca() throws IOException {
        if (instanca == null) {
            instanca = new Komunikacija();
        }
        return instanca;
    }

    public Socket getSocket() {
        return socket;
    }
    
    public void krajRada() throws Exception{
        Request request = new Request(Operation.KRAJ_RADA_KORISNIKA, MainKoordinator.getInstanca().getParam(Konstante.CURRENT_USER));
        sender.posalji(request);
    }
    
    public void krajRadaLogin() throws Exception{
        Request request = new Request(Operation.KRAJ_RADA_LOGIN, null);
        sender.posalji(request);
    }
    
    public Korisnik login(String username, String password) throws Exception {
        Korisnik korisnik = new Korisnik();
        korisnik.setUsername(username);
        korisnik.setPassword(password);
        Request request = new Request(Operation.LOGIN, korisnik);
        sender.posalji(request);
        Response response = (Response) receiver.receive();
        if (response.getException() == null) {
            return (Korisnik) response.getRezultat();
        } else {
            throw response.getException();
        }
    }

    public List<Grad> vratiSveGradove() throws Exception {
        Request request = new Request(Operation.VRATI_SVE_GRADOVE, null);
        sender.posalji(request);
        Response response = (Response) receiver.receive();
        if (response.getException() == null) {
            return (List<Grad>) response.getRezultat();
        } else {
            throw response.getException();
        }
    }

    public List<VrstaSobe> vratiSveVrsteSoba() throws Exception {
        Request request = new Request(Operation.VRATI_SVE_VRSTE_SOBA, null);
        sender.posalji(request);
        Response response = (Response) receiver.receive();
        if (response.getException() == null) {
            return (List<VrstaSobe>) response.getRezultat();
        } else {
            throw response.getException();
        }
    }

    public void sacuvajGosta(Gost gost) throws Exception {
        Request request = new Request(Operation.SACUVAJ_GOSTA, gost);
        sender.posalji(request);
        Response response = (Response) receiver.receive();
        if (response.getException() != null) {
            throw response.getException();
        }
    }

    public List<Gost> vratiSveGoste() throws Exception {
        Request request = new Request(Operation.VRATI_SVE_GOSTE, null);
        sender.posalji(request);
        Response response = (Response) receiver.receive();

        if (response.getException() == null) {
            return (List<Gost>) response.getRezultat();
        } else {
            throw response.getException();
        }
    }

    public void izmeniGosta(Gost gost) throws Exception {
        Request request = new Request(Operation.IZMENI_GOSTA, gost);
        sender.posalji(request);
        Response response = (Response) receiver.receive();
        if (response.getException() != null) {
            throw response.getException();
        }
    }

    public void obrisiGosta(Gost gost) throws Exception {
        Request request = new Request(Operation.OBRISI_GOSTA, gost);
        sender.posalji(request);
        Response response = (Response) receiver.receive();
        if (response.getException() != null) {
            throw response.getException();
        }
    }

    public List<Gost> vratiGosteImePrezime(String ime, String prezime) throws Exception {
        ArrayList<String> imePrezime = new ArrayList<>();
        imePrezime.add(ime);
        imePrezime.add(prezime);
        Request request = new Request(Operation.VRATI_GOSTE_IME_PREZIME, imePrezime);
        sender.posalji(request);
        Response response = (Response) receiver.receive();
        if (response.getException() == null) {
            return (List<Gost>) response.getRezultat();
        } else {
            throw response.getException();
        }
    }

    public void sacuvajSobu(Soba soba) throws Exception {
        Request request = new Request(Operation.SACUVAJ_SOBU, soba);
        sender.posalji(request);
        Response response = (Response) receiver.receive();
        if (response.getException() != null) {
            throw response.getException();
        }
    }

    public List<Soba> vratiSveSobe() throws Exception {
        Request request = new Request(Operation.VRATI_SVE_SOBE, null);
        sender.posalji(request);
        Response response = (Response) receiver.receive();

        if (response.getException() == null) {
            return (List<Soba>) response.getRezultat();
        } else {
            throw response.getException();
        }
    }

    public void izmeniSobu(Soba soba) throws Exception {
        Request request = new Request(Operation.IZMENI_SOBU, soba);
        sender.posalji(request);
        Response response = (Response) receiver.receive();
        if (response.getException() != null) {
            throw response.getException();
        }
    }

    public List<Soba> vratiSobeSprat(int sprat) throws Exception {
        Request request = new Request(Operation.VRATI_SOBE_SPRAT, sprat);
        sender.posalji(request);
        Response response = (Response) receiver.receive();
        if (response.getException() == null) {
            return (List<Soba>) response.getRezultat();
        } else {
            throw response.getException();
        }
    }

    public void sacuvajOcenu(Ocena ocena) throws Exception {
        Request request = new Request(Operation.SACUVAJ_OCENU, ocena);
        sender.posalji(request);
        Response response = (Response) receiver.receive();
        if (response.getException() != null) {
            throw response.getException();
        }
    }

    public void sacuvajRezervaciju(Rezervacija rezervacija) throws Exception {
        Request request = new Request(Operation.SACUVAJ_REZERVACIJU, rezervacija);
        sender.posalji(request);
        Response response = (Response) receiver.receive();
        if (response.getException() != null) {
            throw response.getException();
        }
    }

    public List<Rezervacija> vratiSveRezervacije() throws Exception {
        Request request = new Request(Operation.VRATI_SVE_REZERVACIJE, null);
        sender.posalji(request);
        Response response = (Response) receiver.receive();

        if (response.getException() == null) {
            return (List<Rezervacija>) response.getRezultat();
        } else {
            throw response.getException();
        }
    }

    public List<Rezervacija> vratiRezervacijeDatumOd(Date datumOd) throws Exception {
        Request request = new Request(Operation.VRATI_REZERVACIJE_DATUM_OD, datumOd);
        sender.posalji(request);
        Response response = (Response) receiver.receive();
        if (response.getException() == null) {
            return (List<Rezervacija>) response.getRezultat();
        } else {
            throw response.getException();
        }
    }

    public int vratiMaksIDRezervacija() throws Exception {
        Request request = new Request(Operation.VRATI_MAKS_ID_REZERVACIJA, null);
        sender.posalji(request);
        Response response = (Response) receiver.receive();
        if (response.getException() == null) {
            return (int) response.getRezultat();
        } else {
            throw response.getException();
        }
    }

    public List<StavkaRezervacije> vratiSveStavke() throws Exception {
        Request request = new Request(Operation.VRATI_SVE_STAVKE, null);
        sender.posalji(request);
        Response response = (Response) receiver.receive();
        if (response.getException() == null) {
            return (List<StavkaRezervacije>) response.getRezultat();
        } else {
            throw response.getException();
        }
    }

}
