package gui;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import dominio.*;

public class ItemPedidoTableModel extends AbstractTableModel {
	
	private String[] columnNames =  {"Insumo","Cantidad", "Precio"};
    private List<ItemPedido> data;
	
	public ItemPedidoTableModel(List<ItemPedido> ips) {
		if(ips!=null)
			this.data=ips;
		else this.data= new ArrayList<ItemPedido>();
	}
	
	public int getColumnCount() {
	        return columnNames.length;
	}

	public int getRowCount() {
	        return data.size();
	}
	public String getColumnName(int col) {
	        return columnNames[col];
	}
	
	public List<ItemPedido> getData() {
		return data;
	}

	public void setData(List<ItemPedido> data) {
		this.data = data;
	}

	public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }
    

    public boolean isCellEditable(int row, int col) {
    	return false;
    }
    
    public Object getValueAt(int row, int col) {
        ItemPedido i = data.get(row);
        switch(col) {
	        case 0:
	        	if(i.getInsumo().getNombre()!=null)
	        		return i.getInsumo().getNombre();
	        	else return "Null";
	        case 1:
	        	if(i.getCtidad()!=null)
	        		return i.getCtidad() + "(" + i.getInsumo().getUnidadMedida().getSimbolo() + ")";
	        	else return "Null";
	        case 2:
	        	return i.getPrecio();
        }
        return null;
    }
    
	
	/*
	 * private Insumo insumo;
	private Float ctidad;
	private Pedido pedido;
	 
   

   

    
    
	 */

}
