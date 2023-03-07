/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroler;

import domen.Gost;
import domen.Grad;
import domen.Korisnik;
import domen.Ocena;
import domen.Rezervacija;
import domen.Soba;
import domen.StavkaRezervacije;
import domen.VrstaSobe;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import so.OpstaSO;
import so.gost.IzmeniGosta;
import so.gost.NadjiGoste;
import so.gost.ObrisiGosta;
import so.gost.UcitajGosta;
import so.gost.UcitajListuGostiju;
import so.gost.ZapamtiGosta;
import so.grad.UcitajGrad;
import so.grad.UcitajListuGradova;
import so.korisnik.UlogujKorisnika;
import so.ocena.ZapamtiOcenuSobe;
import so.rezervacija.NadjiRezervacije;
import so.rezervacija.UcitajListuRezervacija;
import so.rezervacija.UcitajMaksimalniID;
import so.rezervacija.UcitajRezervaciju;
import so.rezervacija.ZapamtiRezervaciju;
import so.soba.IzmeniSobu;
import so.soba.NadjiSobe;
import so.soba.ObrisiSobu;
import so.soba.UcitajListuSoba;
import so.soba.UcitajSobu;
import so.soba.ZapamtiSobu;
import so.stavkaRezervacije.UcitajListuStavki;
import so.stavkaRezervacije.ZapamtiStavkuRezervacije;
import so.vrstaSobe.UcitajListuVrstaSoba;
import so.vrstaSobe.UcitajVrstuSobe;
import repository.Repository;
import repository.db.impl.DBBroker;
import server.ServerNit;
import domen.OpstiDomenskiObjekat;

/**
 *
 * @author Bane
 */
public class Kontroler {

    private static Kontroler instanca;
    private final Repository repositoryGeneric;
    private ServerNit server;
    public List<OpstiDomenskiObjekat> prijavljeniKorisnici;

    private Kontroler() {
        this.repositoryGeneric = new DBBroker();
        prijavljeniKorisnici = new ArrayList<>();
    }

    public static Kontroler getInstanca() {
        if (instanca == null) {
            instanca = new Kontroler();
        }
        return instanca;
    }

    public void startujServer() {
        if (server == null || !server.isAlive()) {
            server = new ServerNit();
            server.start();
        }
    }

    public void stopServer() {
        server.kraj();
    }

    public List<OpstiDomenskiObjekat> getPrijavljeniKorisnici() {
        return prijavljeniKorisnici;
    }

    public void setPrijavljeniKorisnici(List<OpstiDomenskiObjekat> prijavljeniKorisnici) {
        this.prijavljeniKorisnici = prijavljeniKorisnici;
    }

    public OpstiDomenskiObjekat login(String username, String password) throws Exception {
        Korisnik korisnik = new Korisnik();
        korisnik.setUsername(username);
        korisnik.setPassword(password);
        OpstaSO operation = new UlogujKorisnika();
        operation.execute(korisnik);
        return ((UlogujKorisnika) operation).getGenericEntity();
    }

    public OpstiDomenskiObjekat dajGrad(int id) throws Exception {
        Grad grad = new Grad();
        grad.setGradID(id);
        OpstaSO operation = new UcitajGrad();
        operation.execute(grad);
        return ((UcitajGrad) operation).getGenericEntity();
    }

    public OpstiDomenskiObjekat dajVrstuSobe(int id) throws Exception {
        VrstaSobe vrstaSobe = new VrstaSobe();
        vrstaSobe.setVrstaSobeID(id);
        OpstaSO operation = new UcitajVrstuSobe();
        operation.execute(vrstaSobe);
        return ((UcitajVrstuSobe) operation).getGenericEntity();
    }

    public List<Grad> vratiSveGradove() throws Exception {
        OpstaSO operation = new UcitajListuGradova();
        operation.execute(new Grad());
        List<Grad> gradovi = ((UcitajListuGradova) operation).getGradovi();
        return gradovi;
    }

    public List<VrstaSobe> vratiSveVrsteSoba() throws Exception {
        OpstaSO operation = new UcitajListuVrstaSoba();
        operation.execute(new VrstaSobe());
        List<VrstaSobe> vrsteSoba = ((UcitajListuVrstaSoba) operation).getVrsteSoba();
        return vrsteSoba;
    }

    public List<Gost> vratiSveGoste() throws Exception {
        OpstaSO operation = new UcitajListuGostiju();
        operation.execute(new Gost());
        List<Gost> gosti = ((UcitajListuGostiju) operation).getGosti();
        for (Gost gost : gosti) {
            gost.setGrad((Grad) dajGrad(gost.getGrad().getGradID()));
        }
        return gosti;
    }

    public List<Soba> vratiSveSobe() throws Exception {
        OpstaSO operation = new UcitajListuSoba();
        operation.execute(new Soba());
        List<Soba> sobe = ((UcitajListuSoba) operation).getSobe();
        for (Soba soba : sobe) {
            soba.setVrstaSobe((VrstaSobe) dajVrstuSobe(soba.getVrstaSobe().getVrstaSobeID()));
        }
        return sobe;
    }

    public void sacuvajGosta(Gost gost) throws Exception {
        OpstaSO operation = new ZapamtiGosta();
        operation.execute(gost);
    }

