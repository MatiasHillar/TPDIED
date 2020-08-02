package controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFormattedTextField;

import dominio.*;
import gui.*;
import gui.util.ControllerException;
import gui.util.DatosObligatoriosException;
import gui.util.FormatoNumeroException;
import servicios.*;

public class PedidoController {
	
	
	
	private PedidoService pedidoService;
	private Pedido p;
	private ItemPedido ip;
	private List<Pedido> lista;
	private PanelAltaPedido panel;
	private PlantaService plantaService;
	private InsumoService insumoService;
	
	public PedidoController(PanelAltaPedido p) {
		this.pedidoService = new PedidoService();
		this.plantaService = new PlantaService();
		this.insumoService = new InsumoService();
		this.lista = new ArrayList<Pedido>();
		this.panel = p;
		this.p= new Pedido();
		this.ip = new ItemPedido();
	}
	
	private Float obtenerValor(JFormattedTextField f) {
		if(f.getValue()==null) return 0f;
		return ((Number) f.getValue()).floatValue();
	}
	
	public List<Planta> listarTodasPlantas(){
		return this.plantaService.buscarTodos();
	}
	
	public List<Insumo> listarTodosInsumos(){
		return this.insumoService.buscarTodos();
	}
	 
	public void actualizarItem() {
		ip.setCtidad(this.obtenerValor(this.panel.getCantidad()));
		ip.setInsumo((Insumo) this.panel.getJcbInsumo().getSelectedItem());
		if(ip.getInsumo()!=null && ip.getCtidad()!=null)this.panel.getPrecio().setValue(ip.getPrecio());
		
	}
	
	public void actualizarPedido()  throws DatosObligatoriosException{
		List<ItemPedido> ips = this.panel.getModeloTablaItem().getData();
		for(ItemPedido item : ips) {
			item.setPedido(p);
		}
		p.setListaItems(ips);
		if(this.panel.getTxtFechaCompra()!=null) p.setFechaEntrega(LocalDate.parse(this.panel.getTxtFechaCompra().getText(),DateTimeFormatter.ofPattern("dd/MM/yyyy")));
		else throw new DatosObligatoriosException("Fecha de entrega", "La fecha de entrega es obligatoria");
	}
	
	public Pedido guardar() throws DatosObligatoriosException{
		this.actualizarPedido();
		pedidoService.crearPedido(p);
		return p;
	}

	public ItemPedido getIp() {
		return ip;
	}

	public void setIp(ItemPedido ip) {
		this.ip = ip;
	}
	
	

}
