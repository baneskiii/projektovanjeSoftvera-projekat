/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view.forme.komponenta.tabele;

import domen.StavkaRezervacije;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Bane
 */
public class StavkaRezervacijeTableModel extends AbstractTableModel {

    private final String[] columnNames = {"Redni broj", "Gost", "Soba"};
    private List<StavkaRezervacije> stavke;

    public StavkaRezervacijeTableModel(List<StavkaRezervacije> stavke) {
        this.stavke = stavke;
    }

    @Override
    public int getRowCount() {
        if (stavke == null) {
            return 0;
        }
        return stavke.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        StavkaRezervacije stavka = stavke.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return stavka.getRbStavke();
            case 1:
                return stavka.getGost().toString();
            case 2:
                return stavka.getSoba().toString();
            default:
                return "n/a";
        }
    }

    @Override
    public String getColumnName(int column) {
        if (column > columnNames.length) {
            return "n/a";
        }
        return columnNames[column];
    }

    public void dodajStavku(StavkaRezervacije stavka) {
        stavke.add(stavka);
        fireTableDataChanged();
    }
    
    public void obrisiStavku(int row){
        stavke.remove(row);
        fireTableDataChanged();
    }
    
    public StavkaRezervacije getStavkaAt(int row) {
        return stavke.get(row);
    }

    public void setStavke(List<StavkaRezervacije> stavke) {
        this.stavke = stavke;
        fireTableDataChanged();
    }

    public List<StavkaRezervacije> getStavke() {
        return stavke;
    }

    public void refresh() {
        fireTableDataChanged();
    }
    
}
