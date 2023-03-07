/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.gost;

import domen.Gost;
import java.util.List;
import so.OpstaSO;

/**
 *
 * @author Bane
 */
public class UcitajListuGostiju extends OpstaSO{
    
    private List<Gost> gosti;
    
    @Override
    protected void preconditions(Object param) throws Exception {
        if (param == null || !(param instanceof Gost)) {
            throw new Exception("Invalid parameter");
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        gosti = repository.vratiSve((Gost)param);
    }

    public List<Gost> getGosti() {
        return gosti;
    }
    
}
