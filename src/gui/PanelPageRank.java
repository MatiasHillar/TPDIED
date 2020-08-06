package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import dominio.Planta;
import dominio.Stock;
import servicios.MapaService;



public class PanelPageRank extends JPanel {

	private JLabel lblTitulo = new JLabel("Page Rank:");
	
	private MapaService ms;
	private JTable tablaPageRank;
	private PageRankTableModel modeloTablaPageRank; 
	
	public PanelPageRank(){
		super();
		ms= new MapaService();
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
		
		HashMap<Planta,Double> pageRank = this.ms.pageRank(ms.construir());
		List<Planta> ps = pageRank.keySet().stream().collect(Collectors.toList());
		
		constraints.insets = new Insets(10, 50, 10, 50);
		modeloTablaPageRank = new PageRankTableModel(ps, pageRank);
		tablaPageRank = new JTable();
		tablaPageRank.setModel(modeloTablaPageRank);
		JScrollPane scrollPane = new JScrollPane(tablaPageRank);
		tablaPageRank.setFillsViewportHeight(true);
		constraints.gridx = 0;
		constraints.gridy = 2;		
		constraints.gridwidth = 11;
		constraints.weighty=1;
		constraints.weightx=2;
		constraints.anchor = GridBagConstraints.NORTH;
		constraints.fill= GridBagConstraints.HORIZONTAL;
		this.add(scrollPane,constraints);
		
	}
}
