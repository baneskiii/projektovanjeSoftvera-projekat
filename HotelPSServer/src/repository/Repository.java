/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package repository;

import java.util.List;
import domen.OpstiDomenskiObjekat;

/**
 *
 * @author Bane
 */
public interface Repository<T> {
    
    public void dodaj(T param) throws Exception;
    public void promeni(T param) throws Exception;
    public void obrisi(T param) throws Exception;
    public List<T> vratiSve(T param) throws Exception;
    public OpstiDomenskiObjekat nadji(OpstiDomenskiObjekat entity, int id) throws Exception;
    public List<OpstiDomenskiObjekat> filter(OpstiDomenskiObjekat entity) throws Exception;
    public OpstiDomenskiObjekat uloguj(OpstiDomenskiObjekat entity, String username, String password) throws Exception;
    public int vratiMaksIndeks(OpstiDomenskiObjekat entity) throws Exception;
}
