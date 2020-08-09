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
			"SELECT S.ID, ID_INSUMO, ID_PLANTA, PUNTO_REPOSICION, CANTIDAD"
			+ " FROM STOCK S"
			+ " WHERE S.ID_PLANTA = ?";
	
	private static final String UPDATE_STOCK = 
			"UPDATE INSUMO SET ID_INSUMO = ?, ID_PLANTA = ?, PUNTO_REPOSICION = ?, CANTIDAD = ?"
			+ " WHERE ID = ?";
	
	private static final String INSERT_STOCK = 
			"INSERT INTO STOCK(ID_INSUMO, ID_PLANTA, PUNTO_REPOSICION, CANTIDAD) VALUES(?,?,?,?)";
	
	private static final String SELECT_STOCK_TOTAL =
			"SELECT SUM(S.CANTIDAD) AS STOCK_TOTAL"
			+ " FROM STOCK S, INSUMO I"
			+ " WHERE I.ID = ?"
			+ " AND S.ID_INSUMO = I.ID"
			+ " GROUP BY(I.ID)";
	
	private static final String DELETE_STOCK =
			"DELETE FROM STOCK WHERE ID = ?";
	
	private static final String SELECT_ALL_STOCK =
			"SELECT * FROM STOCK";
	
	private InsumoDao insumoDao;
	private PlantaDao plantadao;
	
	public StockDaoPostgreSQL() {
		super();
		insumoDao = new InsumoDaoPostgreSQL();
		plantadao = new PlantaDaoPostgreSQL(this);
	}
	
	
	
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
public StockDaoPostgreSQL(PlantaDao plantadao) {
		super();
		insumoDao = new InsumoDaoPostgreSQL();
		this.plantadao = plantadao;
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
	public List<Stock> buscarPorPlanta(Planta p, Connection conn) {
		List<Stock> lista = new ArrayList<Stock>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(SELECT_STOCK_PLANTA);
			pstmt.setInt(1, p.getId());
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Stock s = new Stock();
				s.setId(rs.getInt("ID"));
				s.setCtidad(rs.getFloat("CANTIDAD"));
				s.setPuntoRepo(rs.getFloat("PUNTO_REPOSICION"));
				s.setInsumo(insumoDao.buscar(rs.getInt("ID_INSUMO"), conn));
				s.setP(p);
				lista.add(s);
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(pstmt!=null) pstmt.close();
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return lista;
	}

	public List<Stock> buscarTodos(){
		List<Stock> lista = new ArrayList<Stock>();
		Connection conn = DB.getConexion();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Stock s = null;
		try {
			pstmt = conn.prepareStatement(SELECT_ALL_STOCK);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				s = new Stock();
				s.setCtidad(rs.getFloat("CANTIDAD"));
				s.setId(rs.getInt("ID"));
				s.setInsumo(insumoDao.buscar(rs.getInt("ID_INSUMO"), conn));
				s.setP(plantadao.buscar(rs.getInt("ID_PLANTA"), conn));
				s.setPuntoRepo(rs.getFloat("PUNTO_REPOSICION"));
				lista.add(s);
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
	public void borrar(Integer idprod) {
		Connection conn = DB.getConexion();
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(DELETE_STOCK);
			pstmt.setInt(1, idprod);
			pstmt.execute();
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
	}

}
