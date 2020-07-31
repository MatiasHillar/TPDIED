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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.*;

import controller.*;
import gui.util.*;
import prueba.App;
import dominio.*;


public class PanelCamiones extends JPanel{
	
	private JLabel lblTitulo = new JLabel("Administracion de camiones:");
	private JLabel lblBuscar = new JLabel("Busqueda de camiones:");
	private JLabel lblPatente = new JLabel("Patente:");
	private JTextField txtPatente;
	private JLabel lblModelo = new JLabel("Modelo:");
	private JTextField txtModelo;
	private JLabel lblMarca = new JLabel("Marca:");
	private JTextField txtMarca;
	private JLabel lblCostoHora = new JLabel("Costo por hora minimo:");
	private JFormattedTextField costoHora = new JFormattedTextField(NumberFormat.getNumberInstance());
	private JLabel lblCostoKm = new JLabel("Costo por Km minimo:");
	private JFormattedTextField costoKm = new JFormattedTextField(NumberFormat.getNumberInstance());
	
	private JLabel lblCostoHoraMax = new JLabel("Costo por hora maximo:");
	private JFormattedTextField costoHoraMax = new JFormattedTextField(NumberFormat.getNumberInstance());
	private JLabel lblCostoKmMax = new JLabel("Costo por Km maximo:");
	private JFormattedTextField costoKmMax = new JFormattedTextField(NumberFormat.getNumberInstance());

	
	private JLabel lblFecha = new JLabel("Fecha:");
	private DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
	private JFormattedTextField txtFechaCompra = new JFormattedTextField(df);	
	
	private JLabel lblKm = new JLabel("Kms recorridos minimo:");
	
	private JFormattedTextField txtKm = new JFormattedTextField(NumberFormat.getNumberInstance());

	private JLabel lblKmMax = new JLabel("Kms recorridos maximo:");
	
	private JFormattedTextField txtKmMax = new JFormattedTextField(NumberFormat.getNumberInstance());
	
	
	private JButton btnGuardar;
	private JButton btnCancelar;
	
	private JButton btnModificar;
	private JButton btnEliminar;
	
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
		

