/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Bane
 */
public class Rezervacija implements OpstiDomenskiObjekat{
    private int rezervacijaID;
    private Date datumOd;
    private Date datumDo;
    private Gost gost;
    
    private List<StavkaRezervacije> stavkeRezervacije;

    public Rezervacija() {
        stavkeRezervacije = new ArrayList<>();
    }

    public Rezervacija(int rezervacijaID, Date datumOd, Date datumDo, Gost gost, List<StavkaRezervacije> stavkeRezervacije) {
        this.rezervacijaID = rezervacijaID;
        this.datumOd = datumOd;
        this.datumDo = datumDo;
        this.gost = gost;
        this.stavkeRezervacije = stavkeRezervacije;
    }

    public List<StavkaRezervacije> getStavkeRezervacije() {
        return stavkeRezervacije;
    }

    public void setStavkeRezervacije(List<StavkaRezervacije> stavkeRezervacije) {
        this.stavkeRezervacije = stavkeRezervacije;
    }

    public int getRezervacijaID() {
        return rezervacijaID;
    }

    public void setRezervacijaID(int rezervacijaID) {
        this.rezervacijaID = rezervacijaID;
    }

    public Date getDatumOd() {
        return datumOd;
    }

    public void setDatumOd(Date datumOd) {
        this.datumOd = datumOd;
    }

    public Date getDatumDo() {
        return datumDo;
    }

    public void setDatumDo(Date datumDo) {
        this.datumDo = datumDo;
    }

    public Gost getGost() {
        return gost;
    }

    public void setGost(Gost gost) {
        this.gost = gost;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + this.rezervacijaID;
        hash = 37 * hash + Objects.hashCode(this.datumOd);
        hash = 37 * hash + Objects.hashCode(this.datumDo);
        hash = 37 * hash + Objects.hashCode(this.gost);
        hash = 37 * hash + Objects.hashCode(this.stavkeRezervacije);
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
        final Rezervacija other = (Rezervacija) obj;
        if (this.rezervacijaID != other.rezervacijaID) {
            return false;
        }
        if (!Objects.equals(this.datumOd, other.datumOd)) {
            return false;
        }
        if (!Objects.equals(this.datumDo, other.datumDo)) {
            return false;
        }
        if (!Objects.equals(this.gost, other.gost)) {
            return false;
        }
        return Objects.equals(this.stavkeRezervacije, other.stavkeRezervacije);
    }

    @Override
    public String getTableName() {
        return "rezervacija";
    }

    @Override
    public List<OpstiDomenskiObjekat> getList(ResultSet resultSet) throws Exception {
        List<OpstiDomenskiObjekat> list = new LinkedList<>();
        while(resultSet.next()){
            int id = resultSet.getInt("id");
            Date datumOd = new Date(resultSet.getDate("datumOd").getTime());
            Date datumDo = new Date(resultSet.getDate("datumDo").getTime());
            int gostID = resultSet.getInt("gostID");
            
            Gost g = new Gost();
            g.setGostID(gostID);
            
            Rezervacija r = new Rezervacija(id, datumOd, datumDo, g, new LinkedList<>());
            list.add(r);
        }
        return list;
    }

    @Override
    public String getAttributeNames() {
        return "id,datumOd,datumDo,gostID";
    }

    @Override
    public String getUnknownValues() {
        return "?,?,?,?";
    }

    @Override
    public void prepareStatement(PreparedStatement ps, OpstiDomenskiObjekat entity) throws Exception {
        Rezervacija r = (Rezervacija) entity;
        ps.setInt(1, r.getRezervacijaID());
        ps.setDate(2, new java.sql.Date(r.getDatumOd().getTime()));
        ps.setDate(3, new java.sql.Date(r.getDatumDo().getTime()));
        ps.setInt(4, r.getGost().getGostID());
    }

    @Override
    public String getUpdateQuery() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getID(OpstiDomenskiObjekat entity) {
        Rezervacija r = (Rezervacija) entity;
        return String.valueOf(r.getRezervacijaID());
    }

    @Override
    public String getOrderCondition() {
        return "id";
    }

    @Override
    public OpstiDomenskiObjekat getResult(ResultSet resultSet) throws Exception {
        OpstiDomenskiObjekat entity = null;
        if (resultSet.next()){
            int id = resultSet.getInt("id");
            Date datumOd = new Date(resultSet.getDate("datumOd").getTime());
            Date datumDo = new Date(resultSet.getDate("datumDo").getTime());
            int gostID = resultSet.getInt("gostID");
            
            Gost g = new Gost();
            g.setGostID(gostID);
            
            entity = new Rezervacija(id, datumOd, datumDo, g, new LinkedList<>());
            return entity;
        }
        return null;
    }

    @Override
    public String getCondition(OpstiDomenskiObjekat entity) {
        Rezervacija r = (Rezervacija) entity;
        return "datumOd='"+new java.sql.Date(r.getDatumOd().getTime())+"'";
    }
    
}
