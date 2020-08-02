package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dominio.Insumo;
import dominio.Planta;
import dominio.Stock;

public class StockDaoPostgreSQL implements StockDao {

	private static final String SELECT_STOCK_PLANTA= 
			"SELECT S.ID, S.ID_INSUMO, S.ID_PLANTA, S.PUNTO_REPOSICION, S.CANTIDAD"
			+ "FROM STOCK S, PLANTA P"
			+ "WHERE S.ID_PLANTA = P.ID";
	
	@Override
	public Stock saveOrUpdate(Stock s) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer stockTotal(Integer idprod) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Planta> filtrar() {
		// TODO Auto-generated method stub
		return null;
	}
//SEGUIR CON ESTO
//	@Override
//	public List<Stock> buscarPorPlanta(Integer idPlanta, Connection conn) {
//		List<Stock> lista = new ArrayList<Stock>();
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		Stock s = new Stock();
//		try {
//			pstmt = conn.prepareStatement(SELECT_STOCK_PLANTA);
//			rs = pstmt.executeQuery();
//			while(rs.next()) {
//				s.setId(rs.getInt("S.ID"));
//				s.setCtidad(rs.getFloat("S.CANTIDAD"));
//				s.setInsumo(new Insumo());
//				
//			}
//		}
//		return lista;
//	}

	@Override
	public Void borrar(Integer idprod) {
		// TODO Auto-generated method stub
		return null;
	}

}
