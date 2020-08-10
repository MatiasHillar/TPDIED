package controller;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;

import dominio.*;
import gui.PanelProcesarPedido;
import gui.PanelSeleccionarCamino;
import gui.util.*;
import prueba.App;
import servicios.*;

public class SeleccionarCaminoController {
	private PedidoService pedidoService;
	private Pedido p;
	private App a;
	private List<Planta> pOrigen;
	private Planta destino;
	private List<Pedido> lista;
	private PanelSeleccionarCamino panel;
	private PlantaService plantaService;
	private MapaService mapaService;
	
	public SeleccionarCaminoController(PanelSeleccionarCamino panel, App a, List<Planta> pOrigen, Planta destino, Pedido ped) {
		this.pedidoService = new PedidoService();
		this.plantaService = new PlantaService();
		this.mapaService = new MapaService();
		this.lista = new ArrayList<Pedido>();
		this.panel = panel;
		this.p= ped;
		this.a=a;
		this.pOrigen=pOrigen;
		this.destino=destino;
		this.panel.getTxtPlanta().setText(destino.toString());
		this.panel.getTxtPlanta().setEditable(false);
		this.panel.setJcbPlantas(new JComboBox<Planta>( pOrigen.toArray(new Planta[0])));
	}
	private Float obtenerValor(JFormattedTextField f) {
		if(f.getValue()==null) return 0f;
		return ((Number) f.getValue()).floatValue();
	}
	
	public void actualizar() {
		this.panel.setJcbPlantas(new JComboBox<Planta>( pOrigen.toArray(new Planta[0])));
	}
	
	public void caminosKm() throws DatosObligatoriosException, ControllerException {
		try {
			if(!(this.panel.getJcbPlantas().getSelectedItem() !=null && this.panel.getJcbPlantas().getSelectedItem() instanceof Planta)) {
				throw new DatosObligatoriosException("Planta", "No hay ninguna planta seleccionada");
			}
			else {
				this.panel.getListModelKm().clear();
				this.panel.getListModelKm().addAll(this.mapaService.menosKm((Planta)this.panel.getJcbPlantas().getSelectedItem(), destino));
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new ControllerException("Error:"+e.getLocalizedMessage());
		}
		
	}
	
	public void caminosT() throws DatosObligatoriosException, ControllerException {
		try {
			if(!(this.panel.getJcbPlantas().getSelectedItem() !=null && this.panel.getJcbPlantas().getSelectedItem() instanceof Planta)) {
				throw new DatosObligatoriosException("Planta", "No hay ninguna planta seleccionada");
			}
			else {
				this.panel.getListModelT().clear();
				this.panel.getListModelT().addAll(this.mapaService.menosTiempo((Planta)this.panel.getJcbPlantas().getSelectedItem(), destino));
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new ControllerException("Error:"+e.getLocalizedMessage());
		}
		
		
	}
	
	
	public void seleccionarCamino() throws DatosObligatoriosException, ControllerException, NoHayCamionesException {
			if(this.panel.getListKm().getSelectedValuesList().size() + this.panel.getListT().getSelectedValuesList().size() < 1) {
				throw new DatosObligatoriosException("Camino", "No hay ningun camino seleccionado");
			}
			else if(this.panel.getListKm().getSelectedValuesList().size() + this.panel.getListT().getSelectedValuesList().size() > 1)
				throw new ControllerException("Debe seleccionar solo un camino");
			else {
				if(this.panel.getListKm().getSelectedValuesList().size()==1) {
					p.setRuta(this.panel.getListKm().getSelectedValue());
				}
				else {
					p.setRuta(this.panel.getListT().getSelectedValue());
				}
				this.pedidoService.asignarCamion(p);
				//ACA FALTA CARTEL PARA AVISAR EXITO
				
				this.a.setContentPane(new PanelProcesarPedido(a));
				//this.pack();
				this.a.revalidate();
				this.a.repaint();
				
			}
	}
	
	
}
