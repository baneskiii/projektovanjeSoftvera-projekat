/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package komunikacija;

import java.io.Serializable;

/**
 *
 * @author Bane
 */
public class Response implements Serializable {

    private Operation operacija;
    private Object rezultat;
    private Exception exception;
    private boolean uspesno;
    private String poruka;

    public Response() {
    }

    public Response(Operation operacija, Object rezultat, Exception exception, boolean uspesno, String poruka) {
        this.operacija = operacija;
        this.rezultat = rezultat;
        this.exception = exception;
        this.uspesno = uspesno;
        this.poruka = poruka;
    }

    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }

    public Object getRezultat() {
        return rezultat;
    }

    public void setRezultat(Object rezultat) {
        this.rezultat = rezultat;
    }

    public Operation getOperacija() {
        return operacija;
    }

    public void setOperacija(Operation operacija) {
        this.operacija = operacija;
    }

    public boolean isUspesno() {
        return uspesno;
    }

    public void setUspesno(boolean uspesno) {
        this.uspesno = uspesno;
    }

    public String getPoruka() {
        return poruka;
    }

    public void setPoruka(String poruka) {
        this.poruka = poruka;
    }

}
