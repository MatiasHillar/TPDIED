package dao;

import java.sql.Connection;
import java.util.List;

import dominio.Planta;

public interface PlantaDao {

	public Planta saveOrUpdate(Planta p);
	public Planta buscar(Integer id, Connection conn);
	public List<Planta> buscarTodas();
	public List<Planta> checkInsumos(Integer nro_pedido, Connection conn);
	public void borrar(Integer id);
	
	
}
