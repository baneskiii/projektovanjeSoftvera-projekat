/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.grad;

import domen.Grad;
import java.util.List;
import so.OpstaSO;

/**
 *
 * @author Bane
 */
public class UcitajListuGradova extends OpstaSO{
    
    private List<Grad> gradovi;
    
    @Override
    protected void preconditions(Object param) throws Exception {
        if (param == null || !(param instanceof Grad)) {
            throw new Exception("Invalid parameter");
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        gradovi = repository.vratiSve((Grad)param);
    }

    public List<Grad> getGradovi() {
        return gradovi;
    }
    
}
