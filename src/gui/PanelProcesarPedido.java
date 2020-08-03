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
import java.time.ZoneId;
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

import controller.ProcesarPedidoController;
import dominio.*;
import gui.util.DatosObligatoriosException;
import prueba.App;

public class PanelProcesarPedido extends JPanel {
	
	private JLabel lblTitulo = new JLabel("Procesar pedido:");
	private JLabel lblPedidos = new JLabel("Pedidos:");
	private JLabel lblPlDestino = new JLabel("Planta destino:");
	private JLabel lblInsumos = new JLabel("Insumos:");
	private JLabel lblCaminosMenosKm = new JLabel("Caminos con menor Km:");
	private JLabel lblCaminosMenosT = new JLabel("Caminos con menor tiempo:");
	private JComboBox<Pedido> jcbPedidos;
	private JComboBox<Planta> jcbPlantas;
//	private JFormattedTextField cantidad = new JFormattedTextField(NumberFormat.getNumberInstance());
//	private JFormattedTextField precio = new JFormattedTextField(NumberFormat.getNumberInstance());
	
	private JButton btnVerDetalle = new JButton("Ver detalle");
	private JButton btnBuscarPlantas= new JButton("Buscar plantas");
	private JButton btnBuscarCaminos= new JButton("Buscar caminos");
	private JButton btnElegirCamino= new JButton("Elegir camino");
	private JButton btnElegirCamino2= new JButton("Elegir camino");
	
	
	private JLabel lblFecha = new JLabel("Fecha maxima de entrega:");
	private DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
	private JFormattedTextField txtFecha = new JFormattedTextField(df);	
	private JTextField txtPlanta = new JTextField();
	private JLabel lblFechaSolicitud = new JLabel("Fecha de solicitud:");
	private JFormattedTextField txtFechaS = new JFormattedTextField(df);	
	
	/*
	private DefaultListModel<ItemPedido> listModel = new DefaultListModel<ItemPedido>();
	private JList<ItemPedido> list = new JList<ItemPedido>(listModel);
	*/
	private ProcesarPedidoController controller;
	
	
	private JTable tablaItems;
	private ItemPedidoTableModel modeloTablaItem; 
	private App app; 
	
	
	
