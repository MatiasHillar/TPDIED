package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PanelStock extends JPanel {
	
	private String[] plantas = {"p1", "p2", "p3", "p4", "p5"};
	private String[] insumos = {"i1", "i2", "i3", "i4", "i5"};

	private JLabel lblTitulo = new JLabel("Stocks en Planta:");
	private JLabel lblPlanta = new JLabel("Seleccionar Planta:");
	private JComboBox<String> jcbPlantas = new JComboBox(plantas); //CAMBIAR STRING POR PLANTAS
	private JLabel lblInsumo = new JLabel("Seleccionar Insumo:");
	private JList<String> jlInsumos = new JList(insumos); //CAMBIAR STRING POR INSUMOS DE LA PLANTA ELEGIDA
	private JLabel lblCantidadInsumo = new JLabel("Cantidad:");
	private JTextField txtCantidadInsumo;
	private JLabel lblPuntoDePedido = new JLabel("Punto de pedido:");
	private JTextField txtPuntoDePedido;
	private JButton btnGuardar;
	//private RutasController controller;
	
	public PanelStock(){
		super();
		//this.controller= new RutasController(this);
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
		this.add(jlInsumos, constraints);
		
	}
	
}
