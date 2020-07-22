package dao;

import java.util.List;

import dominio.Camion;

public interface CamionDao {

	public Camion saveOrUpdate(Camion c);
	public Void borrar(Camion c);
	public Camion buscarporPatente(Integer id);
	public List<Camion> buscarTodos();
}
