package gui;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import dominio.*;

public class PageRankTableModel extends AbstractTableModel {

	public PageRankTableModel(List<Planta> plantas,HashMap<Planta,Double> pageRank) {
		if(plantas!=null)
		this.data = plantas;
		else this.data= new ArrayList<Planta>();
		this.pageRank=pageRank;
		this.data.sort((p1,p2)-> this.pageRank.get(p2).compareTo(this.pageRank.get(p1)));
		this.fireTableDataChanged();
	}
	
	
    private String[] columnNames =  {"Planta","PageRank"};
    private List<Planta> data ;
    private HashMap<Planta,Double> pageRank;

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
        Planta p = data.get(row);
        switch(col) {
	        case 0:
	        	if(p.getNombre()!=null)
	        		return p.getNombre();
	        	else return "Null";
	        case 1:
	        	if(p!=null)
	        		return pageRank.get(p);
	        	else return "Null";
        }
        return null;
    }

    
    public List<Planta> getData() {
		return data;
	}

	public void setData(List<Planta> data) {
		this.data = data;
	}

	public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }
    

    public boolean isCellEditable(int row, int col) {
    	return false;
    }

}
