/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tableModel;

import domain.Angazovanje;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Anđela
 */
public class TableModelAngazovanja extends AbstractTableModel {
    String[] kolone = {"Ime","Prezime","Predmet","Kod predmeta"};
    private List<Angazovanje> listaAngazovanja;
    public TableModelAngazovanja(){
        
    }
    public TableModelAngazovanja(List<Angazovanje> listaAngazovanja){
        this.listaAngazovanja = listaAngazovanja;
    }
    @Override
    public int getRowCount() {
        return listaAngazovanja.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Angazovanje a = listaAngazovanja.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return a.getProfesor().getIme();
            case 1:
                return a.getProfesor().getPrezime();
            case 2:
                return a.getPredmet().getNaziv();
            case 3:
                return a.getPredmet().getKod();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    public List<Angazovanje> getListaAngazovanja() {
        return listaAngazovanja;
    }

    public void setListaAngazovanja(List<Angazovanje> listaAngazovanja) {
        this.listaAngazovanja = listaAngazovanja;
    }
    
    
    
}
