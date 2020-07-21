package dao;

import java.util.List;

import Dominio.Camion;

public interface CamionDao {

	public Camion saveorUpdate(Camion c);
	public Void borrar(Camion c);
	public Camion buscarporId(Integer id);
	public List<Camion> buscarTodos();
}
