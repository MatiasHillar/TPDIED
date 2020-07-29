package servicios;

import java.time.LocalDate;
import java.util.*;

import dao.PedidoDao;
import dominio.*;
import gui.util.*;

public class PedidoService {
	private PedidoDao pedidoDao;
	private PlantaService plantaService;
	private CamionService camionService;
	private MapaService mapaService;
	
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
	
	public List<Pedido> buscarTodosCreados() {
		return pedidoDao.buscarTodosCreados();
	}
	
	public List<Planta> buscarPlantasParaPedido(Pedido p) {
		List<Planta> resultado= plantaService.buscarPlantasParaPedido(p.getListaItems());
		if(resultado.isEmpty())
			p.setEstado(Estado.CANCELADO);
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
	}
	
	
	
	
	/*
	 * private PlantaDao plantaDao;
	
	public PlantaService() {
		plantaDao = new PlantaDaoPostgreSQL();
	}

	public Planta crearPlanta(Planta p) {
		// si hay alguna regla de negocio que indque que no se 
		// puede agregar un camion si no se cumplen determinadas
		// condiciones en otras entidades o reglas 
		// se valida aqu�
			return this.plantaDao.saveOrUpdate(p);
	}
	
	public List<Planta> buscarTodos() {
		return plantaDao.buscarTodas();
	}
	
	 */

}
