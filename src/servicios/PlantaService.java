package servicios;

import java.util.List;

import dominio.*;
import dao.PlantaDao;
import dao.PlantaDaoPostgreSQL;
import dao.StockDao;
import dao.StockDaoPostgreSQL;


public class PlantaService {
	private PlantaDao plantaDao;
	private StockDao stockDao;
	
	public PlantaService() {
		plantaDao = new PlantaDaoPostgreSQL();
		stockDao = new StockDaoPostgreSQL();
	}

	public Planta crearPlanta(Planta p) {
		Planta pp =this.plantaDao.saveOrUpdate(p);
		stockDao.saveOrUpdate(pp.getId(), pp.getListaStock());
		return pp;
	}
	
	public List<Planta> buscarTodos() {
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
