package controller;

import java.util.ArrayList;
import java.util.List;


import dominio.Planta;
import gui.PanelPlantas;
import gui.util.*;
import servicios.PlantaService;



public class PlantaController {
	private PlantaService plantaService;
	private Planta p;
	private List<Planta> lista;
	private PanelPlantas panel;
	
	public PlantaController(PanelPlantas p) {
		this.plantaService= new PlantaService();
		this.lista = new ArrayList<Planta>();
		this.panel=p;
		this.p= new Planta();
	}
	public PlantaController() {
		this.plantaService= new PlantaService();
		this.lista = new ArrayList<Planta>();
		this.p= new Planta();
	}
	
	public void actualizarPlanta() throws DatosObligatoriosException {
		if(this.panel.getTxtNombre()!=null) {
			p.setNombre(this.panel.getTxtNombre().getText());
		}
		else throw new DatosObligatoriosException("Nombre", "El nombre de la planta es obligatorio");	
	}
	
	public Planta guardar()throws DatosObligatoriosException{
		this.actualizarPlanta();
		plantaService.crearPlanta(p);
		this.lista.clear();
		this.lista.addAll(plantaService.buscarTodos());
		return p;
	}
	public List<Planta> listarTodos(){
		this.lista.clear();
		this.lista.addAll(plantaService.buscarTodos()); 
		System.out.println("Resultado res   "+lista);
		return this.lista;
	}
	

}
