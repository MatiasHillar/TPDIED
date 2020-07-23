package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;


public class PanelRutas extends JPanel{
	private String[] letras = {"a", "e", "i", "o", "u"};

	private JLabel lblTitulo = new JLabel("Añadir Ruta:");
	private JLabel lblPlantaOrigen = new JLabel("Planta Origen:");
	private JLabel lblPlantaDestino = new JLabel("Planta Destino:");
	private JComboBox<String> jcbPlantaOrigen = new JComboBox(letras); //CAMBIAR STRING POR RUTA 
	private JComboBox<String> jcbPlantaDestino = new JComboBox(letras); //CAMBIAR STRING POR RUTA 
	private JLabel lblDuracionHs = new JLabel("Duracion (Hs):");
	private JLabel lblDistanciaKm = new JLabel("Distancia (Km):");
	private JLabel lblPesoMaximo = new JLabel("Peso maximo (Kg):");
	private JTextField txtDuracionHs;
	private JTextField txtDistanciaKm;
	private JTextField txtPesoMaximo;
	private JButton btnGuardar;
	//private RutasController controller;
	
	public PanelRutas(){
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
		lblPlantaOrigen.setFont(new Font("Calibri", Font.BOLD, 20));
		this.add(lblPlantaOrigen,constraints);
		
		constraints.gridx = 1;
		constraints.gridy = 1;
		constraints.gridwidth = 1;
		this.add(jcbPlantaOrigen, constraints);
		
		
		constraints.gridx = 2;
		constraints.gridy = 1;
		constraints.gridwidth = 1;
		lblPlantaDestino.setFont(new Font("Calibri", Font.BOLD, 20));
		this.add(lblPlantaDestino,constraints);
		
		
		constraints.gridx = 3;
		constraints.gridy = 1;
		constraints.gridwidth = 1;
		this.add(jcbPlantaDestino, constraints);
		
		constraints.gridx = 0;
		constraints.gridy = 2;
//		constraints.weightx = 2.0;
		constraints.gridwidth = 1;
		constraints.insets = new Insets(4, 20, 0, 0);
		lblDistanciaKm.setFont(new Font("Calibri", Font.BOLD, 20));
		this.add(lblDistanciaKm,constraints);
		
		constraints.gridx = 1;
		constraints.gridy = 2;
		constraints.gridwidth = 1;
		constraints.insets = new Insets(0, 20, 0, 0);
		this.txtDistanciaKm = new JTextField(20);
		this.add(txtDistanciaKm,constraints);
		
		constraints.gridx = 2;
		constraints.gridy = 2;
		constraints.gridwidth = 1;
		constraints.insets = new Insets(4, 20, 0, 0);
		lblDuracionHs.setFont(new Font("Calibri", Font.BOLD, 20));
		this.add(lblDuracionHs,constraints);
		
		constraints.gridx = 3;
		constraints.gridy = 2;
		constraints.gridwidth = 1;
		constraints.insets = new Insets(0, 20, 0, 0);
		this.txtDuracionHs = new JTextField(20);
		this.add(txtDuracionHs,constraints);
		
		constraints.gridx = 4;
		constraints.gridy = 2;
		constraints.gridwidth = 1;
		constraints.insets = new Insets(4, 20, 0, 0);
		lblPesoMaximo.setFont(new Font("Calibri", Font.BOLD, 20));
		this.add(lblPesoMaximo,constraints);
		
		constraints.gridx = 6;
		constraints.gridy = 2;
//		constraints.weightx = 2.0;
		constraints.gridwidth = 1;
		constraints.insets = new Insets(0, 20, 0, 0);
		this.txtPesoMaximo = new JTextField(20);
		this.add(txtPesoMaximo,constraints);
		
		constraints.gridx = 6;
		constraints.gridy = 3;
//		constraints.weightx = 2.0;
//		constraints.weighty = 2.0;
		constraints.gridwidth = 6;
		constraints.anchor = GridBagConstraints.SOUTHEAST;
		this.btnGuardar = new JButton("Guardar");
//		this.btnGuardar.addActionListener( e ->
//		{
//			try {
//				controller.guardar();
//			} catch (DatosObligatoriosException | FormatoNumeroException | ControllerException | ElementoDiferenteException e1) {
//				this.mostrarError("Error al guardar", e1.getMessage());
//			}
//			this.limpiarFormulario();
//		}
//	);
		this.add(btnGuardar, constraints);
	}
	
