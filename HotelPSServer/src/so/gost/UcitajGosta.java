/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.gost;

import domen.Gost;
import so.OpstaSO;
import domen.OpstiDomenskiObjekat;

/**
 *
 * @author Bane
 */
public class UcitajGosta extends OpstaSO{
    
    private OpstiDomenskiObjekat genericEntity;
    
    @Override
    protected void preconditions(Object param) throws Exception {
        if (param == null || !(param instanceof Gost)) {
            throw new Exception("Invalid parameter");
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        Gost gost = (Gost) param;
        genericEntity = repository.nadji(gost, gost.getGostID());
    }

    public OpstiDomenskiObjekat getGenericEntity() {
        return genericEntity;
    }
    
}
