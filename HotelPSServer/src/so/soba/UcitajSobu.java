/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.soba;

import domen.Soba;
import so.OpstaSO;
import domen.OpstiDomenskiObjekat;

/**
 *
 * @author Bane
 */
public class UcitajSobu extends OpstaSO{
    
    private OpstiDomenskiObjekat genericEntity;
    
    @Override
    protected void preconditions(Object param) throws Exception {
        if (param == null || !(param instanceof Soba)) {
            throw new Exception("Invalid parameter");
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        Soba soba = (Soba) param;
        genericEntity = repository.nadji(soba, soba.getSobaID());
    }

    public OpstiDomenskiObjekat getGenericEntity() {
        return genericEntity;
    }
    
}
