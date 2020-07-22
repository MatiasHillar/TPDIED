package dao;

import java.util.List;

import dominio.Planta;
import dominio.Stock;

public interface StockDao {

	public Stock saveOrUpdate(Stock s);
	public Integer stockTotal(Integer idprod);
	//filtra las plantas con algun insumo cuyo stock sea menor al punto de reposicion
	public List<Planta> filtrar();
	public Void borrar(Integer idprod);
}
