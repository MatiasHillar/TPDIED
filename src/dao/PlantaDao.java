package dao;

import java.sql.Connection;
import java.util.List;

import dominio.Planta;

public interface PlantaDao {

	public Planta saveOrUpdate(Planta p);
	public Planta buscar(Integer id, Connection conn);
	public List<Planta> buscarTodas();
	public void borrar(Integer id);
	
}
