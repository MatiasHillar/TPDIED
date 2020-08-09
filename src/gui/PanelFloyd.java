package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import servicios.MapaService;

public class PanelFloyd extends JPanel {

//	private JTextArea floydKm;
//	private JTextArea floydT;
	private MapaService ms;
	private JLabel lblTitulo = new JLabel("Camino minimo:");
	private JLabel lblKm = new JLabel("Camino minimo en KM:");
	private JLabel lblT = new JLabel("Camino minimo en minutos:");
	public PanelFloyd() {
//		floydKm= new JTextArea();
//		floydT = new JTextArea();
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
		
		constraints.insets = new Insets(0, 100, 10, 0);
		constraints.gridheight=1;
		constraints.gridwidth = 1;
		constraints.gridy=2;
		this.add(lblKm,constraints);
		constraints.gridy=3;
		this.add(new PanelMatriz(ms.caminosMenosKm()),constraints);
		
		

		
//		constraints.gridx=1;
//		constraints.gridy=2;
		constraints.gridy=4;
		this.add(lblT,constraints);
//		constraints.gridy=3;
		constraints.gridy=5;
		this.add(new PanelMatriz(ms.caminosMenosTiempo()),constraints);
		
		
		//floydKm.setText(t);
		
		
	}
	
}
