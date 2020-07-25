package servicios;

import java.util.List;	

import dao.CamionDao;
import dao.CamionDaoPostgreSQL;
import dominio.*;



public class CamionService {
	private CamionDao camionDao;

	public CamionService() {
		camionDao = new CamionDaoPostgreSQL();
	}
	
	public Camion crearCamion(Camion c) {
		// si hay alguna regla de negocio que indque que no se 
		// puede agregar un camion si no se cumplen determinadas
		// condiciones en otras entidades o reglas 
		// se valida aqu�
			return this.camionDao.saveOrUpdate(c);
	}
	
	public List<Camion> buscarTodos() {
		return camionDao.buscarTodos();
	}
	
	

}