    public OpstiDomenskiObjekat dajGosta(int id) throws Exception {
        Gost gost = new Gost();
        gost.setGostID(id);
        OpstaSO operation = new UcitajGosta();
        operation.execute(gost);
        return ((UcitajGosta) operation).getGenericEntity();
    }

    public void obrisiGosta(Gost gost) throws Exception {
        OpstaSO operation = new ObrisiGosta();
        operation.execute(gost);
    }

    public List<OpstiDomenskiObjekat> filterGoste(String ime, String prezime) throws Exception {
        Gost gost = new Gost();
        gost.setIme(ime);
        gost.setPrezime(prezime);
        OpstaSO operation = new NadjiGoste();
        operation.execute(gost);
        List<OpstiDomenskiObjekat> gosti = ((NadjiGoste) operation).getList();
        for (OpstiDomenskiObjekat genericEntity : gosti) {
            Gost g = (Gost) genericEntity;
            g.setGrad((Grad) dajGrad(g.getGrad().getGradID()));
        }
        return gosti;
    }

    public void promeniGosta(Gost gost) throws Exception {
        OpstaSO operation = new IzmeniGosta();
        operation.execute(gost);
    }

    public void sacuvajSobu(Soba soba) throws Exception {
        OpstaSO operation = new ZapamtiSobu();
        operation.execute(soba);
    }

    public OpstiDomenskiObjekat dajSobu(int id) throws Exception {
        Soba soba = new Soba();
        soba.setSobaID(id);
        OpstaSO operation = new UcitajSobu();
        operation.execute(soba);
        return ((UcitajSobu) operation).getGenericEntity();
    }

    public void obrisiSobu(Soba soba) throws Exception {
        OpstaSO operation = new ObrisiSobu();
        operation.execute(soba);
    }

    public List<OpstiDomenskiObjekat> filterSobe(int sprat) throws Exception {
        Soba soba = new Soba();
        soba.setSprat(sprat);
        OpstaSO operation = new NadjiSobe();
        operation.execute(soba);
        List<OpstiDomenskiObjekat> sobe = ((NadjiSobe) operation).getList();
        for (OpstiDomenskiObjekat genericEntity : sobe) {
            Soba s = (Soba) genericEntity;
            s.setVrstaSobe((VrstaSobe) dajVrstuSobe(s.getVrstaSobe().getVrstaSobeID()));
        }
        return sobe;
    }

    public void promeniSobu(Soba soba) throws Exception {
        OpstaSO operation = new IzmeniSobu();
        operation.execute(soba);
    }

    public void sacuvajOcenu(Ocena ocena) throws Exception {
        OpstaSO operation = new ZapamtiOcenuSobe();
        operation.execute(ocena);
    }

    public void sacuvajRezervaciju(Rezervacija rezervacija) throws Exception {
        OpstaSO operation = new ZapamtiRezervaciju();
        operation.execute(rezervacija);
    }

    public OpstiDomenskiObjekat dajRezervaciju(int id) throws Exception {
        Rezervacija rezervacija = new Rezervacija();
        rezervacija.setRezervacijaID(id);
        OpstaSO operation = new UcitajRezervaciju();
        operation.execute(rezervacija);
        Rezervacija rezervacija1 = (Rezervacija) ((UcitajRezervaciju) operation).getGenericEntity();
        rezervacija1.setGost((Gost) dajGosta(rezervacija1.getGost().getGostID()));
        return rezervacija1;
    }

    public List<Rezervacija> vratiSveRezervacije() throws Exception {
        OpstaSO operation = new UcitajListuRezervacija();
        operation.execute(new Rezervacija());
        List<Rezervacija> rezervacije = ((UcitajListuRezervacija) operation).getRezervacije();
        for (Rezervacija rezervacija : rezervacije) {
            rezervacija.setGost((Gost) dajGosta(rezervacija.getGost().getGostID()));
        }
        return rezervacije;
    }

    public List<OpstiDomenskiObjekat> filterRezervacije(Date datumOd) throws Exception {
        Rezervacija rezervacija = new Rezervacija();
        rezervacija.setDatumOd(datumOd);
        OpstaSO operation = new NadjiRezervacije();
        operation.execute(rezervacija);
        List<OpstiDomenskiObjekat> rezervacije = ((NadjiRezervacije) operation).getList();
        for (OpstiDomenskiObjekat genericEntity : rezervacije) {
            Rezervacija r = (Rezervacija) genericEntity;
            r.setGost((Gost) dajGosta(r.getGost().getGostID()));
        }
        return rezervacije;
    }

    public int vratiMaksIDRezervacija() throws Exception {
        OpstaSO operation = new UcitajMaksimalniID();
        operation.execute(new Rezervacija());
        return ((UcitajMaksimalniID) operation).getId();
    }

    public List<StavkaRezervacije> vratiSveStavke() throws Exception {
        OpstaSO operation = new UcitajListuStavki();
        operation.execute(new StavkaRezervacije());
        List<StavkaRezervacije> stavke = ((UcitajListuStavki) operation).getStavke();
        for (StavkaRezervacije stavkaRezervacije : stavke) {
            stavkaRezervacije.setGost((Gost) dajGosta(stavkaRezervacije.getGost().getGostID()));
            stavkaRezervacije.setSoba((Soba) dajSobu(stavkaRezervacije.getSoba().getSobaID()));
        }
        return stavke;
    }

}
