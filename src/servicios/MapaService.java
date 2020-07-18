package servicios;

import java.util.List;



public class MapaService {
	private PlantaDao plantaDao = new PlantaDaoMysql();
	private RutaDao rutaDao = new RutaDaoMysql();
	
	
	public List<Ruta> buscarTodos() {
		return rutaDao.buscarTodos();
	}
	public List<Planta> buscarTodos() {
		return plantaDao.buscarTodos();
	}
	
	

}
