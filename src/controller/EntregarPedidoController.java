package controller;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JComboBox;

import dominio.*;
import gui.PanelEntregarPedido;
import gui.util.DatosObligatoriosException;
import servicios.InsumoService;
import servicios.PedidoService;
import servicios.PlantaService;

public class EntregarPedidoController {
	private PedidoService pedidoService;
	private Pedido p;
	private PanelEntregarPedido panel;
	
	public EntregarPedidoController(PanelEntregarPedido p) {
		this.pedidoService = new PedidoService();
		this.panel = p;
		this.p= new Pedido();
	}
	
	public List<Pedido> listarPedidosProcesados(){
		return this.pedidoService.buscarTodosProcesados();
	}
	
	public void actualizarPedidos() {
		this.panel.setJcbPedidos(new JComboBox<Pedido>(this.listarPedidosProcesados().toArray(new Pedido[0])));
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
			this.panel.getTxtCamino().setText(ped.getRuta().toString());
			this.panel.getTxtCosto().setValue(ped.getCostoEnvio());
			this.panel.getTxtCamion().setText(ped.getCamion().getPatente());
			
		}
	}
	
	public void entregarPedido()throws DatosObligatoriosException{
		if(!(this.panel.getJcbPedidos().getSelectedItem() !=null && this.panel.getJcbPedidos().getSelectedItem() instanceof Pedido)) {
			throw new DatosObligatoriosException("Pedido", "No hay ningun pedido seleccionado");
			//this.mostrarError("Ver detalle", "No hay ningun pedido seleccionado");
		}
		else {
			Pedido ped = (Pedido) this.panel.getJcbPedidos().getSelectedItem();
			this.pedidoService.entregarPedido(ped);			
		}
	}
}
