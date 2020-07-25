package servicios;

import java.util.List;

import dominio.Planta;
import dao.PlantaDao;


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
		return plantaDao.buscarTodas();
	}
	
	
	

}
