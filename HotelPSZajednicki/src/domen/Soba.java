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
public class Soba implements OpstiDomenskiObjekat{
    private int sobaID;
    private int sprat;
    private boolean status;
    private VrstaSobe vrstaSobe;

    public Soba() {
    }

    public Soba(int sobaID, int sprat, boolean status, VrstaSobe vrstaSobe) {
        this.sobaID = sobaID;
        this.sprat = sprat;
        this.status = status;
        this.vrstaSobe = vrstaSobe;
    }

    public VrstaSobe getVrstaSobe() {
        return vrstaSobe;
    }

    public void setVrstaSobe(VrstaSobe vrstaSobe) {
        this.vrstaSobe = vrstaSobe;
    }

    public int getSobaID() {
        return sobaID;
    }

    public void setSobaID(int sobaID) {
        this.sobaID = sobaID;
    }

    public int getSprat() {
        return sprat;
    }

    public void setSprat(int sprat) {
        this.sprat = sprat;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Broj sobe: " + sobaID;
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + this.sobaID;
        hash = 17 * hash + this.sprat;
        hash = 17 * hash + (this.status ? 1 : 0);
        hash = 17 * hash + Objects.hashCode(this.vrstaSobe);
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
        final Soba other = (Soba) obj;
        if (this.sobaID != other.sobaID) {
            return false;
        }
        if (this.sprat != other.sprat) {
            return false;
        }
        if (this.status != other.status) {
            return false;
        }
        return Objects.equals(this.vrstaSobe, other.vrstaSobe);
    }

    @Override
    public String getTableName() {
        return "soba";
    }

    @Override
    public List<OpstiDomenskiObjekat> getList(ResultSet resultSet) throws Exception {
        List<OpstiDomenskiObjekat> list = new LinkedList<>();
        while (resultSet.next()){
            int id = resultSet.getInt("id");
            int sprat = resultSet.getInt("sprat");
            boolean status = resultSet.getBoolean("status");
            int vrstaSobeID = resultSet.getInt("vrstaSobeID");
            
            VrstaSobe vs = new VrstaSobe();
            vs.setVrstaSobeID(vrstaSobeID);
            
            Soba s = new Soba(id, sprat, status, vs);
            list.add(s);
        }
        return list;
    }

    @Override
    public String getAttributeNames() {
        return "sprat,status,vrstaSobeID";
    }

    @Override
    public String getUnknownValues() {
        return "?,?,?";
    }

    @Override
    public void prepareStatement(PreparedStatement ps, OpstiDomenskiObjekat entity) throws Exception {
        Soba s = (Soba) entity;
        ps.setInt(1, s.getSprat());
        ps.setBoolean(2, s.isStatus());
        ps.setInt(3, s.getVrstaSobe().getVrstaSobeID());
    }

    @Override
    public String getUpdateQuery() {
        return "sprat=?,status=?,vrstaSobeID=?";
    }

    @Override
    public String getID(OpstiDomenskiObjekat entity) {
        Soba s = (Soba) entity;
        return String.valueOf(s.getSobaID());
    }

    @Override
    public String getOrderCondition() {
        return "sprat";
    }

    @Override
    public OpstiDomenskiObjekat getResult(ResultSet resultSet) throws Exception {
        OpstiDomenskiObjekat entity = null;
        if (resultSet.next()){
            int id = resultSet.getInt("id");
            int sprat = resultSet.getInt("sprat");
            boolean status = resultSet.getBoolean("status");
            int vrstaSobeID = resultSet.getInt("vrstaSobeID");
            
            VrstaSobe vs = new VrstaSobe();
            vs.setVrstaSobeID(vrstaSobeID);
            
            entity = new Soba(id, sprat, status, vs);
            return entity;
        }
        return null;
    }

    @Override
    public String getCondition(OpstiDomenskiObjekat entity) {
        Soba soba = (Soba) entity;
        return "sprat="+soba.getSprat();
    }
}
