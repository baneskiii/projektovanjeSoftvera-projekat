/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.rezervacija;

import domen.Rezervacija;
import so.OpstaSO;

/**
 *
 * @author Bane
 */
public class UcitajMaksimalniID extends OpstaSO{
    
    private int id;
    
    @Override
    protected void preconditions(Object param) throws Exception {
        if (param == null || !(param instanceof Rezervacija)) {
            throw new Exception("Invalid parameter");
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        Rezervacija rezervacija = (Rezervacija) param;
        id = repository.vratiMaksIndeks(rezervacija);
    }

    public int getId() {
        return id;
    }
    
}
