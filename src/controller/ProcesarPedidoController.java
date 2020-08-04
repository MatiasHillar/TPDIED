package controller;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;

import dominio.*;
import gui.*;
import gui.util.*;
import servicios.*;

public class ProcesarPedidoController {
	private PedidoService pedidoService;
	private Pedido p;
	private List<Pedido> lista;
	private PanelProcesarPedido panel;
	private PlantaService plantaService;
	private InsumoService insumoService;
	
	public ProcesarPedidoController(PanelProcesarPedido panel) {
		this.pedidoService = new PedidoService();
		this.plantaService = new PlantaService();
		this.insumoService = new InsumoService();
		this.lista = new ArrayList<Pedido>();
		this.panel = panel;
		this.p= new Pedido();
	}
	private Float obtenerValor(JFormattedTextField f) {
		if(f.getValue()==null) return 0f;
		return ((Number) f.getValue()).floatValue();
	}
	
	public List<Pedido> listarPedidosCreados(){
		return this.pedidoService.buscarTodosCreados();
	}
	
	public void buscarPlantaParaPedido() throws DatosObligatoriosException{
		if(!(this.panel.getJcbPedidos().getSelectedItem() !=null && this.panel.getJcbPedidos().getSelectedItem() instanceof Pedido)) {
			throw new DatosObligatoriosException("Pedido", "No hay ningun pedido seleccionado");
		}
		else {
			Pedido ped = (Pedido) this.panel.getJcbPedidos().getSelectedItem();
//			this.panel.setJcbPlantas(new JComboBox<Planta>((Planta[]) this.pedidoService.buscarPlantasParaPedido(ped).toArray(new Planta[0])));
			this.panel.setJcbPlantas(new JComboBox<Planta>( this.pedidoService.buscarPlantasParaPedido(ped).toArray(new Planta[0])));
			if(ped.getEstado() == Estado.CANCELADO)
				this.actualizarPedidos();
		}
	}
	
	public void actualizarPedidos() {
		this.panel.setJcbPedidos(new JComboBox<Pedido>(this.listarPedidosCreados().toArray(new Pedido[0])));
	}
	
	public void verDetallePedido() throws DatosObligatoriosException{
		if(!(this.panel.getJcbPedidos().getSelectedItem() !=null && this.panel.getJcbPedidos().getSelectedItem() instanceof Pedido)) {
			throw new DatosObligatoriosException("Pedido", "No hay ningun pedido seleccionado");
			//this.mostrarError("Ver detalle", "No hay ningun pedido seleccionado");
		}
		else {
			Pedido ped = (Pedido) this.panel.getJcbPedidos().getSelectedItem();
			this.panel.getModeloTablaItem().getData().clear();
			this.panel.getModeloTablaItem().getData().addAll(ped.getListaItems());
			this.panel.getModeloTablaItem().fireTableDataChanged();
			ZoneId defaultZoneId = ZoneId.systemDefault();
		    LocalDate localDate = ped.getFechaSolicitud();
		    Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());
			this.panel.getTxtFechaS().setValue(date);
			localDate = ped.getFechaEntrega();
			date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());
			this.panel.getTxtFecha().setValue(date);
			this.panel.getTxtPlanta().setText(ped.getPlantaDestino().toString());
		}
	}
	
	
	
}
