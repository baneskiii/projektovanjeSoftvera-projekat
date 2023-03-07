/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view.forme.komponenta.tabele;

import domen.Gost;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Bane
 */
public class GostTableModel extends AbstractTableModel{
    private final String[] columnNames = {"ID", "Ime", "Prezime", "Datum rodjenja", "Grad"};
    private List<Gost> gosti;
    SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");

    public GostTableModel(List<Gost> gosti) {
        this.gosti = gosti;
    }
    @Override
    public int getRowCount() {
        if(gosti == null) return 0;
        return gosti.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Gost gost = gosti.get(rowIndex);
        switch(columnIndex){
            case 0: return gost.getGostID();
            case 1: return gost.getIme();
            case 2: return gost.getPrezime();
            case 3: return sdf.format(gost.getDatumRodjenja());
            case 4: return gost.getGrad().toString();
            default: return "n/a";
        }
    }

    @Override
    public String getColumnName(int column) {
        if(column > columnNames.length) return "n/a";
        return columnNames[column];
    }
    
    public void dodajGosta(Gost gost){
        gosti.add(gost);
        fireTableDataChanged();
    }
    
    public Gost getGostAt(int row){
        return gosti.get(row);
    }

    public void setGosti(List<Gost> gosti) {
        this.gosti = gosti;
        fireTableDataChanged();
    }
    
}
