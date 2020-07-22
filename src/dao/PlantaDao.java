package dao;

import java.util.List;

import dominio.Planta;

public interface PlantaDao {

	public Planta saveOrUpdate(Planta p);
	public Planta buscar(Integer id);
	public List<Planta> buscarTodas();
	public Void borrar(Integer id);
	
}
