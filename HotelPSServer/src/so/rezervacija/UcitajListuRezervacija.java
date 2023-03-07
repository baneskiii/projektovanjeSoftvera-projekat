/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.rezervacija;

import domen.Rezervacija;
import java.util.List;
import so.OpstaSO;

/**
 *
 * @author Bane
 */
public class UcitajListuRezervacija extends OpstaSO{
    
    private List<Rezervacija> rezervacije;
    
    @Override
    protected void preconditions(Object param) throws Exception {
        if (param == null || !(param instanceof Rezervacija)) {
            throw new Exception("Invalid parameter");
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        rezervacije = repository.vratiSve((Rezervacija)param);
    }

    public List<Rezervacija> getRezervacije() {
        return rezervacije;
    }
    
}
