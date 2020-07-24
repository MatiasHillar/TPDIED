package servicios;

import java.util.List;

import dao.PlantaDao;
import dao.RutaDao;
import dominio.Planta;
import dominio.Ruta;

public class RutaService {
	private RutaDao rutaDao = new RutaDaoPostgreSQL();
	public Ruta crearRuta(Ruta r) {
		return this.rutaDao.saveOrUpdate(r);
	}
	public List<Ruta> buscarTodos(){
		return rutaDao.buscarTodas();
	}
	
	

}
