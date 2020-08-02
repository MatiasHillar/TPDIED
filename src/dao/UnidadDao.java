package dao;

import java.util.List;

import dominio.Unidad;

public interface UnidadDao {

	public Unidad saveOrUpdate(Unidad u);
	public Void borrar(String nombre);

	public List<Unidad> buscarTodas();
}
