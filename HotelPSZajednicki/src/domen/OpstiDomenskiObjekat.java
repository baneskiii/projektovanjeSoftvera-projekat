/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package domen;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

/**
 *
 * @author Bane
 */
public interface OpstiDomenskiObjekat extends Serializable{
    public String getTableName();
    public List<OpstiDomenskiObjekat> getList(ResultSet resultSet) throws Exception;
    public String getAttributeNames();
    public String getUnknownValues();
    public void prepareStatement(PreparedStatement ps, OpstiDomenskiObjekat entity) throws Exception;
    public String getUpdateQuery();
    public String getID(OpstiDomenskiObjekat entity);
    public String getOrderCondition();
    public OpstiDomenskiObjekat getResult(ResultSet resultSet) throws Exception;
    public String getCondition(OpstiDomenskiObjekat entity);
}
