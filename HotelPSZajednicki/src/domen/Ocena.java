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
public class Ocena implements OpstiDomenskiObjekat{
    private Gost gost;
    private Soba soba;
    private int ocenaSobe;

    public Ocena() {
    }

    public Ocena(Gost gost, Soba soba, int ocenaSobe) {
        this.gost = gost;
        this.soba = soba;
        this.ocenaSobe = ocenaSobe;
    }

    public int getOcenaSobe() {
        return ocenaSobe;
    }

    public void setOcenaSobe(int ocenaSobe) {
        this.ocenaSobe = ocenaSobe;
    }

    public Gost getGost() {
        return gost;
    }

    public void setGost(Gost gost) {
        this.gost = gost;
    }

    public Soba getSoba() {
        return soba;
    }

    public void setSoba(Soba soba) {
        this.soba = soba;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 13 * hash + Objects.hashCode(this.gost);
        hash = 13 * hash + Objects.hashCode(this.soba);
        hash = 13 * hash + this.ocenaSobe;
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
        final Ocena other = (Ocena) obj;
        if (this.ocenaSobe != other.ocenaSobe) {
            return false;
        }
        if (!Objects.equals(this.gost, other.gost)) {
            return false;
        }
        return Objects.equals(this.soba, other.soba);
    }

    @Override
    public String getTableName() {
        return "ocena";
    }

    @Override
    public List<OpstiDomenskiObjekat> getList(ResultSet resultSet) throws Exception {
        List<OpstiDomenskiObjekat> list = new LinkedList<>();
        while(resultSet.next()){
            int gostID = resultSet.getInt("gostID");
            Gost gost = new Gost();
            gost.setGostID(gostID);
            
            int sobaID = resultSet.getInt("sobaID");
            Soba soba = new Soba();
            soba.setSobaID(sobaID);
            
            int ocenaSobe = resultSet.getInt("ocenaSobe");
            
            Ocena ocena = new Ocena(gost, soba, ocenaSobe);
            list.add(ocena);
        }
        return list;
    }

    @Override
    public String getAttributeNames() {
        return "gostID,sobaID,ocenaSobe";
    }

    @Override
    public String getUnknownValues() {
        return "?,?,?";
    }

    @Override
    public void prepareStatement(PreparedStatement ps, OpstiDomenskiObjekat entity) throws Exception {
        Ocena o = (Ocena) entity;
        ps.setInt(1, o.getGost().getGostID());
        ps.setInt(2, o.getSoba().getSobaID());
        ps.setInt(3, o.getOcenaSobe());
    }

    @Override
    public String getUpdateQuery() {
        return "ocenaSobe=?";
    }

    @Override
    public String getID(OpstiDomenskiObjekat entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getOrderCondition() {
        return "ocenaSobe";
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