		/*
		constraints.gridx = 6;
		constraints.gridy = 5;		
		this.add(lblFecha,constraints);
		this.txtFechaCompra.setColumns(10);
		constraints.gridx = 7;
		constraints.gridy = 5;		
		this.add(txtFechaCompra,constraints);
		*/
		
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
					modeloTablaCamion.setData(controller.buscarPorAtributos());
					this.limpiarFormulario();
					modeloTablaCamion.fireTableDataChanged();
				} catch (FormatoNumeroException | ControllerException e1) {
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
		
		
		
		
		modeloTablaCamion = new CamionTableModel(new ArrayList<Camion>());
		tablaCamiones = new JTable();
		tablaCamiones.setModel(modeloTablaCamion);
		JScrollPane scrollPane = new JScrollPane(tablaCamiones);
		tablaCamiones.setFillsViewportHeight(true);
		constraints.gridx = 0;
		constraints.gridy = 8;		
		constraints.gridwidth = 11;
		constraints.weighty=1;
		constraints.weightx=2;
		constraints.anchor = GridBagConstraints.NORTH;
		constraints.fill= GridBagConstraints.HORIZONTAL;
		this.add(scrollPane,constraints);
		
		constraints.insets = new Insets(10, 50, 10, 50);
		constraints.gridx = 0;
		constraints.gridy = 10;		
		constraints.gridwidth = 11;
		constraints.weighty=1;
		constraints.weightx=2;
		constraints.anchor = GridBagConstraints.NORTH;
		constraints.fill= GridBagConstraints.HORIZONTAL;
		PanelAltaCamiones pa = new PanelAltaCamiones();
		this.add(pa,constraints);
		
		constraints.insets = new Insets(10, 50, 0, 0);
		constraints.gridx = 8;
		constraints.gridy = 9;		
		constraints.gridwidth = 1;
		constraints.weighty=0;
		constraints.weightx=0;
		constraints.anchor = GridBagConstraints.EAST;
		constraints.fill= GridBagConstraints.NONE;
		this.btnModificar = new JButton("Modificar");
		
		this.btnModificar.addActionListener( e ->
			{
				try {
					if(tablaCamiones.getSelectedRowCount()==0)
						throw new Exception("Debe seleccionar un camion");
					if(tablaCamiones.getSelectedRowCount()>1)
						throw new Exception("Debe seleccionar solamente un camion");
					controller.prepararM(pa, modeloTablaCamion.getData().get(tablaCamiones.getSelectedRow()));
//					modeloTablaCamion.setData(controller.buscarPorAtributos());
//					modeloTablaCamion.fireTableDataChanged();
//				} catch (FormatoNumeroException | ControllerException e1) {
				} catch (Exception e1) {
					this.mostrarError("Error al modificar", e1.getMessage());
					e1.printStackTrace();
				}
			}
		);

		this.add(btnModificar,constraints);
		
		constraints.insets = new Insets(10, 50, 0, 0);
		constraints.gridx = 9;
		constraints.gridy = 9;		
		constraints.gridwidth = 1;
		constraints.weighty=0;
		constraints.weightx=0;
		constraints.anchor = GridBagConstraints.EAST;
		constraints.fill= GridBagConstraints.NONE;
		this.btnEliminar = new JButton("Eliminar");
		
		this.btnEliminar.addActionListener( e ->
			{
				try {
					if(tablaCamiones.getSelectedRowCount()==0)
						throw new Exception("Debe seleccionar un camion");
					int n=1;
					if(tablaCamiones.getSelectedRowCount()==1)
						n = JOptionPane.showConfirmDialog(this, "¿Esta seguro de que desea eliminar " + 1 + " camion?", "Eliminar", JOptionPane.YES_NO_OPTION);
					else
					n = JOptionPane.showConfirmDialog(this, "¿Esta seguro de que desea eliminar los " + tablaCamiones.getSelectedRowCount() + " camiones?", "Eliminar", JOptionPane.YES_NO_OPTION);
					if(n==0)
						controller.eliminar(modeloTablaCamion.getData().get(tablaCamiones.getSelectedRow()));
					modeloTablaCamion.setData(controller.buscarPorAtributos());
					modeloTablaCamion.fireTableDataChanged();
//				} catch (FormatoNumeroException | ControllerException e1) {
				} catch (Exception e1) {
					this.mostrarError("Error al eliminar", e1.getMessage());
					e1.printStackTrace();
				}
			}
		);

		this.add(btnEliminar,constraints);
		
		
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
	
	
	

	public JFormattedTextField getCostoHoraMax() {
		return costoHoraMax;
	}

	public void setCostoHoraMax(JFormattedTextField costoHoraMax) {
		this.costoHoraMax = costoHoraMax;
	}

	public JFormattedTextField getCostoKmMax() {
		return costoKmMax;
	}

	public void setCostoKmMax(JFormattedTextField costoKmMax) {
		this.costoKmMax = costoKmMax;
	}

	public JFormattedTextField getTxtKmMax() {
		return txtKmMax;
	}

	public void setTxtKmMax(JFormattedTextField txtKmMax) {
		this.txtKmMax = txtKmMax;
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
	public JFormattedTextField getTxtKm() {
		return txtKm;
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
	
	
	public void setTxtKm(JFormattedTextField txtKm) {
		this.txtKm = txtKm;
	}

	public void mostrarError(String titulo,String detalle) {
		JFrame padre= (JFrame) SwingUtilities.getWindowAncestor(this);
		JOptionPane.showMessageDialog(padre,
			    detalle,titulo,
			    JOptionPane.ERROR_MESSAGE);
	}

}
