package servicios;

import java.util.List;

import dao.StockDao;
import dao.StockDaoPostgreSQL;
import dominio.*;

public class StockService {
	
	private StockDao stockDao;
	
	public StockService() {
		stockDao = new StockDaoPostgreSQL();
	}

	public Stock crearStock(Stock s) {
			return this.stockDao.saveOrUpdate(s);
	}
	
	public List<Stock> buscarTodos() {
		return stockDao.buscarTodas();
	}
	
}
