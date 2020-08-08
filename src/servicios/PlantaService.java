package servicios;

import java.util.List;

import dominio.*;
import dao.PlantaDao;
import dao.PlantaDaoPostgreSQL;


public class PlantaService {
	private PlantaDao plantaDao;
	
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
		System.out.println(plantaDao.buscarTodas());
		return plantaDao.buscarTodas();
	}
	
/*	
	public List<Planta> buscarPlantasParaPedido(List<ItemPedido> items ) {
		return plantaDao.buscarPlantasParaPedido(items);
	}
	
	public List<Planta> buscarPlantasParaPedido(Pedido p ) {
		return plantaDao.checkPlantas(p.getNroPedido());
	}
	*/
	
	

}
