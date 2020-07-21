package dao;

import java.util.List;

import Dominio.Planta;

public interface PlantaDao {

	public Planta saveorUpdate(Planta p);
	public Planta buscar(Integer id);
	public List<Planta> buscarTodas();
	public Void borrar(Integer id);
	
}
