/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.soba;

import domen.Soba;
import java.util.List;
import so.OpstaSO;
import domen.OpstiDomenskiObjekat;

/**
 *
 * @author Bane
 */
public class NadjiSobe extends OpstaSO{
    
    private List<OpstiDomenskiObjekat> list;
    
    @Override
    protected void preconditions(Object param) throws Exception {
        if(param == null || !(param instanceof Soba)){
            throw new Exception("Invalid parameter");
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        list = repository.filter((Soba)param);
    }

    public List<OpstiDomenskiObjekat> getList() {
        return list;
    }
    
}
