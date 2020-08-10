
package dao;

import java.sql.Connection;	
import java.util.List;

import dominio.Planta;
import dominio.Stock;

public interface StockDao {

	public Stock saveOrUpdate(Stock s);
	public Integer stockTotal(Integer idprod);
	public List<Stock> buscarPorPlanta(Planta p, Connection conn);
	public void borrar(Integer idprod);
	public List<Stock> buscarTodos();
	public void saveOrUpdate(Integer id_planta, List<Stock> lista, Connection conn);
	
}
