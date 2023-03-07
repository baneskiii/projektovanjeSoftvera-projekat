/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.rezervacija;

import domen.Rezervacija;
import domen.StavkaRezervacije;
import java.util.List;
import so.OpstaSO;

/**
 *
 * @author Bane
 */
public class ZapamtiRezervaciju extends OpstaSO{

    @Override
    protected void preconditions(Object param) throws Exception {
        if (param == null || !(param instanceof Rezervacija)) {
            throw new Exception("Invalid parameter");
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        Rezervacija r = (Rezervacija) param;
        repository.dodaj(r);
        List<StavkaRezervacije> stavke = r.getStavkeRezervacije();
        for (StavkaRezervacije stavkaRezervacije : stavke) {
            repository.dodaj(stavkaRezervacije);
        }
    }
    
}
