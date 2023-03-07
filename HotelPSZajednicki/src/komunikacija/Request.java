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
public class Request implements Serializable{
    private Operation operacija;
    private Object argument;

    public Request() {
    }

    public Request(Operation operacija, Object argument) {
        this.operacija = operacija;
        this.argument = argument;
    }

    public Object getArgument() {
        return argument;
    }

    public void setArgument(Object argument) {
        this.argument = argument;
    }

    public Operation getOperacija() {
        return operacija;
    }

    public void setOperacija(Operation operacija) {
        this.operacija = operacija;
    }
}
