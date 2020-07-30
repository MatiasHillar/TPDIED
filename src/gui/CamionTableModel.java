package gui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import dominio.Camion;
import dominio.Modelo;



public class CamionTableModel extends AbstractTableModel {
	//https://docs.oracle.com/javase/tutorial/uiswing/components/table.html#data
	
	public CamionTableModel(List<Camion> camiones) {
		if(camiones!=null)
		this.data = camiones;
		else this.data= new ArrayList<Camion>();
	}
	/*
private Float costoKm;
private Float costoHora;
private LocalDate fechaCompra;
	 */
	
	
    private String[] columnNames =  {"Patente","Marca","Modelo","KM Recorridos", "Fecha de compra", "Costo por hora", "Costo por Km"};
    private List<Camion> data ;

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
        Camion cam = data.get(row);
        switch(col) {
	        case 0:
	        	if(cam.getPatente()!=null)return cam.getPatente();
	        	else return "Null";
	        case 1:
	        	if(cam.getModelo().getMarca()!=null)return cam.getModelo().getMarca();
	        	else return "Null";
	        case 2:
	        	if(cam.getModelo().getModelo()!=null)return cam.getModelo().getModelo();
	        	else return "Null";
	        case 3:
	        	if(cam.getKmRecorridos()!=null)return cam.getKmRecorridos();
	        	else return "Null";
	        case 4:
	        	if(cam.getFechaCompra()!=null) return cam.getFechaCompra().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
	        	else return "Null";
	        case 5:
	        	if(cam.getCostoHora()!=null)return cam.getCostoHora();
	        	else return "Null";
	        case 6:
	        	if(cam.getCostoKm()!=null)return cam.getCostoKm();
	        	else return "Null";
        }
        return null;
    }

    
    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }
    

    /*
     * Don't need to implement this method unless your table's
     * editable.
     */
    public boolean isCellEditable(int row, int col) {
        //Note that the data/cell address is constant,
        //no matter where the cell appears onscreen.
//        if (col < 2) {
//            return false;
//        } else {
//            return true;
//        }
    	return false;
    }

//    /*
//     * Don't need to implement this method unless your table's
//     * data can change.
//     */
//    public void setValueAt(Object value, int row, int col) {
//    	Camion c = data.get(row);
//        switch(col) {
//	        case 0:
//	        	return cam.getId();
//	        case 1:
//	        	return cam.getPatente(); 
//	        case 2:
//	        	return cam.getMarca(); 
//	        case 3:
//	        	return cam.getModelo(); 
//	        case 4:
//	        	return cam.getKm(); 
//        }
//    	c.setCostoHora(value);
//        // data[row][col] = value;
//        // fireTableCellUpdated(row, col);
//    }

}
