package servicios;

import java.util.List;
import java.util.Map;

import dao.CamionDao;
import dao.CamionDaoPostgreSQL;
import dao.ModeloDao;
import dao.ModeloDaoPostgreSQL;
import dominio.*;



public class CamionService {
	private CamionDao camionDao;
	private ModeloDao modeloDao;

	public CamionService() {
		camionDao = new CamionDaoPostgreSQL();
		modeloDao = new ModeloDaoPostgreSQL();
	}
	
	public Camion crearCamion(Camion c) {
			this.modeloDao.saveOrUpdate(c.getModelo());
			return this.camionDao.saveOrUpdate(c);
	}
	
	public List<Camion> buscarTodos() {
		return camionDao.buscarTodos();
	}
	
	public List<Camion> buscarPorAtributos(Map m) {
		return camionDao.buscarPorPatributos(m);
	}
	
	public void borrarCamion(Camion c) {
		camionDao.borrar(c);
	}
	
	

}
