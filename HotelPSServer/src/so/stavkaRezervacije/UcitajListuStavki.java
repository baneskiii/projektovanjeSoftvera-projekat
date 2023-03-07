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
public class UcitajListuStavki extends OpstaSO{
    
    private List<StavkaRezervacije> stavke;
    
    @Override
    protected void preconditions(Object param) throws Exception {
        if (param == null || !(param instanceof StavkaRezervacije)) {
            throw new Exception("Invalid parameter");
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        stavke = repository.vratiSve((StavkaRezervacije)param);
    }

    public List<StavkaRezervacije> getStavke() {
        return stavke;
    }
    
}
