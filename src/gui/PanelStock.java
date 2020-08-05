package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.text.NumberFormat;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import controller.StockController;
import dominio.*;
import gui.util.ControllerException;
import gui.util.DatosObligatoriosException;
import gui.util.FormatoNumeroException;

public class PanelStock extends JPanel {
	
	
	
	//this.panel.setJcbPlantas(new JComboBox<Planta>(this.pedidoService.buscarPlantas().toArray(new Planta[0])));
	//this.panel.setJcbInsumos(new JComboBox<Insumo>( this.insumoService.buscarInsumos().toArray(new Insumo[0])));


	private JLabel lblTitulo = new JLabel("Stocks en Planta:");
	private JLabel lblPlanta = new JLabel("Seleccionar Planta:");
	private JComboBox<Planta> jcbPlantas = new JComboBox<Planta>(); 
	private JLabel lblInsumo = new JLabel("Seleccionar Insumo:");
	private JComboBox<Insumo> jcbInsumos = new JComboBox<Insumo>(); 
	private JLabel lblCantidadInsumo = new JLabel("Cantidad:");
	private JFormattedTextField txtCantidadInsumo = new JFormattedTextField(NumberFormat.getNumberInstance());
	private JLabel lblPuntoDePedido = new JLabel("Punto de pedido:");
	private JFormattedTextField txtPuntoPedido = new JFormattedTextField(NumberFormat.getNumberInstance());
	private JButton btnGuardar;
	private StockController controller;
	
	public PanelStock(){
		super();
		this.controller= new StockController(this);
		this.armarPanel();
	}
	
	private void armarPanel() {
		this.setBackground(Color.GRAY);
		GridBagLayout gbl = new GridBagLayout();
		this.setLayout(gbl);
	
		GridBagConstraints constraints = new GridBagConstraints();
		
		constraints.gridx = 0;
		constraints.gridy = 0;
//		constraints.weighty = 2.0;
		constraints.gridwidth = 6;
		constraints.insets = new Insets(0, 20, 0, 0);
		constraints.anchor = GridBagConstraints.WEST;
		lblTitulo.setFont(new Font("Calibri", Font.BOLD, 24));
		lblTitulo.setForeground(Color.BLUE.darker());
		this.add(lblTitulo,constraints);
		
		
		constraints.gridx = 0;
		constraints.gridy = 1;
//		constraints.weightx = 2.0;
		constraints.gridwidth = 1;
		lblPlanta.setFont(new Font("Calibri", Font.BOLD, 20));
		this.add(lblPlanta,constraints);
		
		constraints.gridx = 1;
		constraints.gridy = 1;
		constraints.gridwidth = 1;
		this.add(jcbPlantas, constraints);
		
		constraints.gridx = 2;
		constraints.gridy = 1;
		constraints.gridwidth = 1;
		lblInsumo.setFont(new Font("Calibri", Font.BOLD, 20));
		this.add(lblInsumo,constraints);
		
		
		constraints.gridx = 3;
		constraints.gridy = 1;
		constraints.gridwidth = 1;
		this.add(jcbInsumos, constraints);
		

		constraints.gridy = 2;
		constraints.gridx = 0;
		this.add(lblPuntoDePedido, constraints);
		
		constraints.gridx = 1;
		this.txtPuntoPedido.setColumns(20);
		this.add(txtPuntoPedido, constraints);
		
		constraints.gridx = 2;
		this.add(this.lblCantidadInsumo, constraints);
		
		
		constraints.gridx = 3;
		this.txtCantidadInsumo.setColumns(20);
		this.add(txtCantidadInsumo, constraints);
		

		constraints.gridy = 3;
		constraints.gridx = 3;
		this.btnGuardar = new JButton("Guardar");
		this.btnGuardar.addActionListener( e ->
		{
			try {
				controller.guardar();
			} catch (DatosObligatoriosException | FormatoNumeroException | ControllerException e1) {
				this.mostrarError("Error al guardar", e1.getMessage());
			}
			this.limpiarFormulario();
		}
	);
	
	this.add(btnGuardar,constraints);
		
		this.limpiarFormulario();
	}
	
	private void limpiarFormulario() {
		this.txtPuntoPedido.setValue(0f);
		this.txtCantidadInsumo.setValue(0f);
		this.controller.setJcbs();
		
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

	public JFormattedTextField getTxtCantidadInsumo() {
		return txtCantidadInsumo;
	}

	public void setTxtCantidadInsumo(JFormattedTextField txtCantidadInsumo) {
		this.txtCantidadInsumo = txtCantidadInsumo;
	}

	public JFormattedTextField getTxtPuntoPedido() {
		return txtPuntoPedido;
	}

	public void setTxtPuntoPedido(JFormattedTextField txtPuntoPedido) {
		this.txtPuntoPedido = txtPuntoPedido;
	}
	
	public void mostrarError(String titulo,String detalle) {
		JFrame padre= (JFrame) SwingUtilities.getWindowAncestor(this);
		JOptionPane.showMessageDialog(padre,
			    detalle,titulo,
			    JOptionPane.ERROR_MESSAGE);
	}
	
}