	private void limpiarFormulario() {
		this.txtDistanciaKm.setText("");
		this.txtDuracionHs.setText("");
		this.txtPesoMaximo.setText("");
	
	}
	
	
	

	public String[] getLetras() {
		return letras;
	}

	public void setLetras(String[] letras) {
		this.letras = letras;
	}

	public JLabel getLblTitulo() {
		return lblTitulo;
	}

	public void setLblTitulo(JLabel lblTitulo) {
		this.lblTitulo = lblTitulo;
	}

	public JLabel getLblPlantaOrigen() {
		return lblPlantaOrigen;
	}

	public void setLblPlantaOrigen(JLabel lblPlantaOrigen) {
		this.lblPlantaOrigen = lblPlantaOrigen;
	}

	public JLabel getLblPlantaDestino() {
		return lblPlantaDestino;
	}

	public void setLblPlantaDestino(JLabel lblPlantaDestino) {
		this.lblPlantaDestino = lblPlantaDestino;
	}

	public JComboBox<String> getJcbPlantaOrigen() {
		return jcbPlantaOrigen;
	}

	public void setJcbPlantaOrigen(JComboBox<String> jcbPlantaOrigen) {
		this.jcbPlantaOrigen = jcbPlantaOrigen;
	}

	public JComboBox<String> getJcbPlantaDestino() {
		return jcbPlantaDestino;
	}

	public void setJcbPlantaDestino(JComboBox<String> jcbPlantaDestino) {
		this.jcbPlantaDestino = jcbPlantaDestino;
	}

	public JLabel getLblDuracionHs() {
		return lblDuracionHs;
	}

	public void setLblDuracionHs(JLabel lblDuracionHs) {
		this.lblDuracionHs = lblDuracionHs;
	}

	public JLabel getLblDistanciaKm() {
		return lblDistanciaKm;
	}

	public void setLblDistanciaKm(JLabel lblDistanciaKm) {
		this.lblDistanciaKm = lblDistanciaKm;
	}

	public JLabel getLblPesoMaximo() {
		return lblPesoMaximo;
	}

	public void setLblPesoMaximo(JLabel lblPesoMaximo) {
		this.lblPesoMaximo = lblPesoMaximo;
	}

	public JTextField getTxtDuracionHs() {
		return txtDuracionHs;
	}

	public void setTxtDuracionHs(JTextField txtDuracionHs) {
		this.txtDuracionHs = txtDuracionHs;
	}

	public JTextField getTxtDistanciaKm() {
		return txtDistanciaKm;
	}

	public void setTxtDistanciaKm(JTextField txtDistanciaKm) {
		this.txtDistanciaKm = txtDistanciaKm;
	}

	public JTextField getTxtPesoMaximo() {
		return txtPesoMaximo;
	}

	public void setTxtPesoMaximo(JTextField txtPesoMaximo) {
		this.txtPesoMaximo = txtPesoMaximo;
	}

	public JButton getBtnGuardar() {
		return btnGuardar;
	}

	public void setBtnGuardar(JButton btnGuardar) {
		this.btnGuardar = btnGuardar;
	}
	
	
	
	
	public void mostrarError(String titulo,String detalle) {
		JFrame padre= (JFrame) SwingUtilities.getWindowAncestor(this);
		JOptionPane.showMessageDialog(padre,
			    detalle,titulo,
			    JOptionPane.ERROR_MESSAGE);
	}
	
}