/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so;

import repository.Repository;
import repository.db.DbRepository;
import repository.db.impl.DBBroker;

/**
 *
 * @author Bane
 */
public abstract class OpstaSO {

    protected final Repository repository;

    public OpstaSO() {
        this.repository = new DBBroker();
    }
    
    public final void execute(Object param) throws Exception {
        try {
            preconditions(param);
            startTransaction();
            executeOperation(param);
            commitTransaction();
        } catch (Exception ex) {
            ex.printStackTrace();
            rollbackTransaction();
            throw ex;
        } finally {
            disconnect();
        }
    }
    
    protected abstract void preconditions(Object param) throws Exception;
    
    private void startTransaction()throws Exception{
        ((DbRepository) repository).connect();
    }
    
    protected abstract void executeOperation(Object param) throws Exception;

    private void commitTransaction() throws Exception {
        ((DbRepository) repository).commit();
    }

    private void rollbackTransaction() throws Exception {
        ((DbRepository) repository).rollback();
    }

    private void disconnect() throws Exception {
        ((DbRepository) repository).disconnect();
    }
}
