/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.vrstaSobe;

import domen.VrstaSobe;
import java.util.List;
import so.OpstaSO;

/**
 *
 * @author Bane
 */
public class UcitajListuVrstaSoba extends OpstaSO{
    
    private List<VrstaSobe> vrsteSoba;
    
    @Override
    protected void preconditions(Object param) throws Exception {
        if (param == null || !(param instanceof VrstaSobe)) {
            throw new Exception("Invalid parameter");
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        vrsteSoba = repository.vratiSve((VrstaSobe)param);
    }

    public List<VrstaSobe> getVrsteSoba() {
        return vrsteSoba;
    }
    
}
