package prueba;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;

import gui.PanelInsumosGenerales;
import gui.PanelInsumosLiquidos;
import gui.PanelPlantas;
import gui.PanelRutas;
import gui.PanelStock;
import dominio.*;
import servicios.MapaService;

public class App extends JFrame{
	
	
	JMenuBar menuBar;
	JMenu menuPlantas;
	JMenu menuRutas;
	JMenu menuInsumos;
	JMenu menuStock;
	JMenuItem menuItemPlantas;
	JMenuItem menuItemRutas;
	JMenuItem menuItemInsumosGenerales;
	JMenuItem menuItemInsumosLiquidos;
	JMenuItem menuItemStock;
	
	
	private App() {
	}
	
	private void armarApp() {
		this.menuBar = new JMenuBar();
		this.menuPlantas = new JMenu("Plantas");
		this.menuRutas = new JMenu("Rutas");
		this.menuInsumos = new JMenu("Insumos");
		this.menuStock = new JMenu("Stock");
		
		
		this.menuItemPlantas = new JMenuItem("Gestionar Plantas");
		this.menuItemPlantas.addActionListener( e -> {
			System.out.println("LISTENER 1");
			this.setContentPane(new PanelPlantas());
			//this.pack();
			this.revalidate();
			this.repaint();
		});
		
		this.menuItemRutas = new JMenuItem("Gestionar Rutas");
		this.menuItemRutas.addActionListener( e -> {
			System.out.println("LISTENER 2");
			this.setContentPane(new PanelRutas());
			//this.pack();
			this.revalidate();
			this.repaint();
		});
		
		this.menuItemInsumosGenerales = new JMenuItem("Gestionar Insumos Generales");
		this.menuItemInsumosGenerales.addActionListener( e -> {
			System.out.println("LISTENER 3");
			this.setContentPane(new PanelInsumosGenerales());
			//this.pack();
			this.revalidate();
			this.repaint();
		});
		
		this.menuItemInsumosLiquidos = new JMenuItem("Gestionar Insumos Liquidos");
		this.menuItemInsumosLiquidos.addActionListener( e -> {
			System.out.println("LISTENER 4");
			this.setContentPane(new PanelInsumosLiquidos());
			//this.pack();
			this.revalidate();
			this.repaint();
		});
		
		this.menuItemStock = new JMenuItem("Actualizar Stock");
		this.menuItemStock.addActionListener( e -> {
			System.out.println("LISTENER 5");
			this.setContentPane(new PanelStock());
			//this.pack();
			this.revalidate();
			this.repaint();
		});
		
		this.menuPlantas.add(menuItemPlantas);
		this.menuRutas.add(menuItemRutas);
		this.menuInsumos.add(menuItemInsumosGenerales);
		this.menuInsumos.add(menuItemInsumosLiquidos);
		this.menuStock.add(menuItemStock);
		
		
		menuBar.add(menuPlantas);
		menuBar.add(menuRutas);
		menuBar.add(menuInsumos);
		menuBar.add(menuStock);
		
		this.setJMenuBar(menuBar);
	
//	this.addMouseListener(new MouseListener() {
//		@Override
//		public void mouseReleased(MouseEvent e) {
//			// TODO Auto-generated method stub
//			System.out.println("mouseReleased en "+e.getPoint());
//
//		}
//		
//		@Override
//		public void mousePressed(MouseEvent e) {
//			// TODO Auto-generated method stub
//			System.out.println("mousePressed en "+e.getPoint());
//
//		}
//		
//		@Override
//		public void mouseExited(MouseEvent e) {
//			// TODO Auto-generated method stub
//			
//		}
//		
//		@Override
//		public void mouseEntered(MouseEvent e) {
//			// TODO Auto-generated method stub
//			
//		}
//		
//		@Override
//		public void mouseClicked(MouseEvent e) {
//			System.out.println("Click en "+e.getPoint());
//		}	
//	});
}
		

	public static void main(String[] args) {
		
//		MapaService ms= new MapaService();
//		Planta p1 = new Planta("p1", 1, new ArrayList<Stock>());
//		Planta p2 = new Planta("p2", 2, new ArrayList<Stock>());
//		Planta p3 = new Planta("p3", 3, new ArrayList<Stock>());
//		Planta p4 = new Planta("p4", 4, new ArrayList<Stock>());
//		Planta p5 = new Planta("p5", 5, new ArrayList<Stock>());
//		System.out.println(ms.maxFlow(p1, p5));
		
		
		//ACA ABAJO ESTA LO DEL COLO

	
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				 try {
			            // Set System L&F
//			          UIManager.setLookAndFeel(
//			          UIManager.getSystemLookAndFeelClassName());
//					  UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");			    }
//					  UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
					  UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
//					  UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			}
			catch (UnsupportedLookAndFeelException e) {
				       // handle exception
			}
			catch (ClassNotFoundException e) {
				       // handle exception
			}
		    catch (InstantiationException e) {
				       // handle exception
		    }
		    catch (IllegalAccessException e) {
				       // handle exception
		    }
			App app = new App();
			app.setTitle("Sistema de gestion log�stica - TP DIED 2020 ");
			app.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
			app.armarApp();
			app.setExtendedState(app.getExtendedState() | JFrame.MAXIMIZED_BOTH);
			app.setVisible(true);
			System.out.println("app creada");
		}
	});
	}

}
