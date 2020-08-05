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

	/*
	 * Agregar una pantalla que me permita visualizar en una tabla, la información
	de todas las plantas que tienen algún insumo con stock menor al punto de
	pedido. Esta tabla debe poder filtrarse por planta, y por producto y debe
	mostrar la siguiente información:
		• Nombre de la planta
		• Nombre del Insumo
		• Stock del insumo en la planta
		• Punto de pedido del insumo en la planta
		• Stock total del producto en toda la empresa
	 * 
	 * 
	 * */
	private JLabel lblTitulo = new JLabel("Stocks faltantes:");
	private JLabel lblBuscar = new JLabel("Filtros:");
	private JLabel lblPlanta = new JLabel("Planta:");
	private JLabel lblInsumo = new JLabel("Insumo:");
	private JComboBox<Planta> jcbPlantas = new JComboBox<Planta>(); 
	private JComboBox<Insumo> jcbInsumos = new JComboBox<Insumo>(); 

	
	
	private JButton btnGuardar;
	private JButton btnCancelar;
	
	
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
		this.add(lblPatente,constraints);
		constraints.insets = new Insets(0, 0, 0, 0);
		constraints.gridx = 1;
		constraints.gridy = 5;		
		this.txtPatente = new JTextField(20);
		this.txtPatente.setPreferredSize(new Dimension(350,25));
		this.txtPatente.setMinimumSize(new Dimension(150,25));
		this.add(txtPatente,constraints);
		

		constraints.gridx = 2;
		constraints.gridy = 5;		
		this.add(lblModelo,constraints);

		constraints.gridx = 3;
		constraints.gridy = 5;		
		this.txtModelo = new JTextField(20);
		this.txtModelo.setMinimumSize(new Dimension(150,25));
		this.add(txtModelo,constraints);
		
		constraints.gridx = 4;
		constraints.gridy = 5;		
		this.add(lblMarca,constraints);
		constraints.gridx = 5;
		constraints.gridy = 5;
		this.txtMarca = new JTextField(20);
		this.txtMarca.setMinimumSize(new Dimension(150,25));
		this.add(txtMarca,constraints);
		

		
		
		constraints.gridx = 6;
		constraints.gridy = 5;	
		this.add(lblKmMax,constraints);
		constraints.gridx = 7;
		constraints.gridy = 5;
		this.txtKmMax.setColumns(10);
		this.add(txtKmMax,constraints);
		
		
		
		constraints.gridx = 8;
		constraints.gridy = 5;		
		this.add(lblKm,constraints);
		constraints.gridx = 9;
		constraints.gridy = 5;		
		

		this.txtKm.setColumns(10);
		
		this.add(txtKm,constraints);
		
		constraints.insets = new Insets(0, 20, 0, 0);
		
		constraints.gridx = 0;
		constraints.gridy = 6;	
		this.add(lblCostoHora,constraints);
		constraints.insets = new Insets(0, 0, 0, 0);
		
		constraints.gridx = 1;
		constraints.gridy = 6;
		this.costoHora.setColumns(10);
		this.add(costoHora,constraints);
		
		
		constraints.gridx = 2;
		constraints.gridy = 6;	
		this.add(lblCostoHoraMax,constraints);
		constraints.gridx = 3;
		constraints.gridy = 6;	
		this.costoHoraMax.setColumns(10);
		this.add(costoHoraMax,constraints);
		
		

		constraints.gridx = 4;
		constraints.gridy = 6;	
		this.add(lblCostoKm,constraints);
		constraints.gridx = 5;
		constraints.gridy = 6;	
		this.costoKm.setColumns(10);
		this.add(costoKm,constraints);
		constraints.gridx = 6;
		constraints.gridy = 6;	
		this.add(lblCostoKmMax,constraints);
		constraints.gridx = 7;
		constraints.gridy = 6;			
		this.costoKmMax.setColumns(10);
		this.add(costoKmMax,constraints);
		
		constraints.gridx = 8;
		constraints.gridy = 7;	
		
		this.btnGuardar = new JButton("Buscar");
		
		this.btnGuardar.addActionListener( e ->
			{
				try {
					modeloTablaStock.setData(controller.buscarPorAtributos());
					this.limpiarFormulario();
					modeloTablaStock.fireTableDataChanged();
				} catch (Exception e1) {
					this.mostrarError("Error al guardar", e1.getMessage());
				}
			}
		);
		
		this.add(btnGuardar,constraints);
		constraints.gridx = 9;
		constraints.gridy = 7;
		this.btnCancelar = new JButton("Cancelar");
		this.btnCancelar.addActionListener( e ->
		{
			this.limpiarFormulario();
		}
	);
		this.add(btnCancelar,constraints);
		constraints.weightx=0;
		
		
		constraints.insets = new Insets(10, 50, 10, 50);
		
		
		
		
		modeloTablaStock = new StockTableModel(new ArrayList<Stock>());
		tablaStocks = new JTable();
		tablaStocks.setModel(modeloTablaStock);
		JScrollPane scrollPane = new JScrollPane(tablaStocks);
		tablaCamiones.setFillsViewportHeight(true);
		constraints.gridx = 0;
		constraints.gridy = 8;		
		constraints.gridwidth = 11;
		constraints.weighty=1;
		constraints.weightx=2;
		constraints.anchor = GridBagConstraints.NORTH;
		constraints.fill= GridBagConstraints.HORIZONTAL;
		this.add(scrollPane,constraints);
		
		
		
		
		this.limpiarFormulario();
		
	}
	
	private void limpiarFormulario() {
	
		this.txtPatente.setText("");
		this.txtModelo.setText("");
		this.txtMarca.setText("");
		this.txtKm.setValue(0f);
		this.costoHora.setValue(0f);
		this.costoKm.setValue(0f);
		this.txtKmMax.setValue(340282346638f);
		this.costoHoraMax.setValue(340282346638f);
		this.costoKmMax.setValue(340282346638f);
		
//		this.txtFechaCompra.setValue(new Date());
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
