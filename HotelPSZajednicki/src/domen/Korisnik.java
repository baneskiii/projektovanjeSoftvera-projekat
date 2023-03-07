/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Bane
 */
public class Korisnik implements OpstiDomenskiObjekat {

    private int korisnikID;
    private String ime;
    private String prezime;
    private String username;
    private String password;

    public Korisnik() {
    }

    public Korisnik(int korisnikID, String ime, String prezime, String username, String password) {
        this.korisnikID = korisnikID;
        this.ime = ime;
        this.prezime = prezime;
        this.username = username;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getKorisnikID() {
        return korisnikID;
    }

    public void setKorisnikID(int korisnikID) {
        this.korisnikID = korisnikID;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + this.korisnikID;
        hash = 89 * hash + Objects.hashCode(this.ime);
        hash = 89 * hash + Objects.hashCode(this.prezime);
        hash = 89 * hash + Objects.hashCode(this.username);
        hash = 89 * hash + Objects.hashCode(this.password);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Korisnik other = (Korisnik) obj;
        if (this.korisnikID != other.korisnikID) {
            return false;
        }
        if (!Objects.equals(this.ime, other.ime)) {
            return false;
        }
        if (!Objects.equals(this.prezime, other.prezime)) {
            return false;
        }
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        return Objects.equals(this.password, other.password);
    }

    @Override
    public String getTableName() {
        return "korisnik";
    }

    @Override
    public List<OpstiDomenskiObjekat> getList(ResultSet resultSet) throws Exception {
        List<OpstiDomenskiObjekat> list = new LinkedList<>();
        while(resultSet.next()){
            int id = resultSet.getInt("id");
            String ime = resultSet.getString("ime");
            String prezime = resultSet.getString("prezime");
            String username = resultSet.getString("username");
            String password = resultSet.getString("password");
            Korisnik k = new Korisnik(id, ime, prezime, username, password);
            list.add(k);
        }
        return list;
    }

    @Override
    public String getAttributeNames() {
        return "ime,prezime,username,password";
    }

    @Override
    public String getUnknownValues() {
        return "?,?,?,?";
    }

    @Override
    public void prepareStatement(PreparedStatement ps, OpstiDomenskiObjekat entity) throws Exception {
        Korisnik k = (Korisnik) entity;
        ps.setString(1, k.getIme());
        ps.setString(2, k.getPrezime());
        ps.setString(3, k.getUsername());
        ps.setString(4, k.getPassword());
    }

    @Override
    public String getUpdateQuery() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getID(OpstiDomenskiObjekat entity) {
        Korisnik k = (Korisnik) entity;
        return String.valueOf(k.getKorisnikID());
    }

    @Override
    public String getOrderCondition() {
        return "ime";
    }

    @Override
    public OpstiDomenskiObjekat getResult(ResultSet resultSet) throws Exception {
        OpstiDomenskiObjekat entity = null;
        if(resultSet.next()){
            int id = resultSet.getInt("id");
            String ime = resultSet.getString("ime");
            String prezime = resultSet.getString("prezime");
            String username = resultSet.getString("username");
            String password = resultSet.getString("password");
            entity = new Korisnik(id, ime, prezime, username, password);
            return entity;
        }
        return null;
    }

    @Override
    public String getCondition(OpstiDomenskiObjekat entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
