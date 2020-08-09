
package dao;

import java.sql.Connection;	
import java.util.List;
import dominio.Stock;

public interface StockDao {

	public Stock saveOrUpdate(Stock s);
	public Integer stockTotal(Integer idprod);
	public List<Stock> buscarPorPlanta(Integer idPlanta, Connection conn);
	public void borrar(Integer idprod);
	public List<Stock> buscarTodos();
}
