package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;

import controller.PedidoController;
import controller.PlantaController;
import dominio.*;
import gui.util.ControllerException;
import gui.util.FormatoNumeroException;

public class PanelAltaPedido extends JPanel {
	
	private JLabel lblTitulo = new JLabel("Registrar pedido:");
	private JLabel lblPlanta = new JLabel("Planta:");
	private JLabel lblInsumo = new JLabel("Insumo:");
	private JLabel lblCantidad = new JLabel("Cantidad:");
	private JLabel lblPrecio = new JLabel("Precio:");
	private JComboBox<Planta> jcbPlanta;
	private JComboBox<Insumo> jcbInsumo;
	private JFormattedTextField cantidad = new JFormattedTextField(NumberFormat.getNumberInstance());
	private JFormattedTextField precio = new JFormattedTextField(NumberFormat.getNumberInstance());
	
	private JButton btnAnadir = new JButton("Añadir");
	private JButton btnCancelar= new JButton("Cancelar");
	private JButton btnEliminar= new JButton("Eliminar");
	private JButton btnGuardar= new JButton("Guardar");
	
	
	private JLabel lblFecha = new JLabel("Fecha maxima de entrega:");
	private DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
	private JFormattedTextField txtFecha = new JFormattedTextField(df);	
	
	/*
	private DefaultListModel<ItemPedido> listModel = new DefaultListModel<ItemPedido>();
	private JList<ItemPedido> list = new JList<ItemPedido>(listModel);
	*/
	private PedidoController controller;
	
	
	private JTable tablaItems;
	private ItemPedidoTableModel modeloTablaItem; 
	 
	
	
	
	public PanelAltaPedido() {
		super();
		this.controller= new PedidoController(this);
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
		this.add(lblPlanta,constraints);
		
		constraints.gridx = 1;
		constraints.gridy = 2;
		constraints.insets = new Insets(0, 0, 0, 0);
	//	jcbPlanta = new JComboBox<Planta>((Planta[]) controller.listarTodasPlantas().toArray(new Planta[0]));
//		jcbPlanta = new JComboBox<Planta>();
		jcbPlanta = new JComboBox<Planta>(controller.listarTodasPlantas().toArray(new Planta[0]));
		this.add(jcbPlanta,constraints);
		
		constraints.gridx = 2;
		constraints.gridy = 2;
		this.add(lblFecha,constraints);
		
		constraints.gridx = 3;
		constraints.gridy = 2;
		this.txtFecha.setColumns(10);
		this.add(txtFecha,constraints);
		
		
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
				/*
				if(listModel.contains(controller.getIp())) {
					int index = listModel.indexOf(controller.getIp());
					listModel.get(index).setCtidad(listModel.get(index).getCtidad() + controller.getIp().getCtidad());
				}
				else
				listModel.addElement(controller.getIp());
				*/
				modeloTablaItem.fireTableDataChanged();
			}
		});
		this.add(btnAnadir, constraints);
		
		/*
		constraints.gridx = 0;
		constraints.gridy = 5;
		constraints.gridwidth=6;
		JScrollPane scrollPane = new JScrollPane(list);
		this.add(scrollPane, constraints);
		*/

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
			/*
			for(ItemPedido ip: this.list.getSelectedValuesList()) {
				this.listModel.removeElement(ip);
			}
			*/
		});
		this.add(btnEliminar, constraints);
		
		
		
		constraints.gridx = 5;
		constraints.gridy = 6;
		this.btnGuardar.addActionListener(e -> {
			try {
				this.controller.guardar();
				this.limpiarFormulario();
			} catch (Exception e1) {
				this.mostrarError("Error al añadir", e1.getMessage());
			}
		});
		this.add(btnGuardar, constraints);
		
		this.limpiarFormulario();
		
	}
	
	private void limpiarFormulario() {
		this.cantidad.setValue(0f);
		this.txtFecha.setValue(new Date());
	}
	
	public void mostrarError(String titulo,String detalle) {
		JFrame padre= (JFrame) SwingUtilities.getWindowAncestor(this);
		JOptionPane.showMessageDialog(padre,
			    detalle,titulo,
			    JOptionPane.ERROR_MESSAGE);
	}

	public JFormattedTextField getCantidad() {
		return cantidad;
	}

	public void setCantidad(JFormattedTextField cantidad) {
		this.cantidad = cantidad;
	}

	public JFormattedTextField getPrecio() {
		return precio;
	}

	public void setPrecio(JFormattedTextField precio) {
		this.precio = precio;
	}

	public JComboBox<Planta> getJcbPlanta() {
		return jcbPlanta;
	}

	public void setJcbPlanta(JComboBox<Planta> jcbPlanta) {
		this.jcbPlanta = jcbPlanta;
	}

	public JComboBox<Insumo> getJcbInsumo() {
		return jcbInsumo;
	}

	public void setJcbInsumo(JComboBox<Insumo> jcbInsumo) {
		this.jcbInsumo = jcbInsumo;
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

	public JFormattedTextField getTxtFechaCompra() {
		return txtFecha;
	}

	public void setTxtFechaCompra(JFormattedTextField txtFechaCompra) {
		this.txtFecha = txtFechaCompra;
	}
	
	

	/*
	public DefaultListModel<ItemPedido> getListModel() {
		return listModel;
	}

	public void setListModel(DefaultListModel<ItemPedido> listModel) {
		this.listModel = listModel;
	}

	public JList<ItemPedido> getList() {
		return list;
	}

	public void setList(JList<ItemPedido> list) {
		this.list = list;
	}
	
	*/
	

}
