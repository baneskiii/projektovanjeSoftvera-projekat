/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.soba;

import domen.Soba;
import java.util.List;
import so.OpstaSO;

/**
 *
 * @author Bane
 */
public class UcitajListuSoba extends OpstaSO{
    
    private List<Soba> sobe;
    
    @Override
    protected void preconditions(Object param) throws Exception {
        if (param == null || !(param instanceof Soba)) {
            throw new Exception("Invalid parameter");
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        sobe = repository.vratiSve((Soba)param);
    }

    public List<Soba> getSobe() {
        return sobe;
    }
    
}
