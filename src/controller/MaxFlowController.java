package controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;

import dominio.*;
import gui.PanelMaxFlow;
import gui.PanelStockFaltante;
import gui.util.DatosObligatoriosException;
import servicios.InsumoService;
import servicios.MapaService;
import servicios.PlantaService;
import servicios.StockService;

public class MaxFlowController {

	private PanelMaxFlow panel;
	private MapaService mapaService;
	private PlantaService plantaService;
//	private InsumoService insumoService;
	
	public MaxFlowController(PanelMaxFlow panel) {
		this.mapaService = new MapaService();
		this.plantaService = new PlantaService();
//		this.insumoService = new InsumoService();
		this.panel = panel;
	}
	private Float obtenerValor(JFormattedTextField f) {
		if(f.getValue()==null) return 0f;
		return ((Number) f.getValue()).floatValue();
	}
	
	public void buscar() throws DatosObligatoriosException {
				
		Planta p= null, pd=null;
		if(this.panel.getJcbPlantasOrigen().getSelectedItem() !=null && this.panel.getJcbPlantasOrigen().getSelectedItem() instanceof Planta) {
			p= (Planta)this.panel.getJcbPlantasOrigen().getSelectedItem();
		}
		else throw new DatosObligatoriosException("Planta origen", "La planta de origen es obligatoria");
		if(this.panel.getJcbPlantasDestino().getSelectedItem() !=null && this.panel.getJcbPlantasDestino().getSelectedItem() instanceof Planta) {
			pd = (Planta)this.panel.getJcbPlantasDestino().getSelectedItem();
		}
		else throw new DatosObligatoriosException("Planta destino", "La planta de destino es obligatoria");
		
		this.panel.getTxtFlujo().setValue(this.mapaService.maxFlow(p, pd));
		
		
	}
	
	public void setJcbs() {
		Planta[] plantas = this.plantaService.buscarTodos().toArray(new Planta[0]);
		this.panel.setJcbPlantasOrigen(new JComboBox<Planta>(plantas));
		this.panel.setJcbPlantasDestino(new JComboBox<Planta>(plantas));
	}
}
