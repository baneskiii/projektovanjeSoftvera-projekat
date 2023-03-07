/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.vrstaSobe;

import domen.VrstaSobe;
import so.OpstaSO;
import domen.OpstiDomenskiObjekat;

/**
 *
 * @author Bane
 */
public class UcitajVrstuSobe extends OpstaSO{
    
    private OpstiDomenskiObjekat genericEntity;
    
    @Override
    protected void preconditions(Object param) throws Exception {
        if (param == null || !(param instanceof VrstaSobe)) {
            throw new Exception("Invalid parameter");
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        VrstaSobe vrstaSobe = (VrstaSobe) param;
        genericEntity = repository.nadji(vrstaSobe, vrstaSobe.getVrstaSobeID());
    }

    public OpstiDomenskiObjekat getGenericEntity() {
        return genericEntity;
    }
    
}
