package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

import controller.EntregarPedidoController;
import dominio.Camion;
import dominio.Estado;
import dominio.ItemPedido;
import dominio.Pedido;
import dominio.Planta;
import dominio.Ruta;
import gui.util.DatosObligatoriosException;
import prueba.App;

public class PanelEntregarPedido extends JPanel {
	private JLabel lblTitulo = new JLabel("Entregar pedido:");
	private JLabel lblPedidos = new JLabel("Pedidos:");
	private JLabel lblPlDestino = new JLabel("Planta destino:");
	private JLabel lblInsumos = new JLabel("Insumos:");
	private JLabel lblCamino = new JLabel("Camino:");
	private JLabel lblCosto = new JLabel("Costo del envio:");
	private JLabel lblCamion = new JLabel("Camion asignado(Patente):");
	private JFormattedTextField txtCosto = new JFormattedTextField(NumberFormat.getNumberInstance());
	private JComboBox<Pedido> jcbPedidos;
	
	private JButton btnVerDetalle = new JButton("Ver detalle");
	private JButton btnEntregado= new JButton("Marcar como entregado");
	
	
	private JLabel lblFecha = new JLabel("Fecha maxima de entrega:");
	private DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
	private JFormattedTextField txtFecha = new JFormattedTextField(df);	
	private JTextField txtPlanta = new JTextField();
	private JTextField txtCamion = new JTextField();
	private JTextField txtCamino = new JTextField();
	private JLabel lblFechaSolicitud = new JLabel("Fecha de solicitud:");
	private JFormattedTextField txtFechaS = new JFormattedTextField(df);	
	
	private JTable tablaItems;
	private ItemPedidoTableModel modeloTablaItem; 
	
	
	private EntregarPedidoController controller;
	  
	public PanelEntregarPedido() {
		super();
		this.controller= new EntregarPedidoController(this);
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
		
		
		constraints.gridx = 0;
		constraints.gridy = 2;
		constraints.gridwidth = 1;
		constraints.gridheight=1;
		constraints.insets = new Insets(0, 20, 0, 0);
		constraints.anchor= GridBagConstraints.CENTER;
		this.add(lblPedidos,constraints);
		
		constraints.gridy = 3;
//			this.controller.actualizarPedidos();
		jcbPedidos = new JComboBox<Pedido>();
		this.add(jcbPedidos,constraints);
		
		constraints.gridy = 4;
		btnVerDetalle.addActionListener( e -> {
			try {
				this.controller.verDetallePedido();
			}
			catch(DatosObligatoriosException e1) {
				this.mostrarError("Error al mostrar detalles", e1.getMessage());
			}
			
		});
		this.add(btnVerDetalle,constraints);
		
		constraints.gridy = 5;
		btnEntregado.addActionListener( e -> {
			try {
				this.controller.entregarPedido();
			}
			catch(DatosObligatoriosException e1) {
				this.mostrarError("Error al marcar entrega", e1.getMessage());
			}
			
		});
		this.add(btnEntregado,constraints);
		
		constraints.gridx = 2;
		constraints.gridy = 2;
		this.add(lblInsumos, constraints);
		
		
		modeloTablaItem = new ItemPedidoTableModel(new ArrayList<ItemPedido>());
		tablaItems = new JTable();
		tablaItems.setModel(modeloTablaItem);
		JScrollPane scrollPane = new JScrollPane(tablaItems);
		tablaItems.setFillsViewportHeight(true);
		constraints.gridx = 2;
		constraints.gridy = 3;
		constraints.gridwidth=3;
		constraints.gridheight=5;
		this.add(scrollPane,constraints);
		constraints.gridwidth=1;
		constraints.gridheight=1;
		
		
		
		
		constraints.gridx = 5;
		constraints.gridy = 2;
		this.add(lblFecha,constraints);
		
		constraints.gridx = 5;
		constraints.gridy = 3;
		this.txtFecha.setColumns(20);
		this.add(txtFecha,constraints);

		constraints.anchor= constraints.NORTH;
		constraints.gridx = 5;
		constraints.gridy = 4;
		this.add(lblFechaSolicitud,constraints);
		
		constraints.gridx = 5;
		constraints.gridy = 5;
		this.txtFechaS.setColumns(20);
		this.add(txtFechaS,constraints);
		
		
		constraints.gridx = 5;
		constraints.gridy = 6;
		this.txtPlanta=new JTextField(20);
		this.add(this.lblPlDestino,constraints);
		constraints.gridx = 5;
		constraints.gridy = 7;
		this.add(txtPlanta, constraints);
		constraints.anchor= constraints.CENTER;

		constraints.gridx = 6;
		constraints.gridy = 2;
		this.add(lblCamino,constraints);
		
		constraints.gridy = 3;
		this.txtCamino= new JTextField(20);
		this.add(txtCamino,constraints);
		
		constraints.gridy = 4;
		this.add(lblCosto,constraints);
		
		constraints.gridy = 5;
		this.txtCosto.setColumns(20);
		this.add(txtCosto,constraints);
		
		constraints.gridy = 6;
		this.add(lblCamion,constraints);

		constraints.anchor= constraints.NORTH;
		constraints.gridy = 7;
		this.txtCamion= new JTextField(20);
		this.add(txtCamion,constraints);
		this.limpiarFormulario();
		this.txtCamino.setEditable(false);
		this.txtCamion.setEditable(false);
		this.txtCosto.setEditable(false);
		this.txtPlanta.setEditable(false);
		this.txtFecha.setEditable(false);
		this.txtFechaS.setEditable(false);
	}
	
