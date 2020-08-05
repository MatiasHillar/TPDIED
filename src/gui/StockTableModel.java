package gui;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import dominio.*;

public class StockTableModel extends AbstractTableModel {

	public StockTableModel(List<Stock> stocks) {
		if(stocks!=null)
		this.data = stocks;
		else this.data= new ArrayList<Stock>();
	}
	
    private String[] columnNames =  {"Planta","Insumo","Cantidad","Punto de pedido", "Stock total"};
    private List<Stock> data ;

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
        Stock stock = data.get(row);
        switch(col) {
	        case 0:
	        	if(stock.getP()!=null)
	        		return stock.getP().toString();
	        	else return "Null";
	        case 1:
	        	if(stock.getInsumo()!=null)
	        		return stock.getInsumo().getNombre();
	        	else return "Null";
	        case 2:
	        	if(stock.getCtidad()!=null)
	        		return stock.getCtidad();
	        	else return "Null";
	        case 3:
	        	if(stock.getPuntoRepo()!=null)
	        		return stock.getPuntoRepo();
	        	else return "Null";
	        case 4:
	        	if(stock.getInsumo()!=null)
	        		return stock.getInsumo().getCantidadTotal();
	        	else return "Null";
        }
        return null;
    }

    
    public List<Stock> getData() {
		return data;
	}

	public void setData(List<Stock> data) {
		this.data = data;
	}

	public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }
    public boolean isCellEditable(int row, int col) {
    	return false;
    }

	 

}
