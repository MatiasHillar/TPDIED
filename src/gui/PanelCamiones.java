package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.*;

import controller.*;
import gui.util.*;


public class PanelCamiones extends JPanel{
	
	private JLabel lblTitulo = new JLabel("Administracion de camiones:");
	private JLabel lblCrear = new JLabel("Alta de camiones:");
	private JLabel lblPatente = new JLabel("Patente:");
	private JTextField txtPatente;
	private JLabel lblModelo = new JLabel("Modelo:");
	private JTextField txtModelo;
	private JLabel lblMarca = new JLabel("Marca:");
	private JTextField txtMarca;
	private JLabel lblFecha = new JLabel("Fecha:");
	private DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
	private JFormattedTextField txtFechaCompra = new JFormattedTextField(df);	
	private JLabel lblKm = new JLabel("KMs:");
	private JTextField txtKm;
	private JButton btnGuardar;
	private JButton btnCancelar;
	private JTable tablaCamiones;
	private CamionTableModel modeloTablaCamion; 
	private CamionController controller;
	
	public PanelCamiones(){
		super();
		this.controller= new CamionController(this);
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
		lblCrear.setFont(new Font("Calibri", Font.BOLD, 20));
		lblCrear.setForeground(Color.BLUE);
		this.add(lblCrear,constraints);
		
		
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
		this.add(lblFecha,constraints);
		this.txtFechaCompra = new JFormattedTextField(20);
		this.txtFechaCompra.setMinimumSize(new Dimension(100,25));
		constraints.gridx = 7;
		constraints.gridy = 5;		
		this.add(txtFechaCompra,constraints);
		constraints.gridx = 8;
		constraints.gridy = 5;		
		this.add(lblKm,constraints);
		constraints.gridx = 9;
		constraints.gridy = 5;		
		this.txtKm = new JTextField(20);
		this.txtKm.setMinimumSize(new Dimension(100,25));		
		this.add(txtKm,constraints);		
		constraints.gridx = 8;
		constraints.gridy = 6;	
		
		this.btnGuardar = new JButton("Guardar");
		
		this.btnGuardar.addActionListener( e ->
			{
				try {
					controller.guardar();
				} catch (DatosObligatoriosException | FormatoNumeroException | ControllerException e1) {
					this.mostrarError("Error al guardar", e1.getMessage());
				}
				this.limpiarFormulario();
				modeloTablaCamion.fireTableDataChanged();
			}
		);
		
		this.add(btnGuardar,constraints);
		constraints.gridx = 9;
		constraints.gridy = 6;
		this.btnCancelar = new JButton("Cancelar");
		this.add(btnCancelar,constraints);
		constraints.weightx=0;
		
		
		constraints.insets = new Insets(10, 50, 10, 50);
		
		
		
		
		modeloTablaCamion = new CamionTableModel(controller.listarTodos());
		tablaCamiones = new JTable();
		tablaCamiones.setModel(modeloTablaCamion);
		JScrollPane scrollPane = new JScrollPane(tablaCamiones);
		tablaCamiones.setFillsViewportHeight(true);
		constraints.gridx = 0;
		constraints.gridy = 7;		
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
		this.txtKm.setText("");
		this.txtFechaCompra.setText(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
	}

	public JTextField getTxtPatente() {
		return txtPatente;
	}

	public void setTxtPatente(JTextField txtPatente) {
		this.txtPatente = txtPatente;
	}

	public JTextField getTxtModelo() {
		return txtModelo;
	}

	public void setTxtModelo(JTextField txtModelo) {
		this.txtModelo = txtModelo;
	}

	public JTextField getTxtMarca() {
		return txtMarca;
	}

	public void setTxtMarca(JTextField txtMarca) {
		this.txtMarca = txtMarca;
	}

	public DateFormat getDf() {
		return df;
	}

	public void setDf(DateFormat df) {
		this.df = df;
	}

	public JFormattedTextField getTxtFechaCompra() {
		return txtFechaCompra;
	}

	public void setTxtFechaCompra(JFormattedTextField txtFechaCompra) {
		this.txtFechaCompra = txtFechaCompra;
	}

	public JTextField getTxtKm() {
		return txtKm;
	}

	public void setTxtKm(JTextField txtKm) {
		this.txtKm = txtKm;
	}

	public JButton getBtnGuardar() {
		return btnGuardar;
	}

	public void setBtnGuardar(JButton btnGuardar) {
		this.btnGuardar = btnGuardar;
	}

	public JButton getBtnCancelar() {
		return btnCancelar;
	}

	public void setBtnCancelar(JButton btnCancelar) {
		this.btnCancelar = btnCancelar;
	}

	public CamionController getController() {
		return controller;
	}

	public void setController(CamionController controller) {
		this.controller = controller;
	}


	public void mostrarError(String titulo,String detalle) {
		JFrame padre= (JFrame) SwingUtilities.getWindowAncestor(this);
		JOptionPane.showMessageDialog(padre,
			    detalle,titulo,
			    JOptionPane.ERROR_MESSAGE);
	}

}
