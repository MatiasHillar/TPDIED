package gui;

import java.awt.Color;
import java.awt.Dimension;
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
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class PanelInsumosLiquidos extends JPanel{
	private String[] unidades = {"M2", "Kg", "Mts"};

	private JLabel lblTitulo = new JLabel("Añadir insumo general:");
	private JLabel lblDescripcion = new JLabel("Descripción:");
	private JLabel lblUnidad = new JLabel("Unidad:");
	private JLabel lblCosto = new JLabel("Costo:");
	private JLabel lblNombre = new JLabel("Nombre:");
	private JLabel lblDensidad = new JLabel("Densidad:");
	private JLabel lblStockTotal = new JLabel("Stock Total:");
	private JLabel lblStockTotalNumero = new JLabel("Aca va el numero"); //metodo toString del stock o como se calcule
	private JTextArea txtADescripcion;
	private JComboBox<String> jcbUnidades = new JComboBox<String>(unidades);
	private JTextField txtCosto;
	private JTextField txtDensidad;
	private JTextField txtNombre;
	private JButton btnGuardar;
	//private InsumosLiquidosController controller;

	
	public PanelInsumosLiquidos(){
		super();
		//this.controller= new InsumosController(this);
		this.armarPanel();
	}
	
	private void armarPanel() {
		this.setBackground(Color.GRAY);
		GridBagLayout gbl = new GridBagLayout();
		this.setLayout(gbl);
	
		GridBagConstraints constraints = new GridBagConstraints();
	
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 6;
		constraints.insets = new Insets(0, 20, 0, 0);
		constraints.anchor = GridBagConstraints.WEST;
		lblTitulo.setFont(new Font("Calibri", Font.BOLD, 24));
		lblTitulo.setForeground(Color.BLUE.darker());
		this.add(lblTitulo,constraints);
		
		constraints.anchor = GridBagConstraints.NORTH;
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.gridwidth = 1;
		constraints.insets = new Insets(5, 20, 0, 0);
		lblNombre.setFont(new Font("Calibri", Font.BOLD, 20));
		this.add(lblNombre,constraints);
		
		constraints.gridx = 1;
		constraints.gridy = 1;
		constraints.gridwidth = 1;
		constraints.insets = new Insets(0, 20, 0, 0);
		constraints.anchor = GridBagConstraints.CENTER;
		this.txtNombre = new JTextField(20);
		this.add(txtNombre,constraints);
		
		constraints.anchor = GridBagConstraints.CENTER;
		constraints.gridx = 2;
		constraints.gridy = 1;
		constraints.gridwidth = 1;
		constraints.insets = new Insets(5, 20, 0, 0);
		lblCosto.setFont(new Font("Calibri", Font.BOLD, 20));
		this.add(lblCosto,constraints);
		
		constraints.gridx = 3;
		constraints.gridy = 1;
		constraints.gridwidth = 1;
		constraints.insets = new Insets(0, 20, 0, 0);
		constraints.anchor = GridBagConstraints.NORTH;
		this.txtCosto = new JTextField(20);
		this.add(txtCosto,constraints);
		
		constraints.anchor = GridBagConstraints.NORTH;
		constraints.gridx = 4;
		constraints.gridy = 1;
		constraints.gridwidth = 1;
		constraints.insets = new Insets(5, 20, 0, 0);
		lblDensidad.setFont(new Font("Calibri", Font.BOLD, 20));
		this.add(lblDensidad,constraints);
		
		constraints.gridx = 5;
		constraints.gridy = 1;
		constraints.gridwidth = 1;
		constraints.insets = new Insets(0, 20, 0, 0);
		constraints.anchor = GridBagConstraints.NORTH;
		this.txtDensidad = new JTextField(20);
		this.add(txtDensidad,constraints);
		
		constraints.anchor = GridBagConstraints.CENTER;
		constraints.gridx = 0;
		constraints.gridy = 2;
		constraints.gridwidth = 1;
		lblDescripcion.setFont(new Font("Calibri", Font.BOLD, 20));
		this.add(lblDescripcion,constraints);
		
		constraints.anchor = GridBagConstraints.SOUTH;
		constraints.gridx = 1;
		constraints.gridy = 2;
		constraints.gridwidth = 1;
		txtADescripcion = new JTextArea(10, 20);
		txtADescripcion.setSize(new Dimension(10, 20));
		this.add(txtADescripcion, constraints);
		
		constraints.anchor = GridBagConstraints.CENTER;
		constraints.gridx = 2;
		constraints.gridy = 2;
		constraints.gridwidth = 1;
		constraints.insets = new Insets(5, 20, 0, 0);
		lblUnidad.setFont(new Font("Calibri", Font.BOLD, 20));
		this.add(lblUnidad,constraints);
		
		constraints.anchor = GridBagConstraints.WEST;
		constraints.gridx = 3;
		constraints.gridy = 2;
		constraints.gridwidth = 1;
		constraints.insets = new Insets(0, 20, 0, 0);
		this.add(jcbUnidades, constraints);
		
		constraints.anchor = GridBagConstraints.CENTER;
		constraints.gridx = 4;
		constraints.gridy = 2;
		constraints.gridwidth = 1;
		lblStockTotal.setFont(new Font("Calibri", Font.BOLD, 20));
		this.add(lblStockTotal,constraints);
		
		constraints.anchor = GridBagConstraints.CENTER;
		constraints.gridx = 5;
		constraints.gridy = 2;
		constraints.gridwidth = 1;
		lblStockTotalNumero.setFont(new Font("Calibri", Font.BOLD, 20));
		this.add(lblStockTotalNumero,constraints);
	
		constraints.gridx = 5;
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
//			} catch (DatosObligatoriosException | FormatoNumeroException | ControllerException e1) {
//				this.mostrarError("Error al guardar", e1.getMessage());
//			}
//			this.limpiarFormulario();
//		}
//	);
		this.add(btnGuardar, constraints);
	}
	
	
	public String[] getUnidades() {
		return unidades;
	}

	public void setUnidades(String[] unidades) {
		this.unidades = unidades;
	}

	public JLabel getLblTitulo() {
		return lblTitulo;
	}

	public void setLblTitulo(JLabel lblTitulo) {
		this.lblTitulo = lblTitulo;
	}

	public JLabel getLblDescripcion() {
		return lblDescripcion;
	}

	public void setLblDescripcion(JLabel lblDescripcion) {
		this.lblDescripcion = lblDescripcion;
	}

	public JLabel getLblUnidad() {
		return lblUnidad;
	}

	public void setLblUnidad(JLabel lblUnidad) {
		this.lblUnidad = lblUnidad;
	}

	public JLabel getLblCosto() {
		return lblCosto;
	}

	public void setLblCosto(JLabel lblCosto) {
		this.lblCosto = lblCosto;
	}

	public JLabel getLblNombre() {
		return lblNombre;
	}

	public void setLblNombre(JLabel lblNombre) {
		this.lblNombre = lblNombre;
	}

	public JLabel getLblDensidad() {
		return lblDensidad;
	}

	public void setLblDensidad(JLabel lblDensidad) {
		this.lblDensidad = lblDensidad;
	}

	public JLabel getLblStockTotal() {
		return lblStockTotal;
	}

	public void setLblStockTotal(JLabel lblStockTotal) {
		this.lblStockTotal = lblStockTotal;
	}

	public JLabel getLblStockTotalNumero() {
		return lblStockTotalNumero;
	}

	public void setLblStockTotalNumero(JLabel lblStockTotalNumero) {
		this.lblStockTotalNumero = lblStockTotalNumero;
	}

	public JTextArea getTxtADescripcion() {
		return txtADescripcion;
	}

	public void setTxtADescripcion(JTextArea txtADescripcion) {
		this.txtADescripcion = txtADescripcion;
	}

	public JComboBox<String> getJcbUnidades() {
		return jcbUnidades;
	}

	public void setJcbUnidades(JComboBox<String> jcbUnidades) {
		this.jcbUnidades = jcbUnidades;
	}

	public JTextField getTxtCosto() {
		return txtCosto;
	}

	public void setTxtCosto(JTextField txtCosto) {
		this.txtCosto = txtCosto;
	}

	public JTextField getTxtDensidad() {
		return txtDensidad;
	}

	public void setTxtDensidad(JTextField txtDensidad) {
		this.txtDensidad = txtDensidad;
	}

	public JTextField getTxtNombre() {
		return txtNombre;
	}

	public void setTxtNombre(JTextField txtNombre) {
		this.txtNombre = txtNombre;
	}

	public JButton getBtnGuardar() {
		return btnGuardar;
	}

	public void setBtnGuardar(JButton btnGuardar) {
		this.btnGuardar = btnGuardar;
	}
	
	

	private void limpiarFormulario() {
		this.txtADescripcion.setText("");
		this.txtNombre.setText("");
		this.txtCosto.setText("");
		this.txtDensidad.setText("");
	}
	
	public void mostrarError(String titulo,String detalle) {
	JFrame padre= (JFrame) SwingUtilities.getWindowAncestor(this);
	JOptionPane.showMessageDialog(padre,
		    detalle,titulo,
		    JOptionPane.ERROR_MESSAGE);
}

}
