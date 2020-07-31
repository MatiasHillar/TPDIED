package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import controller.InsumoController;
import dominio.*;
import gui.util.*;

public class PanelGestionarInsumos extends JPanel{
	
	
	private JLabel lblTitulo = new JLabel("Añadir/modificar insumo general:");
	private JButton btnModificar;
	private JButton btnEliminar;
	
	private JTable tablaInsumos;
	private InsumoTableModel modeloTablaInsumo; 
	private InsumoController controller;
	
	
	
	
	
	public PanelGestionarInsumos(){
		super();
		this.controller= new InsumoController();
		this.armarPanel();
	}
	
	private void armarPanel() {
		this.setBackground(Color.GRAY);
		GridBagLayout gbl = new GridBagLayout();
		this.setLayout(gbl);
		
		
		GridBagConstraints constraints = new GridBagConstraints();
		

		constraints.insets = new Insets(30, 0, 10, 0);
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 11;
		constraints.gridheight=2;
		constraints.anchor= GridBagConstraints.SOUTH;
		lblTitulo.setFont(new Font("Calibri", Font.BOLD, 24));
		lblTitulo.setForeground(Color.BLUE);
		this.add(lblTitulo,constraints);
		
		constraints.insets = new Insets(10, 30, 0, 0);

		
		
		modeloTablaInsumo = new InsumoTableModel(new ArrayList<Insumo>());
		tablaInsumos = new JTable();
		tablaInsumos.setModel(modeloTablaInsumo);
		JScrollPane scrollPane = new JScrollPane(tablaInsumos);
		tablaInsumos.setFillsViewportHeight(true);
		constraints.gridx = 0;
		constraints.gridy = 2;		
		constraints.gridwidth = 11;
		constraints.weighty=1;
		constraints.weightx=2;
		constraints.anchor = GridBagConstraints.NORTH;
		constraints.fill= GridBagConstraints.HORIZONTAL;
		this.add(scrollPane,constraints);
		
		/*
		constraints.insets = new Insets(10, 50, 10, 50);
		constraints.gridx = 0;
		constraints.gridy = 10;		
		constraints.gridwidth = 11;
		constraints.weighty=1;
		constraints.weightx=2;
		constraints.anchor = GridBagConstraints.NORTH;
		constraints.fill= GridBagConstraints.HORIZONTAL;
		PanelInsumosGenerales pa = new PanelInsumosGenerales();
		this.add(pa,constraints);
		*/
		
		
		this.btnModificar = new JButton("Modificar");
		
		this.btnModificar.addActionListener( e ->
			{
				try {
					if(tablaInsumos.getSelectedRowCount()==0)
						throw new Exception("Debe seleccionar un insumo");
					if(tablaInsumos.getSelectedRowCount()>1)
						throw new Exception("Debe seleccionar solamente un insumo");
					if(modeloTablaInsumo.getData().get(tablaInsumos.getSelectedRow()) instanceof InsumoGral) {
						constraints.insets = new Insets(10, 50, 10, 50);
						constraints.gridx = 0;
						constraints.gridy = 4;		
						constraints.gridwidth = 11;
						constraints.weighty=1;
						constraints.weightx=2;
						constraints.anchor = GridBagConstraints.NORTH;
						constraints.fill= GridBagConstraints.HORIZONTAL;
						PanelInsumosGenerales pa = new PanelInsumosGenerales();
						controller.prepararM(pa, (InsumoGral)modeloTablaInsumo.getData().get(tablaInsumos.getSelectedRow()));
						this.add(pa,constraints);
					}
					else {
						constraints.insets = new Insets(10, 50, 10, 50);
						constraints.gridx = 0;
						constraints.gridy = 4;		
						constraints.gridwidth = 11;
						constraints.weighty=1;
						constraints.weightx=2;
						constraints.anchor = GridBagConstraints.NORTH;
						constraints.fill= GridBagConstraints.HORIZONTAL;
						PanelInsumosLiquidos pa = new PanelInsumosLiquidos();
						controller.prepararM(pa, (InsumoLiquido)modeloTablaInsumo.getData().get(tablaInsumos.getSelectedRow()));
						this.add(pa,constraints);			
					}
						
					
					
//					modeloTablaInsumo.setData(controller.buscarPorAtributos());
//					modeloTablaInsumo.fireTableDataChanged();
//				} catch (FormatoNumeroException | ControllerException e1) {
				} catch (Exception e1) {
					this.mostrarError("Error al modificar", e1.getMessage());
					e1.printStackTrace();
				}
			}
		);

		constraints.insets = new Insets(10, 50, 0, 0);
		constraints.gridx = 8;
		constraints.gridy = 3;		
		constraints.gridwidth = 1;
		constraints.weighty=0;
		constraints.weightx=0;
		constraints.anchor = GridBagConstraints.EAST;
		constraints.fill= GridBagConstraints.NONE;
		this.add(btnModificar,constraints);
		
		constraints.insets = new Insets(10, 50, 0, 0);
		constraints.gridx = 9;
		constraints.gridy = 3;		
		constraints.gridwidth = 1;
		constraints.weighty=0;
		constraints.weightx=0;
		constraints.anchor = GridBagConstraints.EAST;
		constraints.fill= GridBagConstraints.NONE;
		this.btnEliminar = new JButton("Eliminar");
		
		this.btnEliminar.addActionListener( e ->
			{
				try {
					if(tablaInsumos.getSelectedRowCount()==0)
						throw new Exception("Debe seleccionar un insumo");
					int n=1;
					if(tablaInsumos.getSelectedRowCount()==1)
						n = JOptionPane.showConfirmDialog(this, "¿Esta seguro de que desea eliminar 1 insumo?", "Eliminar", JOptionPane.YES_NO_OPTION);
					else
					n = JOptionPane.showConfirmDialog(this, "¿Esta seguro de que desea eliminar los " + tablaInsumos.getSelectedRowCount() + " camiones?", "Eliminar", JOptionPane.YES_NO_OPTION);
					if(n==0)
						controller.eliminar(modeloTablaInsumo.getData().get(tablaInsumos.getSelectedRow()));
					modeloTablaInsumo.setData(controller.listarTodos());
					modeloTablaInsumo.fireTableDataChanged();
//				} catch (FormatoNumeroException | ControllerException e1) {
				} catch (Exception e1) {
					this.mostrarError("Error al eliminar", e1.getMessage());
					e1.printStackTrace();
				}
			}
		);

		this.add(btnEliminar,constraints);
		
		
		
	}
	
	
	

	public void mostrarError(String titulo,String detalle) {
		JFrame padre= (JFrame) SwingUtilities.getWindowAncestor(this);
		JOptionPane.showMessageDialog(padre,
			    detalle,titulo,
			    JOptionPane.ERROR_MESSAGE);
	}
	 

}
