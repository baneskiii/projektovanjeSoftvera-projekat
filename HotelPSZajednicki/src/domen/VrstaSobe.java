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

/**
 *
 * @author Bane
 */
public class VrstaSobe implements OpstiDomenskiObjekat{
    private int vrstaSobeID;
    private int brojKreveta;
    private int povrsina;

    public VrstaSobe() {
    }

    public VrstaSobe(int vrstaSobeID, int brojKreveta, int povrsina) {
        this.vrstaSobeID = vrstaSobeID;
        this.brojKreveta = brojKreveta;
        this.povrsina = povrsina;
    }

    public int getPovrsina() {
        return povrsina;
    }

    public void setPovrsina(int povrsina) {
        this.povrsina = povrsina;
    }

    public int getVrstaSobeID() {
        return vrstaSobeID;
    }

    public void setVrstaSobeID(int vrstaSobeID) {
        this.vrstaSobeID = vrstaSobeID;
    }

    public int getBrojKreveta() {
        return brojKreveta;
    }

    public void setBrojKreveta(int brojKreveta) {
        this.brojKreveta = brojKreveta;
    }

    @Override
    public String toString() {
        if(brojKreveta == 1) return "Jednokrevetna";
        if(brojKreveta == 2) return "Dvokrevetna";
        if(brojKreveta == 3) return "Trokrevetna";
        if(brojKreveta == 4) return "Četvorokrevetna";
        return brojKreveta+"";
    }
    
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 83 * hash + this.vrstaSobeID;
        hash = 83 * hash + this.brojKreveta;
        hash = 83 * hash + this.povrsina;
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
        final VrstaSobe other = (VrstaSobe) obj;
        if (this.vrstaSobeID != other.vrstaSobeID) {
            return false;
        }
        if (this.brojKreveta != other.brojKreveta) {
            return false;
        }
        return this.povrsina == other.povrsina;
    }

    @Override
    public String getTableName() {
        return "vrstasobe";
    }

    @Override
    public List<OpstiDomenskiObjekat> getList(ResultSet resultSet) throws Exception {
        List<OpstiDomenskiObjekat> list = new LinkedList<>();
        while(resultSet.next()){
            int id = resultSet.getInt("id");
            int brojKreveta = resultSet.getInt("brojKreveta");
            int povrsina = resultSet.getInt("povrsina");
            
            VrstaSobe vrstaSobe = new VrstaSobe(id, brojKreveta, povrsina);
            list.add(vrstaSobe);
        }
        return list;
    }

    @Override
    public String getAttributeNames() {
        return "brojKreveta,povrsina";
    }

    @Override
    public String getUnknownValues() {
        return "?,?";
    }

    @Override
    public void prepareStatement(PreparedStatement ps, OpstiDomenskiObjekat entity) throws Exception {
        VrstaSobe vrstaSobe = (VrstaSobe) entity;
        ps.setInt(1, vrstaSobe.getBrojKreveta());
        ps.setInt(2, vrstaSobe.getPovrsina());
    }

    @Override
    public String getUpdateQuery() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getID(OpstiDomenskiObjekat entity) {
        VrstaSobe vrstaSobe = (VrstaSobe) entity;
        return String.valueOf(vrstaSobe.getVrstaSobeID());
    }

    @Override
    public String getOrderCondition() {
        return "brojKreveta";
    }

    @Override
    public OpstiDomenskiObjekat getResult(ResultSet resultSet) throws Exception {
        OpstiDomenskiObjekat entity = null;
        if(resultSet.next()){
            int id = resultSet.getInt("id");
            int brojKreveta = resultSet.getInt("brojKreveta");
            int povrsina = resultSet.getInt("povrsina");
            
            entity = new VrstaSobe(id, brojKreveta, povrsina);
            return entity;
        }
        return null;
    }

    @Override
    public String getCondition(OpstiDomenskiObjekat entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
