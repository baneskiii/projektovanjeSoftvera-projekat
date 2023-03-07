/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view.forme.komponenta.tabele;

import domen.Soba;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Bane
 */
public class SobaTableModel extends AbstractTableModel {

    private final String[] columnNames = {"ID", "Sprat", "Status", "Vrsta sobe"};
    private List<Soba> sobe;

    public SobaTableModel(List<Soba> sobe) {
        this.sobe = sobe;
    }

    @Override
    public int getRowCount() {
        if (sobe == null) {
            return 0;
        }
        return sobe.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Soba soba = sobe.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return soba.getSobaID();
            case 1:
                return soba.getSprat();
            case 2:
                if(soba.isStatus()) return "Zauzeta";
                return "Slobodna";
            case 3:
                return soba.getVrstaSobe().toString();
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

    public void dodajSobu(Soba soba) {
        sobe.add(soba);
        fireTableDataChanged();
    }

    public Soba getSobaAt(int row) {
        return sobe.get(row);
    }

    public void setSobe(List<Soba> sobe) {
        this.sobe = sobe;
        fireTableDataChanged();
    }

}
