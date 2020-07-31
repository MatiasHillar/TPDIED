package gui;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import dominio.*;

public class InsumoTableModel extends AbstractTableModel {

	public InsumoTableModel(List<Insumo> insumos) {
		if(insumos!=null)
		this.data = insumos;
		else this.data= new ArrayList<Insumo>();
	}
	
    private String[] columnNames =  {"Nombre","Unidad de medida", "Peso por unidad", "Costo por unidad", "Cantidad total de stock", "Tipo","Densidad"};
    private List<Insumo> data ;

    public int getColumnCount() {
        return columnNames.length;
    }

    public int getRowCount() {
        return data.size();
    }

    public String getColumnName(int col) {
        return columnNames[col];
    }

    public Object getValueAt(int row, int col) {
        Insumo i = data.get(row);
        switch(col) {
	        case 0:
	        	if(i.getNombre()!=null)
	        		return i.getNombre();
	        	else return "Null";
	        case 1:
	        	if(i.getUnidadMedida()!=null)
	        		return i.getUnidadMedida();
	        	else return "Null";
	        case 2:
	        	if(i.pesoPorUnidad()!=null)
	        		return i.pesoPorUnidad();
	        	else return "Null";
	        case 3:
	        	if(i.getCosto()!=null)
	        		return i.getCosto();
	        	else return "Null";
	        case 4:
	        	if(i.getCantidadTotal()!=null) 
	        		return i.getCantidadTotal();
	        	else return "Null";
	        case 5:
	        	if(i instanceof InsumoGral)
	        		return "Insumo general";
	        	else return "Insumo liquido";
	        case 6:
	        	if(i instanceof InsumoLiquido)
	        		return ((InsumoLiquido)i).getDensidad();
	        	else return "Null";
        }
        return null;
    }

    
    public List<Insumo> getData() {
		return data;
	}

	public void setData(List<Insumo> data) {
		this.data = data;
	}

	public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }
    

    public boolean isCellEditable(int row, int col) {
        
    	return false;
    }

}
