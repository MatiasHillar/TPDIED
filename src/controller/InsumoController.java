package controller;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JFormattedTextField;

import dominio.*;
import gui.PanelAltaCamiones;
import gui.PanelInsumosGenerales;
import gui.PanelInsumosLiquidos;
import gui.util.*;
import servicios.InsumoService;

public class InsumoController {
	private InsumoService insumoService;
	private InsumoGral ig;
	private InsumoLiquido il;
	private List<Insumo> lista;
	private PanelInsumosGenerales panelGral;
	private PanelInsumosLiquidos panelLiquido;
	
	public InsumoController(PanelInsumosGenerales panelGral) {
		this.insumoService = new InsumoService();
		this.lista = new ArrayList<Insumo>();
		this.panelGral=panelGral;
		this.ig = new InsumoGral();
		this.il=null;
		this.panelLiquido=null;
	}
	
	public InsumoController(PanelInsumosLiquidos panelLiquido) {
		this.insumoService = new InsumoService();
		this.lista = new ArrayList<Insumo>();
		this.panelLiquido=panelLiquido;
		this.il = new InsumoLiquido();
		this.panelGral=null;
		this.ig = null;
	}
	
	public InsumoController() {
		this.insumoService = new InsumoService();
		this.lista = new ArrayList<Insumo>();
		this.panelGral=null;
		this.ig = null;
		this.il=null;
		this.panelLiquido=null;
	}
	
	
	public void actualizarInsumo() throws DatosObligatoriosException, FormatoNumeroException, ControllerException{
		if(ig!=null)
			this.actualizarInsumoGral();
		if(il!=null)
			this.actualizarInsumoLiquido();
		
	}
	
	private Float obtenerValor(JFormattedTextField f) {
		if(f==null) return 0f;
		return ((Number) f.getValue()).floatValue();
	}
	
	private void actualizarInsumoGral() throws DatosObligatoriosException, FormatoNumeroException, ControllerException{
		String s= "Costo";
		try {
			ig.setCosto(obtenerValor(this.panelGral.getTxtCosto()));
			ig.setPeso(obtenerValor(this.panelGral.getTxtPeso()));

			/*
			if(panelGral.getTxtCosto()!=null) {
				this.ig.setCosto(Float.valueOf(this.panelGral.getTxtCosto().getText()));
			}
			else throw new DatosObligatoriosException("Costo", "El costo es obligatorio");
			s="Peso por unidad";
			if(this.panelGral.getTxtPeso()!=null) {
				this.ig.setPeso(Float.valueOf(this.panelGral.getTxtPeso().getText()));
			}
			else throw new DatosObligatoriosException("Peso por unidad", "El peso por unidad es obligatorio");
			*/
		}
		catch(NumberFormatException nfe) {
			nfe.printStackTrace();
			throw new FormatoNumeroException(s, "Debe ingresar un valor numerico");
		} catch(Exception e) {
			e.printStackTrace();
			throw new ControllerException("Error:"+e.getLocalizedMessage());
		}
		if(this.panelGral.getTxtNombre()!=null && !this.panelGral.getTxtNombre().equals("")) {
			this.ig.setNombre(this.panelGral.getTxtNombre().getText());
		}
		else throw new DatosObligatoriosException("Nombre", "El nombre es obligatorio");
		if(this.panelGral.getJcbUnidades().getSelectedItem()!=null) {
			this.ig.setUnidadMedida((Unidad) this.panelGral.getJcbUnidades().getSelectedItem());
		}
		else throw new DatosObligatoriosException("Unidad", "La unidad es obligatoria");
		
		if(this.panelGral.getTxtADescripcion()!=null) {
			this.ig.setDescripcion(this.panelGral.getTxtADescripcion().getText());
		}
		
		
	}
	private void actualizarInsumoLiquido() throws DatosObligatoriosException, FormatoNumeroException, ControllerException{
		String s= "Costo";
		try {
			

			il.setCosto(obtenerValor(this.panelLiquido.getTxtCosto()));
			il.setDensidad(obtenerValor(this.panelLiquido.getTxtDensidad()));
			/*
			
			if(panelLiquido.getTxtCosto()!=null) {
				this.il.setCosto(Float.valueOf(this.panelLiquido.getTxtCosto().getText()));
			}
			else throw new DatosObligatoriosException("Costo", "El costo es obligatorio");
			s="Densidad";
			if(this.panelLiquido.getTxtDensidad()!=null) {
				this.il.setDensidad(Float.valueOf(this.panelLiquido.getTxtDensidad().getText()));
			}
			else throw new DatosObligatoriosException("Densidad", "La densidad es obligatoria");
			*/
		}
		catch(NumberFormatException nfe) {
			nfe.printStackTrace();
			throw new FormatoNumeroException(s, "Debe ingresar un valor numerico");
		} catch(Exception e) {
			e.printStackTrace();
			throw new ControllerException("Error:"+e.getLocalizedMessage());
		}
		if(this.panelLiquido.getTxtNombre()!=null) {
			this.il.setNombre(this.panelLiquido.getTxtNombre().getText());
		}
		else throw new DatosObligatoriosException("Nombre", "El nombre es obligatorio");
		if(this.panelLiquido.getJcbUnidades().getSelectedItem()!=null) {
			this.il.setUnidadMedida((Unidad) this.panelLiquido.getJcbUnidades().getSelectedItem());
		}
		else throw new DatosObligatoriosException("Unidad", "La unidad es obligatoria");
		
		if(this.panelLiquido.getTxtADescripcion()!=null) {
			this.il.setDescripcion(this.panelLiquido.getTxtADescripcion().getText());
		}
	}
	
	public void prepararM(PanelInsumosGenerales p, InsumoGral i) {
		p.getTxtNombre().setText(i.getNombre());
		p.getTxtADescripcion().setText(i.getDescripcion());
		p.getTxtCosto().setValue(i.getCosto());		
		p.getTxtPeso().setValue(i.pesoPorUnidad());
		
	}
	
	public void prepararM(PanelInsumosLiquidos p, InsumoLiquido i) {
		p.getTxtNombre().setText(i.getNombre());
		p.getTxtADescripcion().setText(i.getDescripcion());
		p.getTxtCosto().setValue(i.getCosto());		
		p.getTxtDensidad().setValue(i.getDensidad());
		
	}
	
	public void eliminar(Insumo i) {
		this.insumoService.borrarInsumo(i);
	}
	
	public Insumo guardar()throws DatosObligatoriosException, FormatoNumeroException, ControllerException {
		this.actualizarInsumo();
		if(ig!=null)
			this.insumoService.crearInsumoGral(ig);
		else this.insumoService.crearInsumoLiquido(il);
		this.lista.clear();
		this.lista.addAll(insumoService.buscarTodos());
		if(ig!=null) return ig;
		else return il;
	}
	
	public List<Insumo> listarTodos(){
		this.lista.clear();
		this.lista.addAll(insumoService.buscarTodos()); 
		System.out.println("Resultado res   "+lista);
		return this.lista;
	}


}
