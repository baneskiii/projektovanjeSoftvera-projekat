/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.db.impl;

import java.util.List;
import repository.db.DbRepository;
import java.sql.*;
import repository.db.DbConnectionFactory;
import domen.OpstiDomenskiObjekat;

/**
 *
 * @author Bane
 */
public class DBBroker implements DbRepository<OpstiDomenskiObjekat> {

    @Override
    public void dodaj(OpstiDomenskiObjekat entity) throws Exception {
        try {
            Connection connection = DbConnectionFactory.getInstance().getConnection();
            String query = "INSERT INTO " + entity.getTableName() + " (" + entity.getAttributeNames() + ") VALUES(" + entity.getUnknownValues() + ")";
            PreparedStatement ps = DbConnectionFactory.getInstance().getConnection().prepareStatement(query);
            entity.prepareStatement(ps, entity);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    @Override
    public void promeni(OpstiDomenskiObjekat entity) throws Exception {
        try {
            String query = "UPDATE " + entity.getTableName() + " SET " + entity.getUpdateQuery() + " WHERE id=" + entity.getID(entity);
            PreparedStatement ps = DbConnectionFactory.getInstance().getConnection().prepareStatement(query);
            entity.prepareStatement(ps, entity);
            ps.executeUpdate();
            ps.close();
        } catch (Exception ex) {
            if (DbConnectionFactory.getInstance().getConnection() != null) {
                DbConnectionFactory.getInstance().getConnection().rollback();
            }
            ex.printStackTrace();
            throw ex;
        }
    }

    @Override
    public void obrisi(OpstiDomenskiObjekat entity) throws Exception {
        try {
            Connection connection = DbConnectionFactory.getInstance().getConnection();
            String query = "DELETE FROM " + entity.getTableName() + " WHERE id=" + entity.getID(entity);
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            statement.close();
        } catch (Exception ex) {
            if (DbConnectionFactory.getInstance().getConnection() != null) {
                DbConnectionFactory.getInstance().getConnection().rollback();
            }
            ex.printStackTrace();
            throw ex;
        }
    }

    @Override
    public List<OpstiDomenskiObjekat> vratiSve(OpstiDomenskiObjekat entity) throws Exception {
        List<OpstiDomenskiObjekat> list = null;
        try {
            Connection connection = DbConnectionFactory.getInstance().getConnection();
            String query = "SELECT * FROM " + entity.getTableName() + " ORDER BY " + entity.getOrderCondition();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            list = entity.getList(resultSet);
            resultSet.close();
            statement.close();
            return list;
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    @Override
    public OpstiDomenskiObjekat nadji(OpstiDomenskiObjekat entity, int id) throws Exception {
        OpstiDomenskiObjekat genericEntity = null;
        try {
            Connection connection = DbConnectionFactory.getInstance().getConnection();
            String query = "SELECT * FROM " + entity.getTableName() + " WHERE id=" + id;
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            genericEntity = entity.getResult(resultSet);
            resultSet.close();
            statement.close();
            return genericEntity;
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    @Override
    public List<OpstiDomenskiObjekat> filter(OpstiDomenskiObjekat entity) throws Exception {
        List<OpstiDomenskiObjekat> list = null;
        try {
            Connection connection = DbConnectionFactory.getInstance().getConnection();
            String query = "SELECT * FROM " + entity.getTableName() + " WHERE " + entity.getCondition(entity) + " ORDER BY " + entity.getOrderCondition();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            list = entity.getList(resultSet);
            resultSet.close();
            statement.close();
            return list;
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    @Override
    public OpstiDomenskiObjekat uloguj(OpstiDomenskiObjekat entity, String username, String password) throws Exception {
        OpstiDomenskiObjekat genericEntity = null;
        try {
            Connection connection = DbConnectionFactory.getInstance().getConnection();
            String query = "SELECT * FROM " + entity.getTableName() + " WHERE username='" + username + "' AND password='" + password + "'";
            System.out.println(query);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            genericEntity = entity.getResult(resultSet);
            resultSet.close();
            statement.close();
            return genericEntity;
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    @Override
    public int vratiMaksIndeks(OpstiDomenskiObjekat entity) throws Exception {
        int maksIndeks = 0;
        try {
            String query = "SELECT MAX(id) AS maks FROM " + entity.getTableName();
            Statement statement = DbConnectionFactory.getInstance().getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                maksIndeks = resultSet.getInt("maks");
            }
            resultSet.close();
            statement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }
        return maksIndeks + 1;
    }

    

}
