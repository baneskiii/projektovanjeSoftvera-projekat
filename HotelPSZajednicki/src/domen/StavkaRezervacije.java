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
public class StavkaRezervacije implements OpstiDomenskiObjekat{
    private Rezervacija rezervacija;
    private int rbStavke;
    private Gost gost;
    private Soba soba;

    public StavkaRezervacije() {
    }

    public StavkaRezervacije(Rezervacija rezervacija, int rbStavke, Gost gost, Soba soba) {
        this.rezervacija = rezervacija;
        this.rbStavke = rbStavke;
        this.gost = gost;
        this.soba = soba;
    }

    public Soba getSoba() {
        return soba;
    }

    public void setSoba(Soba soba) {
        this.soba = soba;
    }

    public Rezervacija getRezervacija() {
        return rezervacija;
    }

    public void setRezervacija(Rezervacija rezervacija) {
        this.rezervacija = rezervacija;
    }

    public int getRbStavke() {
        return rbStavke;
    }

    public void setRbStavke(int rbStavke) {
        this.rbStavke = rbStavke;
    }

    public Gost getGost() {
        return gost;
    }

    public void setGost(Gost gost) {
        this.gost = gost;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.rezervacija);
        hash = 59 * hash + this.rbStavke;
        hash = 59 * hash + Objects.hashCode(this.gost);
        hash = 59 * hash + Objects.hashCode(this.soba);
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
        final StavkaRezervacije other = (StavkaRezervacije) obj;
        if (this.rbStavke != other.rbStavke) {
            return false;
        }
        if (!Objects.equals(this.rezervacija, other.rezervacija)) {
            return false;
        }
        if (!Objects.equals(this.gost, other.gost)) {
            return false;
        }
        return Objects.equals(this.soba, other.soba);
    }

    @Override
    public String getTableName() {
        return "stavkarezervacije";
    }

    @Override
    public List<OpstiDomenskiObjekat> getList(ResultSet resultSet) throws Exception {
        List<OpstiDomenskiObjekat> list = new LinkedList<>();
        while(resultSet.next()){
            int rezervacijaID = resultSet.getInt("rezervacijaID");
            Rezervacija r = new Rezervacija();
            r.setRezervacijaID(rezervacijaID);
            
            int rbStavke = resultSet.getInt("rbStavke");
            
            int gostID = resultSet.getInt("gostID");
            Gost g = new Gost();
            g.setGostID(gostID);
            
            int sobaID = resultSet.getInt("sobaID");
            Soba s = new Soba();
            s.setSobaID(sobaID);
            
            StavkaRezervacije stavkaRezervacije = new StavkaRezervacije(r, rbStavke, g, s);
            list.add(stavkaRezervacije);
        }
        return list;
    }

    @Override
    public String getAttributeNames() {
        return "rezervacijaID,rbStavke,gostID,sobaID";
    }

    @Override
    public String getUnknownValues() {
        return "?,?,?,?";
    }

    @Override
    public void prepareStatement(PreparedStatement ps, OpstiDomenskiObjekat entity) throws Exception {
        StavkaRezervacije stavkaRezervacije = (StavkaRezervacije) entity;
        ps.setInt(1, stavkaRezervacije.getRezervacija().getRezervacijaID());
        ps.setInt(2, stavkaRezervacije.getRbStavke());
        ps.setInt(3, stavkaRezervacije.getGost().getGostID());
        ps.setInt(4, stavkaRezervacije.getSoba().getSobaID());
    }

    @Override
    public String getUpdateQuery() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getID(OpstiDomenskiObjekat entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getOrderCondition() {
        return "rezervacijaID";
    }

    @Override
    public OpstiDomenskiObjekat getResult(ResultSet resultSet) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getCondition(OpstiDomenskiObjekat entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
