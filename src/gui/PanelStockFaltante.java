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
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import controller.StockFaltanteController;
import dominio.*;
import gui.util.*;

public class PanelStockFaltante extends JPanel {

	private JLabel lblTitulo = new JLabel("Stocks faltantes:");
	private JLabel lblBuscar = new JLabel("Filtros:");
	private JLabel lblPlanta = new JLabel("Planta:");
	private JLabel lblInsumo = new JLabel("Insumo:");
//	private JComboBox<Planta> jcbPlantas = new JComboBox<Planta>(); 
//	private JComboBox<Insumo> jcbInsumos = new JComboBox<Insumo>(); 
	private JComboBox<Planta> jcbPlantas;
	private JComboBox<Insumo> jcbInsumos;
	
	private JButton btnGuardar;
	private JButton btnCancelar;
	private JButton btnBuscarTodo;
	
	
	private JTable tablaStocks;
	private StockTableModel modeloTablaStock; 
	private StockFaltanteController controller;
	
	
	
	
	
	public PanelStockFaltante(){
		super();
		this.controller= new StockFaltanteController(this);
		this.armarPanel();
	}
	
	private void armarPanel() {
		this.setBackground(Color.GRAY);
		GridBagLayout gbl = new GridBagLayout();
		this.setLayout(gbl);
		GridBagConstraints constraints = new GridBagConstraints();
		
		this.controller.setJcbs();
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

		constraints.gridx = 0;
		constraints.gridy = 4;
		constraints.gridwidth = 11;
		constraints.gridheight=1;
		constraints.anchor= GridBagConstraints.WEST;
		lblBuscar.setFont(new Font("Calibri", Font.BOLD, 20));
		lblBuscar.setForeground(Color.BLUE);
		this.add(lblBuscar,constraints);
		
		
		constraints.insets = new Insets(0, 20, 0, 0);
		constraints.anchor= GridBagConstraints.CENTER;
		
		
		constraints.gridwidth = 1;

		constraints.gridx = 0;
		constraints.gridy = 5;		
		this.add(lblPlanta,constraints);
		constraints.insets = new Insets(0, 0, 0, 0);
		constraints.gridx = 1;
		constraints.gridy = 5;		
		this.add(jcbPlantas,constraints);
		

		constraints.gridx = 2;
		constraints.gridy = 5;		
		this.add(lblInsumo,constraints);

		constraints.gridx = 3;
		constraints.gridy = 5;		
		this.add(jcbInsumos,constraints);
		
		
		constraints.gridx = 3;
		constraints.gridy = 6;	
		
		this.btnGuardar = new JButton("Buscar");
		
		this.btnGuardar.addActionListener( e ->
			{
				try {
					this.controller.buscar();
//					modeloTablaStock.setData(controller.buscarPorAtributos());
//					this.limpiarFormulario();
//					modeloTablaStock.fireTableDataChanged();
					this.controller.setJcbs();
				} catch (Exception e1) {
					this.mostrarError("Error al guardar", e1.getMessage());
				}
			}
		);
		
		this.add(btnGuardar,constraints);
		constraints.weightx=0;
		/*
		constraints.gridx = 4;
		this.btnBuscarTodo = new JButton("Buscar todo");
		
		this.btnBuscarTodo.addActionListener( e ->
			{
				try {
					this.controller.buscarTodo();
//					modeloTablaStock.setData(controller.buscarPorAtributos());
//					this.limpiarFormulario();
//					modeloTablaStock.fireTableDataChanged();
				} catch (Exception e1) {
					this.mostrarError("Error al guardar", e1.getMessage());
				}
			}
		);
		
		this.add(btnBuscarTodo,constraints);
		constraints.weightx=0;
		*/
		
		constraints.insets = new Insets(10, 50, 10, 50);
		
		
		
		
		modeloTablaStock = new StockTableModel(new ArrayList<Stock>());
		tablaStocks = new JTable();
		tablaStocks.setModel(modeloTablaStock);
		JScrollPane scrollPane = new JScrollPane(tablaStocks);
		tablaStocks.setFillsViewportHeight(true);
		constraints.gridx = 0;
		constraints.gridy = 8;		
		constraints.gridwidth = 11;
		constraints.weighty=1;
		constraints.weightx=2;
		constraints.anchor = GridBagConstraints.NORTH;
		constraints.fill= GridBagConstraints.HORIZONTAL;
		this.add(scrollPane,constraints);
		
		
		
		
		this.controller.setJcbs();
		
		
	}
	
	
	
	public void mostrarError(String titulo,String detalle) {
		JFrame padre= (JFrame) SwingUtilities.getWindowAncestor(this);
		JOptionPane.showMessageDialog(padre,
			    detalle,titulo,
			    JOptionPane.ERROR_MESSAGE);
	}

	public JComboBox<Planta> getJcbPlantas() {
		return jcbPlantas;
	}

	public void setJcbPlantas(JComboBox<Planta> jcbPlantas) {
		this.jcbPlantas = jcbPlantas;
	}

	public JComboBox<Insumo> getJcbInsumos() {
		return jcbInsumos;
	}

	public void setJcbInsumos(JComboBox<Insumo> jcbInsumos) {
		this.jcbInsumos = jcbInsumos;
	}

	public JTable getTablaStocks() {
		return tablaStocks;
	}

	public void setTablaStocks(JTable tablaStocks) {
		this.tablaStocks = tablaStocks;
	}

	public StockTableModel getModeloTablaStock() {
		return modeloTablaStock;
	}

	public void setModeloTablaStock(StockTableModel modeloTablaStock) {
		this.modeloTablaStock = modeloTablaStock;
	}

	public StockFaltanteController getController() {
		return controller;
	}

	public void setController(StockFaltanteController controller) {
		this.controller = controller;
	}
	
	 
}
