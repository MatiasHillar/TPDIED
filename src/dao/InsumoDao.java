package dao;

import java.util.List;

import dominio.Insumo;
import dominio.Unidad;

public interface InsumoDao {

	public Insumo saveOrUpdate(Insumo i);
	public Void borrar(Insumo i);
	public List<Insumo> buscarTodos();
	//acá o en UnidadDao?
	public Unidad buscarUnidad(String nombre);
}
