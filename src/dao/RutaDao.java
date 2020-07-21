package dao;

import java.util.List;

import Dominio.Ruta;

public interface RutaDao {

	public Ruta saveorUpdate(Ruta r);
	public Ruta buscar(Integer id);
	public Void borrar(Integer id);
	public List<Ruta> buscarTodas();
	
}
