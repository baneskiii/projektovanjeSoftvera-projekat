/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.ocena;

import domen.Ocena;
import so.OpstaSO;

/**
 *
 * @author Bane
 */
public class ZapamtiOcenuSobe extends OpstaSO{

    @Override
    protected void preconditions(Object param) throws Exception {
        if (param == null || !(param instanceof Ocena)) {
            throw new Exception("Invalid parameter");
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        repository.dodaj((Ocena)param);
    }
    
}
