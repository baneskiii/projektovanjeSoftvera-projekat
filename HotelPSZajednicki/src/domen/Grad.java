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
public class Grad implements OpstiDomenskiObjekat{
    private int gradID;
    private int pttBroj;
    private String naziv;

    public Grad() {
    }

    public Grad(int gradID, int pttBroj, String naziv) {
        this.gradID = gradID;
        this.pttBroj = pttBroj;
        this.naziv = naziv;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public int getGradID() {
        return gradID;
    }

    public void setGradID(int gradID) {
        this.gradID = gradID;
    }

    public int getPttBroj() {
        return pttBroj;
    }

    public void setPttBroj(int pttBroj) {
        this.pttBroj = pttBroj;
    }

    @Override
    public String toString() {
        return naziv;
    }
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 31 * hash + this.gradID;
        hash = 31 * hash + this.pttBroj;
        hash = 31 * hash + Objects.hashCode(this.naziv);
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
        final Grad other = (Grad) obj;
        if (this.gradID != other.gradID) {
            return false;
        }
        if (this.pttBroj != other.pttBroj) {
            return false;
        }
        return Objects.equals(this.naziv, other.naziv);
    }

    @Override
    public String getTableName() {
        return "grad";
    }

    @Override
    public List<OpstiDomenskiObjekat> getList(ResultSet resultSet) throws Exception {
        List<OpstiDomenskiObjekat> list = new LinkedList<>();
        while(resultSet.next()){
            int id = resultSet.getInt("id");
            int pttBroj = resultSet.getInt("pttBroj");
            String naziv = resultSet.getString("naziv");
            
            Grad g = new Grad(id, pttBroj, naziv);
            list.add(g);
        }
        return list;
    }

    @Override
    public String getAttributeNames() {
        return "pttBroj,naziv";
    }

    @Override
    public String getUnknownValues() {
        return "?,?";
    }

    @Override
    public void prepareStatement(PreparedStatement ps, OpstiDomenskiObjekat entity) throws Exception {
        Grad g = (Grad) entity;
        ps.setInt(1, g.getPttBroj());
        ps.setString(2, g.getNaziv());
    }

    @Override
    public String getUpdateQuery() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getID(OpstiDomenskiObjekat entity) {
        Grad g = (Grad) entity;
        return String.valueOf(g.getGradID());
    }

    @Override
    public String getOrderCondition() {
        return "naziv";
    }

    @Override
    public OpstiDomenskiObjekat getResult(ResultSet resultSet) throws Exception {
        OpstiDomenskiObjekat entity = null;
        if(resultSet.next()){
            int id = resultSet.getInt("id");
            int pttBroj = resultSet.getInt("pttBroj");
            String naziv = resultSet.getString("naziv");
            
            entity = new Grad(id, pttBroj, naziv);
            return entity;
        }
        return null;
    }

    @Override
    public String getCondition(OpstiDomenskiObjekat entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
