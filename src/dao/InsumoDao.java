package dao;

import java.util.List;

import Dominio.Insumo;
import Dominio.Unidad;

public interface InsumoDao {

	public Insumo saveorUpdate(Insumo i);
	public Void borrar(Insumo i);
	public List<Insumo> buscarTodos();
	//acá o en UnidadDao?
	public Unidad buscarUnidad(String nombre);
}
