package dao;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import dominio.Camion;

public interface CamionDao {

	public Camion saveOrUpdate(Camion c);
	public void borrar(Camion c);
	public List<Camion> buscarTodos();
	public List<Camion> buscarPorPatributos(Map<String, ?> atributos);
}
