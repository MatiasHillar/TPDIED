package controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import dominio.*;
import gui.*;
import gui.util.*;
import servicios.*;



public class CamionController {
	
	private CamionService camionService;
	private Camion c;
	private List<Camion> lista;
	private PanelCamiones panel;
	
	public CamionController(PanelCamiones p) {
		this.camionService = new CamionService();
		this.lista = new ArrayList<Camion>();
		this.panel = p;
		c = new Camion();
	}
	
	public void actualizarModelo() throws DatosObligatoriosException,FormatoNumeroException,ControllerException {
		try {
			if(this.panel.getTxtPatente()!=null) {
				c.setPatente(this.panel.getTxtPatente().getText()); 
			} else {
				throw new DatosObligatoriosException("Patente", "La patente es obligatoria");
			}
			Modelo m = new Modelo();
			
			if(this.panel.getTxtModelo()!=null) m.setModelo(this.panel.getTxtModelo().getText()); 
			if(this.panel.getTxtMarca()!=null) m.setMarca(this.panel.getTxtMarca().getText()); 
			c.setModelo(m);
			if(this.panel.getTxtKm()!=null) c.setKmRecorridos(Float.valueOf(this.panel.getTxtKm().getText())); 
			if(this.panel.getTxtFechaCompra()!=null) c.setFechaCompra(LocalDate.parse(this.panel.getTxtFechaCompra().getText(),DateTimeFormatter.ofPattern("dd/MM/yyyy")));
		} catch(NumberFormatException nfe) {
			nfe.printStackTrace();
			throw new FormatoNumeroException("Kilometros", "Debe ingresar un valor numerico");
		} catch(Exception e) {
			e.printStackTrace();
			throw new ControllerException("Error:"+e.getLocalizedMessage());
		}
	}
	
	public Camion guardar() throws DatosObligatoriosException, FormatoNumeroException, ControllerException {
		this.actualizarModelo();
		System.out.println("ACTUALIZADO "+c.toString());
		camionService.crearCamion(c);
		this.lista.clear();
		this.lista.addAll(camionService.buscarTodos()); 
		return null;
	}
	
	public List<Camion> listarTodos(){
		this.lista.clear();
		this.lista.addAll(camionService.buscarTodos()); 
		System.out.println("Resultado res   "+lista);
		return this.lista;
	}

}
