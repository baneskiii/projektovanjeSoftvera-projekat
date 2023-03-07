/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.korisnik;

import domen.Korisnik;
import so.OpstaSO;
import domen.OpstiDomenskiObjekat;

/**
 *
 * @author Bane
 */
public class UlogujKorisnika extends OpstaSO{
    
    private OpstiDomenskiObjekat genericEntity;
    
    @Override
    protected void preconditions(Object param) throws Exception {
        if (param == null || !(param instanceof Korisnik)) {
            throw new Exception("Invalid parameter");
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        Korisnik k = (Korisnik) param;
        genericEntity = repository.uloguj(k, k.getUsername(), k.getPassword());
    }

    public OpstiDomenskiObjekat getGenericEntity() {
        return genericEntity;
    }
    
}
