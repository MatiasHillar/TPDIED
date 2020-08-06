package gui;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import dominio.util.MatrizFloyd;

public class PanelMatriz extends JPanel {

	private MatrizFloyd mf;
	private static final int GAP = 1;
	private JLabel[][] grid;
	public PanelMatriz(MatrizFloyd mf) {
		super();
		this.mf=mf;
		armarPanel();
	}
	
	private void armarPanel() {
		
		this.setBackground(Color.GRAY);
		Integer tam= mf.getMatriz()[0].length;
		GridLayout gl = new GridLayout(tam+1, tam+1, GAP, GAP);
		this.setLayout(gl);
    	grid = new JLabel[tam+1][tam+1];
//		GridBagLayout gbl = new GridBagLayout();
//		this.setLayout(gbl);
//		GridBagConstraints constraints = new GridBagConstraints();
		
    	
 //       	JPanel sudokuPanel = new JPanel(new GridLayout(9, 9, GAP, GAP));
        this.setBorder(BorderFactory.createEmptyBorder(GAP, GAP, GAP, GAP));
        grid[0][0]= new JLabel(" ", SwingConstants.CENTER);
       	grid[0][0].setOpaque(true);
       	grid[0][0].setBackground(Color.BLACK);
       	this.add(grid[0][0]);
        for(int i = 0; i<tam ; i++) {
        	grid[0][i+1]= new JLabel(mf.getTrad().get(i).getNombre(), SwingConstants.CENTER);
//        	grid[0][i+1]= new JLabel(mf.getTrad().get(i).getNombre());
           	grid[0][i+1].setOpaque(true);
           	grid[0][i+1].setBackground(Color.YELLOW);
           	this.add(grid[0][i+1]);
        }
        	
        for (int row = 0; row < tam; row++) {
        	grid[row+1][0]= new JLabel(mf.getTrad().get(row).getNombre(), SwingConstants.CENTER);
//       	grid[row+1][0].setFont(LABEL_FONT); // make it big
        	grid[row+1][0].setOpaque(true);
        	grid[row+1][0].setBackground(Color.YELLOW);
        	this.add(grid[row+1][0]);
           	for (int col = 0; col < tam; col++) {
           		if(mf.getMatriz()[row][col].equals(Float.MAX_VALUE))
           			grid[row+1][col+1] = new JLabel("INF", SwingConstants.CENTER);
           		else
               	grid[row+1][col+1] = new JLabel(mf.getMatriz()[row][col].toString(), SwingConstants.CENTER);
//               	grid[row+1][col+1].setFont(LABEL_FONT); // make it big
               	grid[row+1][col+1].setOpaque(true);
               	grid[row+1][col+1].setBackground(Color.WHITE);
               	this.add(grid[row+1][col+1]);
           	}
        }

		 
	}
	
}
