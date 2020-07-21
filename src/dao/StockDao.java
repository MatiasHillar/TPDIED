package dao;

import Dominio.Stock;

import java.util.List;

import Dominio.Planta;

public interface StockDao {

	public Stock saveorUpdate(Stock s);
	public Integer stockTotal(Integer idprod);
	//filtra las plantas con algun insumo cuyo stock sea menor al punto de reposicion
	public List<Planta> filtrar();
	public Void borrar(Integer idprod);
}
