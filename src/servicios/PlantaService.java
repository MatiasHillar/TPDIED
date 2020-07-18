package servicios;

import java.util.List;


public class PlantaService {
	private PlantaDao plantaDao = new PlantaDaoMysql();

	public Planta crearPlanta(Planta p) {
		// si hay alguna regla de negocio que indque que no se 
		// puede agregar un camion si no se cumplen determinadas
		// condiciones en otras entidades o reglas 
		// se valida aquí
			return this.plantaDao.saveOrUpdate(p);
	}
	
	public List<Planta> buscarTodos() {
		return plantaDao.buscarTodos();
	}
	
	
	

}
