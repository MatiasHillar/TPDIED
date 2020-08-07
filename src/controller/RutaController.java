package controller;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFormattedTextField;

import dominio.*;
import gui.PanelRutas;
import gui.util.ControllerException;
import gui.util.DatosObligatoriosException;
import gui.util.FormatoNumeroException;
import servicios.PlantaService;
import servicios.RutaService;



public class RutaController {
	
	private RutaService rutaService;
	private PlantaService plantaService;
	private Ruta r;
	private List<Ruta> lista;
	private PanelRutas panel;
	
	public RutaController (PanelRutas pr) {
		this.rutaService = new RutaService();
		this.plantaService = new PlantaService();
		this.lista = new ArrayList<Ruta>();
		this.panel=pr;
		this.r = new Ruta();
	}
	
	private Float obtenerValor(JFormattedTextField f) {
		if(f.getValue()==null) return 0f;
		return ((Number) f.getValue()).floatValue();
	}

	public void actualizarRuta() throws DatosObligatoriosException, FormatoNumeroException, ControllerException{
		r.setDistanciaKm(obtenerValor(this.panel.getTxtDistanciaKm()));
		r.setDuracionMin(60f *obtenerValor(this.panel.getTxtDuracionHs()));
		r.setPesoMaximoKg(obtenerValor(this.panel.getTxtPesoMaximo()));
		/*String s= "Kilometros";
		
		try {
			if(this.panel.getTxtDistanciaKm()!=null) {
				r.setDistanciaKm(Float.valueOf(this.panel.getTxtDistanciaKm().getText()));
			}
			else throw new DatosObligatoriosException("Distancia en Km", "La distancia en Km de la ruta es obligatoria");
			s="Duracion en horas";
			if(this.panel.getTxtDuracionHs()!=null) {
				r.setDuracionMin(60f *Float.valueOf(this.panel.getTxtDuracionHs().getText()));
			}
			else throw new DatosObligatoriosException("Duracion en Horas", "La distancia en horas de la ruta es obligatoria");
			s="Peso maximo";
			if(this.panel.getTxtPesoMaximo()!=null) {
				r.setPesoMaximoKg(Float.valueOf(this.panel.getTxtPesoMaximo().getText()));
			}
			else throw new DatosObligatoriosException("Peso maximo en kg", "El peso maximo en kg de la ruta es obligatorio");
			
		
		}
		catch(NumberFormatException nfe) {
			nfe.printStackTrace();
			throw new FormatoNumeroException(s, "Debe ingresar un valor numerico");
		} catch(Exception e) {
			e.printStackTrace();
			throw new ControllerException("Error:"+e.getLocalizedMessage());
		}
		*/
		if(!(this.panel.getJcbPlantaOrigen().getSelectedItem() !=null && this.panel.getJcbPlantaOrigen().getSelectedItem() instanceof Planta)) {
			throw new DatosObligatoriosException("Planta", "No hay ninguna planta seleccionada");
		}
		else {
			r.setPlantaOrigen((Planta)(this.panel.getJcbPlantaOrigen().getSelectedItem()));
		}
		if(!(this.panel.getJcbPlantaDestino().getSelectedItem() !=null && this.panel.getJcbPlantaDestino().getSelectedItem() instanceof Planta)) {
			throw new DatosObligatoriosException("Planta", "No hay ninguna planta seleccionada");
		}
		else {
			r.setPlantaDestino((Planta)(this.panel.getJcbPlantaDestino().getSelectedItem()));
		}
		
		/*
		if(this.panel.getJcbPlantaOrigen().getSelectedItem()!=null) {
			r.setPlantaOrigen((Planta)(this.panel.getJcbPlantaOrigen().getSelectedItem()));
		}
		else throw new DatosObligatoriosException("Planta origen", "La planta de origen de la ruta es obligatoria");
		if(this.panel.getJcbPlantaDestino().getSelectedItem()!=null) {
			r.setPlantaDestino((Planta)(this.panel.getJcbPlantaDestino().getSelectedItem()));
		}
		else throw new DatosObligatoriosException("Planta destino", "La planta de destino de la ruta es obligatoria");
		*/
		
	}
	
	public Ruta guardar()throws DatosObligatoriosException, FormatoNumeroException, ControllerException {
		this.actualizarRuta();
		this.rutaService.crearRuta(r);
		this.lista.clear();
		this.lista.addAll(rutaService.buscarTodos());
		return r;
	}
	
	public List<Ruta> listarTodas(){
		this.lista.clear();
		this.lista.addAll(rutaService.buscarTodos()); 
		System.out.println("Resultado res   "+lista);
		return this.lista;
	}
	
	public List<Planta> listarTodasPlantas(){
		return this.plantaService.buscarTodos();
	}
	
	

}
