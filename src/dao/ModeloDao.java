package dao;

import dominio.Modelo;

public interface ModeloDao {

	public Modelo saveOrUpdate(Modelo m);
	public Modelo buscar(String modelo);
	public void borrar(String modelo);
}
