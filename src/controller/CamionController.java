package controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFormattedTextField;

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
	
	private Float obtenerValor(JFormattedTextField f) {
		if(f==null) return 0f;
		return ((Number) f.getValue()).floatValue();
	}
	
	public void actualizarModelo() throws DatosObligatoriosException,FormatoNumeroException,ControllerException {
		String s="Kilometros";
		try {
			if(this.panel.getTxtPatente()!=null && !this.panel.getTxtPatente().getText().equals("")) {
				c.setPatente(this.panel.getTxtPatente().getText()); 
			} else {
				throw new DatosObligatoriosException("Patente", "La patente es obligatoria");
			}
			Modelo m = new Modelo();
			if(this.panel.getTxtModelo()!=null && !this.panel.getTxtModelo().getText().equals("")) m.setModelo(this.panel.getTxtModelo().getText()); 
			else throw new DatosObligatoriosException("Modelo", "El Modelo es obligatorio");
			if(this.panel.getTxtMarca()!=null && !this.panel.getTxtMarca().getText().equals("")) m.setMarca(this.panel.getTxtMarca().getText());  
			else throw new DatosObligatoriosException("Marca", "La marca es obligatoria");
			c.setModelo(m);
//			if(this.panel.getTxtKm()!=null) c.setKmRecorridos(Float.valueOf(this.panel.getTxtKm().getText()));
//			if(this.panel.getTxtKm()!=null) c.setKmRecorridos(((Number) this.panel.getTxtKm().getValue()).floatValue());
			c.setKmRecorridos(obtenerValor(this.panel.getTxtKm()));
			c.setCostoHora(this.obtenerValor(this.panel.getCostoHora()));
			c.setCostoKm(this.obtenerValor(this.panel.getCostoKm()));
			if(this.panel.getTxtFechaCompra()!=null) c.setFechaCompra(LocalDate.parse(this.panel.getTxtFechaCompra().getText(),DateTimeFormatter.ofPattern("dd/MM/yyyy")));
		} catch(NumberFormatException nfe) {
			nfe.printStackTrace();
			throw new FormatoNumeroException(s, "Debe ingresar un valor numerico");
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
