package gui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;


import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

public class PanelPlantas extends JPanel{
	
	private JLabel lblTitulo = new JLabel("Añadir planta:");
	private JLabel lblNombre = new JLabel("Nombre:");
	private JTextField txtNombre;
	private JButton btnGuardar;
	private JButton btnCancelar;
	//private PlantaController controller;
	
	public PanelPlantas(){
		super();
		//this.controller= new CamionController(this);
		this.armarPanel();
	}
	
	private void armarPanel() {
		this.setBackground(Color.GRAY);
		GridBagLayout gbl = new GridBagLayout();
		this.setLayout(gbl);
		
		GridBagConstraints constraints = new GridBagConstraints();
		
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 3;
		constraints.weighty = 1.0;
		constraints.insets = new Insets(0, 20, 0, 0);
		constraints.anchor = GridBagConstraints.WEST;
		lblTitulo.setFont(new Font("Calibri", Font.BOLD, 24));
		lblTitulo.setForeground(Color.BLUE.darker());
		this.add(lblTitulo,constraints);
		
		
		
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.gridwidth = 1;
		constraints.weighty = 1.0;
		constraints.insets = new Insets(3, 20, 0, 0);
		constraints.anchor = GridBagConstraints.NORTH;
		lblNombre.setFont(new Font("Calibri", Font.BOLD, 20));
		this.add(lblNombre,constraints);
		
		
		constraints.gridx = 1;
		constraints.gridy = 1;
		constraints.gridwidth = 1;
		constraints.weighty = 1.0;
		constraints.anchor = GridBagConstraints.NORTH;
		this.txtNombre = new JTextField(20);
		this.add(txtNombre,constraints);
		

		constraints.gridx = 2;
		constraints.gridy = 1;
		constraints.gridwidth = 3;
		constraints.insets = new Insets(0, 20, 0, 0);
		constraints.anchor = GridBagConstraints.NORTHWEST;
		constraints.weighty = 1.0;
		constraints.weightx = 2.0;
		this.btnGuardar = new JButton("Guardar");
//		this.btnGuardar.addActionListener( e ->
//		{
//			try {
//				controller.guardar();
//			} catch (DatosObligatoriosException | ControllerException e1) {
//				this.mostrarError("Error al guardar", e1.getMessage());
//			}
//			this.limpiarFormulario();
//		}
//	);
		this.add(btnGuardar, constraints);
		
		
	}
	
	
	private void limpiarFormulario() {
		this.txtNombre.setText("");
	}

	
	
	public JLabel getLblTitulo() {
		return lblTitulo;
	}

	public void setLblTitulo(JLabel lblTitulo) {
		this.lblTitulo = lblTitulo;
	}

	public JLabel getLblNombre() {
		return lblNombre;
	}

	public void setLblNombre(JLabel lblNombre) {
		this.lblNombre = lblNombre;
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

	public JButton getBtnCancelar() {
		return btnCancelar;
	}

	public void setBtnCancelar(JButton btnCancelar) {
		this.btnCancelar = btnCancelar;
	}
	
	
	
	
	
	public void mostrarError(String titulo,String detalle) {
		JFrame padre= (JFrame) SwingUtilities.getWindowAncestor(this);
		JOptionPane.showMessageDialog(padre,
			    detalle,titulo,
			    JOptionPane.ERROR_MESSAGE);
	}
	
	
}
