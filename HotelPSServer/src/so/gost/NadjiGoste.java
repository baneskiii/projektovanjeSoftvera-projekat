/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.gost;

import domen.Gost;
import java.util.List;
import so.OpstaSO;
import domen.OpstiDomenskiObjekat;

/**
 *
 * @author Bane
 */
public class NadjiGoste extends OpstaSO{
    
    private List<OpstiDomenskiObjekat> list;
    
    @Override
    protected void preconditions(Object param) throws Exception {
        if(param == null || !(param instanceof Gost)){
            throw new Exception("Invalid parameter");
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        list = repository.filter((Gost)param);
    }

    public List<OpstiDomenskiObjekat> getList() {
        return list;
    }
    

}
