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
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import controller.CamionController;
import gui.util.ControllerException;
import gui.util.DatosObligatoriosException;
import gui.util.FormatoNumeroException;

public class PanelAltaCamiones extends JPanel{
	
	private JLabel lblCrear = new JLabel("Registrar/Modificar camion:");
	private JLabel lblPatente = new JLabel("Patente:");
	private JTextField txtPatente;
	private JLabel lblModelo = new JLabel("Modelo:");
	private JTextField txtModelo;
	private JLabel lblMarca = new JLabel("Marca:");
	private JTextField txtMarca;
	private JLabel lblCostoHora = new JLabel("Costo por hora:");
	private JFormattedTextField costoHora = new JFormattedTextField(NumberFormat.getNumberInstance());
	private JLabel lblCostoKm = new JLabel("Costo por Km:");
	private JFormattedTextField costoKm = new JFormattedTextField(NumberFormat.getNumberInstance());

	
	private JLabel lblFecha = new JLabel("Fecha:");
	private DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
	private JFormattedTextField txtFechaCompra = new JFormattedTextField(df);	
	
	private JLabel lblKm = new JLabel("KMs:");
	
	private JFormattedTextField txtKm = new JFormattedTextField(NumberFormat.getNumberInstance());
	
	
	private JButton btnGuardar;
	private JButton btnCancelar;
	private JTable tablaCamiones;
	private CamionController controller;
	
	public PanelAltaCamiones(){
		super();
		this.controller= new CamionController(this);
		this.armarPanel();
	}
	
	
	private void armarPanel() {
		this.setBackground(Color.GRAY);
		GridBagLayout gbl = new GridBagLayout();
		this.setLayout(gbl);
		
		
		GridBagConstraints constraints = new GridBagConstraints();
		

		constraints.insets = new Insets(0, 0, 10, 0);
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 11;
		constraints.gridheight=2;
		//constraints.anchor= GridBagConstraints.SOUTH;
		constraints.anchor= GridBagConstraints.NORTH;
		constraints.weighty=0;
		
		lblCrear.setFont(new Font("Calibri", Font.BOLD, 24));
		lblCrear.setForeground(Color.BLUE);
		this.add(lblCrear,constraints);
		
		/*
		constraints.insets = new Insets(10, 30, 0, 0);

		constraints.gridx = 0;
		constraints.gridy = 4;
		constraints.gridwidth = 11;
		constraints.gridheight=1;
		constraints.anchor= GridBagConstraints.WEST;
		lblCrear.setFont(new Font("Calibri", Font.BOLD, 20));
		lblCrear.setForeground(Color.BLUE);
		this.add(lblCrear,constraints);
		*/

		constraints.gridheight=1;
		constraints.insets = new Insets(0, 20, 0, 0);
		constraints.anchor= GridBagConstraints.CENTER;
		
		
		constraints.gridwidth = 1;

		constraints.gridx = 0;
		constraints.gridy = 2;		
		this.add(lblPatente,constraints);
		constraints.insets = new Insets(0, 0, 0, 0);
		constraints.gridx = 1;
		constraints.gridy = 2;		
		this.txtPatente = new JTextField(20);
		this.txtPatente.setPreferredSize(new Dimension(350,25));
		this.txtPatente.setMinimumSize(new Dimension(150,25));
		this.add(txtPatente,constraints);
		

		constraints.gridx = 2;
		constraints.gridy = 2;		
		this.add(lblModelo,constraints);

		constraints.gridx = 3;
		constraints.gridy = 2;		
		this.txtModelo = new JTextField(20);
		this.txtModelo.setMinimumSize(new Dimension(150,25));
		this.add(txtModelo,constraints);
		
		constraints.gridx = 4;
		constraints.gridy = 2;		
		this.add(lblMarca,constraints);
		constraints.gridx = 5;
		constraints.gridy = 2;
		this.txtMarca = new JTextField(20);
		this.txtMarca.setMinimumSize(new Dimension(150,25));
		this.add(txtMarca,constraints);
		

		constraints.gridx = 6;
		constraints.gridy = 2;		
		this.add(lblFecha,constraints);
		//this.txtFechaCompra = new JFormattedTextField(20);
		this.txtFechaCompra.setColumns(10);
		//this.txtFechaCompra.setMinimumSize(new Dimension(100,25));
		constraints.gridx = 7;
		constraints.gridy = 2;		
		this.add(txtFechaCompra,constraints);
		
		
		constraints.insets = new Insets(20, 20, 0, 0);
		
		constraints.gridx = 0;
		constraints.gridy = 4;	
		this.add(lblCostoHora,constraints);
		constraints.insets = new Insets(20, 0, 0, 0);
		
		constraints.gridx = 1;
		constraints.gridy = 4;
		this.costoHora.setColumns(10);
		this.add(costoHora,constraints);
		
		
		constraints.gridx = 2;
		constraints.gridy = 4;	
		this.add(lblCostoKm,constraints);
		this.costoKm.setColumns(10);
		constraints.gridx = 3;
		constraints.gridy = 4;	
		this.add(costoKm,constraints);
		
		constraints.gridx = 4;
		constraints.gridy = 4;		
		this.add(lblKm,constraints);
		constraints.gridx = 5;
		constraints.gridy = 4;		
		

		this.txtKm.setColumns(20);
		
		this.add(txtKm,constraints);
		
		constraints.gridx = 5;
		constraints.gridy = 5;	
		
		constraints.anchor=constraints.EAST;
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
		constraints.anchor=constraints.WEST;
		constraints.gridx = 7;
		constraints.gridy = 5;
		this.btnCancelar = new JButton("Cancelar");
		this.btnCancelar.addActionListener( e ->
		{
			this.limpiarFormulario();
		}
		
	);
		
		this.add(btnCancelar,constraints);
		this.limpiarFormulario();
	}
	
	private void limpiarFormulario() {
		this.txtPatente.setText("");
		this.txtModelo.setText("");
		this.txtMarca.setText("");
		this.txtKm.setValue(0f);
		this.costoHora.setValue(0f);
		this.costoKm.setValue(0f);
		this.txtFechaCompra.setValue(new Date());
	}
	
	public void mostrarError(String titulo,String detalle) {
		JFrame padre= (JFrame) SwingUtilities.getWindowAncestor(this);
		JOptionPane.showMessageDialog(padre,
			    detalle,titulo,
			    JOptionPane.ERROR_MESSAGE);
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


	public JFormattedTextField getCostoHora() {
		return costoHora;
	}


	public void setCostoHora(JFormattedTextField costoHora) {
		this.costoHora = costoHora;
	}


	public JFormattedTextField getCostoKm() {
		return costoKm;
	}


	public void setCostoKm(JFormattedTextField costoKm) {
		this.costoKm = costoKm;
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


	public JFormattedTextField getTxtKm() {
		return txtKm;
	}


	public void setTxtKm(JFormattedTextField txtKm) {
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


	public JTable getTablaCamiones() {
		return tablaCamiones;
	}


	public void setTablaCamiones(JTable tablaCamiones) {
		this.tablaCamiones = tablaCamiones;
	}


	public CamionController getController() {
		return controller;
	}


	public void setController(CamionController controller) {
		this.controller = controller;
	}

}
