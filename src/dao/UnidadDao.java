package dao;

import dominio.Unidad;

public interface UnidadDao {

	public Unidad saveOrUpdate(Unidad u);
	public Void borrar(String nombre);
}
