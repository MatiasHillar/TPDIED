package controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;

import dominio.Insumo;
import dominio.Planta;
import dominio.Stock;
import gui.PanelStockFaltante;
import gui.util.ControllerException;
import gui.util.DatosObligatoriosException;
import gui.util.FormatoNumeroException;
import servicios.InsumoService;
import servicios.PlantaService;
import servicios.StockService;

public class StockFaltanteController {

	private PanelStockFaltante panel;
	private PlantaService plantaService;
	private StockService stockService;
	private InsumoService insumoService;
	
	public StockFaltanteController(PanelStockFaltante panel) {
		this.stockService = new StockService();
		this.plantaService = new PlantaService();
		this.insumoService = new InsumoService();
		this.panel = panel;
	}
	private Float obtenerValor(JFormattedTextField f) {
		if(f.getValue()==null) return 0f;
		return ((Number) f.getValue()).floatValue();
	}
	
	public void buscar() {
		/*		
		Planta p= null;
		Insumo ins=null;
		if(this.panel.getJcbPlantas().getSelectedItem() !=null && this.panel.getJcbPlantas().getSelectedItem() instanceof Planta) {
			p= (Planta)this.panel.getJcbPlantas().getSelectedItem();
		}
		if(this.panel.getJcbInsumos().getSelectedItem() !=null && this.panel.getJcbInsumos().getSelectedItem() instanceof Insumo) {
			ins = (Insumo)this.panel.getJcbInsumos().getSelectedItem();
		}
		*/
		
		/*
		List<Stock> stocks= this.stockService.buscarTodos().stream()
				.filter(s -> (!(this.panel.getJcbPlantas().getSelectedItem() !=null && this.panel.getJcbPlantas().getSelectedItem() instanceof Planta)
						|| s.getP().equals((Planta)this.panel.getJcbPlantas().getSelectedItem()))
						&& 
						(!(this.panel.getJcbInsumos().getSelectedItem() !=null && this.panel.getJcbInsumos().getSelectedItem() instanceof Insumo) 
								|| s.getInsumo().equals((Insumo)this.panel.getJcbInsumos().getSelectedItem())) )
				.collect(Collectors.toList());
				
				*/
		if(this.panel.getJcbPlantas().getSelectedItem() != null)
			System.out.println((Planta)this.panel.getJcbPlantas().getSelectedItem());
		else System.out.println("F");
		if(this.panel.getJcbInsumos().getSelectedItem() != null)
			System.out.println((Insumo)this.panel.getJcbInsumos().getSelectedItem());
		else System.out.println("F");
		
		List<Stock> stocks= this.stockService.buscarTodos().stream()
				.filter(s -> (this.panel.getJcbPlantas().getSelectedItem() ==null || s.getP().equals((Planta)this.panel.getJcbPlantas().getSelectedItem()))
						&& 
						(this.panel.getJcbInsumos().getSelectedItem() ==null || s.getInsumo().equals((Insumo)this.panel.getJcbInsumos().getSelectedItem())) )
				.collect(Collectors.toList());
		
		
		this.panel.getModeloTablaStock().getData().clear();
		
		this.panel.getModeloTablaStock().getData().addAll(stocks);
		this.panel.getModeloTablaStock().fireTableDataChanged();
		
	}
	/*
	public void buscarTodo() {
		List<Stock> stocks= this.stockService.buscarTodos();
		this.panel.getModeloTablaStock().getData().clear();
		
		this.panel.getModeloTablaStock().getData().addAll(stocks);
		this.panel.getModeloTablaStock().fireTableDataChanged();
		
	}*/
	
	
	public void setJcbs() {
		this.panel.setJcbPlantas(new JComboBox<Planta>(this.plantaService.buscarTodos().toArray(new Planta[0])));
		this.panel.getJcbPlantas().addItem(null);
		this.panel.getJcbPlantas().setSelectedIndex(this.panel.getJcbPlantas().getItemCount()-1);
		this.panel.getJcbPlantas().addActionListener(e->{
			this.panel.setJcbPlantas(((JComboBox<Planta>)e.getSource()));
		});
		this.panel.setJcbInsumos(new JComboBox<Insumo>( this.insumoService.buscarTodos().toArray(new Insumo[0])));
		this.panel.getJcbInsumos().addItem(null);
		this.panel.getJcbInsumos().setSelectedIndex(this.panel.getJcbInsumos().getItemCount()-1);
		this.panel.getJcbInsumos().addActionListener(e->{
			this.panel.setJcbInsumos(((JComboBox<Insumo>)e.getSource()));
		});
	}
	 
}