	public void mostrarError(String titulo,String detalle) {
		JFrame padre= (JFrame) SwingUtilities.getWindowAncestor(this);
		JOptionPane.showMessageDialog(padre,
			    detalle,titulo,
			    JOptionPane.ERROR_MESSAGE);
	}
	private void limpiarFormulario() {
		this.txtCosto.setValue(0f);
		this.txtCamion.setText("");
		this.txtCamino.setText("");
		this.txtPlanta.setText("");
		this.txtFecha.setValue(new Date());
		this.txtFechaS.setValue(new Date());
	}

	public JFormattedTextField getTxtCosto() {
		return txtCosto;
	}

	public void setTxtCosto(JFormattedTextField txtCosto) {
		this.txtCosto = txtCosto;
	}

	public JComboBox<Pedido> getJcbPedidos() {
		return jcbPedidos;
	}

	public void setJcbPedidos(JComboBox<Pedido> jcbPedidos) {
		this.jcbPedidos = jcbPedidos;
	}

	public JFormattedTextField getTxtFecha() {
		return txtFecha;
	}

	public void setTxtFecha(JFormattedTextField txtFecha) {
		this.txtFecha = txtFecha;
	}

	public JTextField getTxtPlanta() {
		return txtPlanta;
	}

	public void setTxtPlanta(JTextField txtPlanta) {
		this.txtPlanta = txtPlanta;
	}

	public JTextField getTxtCamion() {
		return txtCamion;
	}

	public void setTxtCamion(JTextField txtCamion) {
		this.txtCamion = txtCamion;
	}

	public JTextField getTxtCamino() {
		return txtCamino;
	}

	public void setTxtCamino(JTextField txtCamino) {
		this.txtCamino = txtCamino;
	}

	public JFormattedTextField getTxtFechaS() {
		return txtFechaS;
	}

	public void setTxtFechaS(JFormattedTextField txtFechaS) {
		this.txtFechaS = txtFechaS;
	}

	public JTable getTablaItems() {
		return tablaItems;
	}

	public void setTablaItems(JTable tablaItems) {
		this.tablaItems = tablaItems;
	}

	public ItemPedidoTableModel getModeloTablaItem() {
		return modeloTablaItem;
	}

	public void setModeloTablaItem(ItemPedidoTableModel modeloTablaItem) {
		this.modeloTablaItem = modeloTablaItem;
	}
	
}
