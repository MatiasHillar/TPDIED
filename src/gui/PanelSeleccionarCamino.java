package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import controller.*;
import dominio.*;
import gui.util.DatosObligatoriosException;
import prueba.App;

public class PanelSeleccionarCamino extends JPanel {
	
	
	private SeleccionarCaminoController controller;
	private JLabel lblTitulo = new JLabel("Seleccionar camino:");
	private JLabel lblDestino = new JLabel("Planta destino:");
	private JLabel lblOrigen = new JLabel("Planta origen:");
	private JLabel lblCaminosMenosKm = new JLabel("Caminos con menor Km:");
	private JLabel lblCaminosMenosT = new JLabel("Caminos con menor tiempo:");
	private JButton btnBuscarCaminos= new JButton("Buscar caminos");
	private JButton btnElegirCamino= new JButton("Elegir camino");
//	private JButton btnElegirCamino2= new JButton("Elegir camino");
	private JComboBox<Planta> jcbPlantas;
	private JTextField txtPlanta = new JTextField();
	
	private DefaultListModel<List<Ruta>> listModelT = new DefaultListModel<List<Ruta>>();
	private JList<List<Ruta>> listT = new JList<List<Ruta>>(listModelT);
	
	private DefaultListModel<List<Ruta>> listModelKm = new DefaultListModel<List<Ruta>>();
	private JList<List<Ruta>> listKm = new JList<List<Ruta>>(listModelKm);
	
	
	public PanelSeleccionarCamino(App a, List<Planta> pOrigen, Planta destino, Pedido p) {
		super();
		this.controller= new SeleccionarCaminoController(this,a,pOrigen,destino,p);
		this.armarPanel();
	}
	
	private void armarPanel() {
		this.controller.actualizar();
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
		this.add(lblDestino,constraints);
		
		constraints.gridx = 1;
		constraints.insets = new Insets(0, 0, 0, 0);
		this.add(txtPlanta,constraints);
		
		constraints.gridx = 2;
		this.add(lblOrigen,constraints);
		
		constraints.gridx = 3;
		this.add(jcbPlantas, constraints);
		
		constraints.gridx = 4;
		btnBuscarCaminos.addActionListener(e -> {
			try {
				this.controller.caminosKm();
				this.controller.caminosT();
			}
			catch(Exception e1) {
				this.mostrarError("Error al buscar caminos", e1.getMessage());
			}
		});
		this.add(btnBuscarCaminos, constraints);
		
		constraints.gridx = 0;
		constraints.gridy = 3;
		constraints.insets = new Insets(0, 20, 0, 0);
		this.add(lblCaminosMenosT, constraints);

		constraints.gridy = 4;
		JScrollPane scrollPane = new JScrollPane(listT);
		this.add(scrollPane, constraints);
		
	

		constraints.insets = new Insets(0, 0, 0, 0);
		constraints.gridx = 1;
		constraints.gridy = 3;
		this.add(lblCaminosMenosKm, constraints);
		
		constraints.gridy = 4;
		JScrollPane scrollPane2 = new JScrollPane(listKm);
		this.add(scrollPane2, constraints);
		
		constraints.gridy = 5;
		this.btnElegirCamino.addActionListener(e -> {
			try {
				this.controller.seleccionarCamino();
//				this.controller.caminosKm();
//				this.controller.caminosT();
			}
			catch(Exception e1) {
				this.mostrarError("Error al elegir camino", e1.getMessage());
				e1.printStackTrace();
			}
		});
		this.add(btnElegirCamino,constraints);
		
		
	}

	public JComboBox<Planta> getJcbPlantas() {
		return jcbPlantas;
	}

	public void setJcbPlantas(JComboBox<Planta> jcbPlantas) {
		this.jcbPlantas = jcbPlantas;
	}

	public JTextField getTxtPlanta() {
		return txtPlanta;
	}

	public void setTxtPlanta(JTextField txtPlanta) {
		this.txtPlanta = txtPlanta;
	}

	public DefaultListModel<List<Ruta>> getListModelT() {
		return listModelT;
	}

	public void setListModelT(DefaultListModel<List<Ruta>> listModelT) {
		this.listModelT = listModelT;
	}

	public JList<List<Ruta>> getListT() {
		return listT;
	}

	public void setListT(JList<List<Ruta>> listT) {
		this.listT = listT;
	}

	public DefaultListModel<List<Ruta>> getListModelKm() {
		return listModelKm;
	}

	public void setListModelKm(DefaultListModel<List<Ruta>> listModelKm) {
		this.listModelKm = listModelKm;
	}

	public JList<List<Ruta>> getListKm() {
		return listKm;
	}

	public void setListKm(JList<List<Ruta>> listKm) {
		this.listKm = listKm;
	}
	
	public void mostrarError(String titulo,String detalle) {
		JFrame padre= (JFrame) SwingUtilities.getWindowAncestor(this);
		JOptionPane.showMessageDialog(padre,
			    detalle,titulo,
			    JOptionPane.ERROR_MESSAGE);
	}

	
	
}
