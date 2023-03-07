/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.grad;

import domen.Grad;
import so.OpstaSO;
import domen.OpstiDomenskiObjekat;

/**
 *
 * @author Bane
 */
public class UcitajGrad extends OpstaSO{
    
    private OpstiDomenskiObjekat genericEntity;
    
    @Override
    protected void preconditions(Object param) throws Exception {
        if (param == null || !(param instanceof Grad)) {
            throw new Exception("Invalid parameter");
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        Grad grad = (Grad) param;
        genericEntity = repository.nadji(grad, grad.getGradID());
    }

    public OpstiDomenskiObjekat getGenericEntity() {
        return genericEntity;
    }
    
}