	public PanelProcesarPedido(App a) {
		super();
		this.controller= new ProcesarPedidoController(this);
		this.armarPanel();
		this.app=a;
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

//		jcbPlantas = new JComboBox<Planta>();
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
		
		constraints.gridx = 1;
		btnBuscarPlantas.addActionListener(e -> {
//			try {
				this.app.setContentPane(new PanelAltaCamiones());
				this.app.revalidate();
				this.app.repaint();
				System.out.println("Acordarse de descomentar esto");
				//Si no hay plantas avisar por pantalla
				
//				this.controller.buscarPlantaParaPedido();
//			}
//			catch(DatosObligatoriosException e1) {
//				this.mostrarError("Error al buscar plantas", e1.getMessage());
//			}
		});
		
		this.add(btnBuscarPlantas, constraints);
		
		

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
		this.txtFecha.setColumns(10);
		this.add(txtFecha,constraints);

		constraints.anchor= constraints.NORTH;
		constraints.gridx = 5;
		constraints.gridy = 4;
		this.add(lblFechaSolicitud,constraints);
		
		constraints.gridx = 5;
		constraints.gridy = 5;
		this.txtFechaS.setColumns(10);
		this.add(txtFechaS,constraints);
		
		
		constraints.gridx = 5;
		constraints.gridy = 6;
		this.add(this.lblPlDestino,constraints);
		constraints.gridx = 5;
		constraints.gridy = 7;
		this.add(txtPlanta, constraints);
		constraints.anchor= constraints.CENTER;
		
		
		/*
		constraints.insets = new Insets(0, 20, 0, 0);
		constraints.gridx = 0;
		constraints.gridy = 3;
		this.add(lblInsumo,constraints);

		constraints.insets = new Insets(0, 0, 0, 0);
		constraints.gridx = 1;
		constraints.gridy = 3;
		jcbInsumo = new JComboBox<Insumo>( controller.listarTodosInsumos().toArray(new Insumo[0]));
		this.jcbInsumo.addPropertyChangeListener(e -> {
			this.controller.actualizarItem();
			this.cantidad.setValue(0f);
		});
		this.add(jcbInsumo,constraints);
		
		
		constraints.gridx = 2;
		constraints.gridy = 3;
		this.add(this.lblCantidad,constraints);
		
		constraints.gridx = 3;
		constraints.gridy = 3;
		this.cantidad.setColumns(15);
		this.cantidad.addPropertyChangeListener(e -> {
			this.controller.actualizarItem();
		});
		this.add(cantidad,constraints);
		
		constraints.gridx = 4;
		constraints.gridy = 3;
		this.add(this.lblPrecio,constraints);
		
		constraints.gridx = 5;
		constraints.gridy = 3;
		this.precio.setColumns(15);
		this.precio.setEditable(false);
		this.add(precio,constraints);
		
		constraints.gridx = 4;
		constraints.gridy = 4;
		this.btnCancelar.addActionListener(e -> {
			this.limpiarFormulario();
		});
		this.add(btnCancelar, constraints);
		
		modeloTablaItem = new ItemPedidoTableModel(new ArrayList<ItemPedido>());
		tablaItems = new JTable();
		tablaItems.setModel(modeloTablaItem);
		JScrollPane scrollPane = new JScrollPane(tablaItems);
		tablaItems.setFillsViewportHeight(true);
		constraints.gridx = 0;
		constraints.gridy = 5;
		constraints.gridwidth=6;
		this.add(scrollPane,constraints);
		constraints.gridwidth=1;
		
//		list = new JList<ItemPedido>(listModel);
		
		
		constraints.gridx = 5;
		constraints.gridy = 4;
		this.btnAnadir.addActionListener(e -> {
			if((Float)this.cantidad.getValue() > 0f) {
				if(modeloTablaItem.getData().contains(controller.getIp())) {
					int index = modeloTablaItem.getData().indexOf(controller.getIp());
					modeloTablaItem.getData().get(index).setCtidad(modeloTablaItem.getData().get(index).getCtidad() + controller.getIp().getCtidad());
				}
				else modeloTablaItem.getData().add(controller.getIp());
				
				modeloTablaItem.fireTableDataChanged();
			}
		});
		this.add(btnAnadir, constraints);
		
		

		constraints.gridwidth=1;
		constraints.gridx = 4;
		constraints.gridy = 6;
		this.btnEliminar.addActionListener(e -> {
			List<ItemPedido> itemss = new ArrayList<ItemPedido>();
			for(int index : tablaItems.getSelectedRows()) {
				itemss.add(modeloTablaItem.getData().get(index));
			}
			modeloTablaItem.getData().removeAll(itemss);
			modeloTablaItem.fireTableDataChanged();
			
		});
		this.add(btnEliminar, constraints);
		
		
		
		constraints.gridx = 5;
		constraints.gridy = 6;
		this.btnAnadir.addActionListener(e -> {
			try {
				this.controller.guardar();
				this.limpiarFormulario();
			} catch (Exception e1) {
				this.mostrarError("Error al añadir", e1.getMessage());
			}
		});
		this.add(btnAnadir, constraints);
		
		this.limpiarFormulario();
		
	}
	
	private void limpiarFormulario() {
//		this.cantidad.setValue(0f);
		this.txtFecha.setValue(new Date());
		this.txtFechaS.setValue(new Date());
		this.jcbPlantas =  new JComboBox<Planta>();
	}
	
	*/
	}
		
	public void mostrarError(String titulo,String detalle) {
		JFrame padre= (JFrame) SwingUtilities.getWindowAncestor(this);
		JOptionPane.showMessageDialog(padre,
			    detalle,titulo,
			    JOptionPane.ERROR_MESSAGE);
	}

	public JComboBox<Pedido> getJcbPedidos() {
		return jcbPedidos;
	}

	public void setJcbPedidos(JComboBox<Pedido> jcbPedidos) {
		this.jcbPedidos = jcbPedidos;
	}

	public JComboBox<Planta> getJcbPlantas() {
		return jcbPlantas;
	}

	public void setJcbPlantas(JComboBox<Planta> jcbPlantas) {
		this.jcbPlantas = jcbPlantas;
	}

	public JFormattedTextField getTxtFecha() {
		return txtFecha;
	}

	public void setTxtFecha(JFormattedTextField txtFecha) {
		this.txtFecha = txtFecha;
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

	public JTextField getTxtPlanta() {
		return txtPlanta;
	}

	public void setTxtPlanta(JTextField txtPlanta) {
		this.txtPlanta = txtPlanta;
	}

	
	

}
