/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.gost;

import domen.Gost;
import so.OpstaSO;

/**
 *
 * @author Bane
 */
public class IzmeniGosta extends OpstaSO{

    @Override
    protected void preconditions(Object param) throws Exception {
        if (param == null || !(param instanceof Gost)) {
            throw new Exception("Invalid parameter");
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        Gost gost = (Gost) param;
        repository.promeni(gost);
    }
    
}
