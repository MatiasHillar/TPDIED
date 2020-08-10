package servicios;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import dao.PedidoDao;
import dao.PedidoDaoPostgreSQL;
import dominio.*;
import gui.util.*;

public class PedidoService {
	private PedidoDao pedidoDao;
	private PlantaService plantaService;
	private CamionService camionService;
	private MapaService mapaService;
//	private StockService stockService;
	
	public PedidoService(){
		pedidoDao = new PedidoDaoPostgreSQL();
		plantaService = new PlantaService();
		camionService = new CamionService();
		mapaService = new MapaService();
	}
	
	public Pedido crearPedido(Pedido p) {
		p.setEstado(Estado.CREADO);
		p.setFechaSolicitud(LocalDate.now());
		return pedidoDao.saveOrUpdate(p);
	}
	public Pedido crearPedido(Pedido p, LocalDate fechaSolicitud) {
		p.setEstado(Estado.CREADO);
		p.setFechaSolicitud(fechaSolicitud);
		return pedidoDao.saveOrUpdate(p);
	}
	
	public Pedido entregarPedido(Pedido p) {
		p.setEstado(Estado.ENTREGADO);
		for(ItemPedido ipp: p.getListaItems()) {
			List<Stock> s = p.getPlantaDestino().getListaStock().stream().filter(ss-> ss.getInsumo().equals(ipp.getInsumo())).collect(Collectors.toList());
			if(s.isEmpty())
				p.getPlantaDestino().getListaStock().add(new Stock(ipp.getInsumo(), ipp.getCtidad(),ipp.getCtidad() , p.getPlantaDestino()));
			else s.get(0).setCtidad(s.get(0).getCtidad() + ipp.getCtidad());
		}
//		p.getPlantaDestino().getListaStock().stream().filter(ss-> ss.getInsumo().equals(ipp.getInsumo())).forEach(ss-> ss.setCtidad(ss.getCtidad()-ipp.getCtidad()));
		return pedidoDao.saveOrUpdate(p);
	}
	
	
	public List<Pedido> buscarTodosCreados() {
		return pedidoDao.filtrarCreadas();
	}
	public List<Pedido> buscarTodosProcesados() {
		return pedidoDao.filtrarProcesadas();
	}
	
	
	public List<Planta> buscarPlantasParaPedido(Pedido p) {
//		List<Planta> resultado= plantaService.buscarPlantasParaPedido(p.getNroPedido());
		List<Planta> resultado= pedidoDao.checkPlantas(p.getNroPedido());
		if(resultado.isEmpty()) {
			p.setEstado(Estado.CANCELADO);
			pedidoDao.saveOrUpdate(p);
		}
		return resultado;		
	}
	
	public void asignarCamion(Pedido p) throws NoHayCamionesException {
		PriorityQueue<Camion> pq = new PriorityQueue<Camion>((c1,c2)-> c1.getKmRecorridos().compareTo(c2.getKmRecorridos()));
		pq.addAll(this.camionService.buscarTodos());
		if(pq.isEmpty()) throw new NoHayCamionesException();
		Camion c = pq.poll();
		p.setCamion(c);
		c.setKmRecorridos(c.getKmRecorridos() + mapaService.getKm(p.getRuta()));
		p.setCostoEnvio((c.getCostoKm()* mapaService.getKm(p.getRuta())) + (c.getCostoHora() * mapaService.getHs(p.getRuta())) );
		p.setEstado(Estado.PROCESADO);
		pedidoDao.saveOrUpdate(p);
		Planta origen=p.getRuta().get(0).getPlantaOrigen();
		for(ItemPedido ipp: p.getListaItems()) {
			origen.getListaStock().stream().filter(ss-> ss.getInsumo().equals(ipp.getInsumo())).forEach(ss-> ss.setCtidad(ss.getCtidad()-ipp.getCtidad()));
					}
		plantaService.crearPlanta(origen);
	}
	
	

}
