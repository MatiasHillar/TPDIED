package dao;

import Dominio.Modelo;

public interface ModeloDao {

	public Modelo saveOrUpdate(Modelo m);
	public Modelo buscar(Integer id);
	public Void borrar(Integer id);
}
