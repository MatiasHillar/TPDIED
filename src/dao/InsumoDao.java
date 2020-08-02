package dao;

import java.sql.Connection;
import java.util.List;

import dominio.Insumo;
import dominio.InsumoGral;
import dominio.InsumoLiquido;
import dominio.Unidad;

public interface InsumoDao {

	public Insumo saveOrUpdate(InsumoGral i);
	public Insumo saveOrUpdate(InsumoLiquido i);
	public Insumo buscar(Integer id_insumo, Connection conn);
	public void borrar(Insumo i);
	public List<Insumo> buscarTodos();
	//acá o en UnidadDao?
	public Unidad buscarUnidad(String nombre);
}
