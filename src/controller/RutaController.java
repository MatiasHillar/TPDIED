package controller;

import java.util.ArrayList;
import java.util.List;

import dominio.*;
import gui.PanelRutas;
import gui.util.ControllerException;
import gui.util.DatosObligatoriosException;
import gui.util.FormatoNumeroException;
import servicios.RutaService;



public class RutaController {
	
	private RutaService rutaService;
	private Ruta r;
	private List<Ruta> lista;
	private PanelRutas panel;
	
	public RutaController (PanelRutas pr) {
		this.rutaService = new RutaService();
		this.lista = new ArrayList<Ruta>();
		this.panel=pr;
		this.r = new Ruta();
	}
	
	

	public void actualizarRuta() throws DatosObligatoriosException, FormatoNumeroException, ControllerException{
		try {
			if(this.panel.getTxtDistanciaKm()!=null) {
				r.setDistanciaKm(Float.valueOf(this.panel.getTxtDistanciaKm().getText()));
			}
			else throw new DatosObligatoriosException("Distancia en Km", "La distancia en Km de la ruta es obligatoria");
			if(this.panel.getTxtDuracionHs()!=null) {
				r.setDuracionMin(60f *Float.valueOf(this.panel.getTxtDuracionHs().getText()));
			}
			else throw new DatosObligatoriosException("Duracion en Horas", "La distancia en horas de la ruta es obligatoria");
			if(this.panel.getTxtPesoMaximo()!=null) {
				r.setPesoMaximoKg(Float.valueOf(this.panel.getTxtPesoMaximo().getText()));
			}
			else throw new DatosObligatoriosException("Peso maximo en kg", "El peso maximo en kg de la ruta es obligatorio");
			
		}
		catch(NumberFormatException nfe) {
			nfe.printStackTrace();
			throw new FormatoNumeroException("Kilometros", "Debe ingresar un valor numerico");
		} catch(Exception e) {
			e.printStackTrace();
			throw new ControllerException("Error:"+e.getLocalizedMessage());
		}
		if(this.panel.getJcbPlantaOrigen().getSelectedItem()!=null) {
			r.setPlantaOrigen((Planta)(this.panel.getJcbPlantaOrigen().getSelectedItem()));
		}
		else throw new DatosObligatoriosException("Planta origen", "La planta de origen de la ruta es obligatoria");
		if(this.panel.getJcbPlantaDestino().getSelectedItem()!=null) {
			r.setPlantaDestino((Planta)(this.panel.getJcbPlantaDestino().getSelectedItem()));
		}
		else throw new DatosObligatoriosException("Planta destino", "La planta de destino de la ruta es obligatoria");
		
		
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
	
	

}
