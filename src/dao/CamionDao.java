package dao;

import java.util.List;
import java.util.Map;

import dominio.Camion;

public interface CamionDao {

	public Camion saveOrUpdate(Camion c);
	public void borrar(Camion c);
	public Camion buscarporPatente(Integer id);
	public List<Camion> buscarTodos();
	public List<Camion> buscarPorPatributos(Map<String, ?> atributos);
}
