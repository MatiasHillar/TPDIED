package controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

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
	private PanelAltaCamiones panelAlta;
	
	public CamionController(PanelCamiones p) {
		this.camionService = new CamionService();
		this.lista = new ArrayList<Camion>();
		this.panel = p;
		c = new Camion();
	}
	
	public CamionController(PanelAltaCamiones p) {
		this.camionService = new CamionService();
		this.lista = new ArrayList<Camion>();
		this.panelAlta = p;
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
	
	
	
	public void altaModelo() throws DatosObligatoriosException,FormatoNumeroException,ControllerException {
		String s="Kilometros";
		try {
			if(this.panelAlta.getTxtPatente()!=null && !this.panelAlta.getTxtPatente().getText().equals("")) {
				c.setPatente(this.panelAlta.getTxtPatente().getText()); 
			} else {
				throw new DatosObligatoriosException("Patente", "La patente es obligatoria");
			}
			Modelo m = new Modelo();
			if(this.panelAlta.getTxtModelo()!=null && !this.panelAlta.getTxtModelo().getText().equals("")) m.setModelo(this.panelAlta.getTxtModelo().getText()); 
			else throw new DatosObligatoriosException("Modelo", "El Modelo es obligatorio");
			if(this.panelAlta.getTxtMarca()!=null && !this.panelAlta.getTxtMarca().getText().equals("")) m.setMarca(this.panelAlta.getTxtMarca().getText());  
			else throw new DatosObligatoriosException("Marca", "La marca es obligatoria");
			c.setModelo(m);
//			if(this.panel.getTxtKm()!=null) c.setKmRecorridos(Float.valueOf(this.panel.getTxtKm().getText()));
//			if(this.panel.getTxtKm()!=null) c.setKmRecorridos(((Number) this.panel.getTxtKm().getValue()).floatValue());
			c.setKmRecorridos(obtenerValor(this.panelAlta.getTxtKm()));
			c.setCostoHora(this.obtenerValor(this.panelAlta.getCostoHora()));
			c.setCostoKm(this.obtenerValor(this.panelAlta.getCostoKm()));
			if(this.panelAlta.getTxtFechaCompra()!=null) c.setFechaCompra(LocalDate.parse(this.panelAlta.getTxtFechaCompra().getText(),DateTimeFormatter.ofPattern("dd/MM/yyyy")));
		} catch(NumberFormatException nfe) {
			nfe.printStackTrace();
			throw new FormatoNumeroException(s, "Debe ingresar un valor numerico");
		} catch(Exception e) {
			e.printStackTrace();
			throw new ControllerException("Error:"+e.getLocalizedMessage());
		}
	}
	
	
	
	public Camion guardar() throws DatosObligatoriosException, FormatoNumeroException, ControllerException {
		if(this.panel!=null) this.actualizarModelo();
		else this.altaModelo();
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
	
	
	public List<Camion> buscarPorAtributos()  throws FormatoNumeroException, ControllerException{
		LinkedHashMap<String,Object> atr= new LinkedHashMap<String,Object>();
		String s="Kilometros";
		Integer campos = 0;
		try {
			if(this.panel.getTxtPatente()!=null && !this.panel.getTxtPatente().getText().equals("")) {
				atr.put("PATENTE", this.panel.getTxtPatente().getText());
				campos++;
			} 
			if(this.panel.getTxtModelo()!=null && !this.panel.getTxtModelo().getText().equals("")) {
				atr.put("MODELO", this.panel.getTxtModelo().getText());
				campos++;
			}
				
			if(this.panel.getTxtMarca()!=null && !this.panel.getTxtMarca().getText().equals("")) {
				atr.put("MARCA", this.panel.getTxtMarca().getText());
				campos++;
			}
			
			
			atr.put("KMMIN" ,obtenerValor(this.panel.getTxtKm()));
			if((Float)atr.get("KMMIN")!=0f) campos++;
			atr.put("KMMAX" ,obtenerValor(this.panel.getTxtKmMax()));
			if((Float)atr.get("KMMAX")!=340282346638f) campos++;
			s="Costo";
			atr.put("COSTOHSMIN",this.obtenerValor(this.panel.getCostoHora()));
			if((Float)atr.get("COSTOHSMIN")!=0f) campos++;
			atr.put("COSTOHSMAX",this.obtenerValor(this.panel.getCostoHoraMax()));
			if((Float)atr.get("COSTOHSMAX")!=340282346638f) campos++;
			atr.put("COSTOKMMIN",this.obtenerValor(this.panel.getCostoKm()));
			if((Float)atr.get("COSTOKMMIN")!=0f) campos++;
			atr.put("COSTOKMMAX",this.obtenerValor(this.panel.getCostoKmMax()));
			if((Float)atr.get("COSTOKMMAX")!=340282346638f) campos++;
			
		} catch(NumberFormatException nfe) {
			nfe.printStackTrace();
			throw new FormatoNumeroException(s, "Debe ingresar un valor numerico");
		} catch(Exception e) {
			e.printStackTrace();
			throw new ControllerException("Error:"+e.getLocalizedMessage());
		}
		
		
		this.lista.clear();
		if(campos!=0)this.lista.addAll(this.camionService.buscarPorAtributos(atr));
		else this.lista.addAll(this.camionService.buscarTodos());
		return this.lista;
	}
	
	

}
