/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.stavkaRezervacije;

import domen.StavkaRezervacije;
import java.util.List;
import so.OpstaSO;

/**
 *
 * @author Bane
 */
public class ZapamtiStavkuRezervacije extends OpstaSO{

    @Override
    protected void preconditions(Object param) throws Exception {
        List<StavkaRezervacije> list = (List<StavkaRezervacije>) param;
        for (StavkaRezervacije stavkaRezervacije : list) {
            if (stavkaRezervacije == null || !(stavkaRezervacije instanceof StavkaRezervacije)){
                throw new Exception("Invalid parameter");
            }
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        List<StavkaRezervacije> list = (List<StavkaRezervacije>) param;
        for (StavkaRezervacije stavkaRezervacije : list) {
            repository.dodaj(stavkaRezervacije);
        }
    }
    
}
