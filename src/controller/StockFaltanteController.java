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
		List<Stock> stocks= this.stockService.buscarTodos().stream()
				.filter(s -> (!(this.panel.getJcbPlantas().getSelectedItem() !=null && this.panel.getJcbPlantas().getSelectedItem() instanceof Planta)
						|| s.getP().equals((Planta)this.panel.getJcbPlantas().getSelectedItem()))
						&& 
						(!(this.panel.getJcbInsumos().getSelectedItem() !=null && this.panel.getJcbInsumos().getSelectedItem() instanceof Insumo) 
								|| s.getInsumo().equals((Insumo)this.panel.getJcbInsumos().getSelectedItem())) )
				.collect(Collectors.toList());
		this.panel.getModeloTablaStock().getData().clear();
		
		this.panel.getModeloTablaStock().getData().addAll(stocks);
		this.panel.getModeloTablaStock().fireTableDataChanged();
		
	}
	
	public void setJcbs() {
		this.panel.setJcbPlantas(new JComboBox<Planta>(this.plantaService.buscarTodos().toArray(new Planta[0])));
		this.panel.setJcbInsumos(new JComboBox<Insumo>( this.insumoService.buscarTodos().toArray(new Insumo[0])));
	}
	 
}
