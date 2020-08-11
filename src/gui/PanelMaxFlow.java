package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.text.NumberFormat;
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
import javax.swing.SwingUtilities;

import controller.MaxFlowController;
import controller.StockFaltanteController;
import dominio.*;

public class PanelMaxFlow extends JPanel {
	private JLabel lblTitulo = new JLabel("Flujo maximo:");
	private JLabel lblFlow = new JLabel("Flujo maximo:");
	private JLabel lblBuscar = new JLabel("Filtros:");
	private JLabel lblPlantaOrigen = new JLabel("Planta origen:");
	private JLabel lblPlantaDestino = new JLabel("Planta destino:");
	private JComboBox<Planta> jcbPlantasOrigen; 
	private JComboBox<Planta> jcbPlantasDestino; 
	private JFormattedTextField txtFlujo = new JFormattedTextField(NumberFormat.getNumberInstance());
	
	
	private JButton btnGuardar;
	private JButton btnCancelar;
	
	
	private MaxFlowController controller;
	
	
	
	
	
	public PanelMaxFlow(){
		super();
		this.controller= new MaxFlowController(this);
		this.armarPanel();
	}
	
	private void armarPanel() {
		this.controller.setJcbs();
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
		this.add(lblPlantaOrigen,constraints);
		constraints.insets = new Insets(0, 0, 0, 0);
		constraints.gridx = 1;
		constraints.gridy = 5;		
		this.add(jcbPlantasOrigen,constraints);
		

		constraints.gridx = 2;
		constraints.gridy = 5;		
		this.add(lblPlantaDestino,constraints);

		constraints.gridx = 3;
		constraints.gridy = 5;		
//		constraints.anchor= constraints.WEST;
		this.add(jcbPlantasDestino,constraints);
		constraints.anchor= GridBagConstraints.CENTER;
		
		
		constraints.gridx = 4;
		constraints.gridy = 5;	
		
		this.btnGuardar = new JButton("Calcular");
		
		this.btnGuardar.addActionListener( e ->
			{
				try {
					this.controller.buscar();
//					modeloTablaStock.setData(controller.buscarPorAtributos());
//					this.limpiarFormulario();
//					modeloTablaStock.fireTableDataChanged();
				} catch (Exception e1) {
					this.mostrarError("Error al guardar", e1.getMessage());
				}
			}
		);
		
		this.add(btnGuardar,constraints);
		constraints.anchor= constraints.NORTH;
		constraints.gridy = 6;	
		constraints.gridx = 3;
		
		this.txtFlujo.setEditable(false);
		this.txtFlujo.setColumns(20);
		this.add(txtFlujo,constraints);
		this.txtFlujo.setValue(0f);
		

		constraints.gridx = 2;
		this.add(this.lblFlow, constraints);
	}
	
	
	
	public JComboBox<Planta> getJcbPlantasOrigen() {
		return jcbPlantasOrigen;
	}

	public void setJcbPlantasOrigen(JComboBox<Planta> jcbPlantasOrigen) {
		this.jcbPlantasOrigen = jcbPlantasOrigen;
	}

	public JComboBox<Planta> getJcbPlantasDestino() {
		return jcbPlantasDestino;
	}

	public void setJcbPlantasDestino(JComboBox<Planta> jcbPlantasDestino) {
		this.jcbPlantasDestino = jcbPlantasDestino;
	}

	public JFormattedTextField getTxtFlujo() {
		return txtFlujo;
	}

	public void setTxtFlujo(JFormattedTextField txtFlujo) {
		this.txtFlujo = txtFlujo;
	}

	public void mostrarError(String titulo,String detalle) {
		JFrame padre= (JFrame) SwingUtilities.getWindowAncestor(this);
		JOptionPane.showMessageDialog(padre,
			    detalle,titulo,
			    JOptionPane.ERROR_MESSAGE);
	}
}
