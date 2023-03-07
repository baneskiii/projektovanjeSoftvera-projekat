/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view.forme.komponenta.tabele;

import domen.Rezervacija;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Bane
 */
public class RezervacijaTableModel extends AbstractTableModel {

    private final String[] columnNames = {"ID", "Datum od", "Datum do", "Ugovarač"};
    private List<Rezervacija> rezervacije;
    SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");

    public RezervacijaTableModel(List<Rezervacija> rezervacije) {
        this.rezervacije = rezervacije;
    }

    @Override
    public int getRowCount() {
        if (rezervacije == null) {
            return 0;
        }
        return rezervacije.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Rezervacija rezervacija = rezervacije.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return rezervacija.getRezervacijaID();
            case 1:
                return sdf.format(rezervacija.getDatumOd());
            case 2:
                return sdf.format(rezervacija.getDatumDo());
            case 3:
                return rezervacija.getGost().toString();
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

    public void dodajRezervaciju(Rezervacija rezervacija) {
        rezervacije.add(rezervacija);
        fireTableDataChanged();
    }

    public Rezervacija getRezervacijaAt(int row) {
        return rezervacije.get(row);
    }

    public void setRezervacije(List<Rezervacija> rezervacije) {
        this.rezervacije = rezervacije;
        fireTableDataChanged();
    }

}
