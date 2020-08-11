package controller;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;

import dominio.*;
import gui.PanelAltaCamiones;
import gui.PanelStock;
import gui.util.ControllerException;
import gui.util.DatosObligatoriosException;
import gui.util.FormatoNumeroException;
import servicios.*;

public class StockController {

	
	
	private PanelStock panel;
	private Stock s;
	private PlantaService plantaService;
	private StockService stockService;
	private InsumoService insumoService;
	
	public StockController(PanelStock panel) {
		this.stockService = new StockService();
		this.plantaService = new PlantaService();
		this.insumoService = new InsumoService();
		this.panel = panel;
		s= new Stock();
	}
	private Float obtenerValor(JFormattedTextField f) {
		if(f.getValue()==null) return 0f;
		return ((Number) f.getValue()).floatValue();
	}
	
	
	public void altaModelo() throws DatosObligatoriosException,FormatoNumeroException,ControllerException {
			if(!(this.panel.getJcbPlantas().getSelectedItem() !=null && this.panel.getJcbPlantas().getSelectedItem() instanceof Planta)) {
				throw new DatosObligatoriosException("Planta", "La planta es obligatoria");
			}
			if(!(this.panel.getJcbInsumos().getSelectedItem() !=null && this.panel.getJcbInsumos().getSelectedItem() instanceof Insumo)) {
				throw new DatosObligatoriosException("Insumo", "El insumo es obligatorio");
			}
			s.setInsumo((Insumo)this.panel.getJcbInsumos().getSelectedItem());
			s.setCtidad(this.obtenerValor(this.panel.getTxtCantidadInsumo()));
			s.setPuntoRepo(this.obtenerValor(this.panel.getTxtPuntoPedido()));
			s.setP((Planta)this.panel.getJcbPlantas().getSelectedItem());
			
		
	}
	
	
	
	public Stock guardar() throws DatosObligatoriosException, FormatoNumeroException, ControllerException {
		this.altaModelo();
		stockService.crearStock(s);
		return s;
	}
	
	public void setJcbs() {
		this.panel.setJcbPlantas(new JComboBox<Planta>(this.plantaService.buscarTodos().toArray(new Planta[0])));
		System.out.println("Hola");
		this.panel.setJcbInsumos(new JComboBox<Insumo>( this.insumoService.buscarTodos().toArray(new Insumo[0])));
	}
	
	 
}
