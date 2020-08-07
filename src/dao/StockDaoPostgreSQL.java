package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.utils.DB;
import dominio.Insumo;
import dominio.Planta;
import dominio.Stock;

public class StockDaoPostgreSQL implements StockDao {

	private static final String SELECT_STOCK_PLANTA = 
			"SELECT S.ID, S.ID_INSUMO, S.ID_PLANTA, S.PUNTO_REPOSICION, S.CANTIDAD"
			+ "FROM STOCK S, PLANTA P"
			+ "WHERE S.ID_PLANTA = P.ID";
	
	private static final String UPDATE_STOCK = 
			"UPDATE INSUMO SET ID_INSUMO = ?, ID_PLANTA = ?, PUNTO_REPOSICION = ?, CANTIDAD = ?"
			+ "WHERE ID = ?";
	
	private static final String INSERT_STOCK = 
			"INSERT INTO STOCK(ID_INSUMO, ID_PLANTA, PUNTO_REPOSICION, CANTIDAD) VALUES(?,?,?,?)";
	
	private static final String SELECT_STOCK_TOTAL =
			"SELECT SUM(S.CANTIDAD) AS STOCK_TOTAL"
			+ "FROM STOCK S, INSUMO I"
			+ "WHERE I.ID = ?"
			+ "AND S.ID_INSUMO = I.ID"
			+ "GROUP BY(I.ID)";
	
	InsumoDao insumoDao = new InsumoDaoPostgreSQL();
	
	@Override
	public Stock saveOrUpdate(Stock s) {
		Connection conn = DB.getConexion();
		PreparedStatement pstmt = null;
		try {
			if(s.getId()!=null) {
				pstmt = conn.prepareStatement(UPDATE_STOCK);
				pstmt.setInt(1, s.getInsumo().getId());
				pstmt.setInt(2, s.getP().getId());
				pstmt.setFloat(3, s.getPuntoRepo());
				pstmt.setFloat(4, s.getCtidad());
				pstmt.setInt(5, s.getId());
			}
			else {
				pstmt = conn.prepareStatement(INSERT_STOCK);
				pstmt.setInt(1, s.getInsumo().getId());
				pstmt.setInt(2, s.getP().getId());
				pstmt.setFloat(3, s.getPuntoRepo());
				pstmt.setFloat(4, s.getCtidad());
			}
			pstmt.executeUpdate();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return s;
	}

	@Override
	public Integer stockTotal(Integer idprod) {
		Connection conn = DB.getConexion();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int stock_total = 0;
		try {
			pstmt = conn.prepareStatement(SELECT_STOCK_TOTAL);
			pstmt.setInt(1, idprod);
			rs = pstmt.executeQuery();
			stock_total = rs.getInt("STOCK_TOTAL");
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return stock_total;
	}

	@Override
	public List<Planta> filtrar() {
		// TODO Auto-generated method stub
		return null;
	}
//SEGUIR CON ESTO
	@Override
	public List<Stock> buscarPorPlanta(Integer idPlanta, Connection conn) {
		List<Stock> lista = new ArrayList<Stock>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(SELECT_STOCK_PLANTA);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Stock s = new Stock();
				s.setId(rs.getInt("ID"));
				s.setCtidad(rs.getFloat("CANTIDAD"));
				s.setPuntoRepo(rs.getFloat("PUNTO_REPOSICION"));
				s.setInsumo(insumoDao.buscar(rs.getInt("ID_INSUMO"), conn));
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return lista;
	}

	@Override
	public Void borrar(Integer idprod) {
		// TODO Auto-generated method stub
		return null;
	}

}
