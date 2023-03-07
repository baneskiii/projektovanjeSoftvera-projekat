/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Bane
 */
public class Gost implements OpstiDomenskiObjekat {

    private int gostID;
    private String ime;
    private String prezime;
    private Date datumRodjenja;
    private Grad grad;

    public Gost() {
    }

    public Gost(int gostID, String ime, String prezime, Date datumRodjenja, Grad grad) {
        this.gostID = gostID;
        this.ime = ime;
        this.prezime = prezime;
        this.datumRodjenja = datumRodjenja;
        this.grad = grad;
    }

    public Grad getGrad() {
        return grad;
    }

    public void setGrad(Grad grad) {
        this.grad = grad;
    }

    public int getGostID() {
        return gostID;
    }

    public void setGostID(int gostID) {
        this.gostID = gostID;
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

    public Date getDatumRodjenja() {
        return datumRodjenja;
    }

    public void setDatumRodjenja(Date datumRodjenja) {
        this.datumRodjenja = datumRodjenja;
    }

    @Override
    public String toString() {
        return ime + " " + prezime;
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + this.gostID;
        hash = 79 * hash + Objects.hashCode(this.ime);
        hash = 79 * hash + Objects.hashCode(this.prezime);
        hash = 79 * hash + Objects.hashCode(this.datumRodjenja);
        hash = 79 * hash + Objects.hashCode(this.grad);
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
        final Gost other = (Gost) obj;
        if (this.gostID != other.gostID) {
            return false;
        }
        if (!Objects.equals(this.ime, other.ime)) {
            return false;
        }
        if (!Objects.equals(this.prezime, other.prezime)) {
            return false;
        }
        if (!Objects.equals(this.datumRodjenja, other.datumRodjenja)) {
            return false;
        }
        return Objects.equals(this.grad, other.grad);
    }

    @Override
    public String getTableName() {
        return "gost";
    }

    @Override
    public List<OpstiDomenskiObjekat> getList(ResultSet resultSet) throws Exception {
        List<OpstiDomenskiObjekat> list = new LinkedList<>();
        while(resultSet.next()){
            int id = resultSet.getInt("id");
            String ime = resultSet.getString("ime");
            String prezime = resultSet.getString("prezime");
            Date datumRodjenja = new Date(resultSet.getDate("datumRodjenja").getTime());
            
            int gradID = resultSet.getInt("gradID");
            Grad grad = new Grad();
            grad.setGradID(gradID);
            
            Gost g = new Gost(id, ime, prezime, datumRodjenja, grad);
            list.add(g);
        }
        return list;
    }

    @Override
    public String getAttributeNames() {
        return "ime,prezime,datumRodjenja,gradID";
    }

    @Override
    public String getUnknownValues() {
        return "?,?,?,?";
    }

    @Override
    public void prepareStatement(PreparedStatement ps, OpstiDomenskiObjekat entity) throws Exception {
        Gost g = (Gost) entity;
        ps.setString(1, g.getIme());
        ps.setString(2, g.getPrezime());
        ps.setDate(3, new java.sql.Date(g.getDatumRodjenja().getTime()));
        ps.setInt(4, g.getGrad().getGradID());
    }

    @Override
    public String getUpdateQuery() {
        return "ime=?,prezime=?,datumRodjenja=?,gradID=?";
    }

    @Override
    public String getID(OpstiDomenskiObjekat entity) {
        Gost g = (Gost) entity;
        return String.valueOf(g.getGostID());
    }

    @Override
    public String getOrderCondition() {
        return "ime";
    }

    @Override
    public OpstiDomenskiObjekat getResult(ResultSet resultSet) throws Exception {
        OpstiDomenskiObjekat entity = null;
        if (resultSet.next()){
            int id = resultSet.getInt("id");
            String ime = resultSet.getString("ime");
            String prezime = resultSet.getString("prezime");
            Date datumRodjenja = new Date(resultSet.getDate("datumRodjenja").getTime());
            
            int gradID = resultSet.getInt("gradID");
            Grad grad = new Grad();
            grad.setGradID(gradID);
            
            entity = new Gost(id, ime, prezime, datumRodjenja, grad);
            return entity;
        }
        return null;
    }

    @Override
    public String getCondition(OpstiDomenskiObjekat entity) {
        Gost gost = (Gost) entity;
        return "ime LIKE '" + gost.getIme() + "%' AND prezime LIKE '" + gost.getPrezime() + "%'";
    }
}
